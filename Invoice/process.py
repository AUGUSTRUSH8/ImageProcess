# -*- coding: utf8 -*-
from PIL import Image
import numpy as np
import cv2
def main():
    img = cv2.imread("E:/business/recognition/Invoice/lena.png")

    #转换为灰度图
    myimg1 = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
    cv2.imshow('myimg1',myimg1)
    (h,w) = img.shape[:2]
    center = (w / 2,h / 2)
    M = cv2.getRotationMatrix2D(center,-90,0.75)#旋转缩放矩阵：(旋转中心，旋转角度，缩放因子)
    rotated = cv2.warpAffine(img,M,(w,h))
    cv2.imshow("Rotated by -90 Degrees",rotated)
    cv2.imwrite('rotatedPicture.jpg',rotated)
    myimg2=cv2.cvtColor(rotated,cv2.COLOR_BGR2GRAY)
    ret,thresh = cv2.threshold(myimg2,180,255,0)   #二值化
    cv2.imshow("threshold Picture",thresh)

    #降低像素
    img2=cv2.pyrDown(img, cv2.IMREAD_UNCHANGED)
    cv2.imshow("pix_down_img",img2)
    cv2.imwrite('pix_down_img.jpg',img2)

    #二值化
    ret, thresh = cv2.threshold(cv2.cvtColor(img.copy(), cv2.COLOR_BGR2GRAY), 127, 255, cv2.THRESH_BINARY)
    image, contours, hier = cv2.findContours(thresh, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    for c in contours:
        # 边界框:
        # find bounding box coordinates
        # boundingRect()将轮廓转化成(x,y,w,h)的简单边框,cv2.rectangle()画出矩形[绿色(0, 255, 0)]
        x, y, w, h = cv2.boundingRect(c)
        cv2.rectangle(img, (x, y), (x + w, y + h), (0, 255, 0), 2)


        # 最小矩形区域:
        # 1 计算出最小矩形区域 2 计算这个矩形的顶点 3 由于计算出来的是浮点数，而像素是整型，所以进行转化 4 绘制轮廓[红色(0, 0, 255)]
        # 找到最小包围区域
        rect = cv2.minAreaRect(c)
        # 计算这个最小矩形区域的坐标
        box = cv2.boxPoints(rect)
        # 得到的坐标可能是浮点型，需要转化为整型（像素）
        box = np.int0(box)
        # 画轮廓
        cv2.drawContours(img, [box], 0, (0, 0, 255), 3)

    cv2.drawContours(img, contours, -1, (255, 0, 0), 1)
    cv2.imshow("contours", img)
    cv2.imwrite('contours.jpg',img)


    # image,contours,hierarchy = cv2.findContours(thresh, 1, 2)#找轮廓
    # cnt = contours[0]   #选取其中的第一个轮廓
    # M = cv2.moments(cnt)  #对第一个轮廓
    # print (M)             #打印出所有计算的M的参数，其各个数值的计算公式参考http://blog.csdn.net/qq_18343569/article/details/46913501
    # x,y,w,h = cv2.boundingRect(cnt)
    # img2 = cv2.rectangle(myimg2,(x,y),(x+w,y+h),(0,255,0),2)


    # rect = cv2.minAreaRect(cnt)
    # box = cv2.cv.BoxPoints(rect)
    # box = np.int0(box)
    # cv2.drawContours(myimg2, [box], 0, (0, 0, 255), 2)
    # cv2.imwrite('contours.jpg', myimg2)
    

    cv2.waitKey()
    cv2.destroyAllWindows()


    # img = Image.open("2.jpg")
    #进行resize操作
    # width=800
    # height=600
    # img = img.resize((width, height),Image.LANCZOS)
    #img.save("2(2).jpg",format="jpeg")
if __name__ == "__main__":
    main()