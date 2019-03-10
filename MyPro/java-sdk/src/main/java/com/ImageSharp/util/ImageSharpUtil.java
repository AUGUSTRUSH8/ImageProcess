package com.ImageSharp.util;

import org.opencv.core.Mat;

public class ImageSharpUtil {
	private static int standardizeCellSize(int cellSize) {
        return cellSize % 2 == 0 ? cellSize + 1 : cellSize;
    }
	
	/**
	 * 图像均值滤波
	 * @param mat
	 * @param cellSize
	 * @return
	 */
	public static Mat averageFiltering(Mat mat, int cellSize) {
        //TODO 性能优化
        Mat ret = new Mat(mat.height(), mat.width(), mat.type());
        double[][][] mm = new double[mat.height()][mat.width()][mat.get(0, 0).length];
        for (int i = 0; i < mat.height(); i++) {
            for (int j = 0; j < mat.width(); j++) {
                mm[i][j] = mat.get(i, j);
            }
        }
        cellSize = standardizeCellSize(cellSize);
        int halfCellSiz = cellSize >> 1;
        for (int i = 0; i < mat.height(); i++) {
            for (int j = 0; j < mat.width(); j++) {
                double[] to = new double[3];
                int fi = i - halfCellSiz, ti = i + halfCellSiz, fj = j - halfCellSiz, tj = j + halfCellSiz;
                for (int k = 0; k < to.length; k++) {
                    double sum = 0;
                    int cnt = 0;
                    for (int l = fi; l <= ti; l++) {
                        for (int m = fj; m <= tj; m++) {
                            if (l >= 0 && l < mat.height() && m >= 0 && m < mat.width()) {
                                sum += mm[l][m][k];
                                cnt++;
                            }
                        }
                    }
                    to[k] = sum / cnt;
                }
                ret.put(i, j, to);
            }
        }
        return ret;
    }
	
	/**
	 * 图像锐化
	 * @param mat
	 * @param cellSize
	 * @param factor
	 * @return
	 */
	public static Mat sharpen(Mat mat, int cellSize, int factor) {
        Mat filter = averageFiltering(mat, cellSize);
        Mat ret = new Mat(mat.height(), mat.width(), mat.type());
        for (int i = 0; i < ret.height(); i++) {
            for (int j = 0; j < ret.width(); j++) {
                double[] rgb = mat.get(i, j);
                double[] frgb = filter.get(i, j);
                for (int k = 0; k < rgb.length; k++) {
                    rgb[k] += factor * (rgb[k] - frgb[k]);
                }
                ret.put(i, j, rgb);
            }
        }
        return ret;
    }
	
	/*
     * 求图像的暗通道
     * */
     private static double[][] getDarkChannel(Mat mat, int cellSize) {
         if (cellSize % 2 == 0)
             cellSize++;
         int hcellSize = cellSize >>> 1;
         double[][] doubles = new double[mat.height()][mat.width()];
         for (int i = 0; i < mat.height(); i++) {
             for (int j = 0; j < mat.width(); j++) {
                 double[] vals = mat.get(i, j);
                 doubles[i][j] = vals[0];
                 for (int k = 1; k < vals.length; k++) {
                     doubles[i][j] = (doubles[i][j] < vals[k]) ? doubles[i][j] : vals[k];
                 }
             }
         }

         double[][] ret = new double[mat.height()][mat.width()];
         for (int i = 0; i < mat.height(); i++) {
             for (int j = 0; j < mat.width(); j++) {
                 double maxx = 300;
                 for (int k = 0; k < cellSize; k++) {
                     for (int l = 0; l < cellSize; l++) {
                         int locx = i + (k - hcellSize);
                         int locy = j + (l - hcellSize);
                         if (locx >= 0 && locx < ret.length && locy >= 0 && locy < ret[locx].length)
                             maxx = (maxx < doubles[locx][locy]) ? maxx : doubles[locx][locy];
                     }
                 }
                 ret[i][j] = maxx;
             }
         }
         return ret;
     }
	
	/**
	 * 图像去雾
	 * @param mat
	 * @param cellSize
	 * @return
	 */
	public static Mat disFog(Mat mat, int cellSize) {
    double[][] dackCha = getDarkChannel(mat, cellSize);
    Mat ret = new Mat(mat.height(), mat.width(), mat.type());

    for (int i = 0; i < ret.height(); i++) {
        for (int j = 0; j < ret.width(); j++) {
            double[] valsTo = new double[3];
            double[] valsFrom = mat.get(i, j);
            double f = dackCha[i][j] / 255;
            for (int k = 0; k < valsFrom.length; k++) {
                valsTo[k] = (valsFrom[k] - 127 * f) / (1 - f);
            }
            ret.put(i, j, valsTo);
        }
    }
    return ret;
}
}
