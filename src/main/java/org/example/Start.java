package org.example;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.Picture;
import org.example.util.ExcelUtils;
import org.example.util.ImageUtils;

/**
 * @author AdamSun
 * @date 2020/5/10 9:40
 */
public class Start {

    private static String imageFile = "C:/JN/excel/qq.png";

    public static void main(String[] args) {
        //读取图片信息
        Picture picture = ImageUtils.getImageInfo(imageFile);
        //根据图片信息生成excel
        SXSSFWorkbook workbook = ExcelUtils.createDefaultWorkBook(picture.getWidth());
        //将像素写入到excel
        ExcelUtils.fillColorIntoExcel(workbook, picture);
        //读取图片目录和文件名
        String[] dirAndFilename = ImageUtils.getImageDirAndFilename(imageFile);
        //将workbook写出到文件
        ExcelUtils.writeExcel2File(workbook, dirAndFilename);
    }
}
