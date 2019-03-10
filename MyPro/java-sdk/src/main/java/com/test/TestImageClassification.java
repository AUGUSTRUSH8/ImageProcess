package com.test;

import java.io.FileNotFoundException;
import java.util.List;

import com.imageClassification.util.ImageClassification;
import com.imageClassification.util.RegularExpressionUtil;

/**
 * @author AUGUSTRUSH
 *测试图片分类的类--成功
 *坑：注意权重文件的路径问题，Java调用python的时候，相对路径是以Java这边为准了！
 *权重文件放在Java项目根目录这边来
 */
public class TestImageClassification {
	public static void main(String[] args) throws FileNotFoundException {
		String imapath="E:/business/recognition/InvoiceClassification/Keras-image-classifer-framework/invoice-code/2.jpg";
		ImageClassification classifier=new ImageClassification();
		StringBuffer stringBuffer=classifier.getImageCategory(imapath);
		RegularExpressionUtil regularExpressionUtil=new RegularExpressionUtil();
		List<String> list=regularExpressionUtil.REparseUtil(stringBuffer);
		System.out.println(list);
		String category=list.get(0);
		System.out.println("this picture's category is: "+category);
		String accuracy=list.get(1)+'.'+list.get(2)+'%';
		System.out.println("the accuracy is: "+accuracy);
	}
	
}
