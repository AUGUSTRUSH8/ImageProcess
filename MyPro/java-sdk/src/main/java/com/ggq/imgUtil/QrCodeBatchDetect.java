package com.ggq.imgUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author AUGUSTRUSH
 *批量检测二维码位置
 *传入参数：图片所在目录
 *返回参数：每张图片当中二维码所在位置的坐标，如下：
 * [[ 329 1491]
 	[ 329 1407]
 	[ 417 1407]
 	[ 417 1491]]
 */
public class QrCodeBatchDetect {
	public StringBuffer getCoordinates(String imgDir) {
		StringBuffer buf = new StringBuffer();
		Process proc;
		try {
			String[] args = new String[] { "python", "E:/business/recognition/BaiDuApi/pythonScript/QRCodeDetect/Invoice/DetectQRCodeJava_new.py",String.valueOf(imgDir)};
			proc = Runtime.getRuntime().exec(args);// 执行py文件
			//用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
//            while(in.readLine()!=null) {
//            	result = in.readLine();
//            }
            
            while ((line = in.readLine()) != null) {
            	System.out.println(line);
            	buf.append(line);
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
