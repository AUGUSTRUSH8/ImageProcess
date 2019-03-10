package com.Integrate.Test;
import java.io.IOException;
import com.ImageAdjust.util.ImageAdjust;

public class Temp {
	public static void main(String[] args) throws IOException {
		String imgDir="E:/business/recognition/BaiDuApi/imgRotated/003";
		String outputDir="";
		ImageAdjust adjust=new ImageAdjust();
		adjust.imgAdjustTool(imgDir,outputDir);
	}

}
