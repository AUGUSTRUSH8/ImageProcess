package com.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class GeneralReceiptRecognize {

	public static void main(String[] args) {
		//api返回的json对象
				JSONObject jsonObject=new JSONObject();
				//以token信息创建api调用对象
				AipOcr aipOcr=new AipOcr("10728591", "k1qEDIj16cfpEQU1FUGYEXIG", "NGZqqvWlaoS8Ydqfz0EtLYKu7ebkiQMW");
				//定义装图片信息的byte数组
				byte[] img=null;
				//图片本地地址
				String path="test.jpg";
				//转换为byte数组
				img=image2byte(path);
				
				//请求百度接口识别图片
				jsonObject=aipOcr.receipt(img,null);
				System.out.println(jsonObject);
				
				JSONArray jsonArray=jsonObject.getJSONArray("words_result");
				for(int i=0;i<jsonArray.length();i++) {
					 JSONObject object = (JSONObject) jsonArray.get(i);
					 String words=object.getString("words");
					 JSONObject location=(JSONObject) object.get("location");
					 System.out.println("words: "+words);
					 System.out.println("location: "+location);
					 System.out.println(object);
				}
	}
	public static byte[] image2byte(String path){
		 byte[] data = null;
		 FileImageInputStream input = null;
		 try {
			 //图片输入
			 input = new FileImageInputStream(new File(path));
			 ByteArrayOutputStream output = new ByteArrayOutputStream();
			 byte[] buf = new byte[1024];
			 int numBytesRead = 0;
			 while ((numBytesRead = input.read(buf)) != -1) {
			      output.write(buf, 0, numBytesRead);
			      }
			      data = output.toByteArray();
			      output.close();
			      input.close();
			  
		}  catch (FileNotFoundException ex1) {
		      ex1.printStackTrace();
	    }
	    catch (IOException ex1) {
	      ex1.printStackTrace();
	    }
		return data;
	}

}
