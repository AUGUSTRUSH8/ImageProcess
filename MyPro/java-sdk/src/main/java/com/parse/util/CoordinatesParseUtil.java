package com.parse.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author AUGUSTRUSH
 *此类的作用主要是根据python脚本返回的约束矩形角点值正则匹配出需要的数据
 *并封装至list当中
 */
public class CoordinatesParseUtil {
	public List<String> rotateDirection(StringBuffer sBuffer) {
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
