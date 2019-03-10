import cv2
import numpy as np
np.set_printoptions(threshold=np.inf)

#打印所有的颜色转换模式
flags = [i for i in dir(cv2) if i.startswith('COLOR_')]
print(flags)

#打印红色的HSV值
red = np.uint8([[[0, 0, 255]]])
hsv_red = cv2.cvtColor(red, cv2.COLOR_BGR2HSV)
print(hsv_red)

lower_red = np.array([0, 43, 35])
upper_red = np.array([20,255,255])

img=cv2.imread("E:/business/recognition/Invoice/1.jpg")
hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)

mask = cv2.inRange(hsv, lower_red, upper_red)
res = cv2.bitwise_and(img, img, mask=mask)

cv2.imshow('imgSource', img)
cv2.imshow('mask', mask)
cv2.imshow('res', res)
cv2.imwrite("res.jpg",res)
cv2.waitKey(0)
# image = cv2.imread('E:/business/recognition/Invoice/1.jpg')
# hue_image = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
# low_range = np.array([160, 70, 100])
# high_range = np.array([179, 255, 255])
# th = cv2.inRange(hue_image, low_range, high_range)
# index1 = th == 255
# img = np.zeros(image.shape, np.uint8)
# img[:, :] = (255,255,255)
# img[index1] = image[index1]
# cv2.imshow('img', img)
# cv2.imwrite('img.jpg',img)
# cv2.waitKey(0)