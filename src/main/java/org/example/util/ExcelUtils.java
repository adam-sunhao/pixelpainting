package org.example.util;

import org.apache.poi.xssf.usermodel.*;
import org.example.excel.Config;
import org.example.exception.BaseException;
import org.example.model.Picture;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author AdamSun
 * @date 2020/5/9 21:49
 */
public class ExcelUtils {

    /**
     * 根据图片宽度，创建默认的workbook
     * @param width
     */
    public static XSSFWorkbook createDefaultWorkBook(int width) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        for(int i = 0;i < width;i++) {
            sheet.setColumnWidth(i, Config.cellWidth);
        }
        sheet.setDefaultRowHeight((short)Config.cellHeight);

        return workbook;
    }

    public static void fillColorIntoExcel(XSSFWorkbook workbook, Picture picture) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        Color[][] pixels = picture.getPixels();

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        for(int i = 0;i < pixels.length;i++) {
            XSSFRow row = sheet.createRow(i);
            System.out.println("当前为第" + (i + 1) + "行!");
            for(int j = 0;j < pixels[i].length;j++) {
                XSSFCell cell = row.createCell(j);
                setBackgroundColor(cell, cellStyle, pixels[i][j]);
            }
        }
    }

    /**
     * 将excel写出到文件
     * @param workbook
     * @param dirAndFilename
     */
    public static void writeExcel2File(XSSFWorkbook workbook, String[] dirAndFilename) {
        String dir = dirAndFilename[0];
        File pathFile = new File(dir);
        if (!pathFile.isDirectory()) {
            throw new BaseException("path is not a directory!");
        }
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        String filename = dirAndFilename[1];
        File excelFile = new File(pathFile, filename + Config.excelFormat);
        try(FileOutputStream fos = new FileOutputStream(excelFile)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setBackgroundColr(XSSFCell cell, XSSFCellStyle cellStyle, byte[] rgb) {
        setBackgroundColor(cell, cellStyle, new Color(rgb[0], rgb[1], rgb[2]));
    }

    /**
     * 设置单元格背景色
     * @param cell
     * @param cellStyle
     * @param color
     */
    public static void setBackgroundColor(XSSFCell cell, XSSFCellStyle cellStyle, Color color) {
        cellStyle.setFillBackgroundColor(new XSSFColor(color, new DefaultIndexedColorMap()));
        cellStyle.setFillPattern(Config.fillPatternType);
        cell.setCellStyle(cellStyle);
    }

}
