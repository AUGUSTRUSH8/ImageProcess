package com.parse.util;

/**
 * @author AUGUSTRUSH
 *生成的矩形框有四个点，用这个点类分别存储该矩形四个角点的坐标值
 */
public class Point {
	int x;
	int y;
	public Point(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
