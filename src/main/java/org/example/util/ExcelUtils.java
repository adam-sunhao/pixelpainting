package org.example.util;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.excel.Config;
import org.example.exception.BaseException;
import org.example.model.Picture;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AdamSun
 * @date 2020/5/9 21:49
 */
public class ExcelUtils {

    /**
     * 根据图片宽度，创建默认的workbook
     *
     * @param width
     */
    public static SXSSFWorkbook createDefaultWorkBook(int width) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet();
        for (int i = 0; i < width; i++) {
            sheet.setColumnWidth(i, Config.cellWidth);
        }
        sheet.setDefaultRowHeight((short) Config.cellHeight);

        return workbook;
    }


    public static void fillColorIntoExcel(SXSSFWorkbook workbook, Picture picture) {
        SXSSFSheet sheet = workbook.getSheetAt(0);
        Color[][] pixels = picture.getPixels();

        Map<String, XSSFCellStyle> cellStyleMap = new HashMap<>();

        for (int i = 0; i < pixels.length; i++) {
            SXSSFRow row = sheet.createRow(i);
            System.out.println("当前为第" + (i + 1) + "行!");
            for (int j = 0; j < pixels[i].length; j++) {
                SXSSFCell cell = row.createCell(j);
                XSSFCellStyle xssfCellStyle = cellStyleMap.get(pixels[i][j].toString());
                if(xssfCellStyle == null) {
                    //创建单元格样式
                    xssfCellStyle = (XSSFCellStyle) workbook.createCellStyle();
                    xssfCellStyle.setFillPattern(Config.fillPatternType);
                    xssfCellStyle.setFillForegroundColor(new XSSFColor(pixels[i][j], new DefaultIndexedColorMap()));
                    cellStyleMap.put(pixels[i][j].toString(), xssfCellStyle);
                }
                cell.setCellStyle(xssfCellStyle);
            }
            //113 height
            //241 width
            row.setHeight((short)113);
        }

        for(int i = 0; i < pixels[0].length; i++) {
            sheet.setColumnWidth(i, 241);
        }

    }

    /**
     * 大失败，一个.xlsx表格只能有64000个样式
     * @param dirAndFilename
     * @param picture
     * @throws IOException
     */
    public static void fillColor(String[] dirAndFilename, Picture picture) throws IOException {
        File excelFile = getExcelPath(dirAndFilename);
        Color[][] pixels = picture.getPixels();


        for (int i = 0; i < pixels.length; i++) {
            FileInputStream fis = new FileInputStream(excelFile);
            SXSSFWorkbook workbook = new SXSSFWorkbook(new XSSFWorkbook(fis));
            SXSSFSheet sheet = workbook.getSheetAt(0);
            SXSSFRow row = sheet.createRow(i);
            System.out.println("当前为第" + (i + 1) + "行!");
            for (int j = 0; j < pixels[i].length; j++) {
                SXSSFCell cell = row.createCell(j);
                //创建单元格样式
                XSSFCellStyle xssfCellStyle = (XSSFCellStyle) workbook.createCellStyle();
                xssfCellStyle.setFillPattern(Config.fillPatternType);
                xssfCellStyle.setFillForegroundColor(new XSSFColor(pixels[i][j], new DefaultIndexedColorMap()));
                cell.setCellStyle(xssfCellStyle);
            }
            writeExcel2File(workbook, dirAndFilename);
            closeStream(fis);
            workbook.close();
        }
    }

    /**
     * 将excel写出到文件
     *
     * @param workbook
     * @param dirAndFilename
     */
    public static void writeExcel2File(SXSSFWorkbook workbook, String[] dirAndFilename) {
        String dir = dirAndFilename[0];
        File pathFile = new File(dir);
        if (!pathFile.isDirectory()) {
            throw new BaseException("path is not a directory!");
        }
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        String filename = dirAndFilename[1];
        File excelFile = getExcelPath(dirAndFilename);
        try (FileOutputStream fos = new FileOutputStream(excelFile)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getExcelPath(String[] dirAndFilename) {
        File file = new File(dirAndFilename[0], dirAndFilename[1] + Config.excelFormat);
        return file;
    }

    public static void setBackgroundColr(SXSSFCell cell, XSSFCellStyle cellStyle, byte[] rgb) {
        setBackgroundColor(cell, cellStyle, new Color(rgb[0], rgb[1], rgb[2]));
    }

    /**
     * 设置单元格背景色
     *
     * @param cell
     * @param cellStyle
     * @param color
     */
    public static void setBackgroundColor(SXSSFCell cell, XSSFCellStyle cellStyle, Color color) {
        cellStyle.setFillBackgroundColor(new XSSFColor(color, new DefaultIndexedColorMap()));
        cell.setCellStyle(cellStyle);
    }

    private static void closeStream(InputStream is) {
        if(is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
