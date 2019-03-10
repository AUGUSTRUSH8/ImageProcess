package com.Integrate.Test;

/**
 * @author AUGUSTRUSH
 * 枚举类：记录每个分类的图片存放地址，不用直接写在代码里，直接通过枚举常量即可得到
 */
public enum DirectoryEnum {
	category1("分类1目录"), category2("分类2目录"), category3("E:/business/recognition/BaiDuApi/imgClassification/003"), category4("分类4目录"), 
	category5("分类5目录"), category6("分类6目录"), category7("分类7目录"),
	rotateDirectoryCategory3("E:/business/recognition/BaiDuApi/imgRotated/003"),
	finalVatInvoicePic("E:/business/recognition/BaiDuApi/final");
	private String category;

    // 定义一个带参数的构造器，枚举类的构造器只能使用 private 修饰
    private DirectoryEnum(String category) {
        this.category = category;
    }
    public String getcategory() {
        return category;
    }

    public void setcategory(String category) {
        this.category = category;
    }
    @Override
    public String toString(){
        return category;
    }
}
