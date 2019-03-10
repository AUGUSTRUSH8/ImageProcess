package qrcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * @author AUGUSTRUSH
 * 功能：检测二维码位置
 * 到时需要把这里封装为接口函数，供调用
 *传入参数：图片地址
 *返回参数：二维码位置
 */
public class Main {
	
	static {  
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  
        //注意程序运行的时候需要在VM option添加该行 指明opencv的dll文件所在路径  
        //-Djava.library.path=$PROJECT_DIR$\opencv\x64  
    }  
	
		


	public static void main(String[] args) {
		Mat src=new Mat();
		Mat grayImage=new Mat();
		Mat edges=new Mat();
		Mat hierarchy=new Mat();
		Detector dector=new Detector();
		//读取图片部分，到时需要把这里封装成为接口供调用
		String basePath="E:\\business\\recognition\\csdn_InUse\\pic_adjust_C++\\Pic_adjust\\Pic_adjust\\";
		String imgName="3copy_meitu_2.jpg";
		String imgPath=basePath+imgName;
		Mat srcImg=Imgcodecs.imread(imgPath);
		Mat distimg=dector.testAction(srcImg);
		ImageViewer ima=new ImageViewer(distimg ,"输出图片");
	    ima.imshow();
	    
	    
	  //封装一个获得二维码坐标点的函数
	  		//arrayName = new type[arrayLength1][arrayLength2];二维数组声明
	  		Double[][] cor=new Double[3][2];
	  		cor=dector.testAction1(srcImg);
	  		System.out.println(cor);
	  		int width=srcImg.cols();
	  		Double d = new Double(width);  
	  		int rows=cor.length;
	  		int count=0;
	  		for(int i=0;i<rows;i++) {
	  			if(cor[i][0]<(d/2)) {
	  				count++;
	  			}
	  			else {
	  				count--;
	  			}
	  		}
	  		//count>0表示二维码在图片的左侧
	  		if(count>0) {
	  			System.out.println("left");
	  		}
	  		else {
	  			System.out.println("right");
	  		}
		 /*
		  * 以下是通过轮廓嵌套的方式查找
		  */
	     /* 
		 Imgproc.cvtColor(srcImg, grayImage, Imgproc.COLOR_BGR2GRAY);
	    	Imgproc.GaussianBlur(grayImage, grayImage, new Size(3,3), 0);
	    	Imgproc.threshold(grayImage, grayImage, 0, 255, Imgproc.THRESH_OTSU);
	    	Imgproc.Canny(grayImage, edges, 100, 200);
	    	List<MatOfPoint> pointlist=new ArrayList<MatOfPoint>();
	    	Imgproc.findContours(edges, pointlist, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
	    	//System.out.println(hierarchy.dump());
	    	List<Integer> found=new ArrayList();
	    	for(int i=0;i<pointlist.size();i++) {
	    		int k=i;
	    		int c=0;
	    		while(hierarchy.get(0, k)[2]!=-1) {
	    			k=(int)hierarchy.get(0, k)[2];
	    			c=c+1;
	    		}
	    		if(c>=5) {
	    			found.add(i);
	    		}
	    	}
	    	for(int i=0;i<found.size();i++) {
	    		src=srcImg.clone();
	    		Imgproc.drawContours(src, pointlist, found.get(i), new Scalar(0, 255, 0), 3);
	    		ImageViewer ima=new ImageViewer(src ,"输出图片");
	   	     ima.imshow();
	    	}*/
	}

}