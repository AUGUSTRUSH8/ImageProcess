package com.ggq.imgUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author AUGUSTRUSH
 * 该类的作用是调用本地的python脚本检测图片中的二维码的位置
 * 传入参数：需要检测的图片地址
 * 返回参数：二维码在图片当中的位置
 */
public class QrCodeDetect {
	public StringBuffer getCoordinates(String imgPath) {
		StringBuffer buf = new StringBuffer();
		Process proc;
		try {
			String[] args = new String[] { "python", "E:\\business\\recognition\\Invoice\\DetectQRCodeJava.py",String.valueOf(imgPath)};
			proc = Runtime.getRuntime().exec(args);// 执行py文件
			//用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
//            while(in.readLine()!=null) {
//            	result = in.readLine();
//            }
            
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
