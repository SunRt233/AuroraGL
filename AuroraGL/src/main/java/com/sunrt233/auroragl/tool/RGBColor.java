package com.sunrt233.auroragl.tool;

public class RGBColor {

    private float a,r,g,b;
    private static float constNum = 1f / 255f;

    public RGBColor(float r, float g, float b, float a) {

        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;

    }

    public RGBColor(float r, float g, float b) {

        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 0;

    }

    public static RGBColor toGPURGB(RGBColor color) {
        return new RGBColor(color.r * constNum, color.g * constNum, color.b * constNum, color.a * constNum);
    }

    public float getA() {
        return a;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

}
