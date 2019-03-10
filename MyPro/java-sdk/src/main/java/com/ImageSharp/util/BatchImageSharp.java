package com.ImageSharp.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * @author AUGUSTRUSH
 * 此类批量完成图片锐化操作并保存
 */
public class BatchImageSharp {
	static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
	private String fullName;
	public void BatchSharpImg(String imgDir,String outputDir) {
		List<String> imgNameList=getImageName(imgDir);
		for(int i=0;i<imgNameList.size();i++) {
			fullName=imgDir+"/"+imgNameList.get(i);
			Mat imread = Imgcodecs.imread(fullName);
			Mat sharpResult=ImageSharpUtil.sharpen(imread, 5, 2);
			String outputFilename=outputDir+"/"+imgNameList.get(i);
			Imgcodecs.imwrite(outputFilename, sharpResult);
		}
		System.out.println("Image Sharpen Done!");
	}
	
	/**
	 * 获取指定目录下的文件名列表（仅包括文件名）
	 * @param imgDir
	 * @return
	 */
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
