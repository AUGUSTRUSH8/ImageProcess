package com.Integrate.Test;

import java.util.ArrayList;
import java.util.List;

import com.imageClassification.util.BatchImageClassification;
import com.imageClassification.util.RegularExpressionUtil;

/**
 * @author AUGUSTRUSH
 *批量获取图片的分类信息
 *传入参数：图片所在目录
 *返回参数：图片的分类列表
 *要求：图片以.jpg为后缀
 */
public class GetTheCategoryClass {
	public List<String> getCategory(String imgDir) {
		BatchImageClassification classifier=new BatchImageClassification();
		StringBuffer stringBuffer=classifier.getImageCategory(imgDir);
		String string=new String(stringBuffer);
		String[] lines=string.split("\\n");
		List<String> imgNameList=new ArrayList<String>();//有序存放图片文件名
		List<String> imgCategoryList=new ArrayList<String>();//有序存放图片类别信息
		for (String line : lines) {
			String string2=line.substring(line.length()-4,line.length());
			if(string2.equalsIgnoreCase(".jpg")) {
				//System.out.println("the picture name is: "+line);
				imgNameList.add(line);
			}
			else{
				StringBuffer sb=new StringBuffer(line);
				RegularExpressionUtil regularExpressionUtil=new RegularExpressionUtil();
				List<String> list=regularExpressionUtil.REparseUtil(sb);
				String category=list.get(0);
				imgCategoryList.add(category);
				//System.out.println("this picture's category is: "+category);
				//String accuracy=list.get(1)+'.'+list.get(2)+'%';
				//System.out.println("the accuracy is: "+accuracy+"\n");
			}
        }
		return imgCategoryList;
	}
}
