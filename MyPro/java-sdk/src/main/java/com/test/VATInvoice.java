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
/**
 * @author AUGUSTRUSH
 *专门用来处理增值税发票得类
 *增值税发票图样见static目录下的VATInvoice.jpg
 */
public class VATInvoice {
	public static void main(String[] arrs) {
		//api返回的json对象
		JSONObject jsonObject=new JSONObject();
		//以token信息创建api调用对象
		AipOcr aipOcr=new AipOcr("10728591", "k1qEDIj16cfpEQU1FUGYEXIG", "NGZqqvWlaoS8Ydqfz0EtLYKu7ebkiQMW");
		//定义装图片信息的byte数组
		byte[] img=null;
		//图片本地地址
		String path="E:/business/recognition/BaiDuApi/java-sdk/FinalImage.jpg";
		//转换为byte数组
		img=image2byte(path);
		//调用Api可选参数，把返回文字外接多边形顶点位置设为true
		HashMap<String, String> options=new HashMap<String, String>();
		options.put("vertexes_location", "true");
		
		//请求百度接口识别图片
		jsonObject=aipOcr.vatInvoice(img,null);

		JSONObject jsonObject1=new JSONObject();
		jsonObject1=jsonObject.getJSONObject("words_result");
		Iterator it = jsonObject1.keys();
		while(it.hasNext()){
			String key=(String) it.next();
			Object value=jsonObject1.get(key);
			System.out.println(key+": "+value);
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
