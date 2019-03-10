package com.Integrate.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author AUGUSTRUSH
 *此类将筛选出来的图片转存到一个专门的文件夹存放
 *传入参数：文件目录名+文件名列表+需要转存的文件索引
 */
public class BatchSaveImages {
	public void saveImages(DirectoryEnum destDir,String imgDir,List<String> imgNameList,List<Integer> imgIndex) throws IOException {
		for(int i=0;i<imgIndex.size();i++) {
			String imaName=imgNameList.get(imgIndex.get(i));
			String absoluteImgName=imgDir+"/"+imaName;
			File file=new File(absoluteImgName);
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(new File(destDir + "/" + imaName));
			byte[] read = new byte[1024];
            int len = 0;
            while((len = fis.read(read))!= -1){
                fos.write(read,0,len);
            }
            fis.close();
            fos.flush();
            fos.close();
		}
	}
}
