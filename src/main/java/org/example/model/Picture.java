package org.example.model;

import java.awt.*;

/**
 * 图片信息
 *
 * @author AdamSun
 * @date 2020/5/9 21:24
 */
public class Picture {
    /**
     * 图片宽的像素数
     */
    private int width;
    /**
     * 图片高的像素数
     */
    private int height;
    /**
     * 像素点集合
     */
    private Color[][] pixels;

    public Picture() {
    }

    public Picture(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Picture(int width, int height, Color[][] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color[][] getPixels() {
        return pixels;
    }

    public void setPixels(Color[][] pixels) {
        this.pixels = pixels;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
