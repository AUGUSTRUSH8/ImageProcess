import numpy as np
import argparse
import cv2
lower_blue = np.array([90, 90, 90])
upper_blue = np.array([130, 255, 255])

ap=argparse.ArgumentParser()
ap.add_argument("-i", "--image", required = True, help = "path to the image file")
args = vars(ap.parse_args())

image = cv2.imread(args["image"])
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

cv2.imshow("gradient",gradient)
#原本没有过滤颜色通道的时候，这个高斯模糊有效，但是如果进行了颜色过滤，不用高斯模糊效果更好
#blurred = cv2.blur(gradient, (9, 9))
(_, thresh) = cv2.threshold(gradient, 225, 255, cv2.THRESH_BINARY)
cv2.imshow("thresh",thresh)
cv2.imwrite('thresh.jpg',thresh)

kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (21, 21))
closed = cv2.morphologyEx(thresh, cv2.MORPH_CLOSE, kernel)
cv2.imshow("closed",closed)
cv2.imwrite('closed.jpg',closed)

closed = cv2.erode(closed, None, iterations = 4)
closed = cv2.dilate(closed, None, iterations = 4)
cv2.imwrite('closed1.jpg',closed)

img,cnts, _ = cv2.findContours(closed.copy(), cv2.RETR_EXTERNAL,cv2.CHAIN_APPROX_SIMPLE)
c = sorted(cnts, key = cv2.contourArea, reverse = True)[0]

rect = cv2.minAreaRect(c)
box = np.int0(cv2.boxPoints(rect))
print(box)
with open("coordinates.txt","w") as f:
    f.write(str(box))

cv2.drawContours(image, [box], -1, (0, 255, 0), 3)
cv2.imwrite("final.jpg",image)
cv2.imshow("Image", image)

cv2.waitKey(0)