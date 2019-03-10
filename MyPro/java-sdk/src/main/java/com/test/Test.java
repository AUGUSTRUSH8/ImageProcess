package com.test;

import java.io.FileNotFoundException;

import com.ggq.imgUtil.WhichDirectionToRotate;

/**
 * @author AUGUSTRUSH
 *测试根据二维码位置决定旋转方向功能--成功
 *
 */
public class Test {
	public static void main(String[] args) throws FileNotFoundException {
		String imgPath="E:\\business\\recognition\\Invoice\\3copy_meitu_2.jpg";
		WhichDirectionToRotate whichDirectionToRotate=new WhichDirectionToRotate();
		Boolean flags=whichDirectionToRotate.DecideDirection(imgPath);
		System.out.println(flags);
	}
}
