package com.imageClassification.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author AUGUSTRUSH
 *此类负责解析图像分类器返还回来的数据，得到其中的有用类别信息
 *返还数据示例：[3]: 97.35%
 */
public class RegularExpressionUtil {
	public List<String> REparseUtil(StringBuffer sBuffer){
		String string=sBuffer.toString();
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(string);
		List<String> list=new ArrayList<String>();
		while (matcher.find()) { 
			String item=matcher.group(0);
	        //System.out.println(matcher.group(0));
	        list.add(item);
	    }
		return list;
	}
}
