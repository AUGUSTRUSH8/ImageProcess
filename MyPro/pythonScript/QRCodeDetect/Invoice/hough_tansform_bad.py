# -*- coding: utf-8 -*-
import cv2
import numpy as np
from matplotlib import pyplot as plt
import math

def rotate_about_center2(src, radian, scale=1.):
    #入参：弧度
    w = src.shape[1]
    h = src.shape[0]
    angle = radian * 180 / np.pi
    # now calculate new image width and height
    nw = (abs(np.sin(radian)*h) + abs(np.cos(radian)*w))*scale
    nh = (abs(np.cos(radian)*h) + abs(np.sin(radian)*w))*scale
    # ask OpenCV for the rotation matrix
    rot_mat = cv2.getRotationMatrix2D((nw*0.5, nh*0.5), angle, scale)
    # calculate the move from the old center to the new center combined
    # with the rotation
    rot_move = np.dot(rot_mat, np.array([(nw-w)*0.5, (nh-h)*0.5,0]))
    # the move only affects the translation, so update the translation
    # part of the transform
    rot_mat[0,2] += rot_move[0]
    rot_mat[1,2] += rot_move[1]
    return cv2.warpAffine(src, rot_mat, (int(math.ceil(nw)), int(math.ceil(nh))), flags=cv2.INTER_LANCZOS4)

def get_group(arr):
    #按照4个弧度区间分组，返回不为空的分组数据
    radian_45 = np.pi/4
    radian_90 = np.pi/2
    radian_135 = radian_45 * 3
    radian_180 = np.pi
    ret_arr = [[],[],[],[]]
    for i in range(len(arr)):
        if arr[i] < radian_45:
            ret_arr[0].append(arr[i])
        elif arr[i] < radian_90:
            ret_arr[1].append(arr[i])
        elif arr[i] < radian_135:
            ret_arr[2].append(arr[i])
        else:
            ret_arr[3].append(arr[i])
            
    while [] in ret_arr:
        ret_arr.remove([])
    
    #print ret_arr
    return ret_arr
    
def get_min_var_avg(arr):
    #按照不同弧度区间分组，返回方差最小的一个分组的弧度平均值
    group_arr = get_group(arr)
    print(group_arr)
    cv2.waitKey(0)
    var_arr = []
    if len(group_arr) <= 1:
        var_arr.append(np.var(group_arr[0]))
        print(var_arr)
        cv2.waitKey(0)

    else:
        for i in range(len(group_arr)):
            var_arr.append(np.var(group_arr[i]))

    print(var_arr)
    min_var = 10000
    min_i = 0
    for i in range(len(var_arr)):
        if var_arr[i] < min_var:
            min_var = var_arr[i]
            min_i = i
    #print min_var, i
    avg = np.mean(group_arr[min_i])
    return avg

def get_rotate_radian(radian, reverse = False):
    #旋转弧度转换
    radian_45 = np.pi/4
    radian_90 = np.pi/2
    radian_135 = radian_45 * 3
    radian_180 = np.pi
    ret_radian = 0
    if radian < radian_45:
        ret_radian = radian
    elif radian < radian_90:
        ret_radian = radian - radian_90
    elif radian < radian_135:
        ret_radian = radian - radian_90
    else:
        ret_radian = radian - radian_180
        
    if reverse:
        ret_radian += radian_90
    print(ret_radian)
    return ret_radian

def rotate():
    image = cv2.imread("test3.jpg", 0)
    
    print(image.shape)
    
    #高斯模糊
    blur = cv2.GaussianBlur(image,(7,7),0)#自己调整，经验数据
    cv2.imshow('image',blur)
    cv2.waitKey(0)
    #Canny边缘检测
    canny = cv2.Canny(blur, 20, 150, 3)
    cv2.imshow("canny",canny)
    lines = cv2.HoughLines(canny, 1, np.pi/180, 200)#自己调整，经验数据
    
    #求平均弧度
    l = len(lines[0])
    print(l)
    
    theta_arr = [lines[0][i][1] for i in range(l)]
    print(theta_arr)
    cv2.waitKey(0)
    rotate_theta = get_min_var_avg(theta_arr)
    print(rotate_theta)
    
    #print lines

    '''for line in lines[0]:
        rho = line[0]
        theta = line[1]
        
        a = np.cos(theta)
        b = np.sin(theta)
        x0 = a*rho
        y0 = b*rho
        cv2.line(image, (int(x0 - 1000*b), int(y0 + 1000*a)), (int(x0 + 1000*b), int(y0 - 1000*a)), (0,255,0), 2)
        #cv2.imshow('image',image)
        #cv2.waitKey(0)'''
    
    img2 = rotate_about_center2(image, get_rotate_radian(rotate_theta, image.shape[0] > image.shape[1])) # hight > width

    plt.imshow(img2)
    plt.show()

if __name__ == '__main__':
    rotate()