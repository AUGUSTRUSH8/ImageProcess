package com.ggq.imgUtil;

public class JNI{
	public native int call(String img_path);
	   static{
	       System.loadLibrary("ConsoleApplication1");  
	   }
}
