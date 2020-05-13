package org.example.util;

/**
 * @author AdamSun
 * @date 2020/5/9 21:13
 */
public enum ImageEnum {
    //支持得图片格式: jpg,jpeg,png
    JPG("jpg"), JPEG("jpeg"), PNG("png");

    private String format;

    ImageEnum(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    /**
     * 输入指定格式，判断是否存在
     *
     * @param format
     * @return
     */
    public static boolean exists(String format) {
        for (ImageEnum ie : ImageEnum.values()) {
            if (ie.getFormat().equals(format)) {
                return true;
            }
        }
        return false;
    }
}
