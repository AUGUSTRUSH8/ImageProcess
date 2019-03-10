package com.Integrate.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AUGUSTRUSH
 *批量获取某目录下的所有图片的图片名
 *传入参数：图片所在目录
 *返回参数：图片名列表
 *要求：图片以.jpg结尾
 */
public class GetImageNameClass {
	public List<String> getImageName(String imgDir){
		List<String> imgNameList=new ArrayList<String>();
		File dir=new File(imgDir);
		File[] files = dir.listFiles(new FilenameFilter() {
			//获取.jpg文件时使用listFiles(FilenameFilter filter)方法，创建一个过滤文件名的Filter
			    @Override
			    public boolean accept(File dir, String name) {
			        if(name != null) {
			        //检测文件名是否是以.jpg结尾，是返回true，否则继续检测下一个文件
			            if(name.toLowerCase().endsWith(".jpg")) {
			                return true;
			            }
			        }
			        return false;
			    }
			});
		for(File file:files) {
			imgNameList.add(file.getName());
		}
		return imgNameList;
	}
}
