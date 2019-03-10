package com.test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.imageClassification.util.BatchImageClassification;
import com.imageClassification.util.RegularExpressionUtil;

/**
 * @author AUGUSTRUSH
 *批量测试图片分类--成功 注意图片要以.jpg为后缀
 *坑：注意权重文件的路径问题，Java调用python的时候，相对路径是以Java这边为准了！
 *权重文件放在Java项目根目录这边来
 */
public class TestBatchImageClassification {
	public static void main(String[] args) throws FileNotFoundException {
		String imgDir="E:/business/recognition/InvoiceClassification/Keras-image-classifer-framework/invoice-code/testBatchRead";
		BatchImageClassification classifier=new BatchImageClassification();
		StringBuffer stringBuffer=classifier.getImageCategory(imgDir);
		//System.out.println(stringBuffer.length());
		//List<String> category=new ArrayList<String>();
		String string=new String(stringBuffer);
		String[] lines=string.split("\\n");
		for (String line : lines) {
			String string2=line.substring(line.length()-4,line.length());
			if(string2.equalsIgnoreCase(".jpg")) {
				System.out.println("the picture name is: "+line);
			}
			else{
				StringBuffer sb=new StringBuffer(line);
				RegularExpressionUtil regularExpressionUtil=new RegularExpressionUtil();
				List<String> list=regularExpressionUtil.REparseUtil(sb);
				String category=list.get(0);
				System.out.println("this picture's category is: "+category);
				String accuracy=list.get(1)+'.'+list.get(2)+'%';
				System.out.println("the accuracy is: "+accuracy+"\n");
			}
            //System.out.println("line"+" : " + line);
        }
	}

}
