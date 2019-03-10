package com.jni.util;

/**
 * @author AUGUSTRUSH
 *图片倾斜矫正
 *jni调用类，调用本地的C++程序代码
 *传入参数：图片旋转方向数组,待矫正图片路径（不允许为空），校正后输出路径（可为空，默认输出路径：）
 *
 */
public abstract class CallCPlusPitureAdjust {
    static {
            System.loadLibrary("AI");
    }
    public native int call(int[] rotateDirectionTransform,String imgSourceDir,String imgDestDir);
}