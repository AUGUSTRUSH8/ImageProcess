package com.test;
import java.io.InputStream;
/**
 * @author AUGUSTRUSH
 *参考文档：https://blog.csdn.net/xys_777/article/details/6164206
 *运行时添加设置-Dpython.console.encoding=UTF-8
 *可以设置命令行执行完以后直接关闭命令行窗口等等
 */
public class TestPythonCall {
	public static void main(String[] args) {
		String path = "E:\\business\\recognition\\Invoice\\run.bat";
		Runtime run = Runtime.getRuntime();      
		try {      
	        // run.exec("cmd /k shutdown -s -t 3600");      
	        Process process = run.exec("cmd.exe /k start " + path);      
	        InputStream in = process.getInputStream();        
	        while (in.read() != -1) {      
	            System.out.println(in.read());      
	        }      
	        in.close();      
	        process.waitFor();      
	    } catch (Exception e) {               
	        e.printStackTrace();      
	    }      
	}

}
