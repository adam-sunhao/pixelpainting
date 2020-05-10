package org.example.test;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

import org.example.util.ExcelUtils;

/**
 * @author AdamSun
 * @date 2020/5/9 20:18
 */
public class ExcelTest {

    private static final String path = "C:/JN/excel";

    public static void main(String[] args) {
        //generateExcel();
        //generateExcelWithSheetName();
        //readExcelWidthAndHeight();
        //fillColor();
        ExcelUtils.createDefaultWorkBook(709);
    }

    public static void fillColor() {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell( 0);
        cell.setCellValue("custom XSSF colors");

        XSSFCellStyle style1 = wb.createCellStyle();
        style1.setFillBackgroundColor(new XSSFColor(new java.awt.Color(48, 128, 16),
                new DefaultIndexedColorMap()));
        style1.setFillPattern(FillPatternType.FINE_DOTS);
        cell.setCellStyle(style1);

        String filename = "test.xls";

        try (OutputStream os = new FileOutputStream(new File(path, filename))) {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * width :273, height: 113
     */
    public static void readExcelWidthAndHeight() {
        String filename = "test.xls";

        try (InputStream is = new FileInputStream(new File(path, filename))) {
            Workbook wb = WorkbookFactory.create(is);
            Sheet sheet = wb.getSheetAt(0);
            int width = sheet.getColumnWidth(0);

            Row row = sheet.getRow(0);

            int height = row.getHeight();

            System.out.println("width :" + width + ", height: " + height);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void generateExcel() {
        String filename = "test.xls";
        Workbook workbook = new HSSFWorkbook();

        try (FileOutputStream fos = new FileOutputStream(new File(path, filename));) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateExcelWithSheetName() {
        String filename = "test.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        String sheetname = "?*AdamSun";
        Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName(sheetname));
        //sheet.setDefaultColumnWidth(1);
        sheet.setColumnWidth(0, 273);
        sheet.setDefaultRowHeight((short) 113);

        sheet.createRow(1);

        CellStyle style = workbook.createCellStyle();

        HSSFPalette palette = workbook.getCustomPalette();

        try (FileOutputStream fos = new FileOutputStream(new File(path, filename));) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
