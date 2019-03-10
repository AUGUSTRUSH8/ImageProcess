package qrcode;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Detector {
	Mat rotateMat,transMat,intricMat,transMat2;
	int startRowToDisplay,endRowToDisplay,startColToDisplay,endColToDisplay,AveRow,AveCol;
    int[] rowsTo,colsTo;
    Mat src;
    List<Point> finalpoint=new ArrayList<Point>();
	public Detector() {
		
		    initMat();
		
	}
	
	Double[][] testAction1(Mat src){
		Double[][] ret=new Double[3][2];
		Mat backg=src.clone();
		Mat imagePoint = getImagePoint(src);
		float[] worldPoint=calWorldPoint(imagePoint);
		for(int i=0;i<finalpoint.size();i++) {
			ret[i][0]=finalpoint.get(i).x;
			ret[i][1]=finalpoint.get(i).y;
		}
		return ret;
	}
	
	Mat testAction(Mat src) {
		Mat backg=src.clone();
//	    if(testParam->do1){

	    Mat imagePoint = getImagePoint(src);
        float[] worldPoint=calWorldPoint(imagePoint);
  
	        /*int[] rowLineAve =new int[4];
	        int[] colLineAve=new int[4];
	        int[][] lines=new int[2][4];
	        rowLineAve[1]=rowLineAve[3]=AveRow;
	        rowLineAve[0]=0;
	        rowLineAve[2]=src.cols()-1;
	        colLineAve[0]=colLineAve[2]=AveCol;
	        colLineAve[1]=0;
	        colLineAve[3]=src.rows()-1;
	        lines[0]=rowLineAve;
	        lines[1]=colLineAve;
	        backg = drawLines(src,lines,new Scalar(0,0,255));*/
         for(int i=0;i<finalpoint.size();i++) {
        	 Imgproc.circle(backg, new Point(finalpoint.get(i).x,finalpoint.get(i).y), 10, new Scalar(0,0,255),2);
         }
         
	    return backg;
	}
	void initMat() {
		
		  rotateMat = new Mat(3,3,CvType.CV_32FC1);
		  rotateMat.put(0, 0, 0.9983f);
		  rotateMat.put(0, 1, -0.0447f);
		  rotateMat.put(0, 2, -0.0385f);
		  rotateMat.put(1, 0, -0.0432f);
		  rotateMat.put(1, 1, 0.9983f);
		  rotateMat.put(1, 2, -0.0399f);
		  rotateMat.put(2, 0, 0.0403f);
		  rotateMat.put(2, 1, 0.0382f);
		  rotateMat.put(2, 2, 0.9985f);
          intricMat =new Mat(3,3,CvType.CV_32FC1);
          intricMat.put(0, 0, 5126.6f);
          intricMat.put(0, 1, 0f);
          intricMat.put(0, 2, 0f);
          intricMat.put(1, 0, 0f);
          intricMat.put(1, 1, 5127.2f);
          intricMat.put(1, 2, 0f);
          intricMat.put(2, 0, 1017.7f);
          intricMat.put(2, 1, 841.9158f);
          intricMat.put(2, 2, 1f);
          transMat =new Mat(1,3,CvType.CV_32FC1);
          transMat.put(0, 0, -8.4226f);
          transMat.put(0, 1, -36.8412f);
          transMat.put(0, 2, 628.8406f);
          transMat2=new Mat(1,3,CvType.CV_32FC1);
          transMat2.put(0, 0, 8.4226f);
          transMat2.put(0, 1, 36.8412f);
          transMat2.put(0, 2, -628.8406f);
	}
    Mat getImagePoint(Mat src) {
    	Mat graySrc =new Mat();
        if(src.channels()>1){
           Imgproc.cvtColor(src,graySrc,Imgproc.COLOR_BGR2GRAY); //转为灰度图
           Imgproc.GaussianBlur(graySrc, graySrc, new Size(3,3), 0,0);
        }else{
            graySrc = src;
        }
        graySrc.convertTo(graySrc,CvType.CV_8UC1);//更换数据类型

        Mat binarySrc=new Mat();

        int blockSize = src.rows()/8;
        if(blockSize%2==0){
            blockSize++;
        }
        //Imgproc.threshold(graySrc, binarySrc, 0, 150, Imgproc.THRESH_OTSU);
       Imgproc.adaptiveThreshold(graySrc,binarySrc,255,Imgproc.ADAPTIVE_THRESH_MEAN_C,Imgproc.THRESH_BINARY,blockSize,5);
        
        ImageViewer ima=new ImageViewer(binarySrc ,"二值输出图片");
	    ima.imshow();
        //二值化的图为binarySrc

        float centerRow = centerRecognize(binarySrc,true);//调用centerRecognize函数
        float centerCol = centerRecognize(binarySrc,false);


        Mat imPoint=new Mat(1,3,CvType.CV_32FC1);
        imPoint.put(0, 0, centerCol);
        imPoint.put(0, 1, centerRow);
        imPoint.put(0, 2, 1.0);
       


        if(src.channels()>1){
            src = binarySrc;
            Imgproc.cvtColor(src,src,Imgproc.COLOR_GRAY2BGR);
        }else{
            src = binarySrc;
        }

        return imPoint;
    }
    float centerRecognize(Mat src1,boolean rowORcol) {
    	FinderPatternFinder  finder=new FinderPatternFinder(src1);
        finder.find();//调用finde对象的find函数

         List<FinderPattern> pattern = finder.possibleCenters;
    
        int centerCount=0;
        int bestOne=0;
        int centerRow=0;
        int centerCol=0;
        if(pattern.isEmpty()){
        }else{
        	int[] countlist=new int [pattern.size()];
         	for(int l=0;l<pattern.size();l++) {
        		countlist[l]=pattern.get(l).count;
        	}
         	FinderPattern temp;
        	int length=countlist.length;
            for(int i=length-1;i>0;i--){//从可能的点中取出前三个
            	if(pattern.get(i).count>centerCount){
                    bestOne=i;
                    centerCount = pattern.get(i).count;
                 }
            	
                for(int j=length-1;j>length-i-1;j--) {
                	if(pattern.get(j-1).count<pattern.get(j).count) {
                		temp=pattern.get(j-1);
                		pattern.set(j-1, pattern.get(j));
                		//pattern.get(j-1)=pattern.get(j);
                		pattern.set(j, temp);
                	}
                }
	
            }
            for(int i=0;i<3;i++) {//去除最大的三个点
            	finalpoint.add( new Point(pattern.get(i).getX(),pattern.get(i).getY()));
            	System.out.println("di"+i+"wei"+pattern.get(i).count);
            }
            centerRow=(int) (pattern.get(bestOne).getY());
            centerCol=(int) (pattern.get(bestOne).getX());
        }

        AveRow = centerRow;
        AveCol = centerCol;

        if(rowORcol){
            return centerRow;
        }else{
            return centerCol;
        }
        

    }
    float[] calWorldPoint(Mat imagePointWith1) {
    

       Mat a=imagePointWith1.clone();
       Mat c=intricMat.inv().clone();
    		  // imagePointWith1*(intricMat.inv());// 1*3
    	Mat A=new Mat(a.rows(),c.cols(),CvType.CV_32FC1);
		double p = 0;
		for(int i=0;i<a.rows();i++) {
			for(int j=0;j<c.cols();j++) {
				for(int k=0;k<a.cols();k++) {
				 p+=(a.get(i, k)[0])*(c.get(k, j)[0]);
				 
				}
				A.put(i, j, p);
			}
		}

         Mat E = rotateMat;
         E.put(2, 0, A.get(0, 0)[0]);
         E.put(2, 1, A.get(0, 1)[0]);
         E.put(2, 2, A.get(0, 2)[0]);
       /* E.at<float>(2,0)=A.at<float>(0,0);
        E.at<float>(2,1)=A.at<float>(0,1);
        E.at<float>(2,2)=A.at<float>(0,2);*/
           Mat e=E.inv().clone();
           Mat tra=transMat2.clone();
           Mat D=new Mat(tra.rows(),e.cols(),CvType.CV_32FC1);
           double p2 = 0;
   		   for(int i=0;i<tra.rows();i++) {
   			for(int j=0;j<e.cols();j++) {
   				for(int k=0;k<tra.cols();k++) {
   				 p2+=(tra.get(i, k)[0])*(e.get(k, j)[0]);
   				 
   				}
   				D.put(i, j, p2);
   			}
   		}
           
       
        float[] worldPoint=new float[2];
     
        worldPoint[0] = (float)(D.get(0, 0)[0]);
        worldPoint[1] = (float)(D.get(0, 1)[0]);

        return worldPoint;
    }
    
    Mat drawLines(Mat backg, int[][]lines,Scalar color)
    {
       // cv::Mat temp;
            int width = (backg.rows()+backg.cols())/2/350;
            for( int i = 0; i < lines.length; i++ )
            {
              int[] l = lines[i];
              Imgproc.line( backg, new Point(l[0], l[1]), new Point(l[2], l[3]), color, width);
            }
        return backg;
    }
}