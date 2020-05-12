package org.example.util;

import org.example.exception.BaseException;
import org.example.model.Picture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author AdamSun
 * @date 2020/5/9 21:12
 */
public class ImageUtils {

    public static String[] getImageDirAndFilename(String file) {
        File imageFile = new File(file);
        if(!imageFile.exists()) {
            throw new BaseException("图片不存在!");
        }
        if(!imageFile.isFile()) {
            throw new BaseException("图片路径异常!");
        }
        String imageFileName = imageFile.getName();
        getFormat(imageFileName);

        String[] dirAndFileName = new String[2];
        dirAndFileName[0] = imageFile.getParent();
        dirAndFileName[1] = getFilename(imageFileName);
        return dirAndFileName;
    }

    public static BufferedImage getBufferedImage(File imageFile) {
        try {
            return ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Picture getImageInfo(String imageFile) {
        return getImageInfo(getBufferedImage(new File(imageFile)));
    }

    /**
     * 获取图片宽、高、rgb值等信息
     * @param image
     * @return
     */
    public static Picture getImageInfo(BufferedImage image) {
        Picture picture = new Picture();
        int width = image.getWidth();
        int height = image.getHeight();
        picture.setWidth(width);
        picture.setHeight(height);
        Color[][] pixels = new Color[height][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[j][i] = new Color(image.getRGB(i, j));
            }
        }
        picture.setPixels(pixels);
        return picture;
    }

    private static String getFilename(String filename) {
        int end = filename.indexOf(".");
        return filename.substring(0, end);
    }

    private static String getFormat(String filename) {
        int end = filename.indexOf(".");
        String format = filename.substring(end + 1);
        if(!ImageEnum.exists(format)) {
            StringBuilder formatsBuilder = new StringBuilder();
            for(ImageEnum imageEnum : ImageEnum.values()) {
                formatsBuilder.append("/").append(imageEnum.getFormat());
            }
            throw new BaseException("请选择图片格式为 ["+ formatsBuilder.toString() +"] 的文件!");
        }
        return format;
    }
}
