#coding:utf-8
# import the necessary packages
from keras.preprocessing.image import img_to_array
from keras.models import load_model
import numpy as np
import argparse
import imutils
import cv2
import sys

norm_size = 64

def args_parse():
# construct the argument parse and parse the arguments
    ap = argparse.ArgumentParser()
    ap.add_argument("-m", "--model", required=True,
        help="path to trained model model")
    ap.add_argument("-i", "--image", required=True,
        help="path to input image")
    ap.add_argument("-s", "--show", action="store_true",
        help="show predict image",default=False)
    args = vars(ap.parse_args())    
    return args

def predict(a):
    # load the trained convolutional neural network
    #print("[INFO] loading network...")
    model = load_model(a[1])
    #load the image
    image = cv2.imread(a[0])
    orig = image.copy()
     
    # pre-process the image for classification
    image = cv2.resize(image, (norm_size, norm_size))
    image = image.astype("float") / 255.0
    image = img_to_array(image)
    image = np.expand_dims(image, axis=0)
     
    # classify the input image
    result = model.predict(image)[0]
    #print (result.shape)
    proba = np.max(result)
    label = str(np.where(result==proba)[0])
    label = "{}: {:.2f}%".format(label, proba * 100)
    return label
    
    # if a[2]:   
    #     # draw the label on the image
    #     output = imutils.resize(orig, width=400)
    #     cv2.putText(output, label, (10, 25),cv2.FONT_HERSHEY_SIMPLEX,
    #         0.7, (0, 255, 0), 2)       
    #     # show the output image
    #     cv2.imshow("Output", output)
    #     cv2.waitKey(0)

#python predict.py --model invoice.model -i ../10.jpg -s
#a元组当中第一个参数a[0]为图片地址，a[1]为模型地址
if __name__ == '__main__':
    print('start')
    # a = []
    # imgPath='E://business//recognition//InvoiceClassification//Keras-image-classifer-framework//invoice-code//2.jpg'
    # a.append(imgPath)
    a = []
    for i in range(1, len(sys.argv)):
        a.append(sys.argv[i])
    #args = args_parse()
    #print(a[0])
    model='invoice.model'
    a.append(model)
    #print(a[1])
    print(predict(a))