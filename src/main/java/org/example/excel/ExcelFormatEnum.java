package org.example.excel;

/**
 * @author AdamSun
 * @date 2020/5/10 9:15
 */
public enum ExcelFormatEnum {
    XLS(".xls"), XLSX(".xlsx");
    private String format;

    ExcelFormatEnum(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
