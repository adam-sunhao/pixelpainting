package org.example.excel;

import org.apache.poi.ss.usermodel.FillPatternType;

/**
 * @author AdamSun
 * @date 2020/5/9 22:15
 */
public class Config {
    /**
     * 设置图片填充模式
     */
    public static FillPatternType fillPatternType = FillPatternType.SOLID_FOREGROUND;
    /**
     * 单元格的宽度
     */
    public static int cellWidth = 273;
    /**
     * 单元格的高度
     */
    public static int cellHeight = 113;
    /**
     * 默认生成的excel格式
     */
    public static String excelFormat = ExcelFormatEnum.XLSX.getFormat();

}
