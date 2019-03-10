import numpy as np
import cv2
import argparse
# 蓝色的范围，不同光照条件下不一样，可灵活调整
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

cv2.imshow('image', image)
cv2.imshow('mask', mask)
cv2.imshow('res', res)
cv2.imwrite('blue.jpg',res)

cv2.waitKey(0)