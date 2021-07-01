package com.knd.common.utils;

import android.graphics.Color;

/**
 * 获取渐变区间某点颜色
 */
public class ColorUtil {
    /**
     * 获取某个百分比位置的颜色
     * @param radio 百分比 0-1
     * @param mColorArr 颜色集
     * @param mColorPosition 位置集
     * @return 颜色值
     */
    public static int getColor(float radio, int[] mColorArr, float[] mColorPosition) {
        int startColor;
        int endColor;
        if (radio >= 1) {
            return mColorArr[mColorArr.length - 1];
        }
        for (int i = 0; i < mColorPosition.length; i++) {
            if (radio <= mColorPosition[i]) {
                if (i == 0) {
                    return mColorArr[0];
                }
                startColor = mColorArr[i - 1];
                endColor = mColorArr[i];
                float areaRadio = getAreaRadio(radio,mColorPosition[i-1],mColorPosition[i]);
                return getColorFrom(startColor,endColor,areaRadio);
            }
        }
        return mColorArr[0];
    }

    public static float getAreaRadio(float radio, float startPosition, float endPosition) {
        return (radio - startPosition) / (endPosition - startPosition);
    }

    /**
     *  取两个颜色间的渐变区间 中的某一点的颜色
     * @param startColor
     * @param endColor
     * @param radio
     * @return
     */
    public static int getColorFrom(int startColor, int endColor, float radio) {
        int redStart = Color.red(startColor);
        int blueStart = Color.blue(startColor);
        int greenStart = Color.green(startColor);
        int redEnd = Color.red(endColor);
        int blueEnd = Color.blue(endColor);
        int greenEnd = Color.green(endColor);

        int red = (int) (redStart + ((redEnd - redStart) * radio + 0.5));
        int greed = (int) (greenStart + ((greenEnd - greenStart) * radio + 0.5));
        int blue = (int) (blueStart + ((blueEnd - blueStart) * radio + 0.5));
        return Color.argb(255, red, greed, blue);
    }
}
