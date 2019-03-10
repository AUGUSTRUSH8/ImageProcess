package com.imageClassification.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author AUGUSTRUSH
 *批量检测图片分类测试类
 *对应调用predict1_new.py
 *传入参数：图片所在目录
 *返回参数：图片名+分类
 *
 */
public class BatchImageClassification {
	public StringBuffer getImageCategory(String imgDir) {
		Process proc;
		StringBuffer buf = new StringBuffer();
		String pythonFile="E:/business/recognition/BaiDuApi/pythonScript/InvoiceClassification/Keras-image-classifer-framework/invoice-code/predict1_new.py";
		try {
			String[] args = new String[] { "python", pythonFile,String.valueOf(imgDir)};
			proc = Runtime.getRuntime().exec(args);// 执行py文件
			//用输入输出流来截取结果
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
            	System.out.println(line);
            	buf.append(line+"\n");
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
		return buf;
	}
}
