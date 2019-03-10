package com.Integrate.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ImageAdjust.util.ImageAdjust;
import com.ImageAdjust.util.ImageRotateUtil;
import com.ImageSharp.util.BatchImageSharp;
import com.ggq.imgUtil.WhichDirectionToRotateBatch;
import com.parse.util.ListToIntArray;

/**
 * @author AUGUSTRUSH
 *综合调配各个接口，实现增值税发票的处理存档--成功
 */
public class ControlCenter {
	public static void main(String[] args) throws IOException {
		String imgDir="E:/business/recognition/InvoiceClassification/Keras-image-classifer-framework/invoice-code/testBatchRead";
		GetImageNameClass getImageNameClass =new GetImageNameClass();
		GetTheCategoryClass getTheCategoryClass=new GetTheCategoryClass();
		BatchSaveImages batchSaveImages=new BatchSaveImages();
		WhichDirectionToRotateBatch whichDirectionToRotateBatch=new WhichDirectionToRotateBatch();
		ListToIntArray listToIntArray=new ListToIntArray();
		ImageRotateUtil imageRotateUtil=new ImageRotateUtil();
		ImageAdjust imageAdjust=new ImageAdjust();
		BatchImageSharp batchImageSharp=new BatchImageSharp();
		//获取指定目录下图片名列表
		//demo：[2.jpg, 3.jpg, 4.jpg, Image_00001.jpg, Image_00002.jpg, Image_00003.jpg, Image_00004.jpg, pioaju22222.jpg]
		String imgPreProcessedDir="E:/business/recognition/BaiDuApi/imagePreProcessed";
		List<String> imgNameList=getImageNameClass.getImageName(imgDir);
		imageAdjust.imgAdjustTool(imgDir, imgPreProcessedDir);
		//获取所有图片的类别信息列表
		List<String> imgCategoryList=getTheCategoryClass.getCategory(imgPreProcessedDir);
		System.out.println(imgNameList);
		System.out.println(imgCategoryList);
		List<Integer> class3=new ArrayList<Integer>();//增值税发票的图片列表下标索引
		for(int i=0;i<imgCategoryList.size();i++) {
			if(imgCategoryList.get(i).equalsIgnoreCase("3")) {
				class3.add(i);
			}
		}
		System.out.println(class3);
		batchSaveImages.saveImages(DirectoryEnum.category3, imgDir, imgNameList, class3);
		List<Boolean> rotateDirection=whichDirectionToRotateBatch.DecideDirection(DirectoryEnum.category3);
		System.out.println(rotateDirection);
		int[] rotateDirectionTransform=listToIntArray.ListToByteArrayTool(rotateDirection);
		String in=DirectoryEnum.category3.toString();
		String out=DirectoryEnum.rotateDirectoryCategory3.toString();
		imageRotateUtil.rotate(in, out, rotateDirectionTransform);
		String adjustDir="E:/business/recognition/BaiDuApi/imgAdjusted/003";
		imageAdjust.imgAdjustTool(out,adjustDir);
		String basicCompletedDir="E:/business/recognition/BaiDuApi/imageBasicCompleted/003";
		imageAdjust.imgAdjustTool(adjustDir, basicCompletedDir);
		String finalDir="E:/business/recognition/BaiDuApi/final/003";
		batchImageSharp.BatchSharpImg(basicCompletedDir, finalDir);
		System.out.println("All Image Processed, Done!");
	}
	
	
}
