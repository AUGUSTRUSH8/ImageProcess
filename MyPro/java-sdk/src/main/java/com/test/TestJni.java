package com.test;

import com.ggq.imgUtil.JNI;

public class TestJni {
	public static void main(String[] args) {
		JNI jni=new JNI();
		int a=jni.call("hello");
		System.out.println(a);
		}
}
