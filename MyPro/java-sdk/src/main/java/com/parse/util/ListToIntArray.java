package com.parse.util;

import java.util.List;

/**
 * @author AUGUSTRUSH
 * 完成List到Byte[]之间的转换
 * 主要是将[false, true, true, true, true, true, true](旋转标志)
 * 转换为[0,11111]
 */
public class ListToIntArray {
	public int[] ListToByteArrayTool(List<Boolean> rotateDirection) {
		int[] rotateDirectionTransform = new int[rotateDirection.size()];
		for(int i=0;i<rotateDirection.size();i++) {
			if(rotateDirection.get(i).toString().equals("false")) {
				rotateDirectionTransform[i]=0;
			}
			else {
				rotateDirectionTransform[i]=1;
			}
		}
		return rotateDirectionTransform;
		
	}
}
