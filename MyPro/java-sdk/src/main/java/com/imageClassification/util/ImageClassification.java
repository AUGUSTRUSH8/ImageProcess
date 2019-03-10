package com.imageClassification.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author AUGUSTRUSH
 *该类调用python脚本预测输入图片所属的类别
 *传入参数：待分类图片地址
 *返回参数：该图片的标识码，如返回0003表示该图片分类为增值税发票
 *检测单张图片  对应调用predict.py
 */
public class ImageClassification {
	public StringBuffer getImageCategory(String imgPath) {
		Process proc;
		StringBuffer buf = new StringBuffer();
		String pythonFile="E:/business/recognition/InvoiceClassification/Keras-image-classifer-framework/invoice-code/predict1.py";
		try {
			String[] args = new String[] { "python", pythonFile,String.valueOf(imgPath)};
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
