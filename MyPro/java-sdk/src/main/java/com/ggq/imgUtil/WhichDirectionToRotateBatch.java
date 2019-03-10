package com.ggq.imgUtil;

import java.util.ArrayList;
import java.util.List;

import com.Integrate.Test.DirectoryEnum;
import com.parse.util.CoordinatesParseUtil;

/**
 * @author AUGUSTRUSH
 * 批量
 *此类封装较多，功能是决定给定的一张图像朝顺时针旋转90度还是逆时针旋转90度
 *扫描的图基本就是纵向的图片，需要根据二维码的位置决定怎么旋转
 *二维码要么在左下角，要么在右上角
 *传入参数：图片所在目录
 *返回参数：flags(false：顺时针90度，true：逆时针90度)
 */
public class WhichDirectionToRotateBatch {
	public List<Boolean> DecideDirection(DirectoryEnum imgDir) {
		String imgDirTransform=imgDir.toString();
		QrCodeBatchDetect qrCodeBatchDetect=new QrCodeBatchDetect();
		StringBuffer stringBuffer= qrCodeBatchDetect.getCoordinates(imgDirTransform);
		CoordinatesParseUtil cp=new CoordinatesParseUtil();
		String coordinates=stringBuffer.toString();
		//System.out.println(coordinates);
		String[] coordinates1=coordinates.split("\\+");
		List<Integer> imageWidthList=new ArrayList<Integer>();//装所有图片宽度
		List<Float> averageXList=new ArrayList<Float>();//装二维码横坐标的平均值
		List<Boolean> rotateDirection=new ArrayList<Boolean>();//装旋转方向标志
		//System.out.println(coordinates1.length);
		for(int i=0;i<coordinates1.length;i++) {
			StringBuffer temp=new StringBuffer(coordinates1[i]);
			List<String> list=cp.rotateDirection(temp);
			if(list.size()==3) {
				int imageWidth=Integer.parseInt(list.get(1));
				imageWidthList.add(imageWidth);
			}
			else {
				int firstPointX=Integer.parseInt(list.get(0));
				int thirdPointX=Integer.parseInt(list.get(4));
				float averageX=(firstPointX+thirdPointX)/2;
				averageXList.add(averageX);
			}
		}
		for(int j=0;j<imageWidthList.size();j++) {
			rotateDirection.add(averageXList.get(j)<(imageWidthList.get(j))/2?false:true);
		}
		
		return rotateDirection;

		
	}

}
