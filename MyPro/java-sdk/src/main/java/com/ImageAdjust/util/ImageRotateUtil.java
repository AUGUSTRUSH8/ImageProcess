package com.ImageAdjust.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author AUGUSTRUSH
 * 此类完成图像的旋转操作，是一个基本的工具类
 * 批量操作
 * 0标志表示顺时针旋转90度
 * 1标志表示逆时针旋转90度
 * 传入参数：图片所在路径
 * 返回参数：控制台输出"rotate done！"
 */
public class ImageRotateUtil {
	public void rotate(String inImgDir,String outImgDir,int[] rotateDirection) throws IOException {
		File file=new File(inImgDir);
		File[] files=file.listFiles();
		int i=0;
		for(File file2:files) {
			if(!file2.isDirectory()) {
				String outputPath=outImgDir+"/"+files[i].getName();
				System.out.println(outputPath);
				BufferedImage image=ImageIO.read(file2);
				File outFile = new File(outputPath);
				System.out.println(rotateDirection[i]);
				switch (rotateDirection[i]) {
				case 0://顺时针旋转
					image=Rotate(image, 90);
					ImageIO.write(image,"jpg",outFile);
					i++;
					break;
				case 1://逆时针旋转
					image=Rotate(image, 270);
					ImageIO.write(image,"jpg",outFile);
					i++;
					break;
				default:
					break;
				}
			}
		}
		System.out.println("rotate done!");
	}
	/**
     * 对图片进行旋转
     *
     * @param src   被旋转图片
     * @param angel 旋转角度
     * @return 旋转后的图片
     */
    public static BufferedImage Rotate(Image src, int angel) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        // 计算旋转后图片的尺寸
        Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(
                src_width, src_height)), angel);
        BufferedImage res = null;
        res = new BufferedImage(rect_des.width, rect_des.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = res.createGraphics();
        // 进行转换
        g2.translate((rect_des.width - src_width) / 2,
                (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);
 
        g2.drawImage(src, null, null);
        return res;
    }
	
    /**
     * 计算旋转后的图片
     *
     * @param src   被旋转的图片
     * @param angel 旋转角度
     * @return 旋转后的图片
     */
    public static Rectangle CalcRotatedSize(Rectangle src, int angel) {
        // 如果旋转的角度大于90度做相应的转换
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }
 
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);
 
        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new Rectangle(new Dimension(des_width, des_height));
    }
}
