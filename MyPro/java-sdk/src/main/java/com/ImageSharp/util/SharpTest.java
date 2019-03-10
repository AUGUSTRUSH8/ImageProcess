package com.ImageSharp.util;

import java.util.List;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * @author AUGUSTRUSH
 * 图像锐化操作测试类--成功
 */
public class SharpTest {
	static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
	private static Mat imread = Imgcodecs.imread("3new.jpg");
	public static void showImg(Mat imread, int len) {
        HighGui.namedWindow("test", HighGui.WINDOW_AUTOSIZE);
        HighGui.imshow("test", imread);
        HighGui.waitKey(len);
    }
	public static class TestBase {
		@Test
        public void testSharpen() {
            showImg(ImageSharpUtil.sharpen(imread, 5, 2), 0);
        }
		@Test//效果不好
        public void testDisFog() {
            showImg(ImageSharpUtil.disFog(imread, 50), 0);
        }
		@Test 
		public void showImgName() {
			String imgDir="E:/business/recognition/BaiDuApi/imageBasicCompleted/003";
			BatchImageSharp batchImageSharp=new BatchImageSharp();
			List<String> imgNameList=batchImageSharp.getImageName(imgDir);
			for(int i=0; i<imgNameList.size();i++) {
				System.out.println(imgNameList.get(i));
			}
		}

	}

}
