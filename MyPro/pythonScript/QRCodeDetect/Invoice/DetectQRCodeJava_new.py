#coding:utf-8
import numpy as np
import cv2
import sys
import os
def get_file_list(imgDir):
    f_list = os.listdir(imgDir)
    f_list1=[]
    for i in f_list:
        if os.path.splitext(i)[1] == '.jpg':
            f_list1.append(i)
    return f_list1

def DetectQrCode(imgDir):
    #在检测二维码的时候首先需要分离颜色通道，在这里需要先把蓝色通道分离出来，下面
    #的两个参数分别表示蓝色HSV空间的上下阈值，这是个调参的工作，目前下限被我调成
    #[90,100,100]效果最好，原来是[90,90,90]效果不尽人意
    lower_blue = np.array([90, 100, 100])
    upper_blue = np.array([130, 255, 255])
    file_list=get_file_list(imgDir)
    #print(file_list)
    resultSet=[]
    j=0
    for i in file_list:
        imgStr=imgDir+'/'+i
        image = cv2.imread(imgStr)
        shape=image.shape
        # print(shape)
        hsv=cv2.cvtColor(image, cv2.COLOR_BGR2HSV)

        # 3.inRange()：介于lower/upper之间的为白色，其余黑色
        mask = cv2.inRange(hsv, lower_blue, upper_blue)

        # 4.只保留原图中的蓝色部分
        res = cv2.bitwise_and(image, image, mask=mask)
        gray = cv2.cvtColor(res, cv2.COLOR_BGR2GRAY)

        gradX = cv2.Sobel(gray, ddepth = cv2.CV_32F, dx = 1, dy = 0, ksize = -1)
        gradY = cv2.Sobel(gray, ddepth = cv2.CV_32F, dx = 0, dy = 1, ksize = -1)

        gradient = cv2.subtract(gradX, gradY)
        gradient = cv2.convertScaleAbs(gradient)

        #cv2.imshow("gradient",gradient)
        #原本没有过滤颜色通道的时候，这个高斯模糊有效，但是如果进行了颜色过滤，不用高斯模糊效果更好
        #blurred = cv2.blur(gradient, (9, 9))
        (_, thresh) = cv2.threshold(gradient, 225, 255, cv2.THRESH_BINARY)
        #cv2.imshow("thresh",thresh)
        #cv2.imwrite('thresh.jpg',thresh)

        kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (21, 21))
        closed = cv2.morphologyEx(thresh, cv2.MORPH_CLOSE, kernel)
        #cv2.imshow("closed",closed)
        #cv2.imwrite('closed.jpg',closed)

        closed = cv2.erode(closed, None, iterations = 4)
        closed = cv2.dilate(closed, None, iterations = 4)
        #cv2.imwrite('closed1.jpg',closed)

        img,cnts, _ = cv2.findContours(closed.copy(), cv2.RETR_EXTERNAL,cv2.CHAIN_APPROX_SIMPLE)
        c = sorted(cnts, key = cv2.contourArea, reverse = True)[0]

        rect = cv2.minAreaRect(c)
        box=np.int0(cv2.boxPoints(rect))
        #在图片上标出ROI区域并保存
        cv2.drawContours(image, [box], -1, (0, 255, 0), 3)
        cv2.imwrite('E:/business/recognition/BaiDuApi/imgQRCodeContour/{}.jpg'.format(str(j)),image)
        j=j+1

        box = str(np.int0(cv2.boxPoints(rect)))

        # return box
        shape1=str(shape)
        resultSet.append(shape1)
        resultSet.append(box)
    return resultSet

        #cv2.drawContours(image, [box], -1, (0, 255, 0), 3)
        #cv2.imwrite("final.jpg",image)

if __name__ == '__main__':
    a = []
    for i in range(1, len(sys.argv)):
        a.append(sys.argv[i])
    # print(DetectQrCode(a[0]))
    imgDir=a[0]
    resultSet=DetectQrCode(imgDir)
    length=len(resultSet)
    for i in range(length):
        print(resultSet[i]+'+')