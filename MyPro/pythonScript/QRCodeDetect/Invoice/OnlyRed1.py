import cv2  
import numpy

img=cv2.imread("E:/business/recognition/Invoice/1.jpg")

b, g, r = cv2.split(img)
cv2.imshow("Blue 1", b)
cv2.imshow("Green 1", g)
cv2.imshow("Red 1", r)
cv2.waitKey(0);