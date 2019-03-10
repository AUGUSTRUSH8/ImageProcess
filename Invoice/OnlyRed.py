import cv2
import numpy as np
img=cv2.imread("E:/business/recognition/Invoice/1.jpg")
HSV = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)  #RGB 转为 HSV
Gray=cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)  #RGB 转为 gray
H, S, V = cv2.split(HSV)    #分离 HSV 三通道
Lowerred0 = np.array([168,43,35])
Upperred0 = np.array([173,255,255])
mask1 = cv2.inRange(HSV, Lowerred0, Upperred0) 
Lowerred1 = np.array([0,43,35])
Upperred1 = np.array([11,255,255])
mask2 = cv2.inRange(HSV, Lowerred1, Upperred1)    #将红色区域部分归为全白，其他区域归为全黑
Apple = mask1 +mask2
Apple1=mask2
Apple2=mask1
cv2.imwrite("red1.jpg",Apple1)
cv2.imwrite("red2.jpg",Apple2)
# cv2.imshow("apple", Apple)
cv2.imwrite("red.jpg",Apple)
cv2.waitKey(0)