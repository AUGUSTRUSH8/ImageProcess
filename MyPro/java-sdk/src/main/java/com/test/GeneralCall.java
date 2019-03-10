package com.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.stream.FileImageInputStream;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class GeneralCall {
	public static void main(String[] arrs) {
		//api返回的json对象
		JSONObject jsonObject=new JSONObject();
		//以token信息创建api调用对象
		AipOcr aipOcr=new AipOcr("10728591", "k1qEDIj16cfpEQU1FUGYEXIG", "NGZqqvWlaoS8Ydqfz0EtLYKu7ebkiQMW");
		//定义装图片信息的byte数组
		byte[] img=null;
		//图片本地地址
		String path="E:/business/recognition/财务系统票据识别/Finace_Rec/票据图片/Image_00003.jpg";
		//转换为byte数组
		img=image2byte(path);
		//调用Api可选参数，把返回文字外接多边形顶点位置设为true
		HashMap<String, String> options=new HashMap<String, String>();
		options.put("vertexes_location", "true");
		
		//请求百度接口识别图片
		jsonObject=aipOcr.vatInvoice(img,null);
//		String log_id=jsonObject.getString("log_id");
//		System.out.println("log_id "+log_id);
		

		//System.out.println(jsonObject.toString());
		JSONObject jsonObject1=new JSONObject();
		jsonObject1=jsonObject.getJSONObject("words_result");
//		int n=jsonObject1.length();
//		System.out.println(n);
		Iterator it = jsonObject1.keys();
		while(it.hasNext()){
			String key=(String) it.next();
			Object value=jsonObject1.get(key);
			System.out.println(key+": "+value);
		}

		
//		JSONArray jsonArray=jsonObject.getJSONArray("words_result");
//		for(int i=0;i<jsonArray.length();i++) {
//			 JSONObject object = (JSONObject) jsonArray.get(i);
//			 String words=object.getString("words");
//			 JSONObject location=(JSONObject) object.get("location");
//			 System.out.println("words  "+words);
//			 System.out.println("location"+location);
//			 System.out.println(object);
//		}
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
