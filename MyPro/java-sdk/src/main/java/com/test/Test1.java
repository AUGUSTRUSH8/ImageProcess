package com.test;

import java.io.FileNotFoundException;

import com.jni.util.CallCPlusPitureAdjust;

/**
 * @author AUGUSTRUSH
 *测试图片矫正算法--成功(不行了，速度慢，而且效果差)
 *
 */
public class Test1 {
	public static void main(String[] args) throws FileNotFoundException {
		CallCPlusPitureAdjust callCPlusPitureAdjust=new CallCPlusPitureAdjust() {
		};
		int[] rotateDirectionTransform= {0,1,1};
		int a=callCPlusPitureAdjust.call(rotateDirectionTransform,"E:/business/recognition/BaiDuApi/imgClassification/003", "E:/business/recognition/BaiDuApi/imgAdjusted/003");
		System.out.println(a);
		
	}

}
