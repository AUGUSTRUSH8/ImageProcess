package com.ggq.imgUtil;

import java.util.List;

import com.parse.util.CoordinatesParseUtil;
import com.parse.util.ImageSize;
import com.parse.util.Point;

/**
 * @author AUGUSTRUSH
 *此类封装较多，功能是决定给定的一张图像朝顺时针旋转90度还是逆时针旋转90度
 *扫描的图基本就是纵向的图片，需要根据二维码的位置决定怎么旋转
 *二维码要么在左下角，要么在右上角
 *传入参数：图片地址
 *返回参数：flags(false：顺时针90度，true：逆时针90度)
 */
public class WhichDirectionToRotate {
	public Boolean DecideDirection(String imgPath) {
		Boolean flags=false;
		QrCodeDetect qrCodeDetect=new QrCodeDetect();
		StringBuffer stringBuffer= qrCodeDetect.getCoordinates(imgPath);
		CoordinatesParseUtil cp=new CoordinatesParseUtil();
		List<String> list=cp.rotateDirection(stringBuffer);
		//System.out.println(list);
		
		int width=Integer.parseInt(list.get(0));
		int height=Integer.parseInt(list.get(1));
		ImageSize imageSize=new ImageSize(width, height);
		
		int point1x=Integer.parseInt(list.get(3));
		int point1y=Integer.parseInt(list.get(4));
		Point point1=new Point(point1x,point1y);
		
		int point2x=Integer.parseInt(list.get(5));
		int point2y=Integer.parseInt(list.get(6));
		Point point2=new Point(point2x,point2y);
		
		int point3x=Integer.parseInt(list.get(7));
		int point3y=Integer.parseInt(list.get(8));
		Point point3=new Point(point3x,point3y);
		
		int point4x=Integer.parseInt(list.get(9));
		int point4y=Integer.parseInt(list.get(10));
		Point point4=new Point(point4x,point4y);
		//System.out.println(point1.x);
		if((point1.getX()+point3.getX())/2<(imageSize.getWidth()/2)) {
			flags=false;//顺时针90度
		}
		else {
			flags=true;//逆时针90度
		}
		return flags;
		
	}
}
