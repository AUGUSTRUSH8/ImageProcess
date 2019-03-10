import cv2
import numpy as np
def main():
    imgOrigion = cv2.imread('test3.jpg');
    imgGray=cv2.cvtColor(imgOrigion,cv2.COLOR_BGR2GRAY)
    imgCanny=cv2.Canny(imgGray,50,150,apertureSize = 3)
    cv2.imshow("Contour detection", imgCanny)


    minLineLength = 100
    maxLineGap = 10
    lines = cv2.HoughLinesP(imgCanny,1,np.pi/180,100,minLineLength,maxLineGap)
    for x1,y1,x2,y2 in lines[0]:
        cv2.line(imgOrigion,(x1,y1),(x2,y2),(0,255,0),2)


    cv2.imwrite('houghlines5.jpg',imgOrigion)



    
    cv2.waitKey()
    cv2.destroyAllWindows()

if __name__ == "__main__":
    main()