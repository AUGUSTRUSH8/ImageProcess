package com.ImageAdjust.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImageAdjust {
	public void imgAdjustTool(String imgDir,String outputDir) {
		Process proc;
		String pythonFile="E:/business/recognition/BaiDuApi/pythonScript/OpenCV-Document-Scanner/scan.py";
		try {
			String[] args = new String[] { "python", pythonFile,String.valueOf(imgDir),String.valueOf(outputDir)};
			proc = Runtime.getRuntime().exec(args);// 执行py文件
			//用输入输出流来截取结果
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
            	System.out.println(line);
            }
            in.close();
            proc.waitFor();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Image Adjust Done！");
	}
}
