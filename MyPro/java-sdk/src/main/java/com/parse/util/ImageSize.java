package com.parse.util;

/**
 * @author AUGUSTRUSH
 *图像大小类，用以存储图像的长宽值
 */
public class ImageSize {
	int width;
	int height;
	public ImageSize(int width,int height){
		this.width=width;
		this.height=height;
		
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
}
