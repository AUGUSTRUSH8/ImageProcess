package com.ggq.VatInvoiceRcognize;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;

import org.json.JSONObject;

import com.Integrate.Test.DirectoryEnum;
import com.baidu.aip.ocr.AipOcr;
enum keywords{AmountInWords,SellerAddress,NoteDrawer,TotalTax,CheckCode,InvoiceCode,InvoiceDate,
	Checker,TotalAmount,PurchaserName,InvoiceType,Payee,SellerName,CommodityName};
public class BatchReadAndRecognize {

	public static void main(String[] args) throws IOException {
		AipOcr aipOcr=new AipOcr("10728591", "k1qEDIj16cfpEQU1FUGYEXIG", "NGZqqvWlaoS8Ydqfz0EtLYKu7ebkiQMW");
		String VatVoicePictureDir=DirectoryEnum.finalVatInvoicePic.toString();
		//System.out.println(VatVoicePictureDir);
		File folders = new File(VatVoicePictureDir);//classPath为文件路径 
		File[] files = folders.listFiles(new FilenameFilter() {
			//获取.jpg文件时使用listFiles(FilenameFilter filter)方法，创建一个过滤文件名的Filter
			    @Override
			    public boolean accept(File dir, String name) {
			        if(name != null) {
			        //检测文件名是否是以.jpg结尾，是返回true，否则继续检测下一个文件
			            if(name.toLowerCase().endsWith(".jpg")) {
			                return true;
			            }
			        }
			        return false;
			    }
			});
		for(File file:files) {
			System.out.println(file);
		}
		List<String> pictureName=pictureNameTransform(files);
		List<VatDataBean> VatBeanList=new ArrayList<VatDataBean>();
		for(int i=0;i<pictureName.size();i++) {
			System.out.println(pictureName.get(i));
			byte[] image=null;
			JSONObject jsonObject=new JSONObject();
			image=image2byte(pictureName.get(i));
			jsonObject=aipOcr.vatInvoice(image, null);
			JSONObject jsonObject2=jsonObject.getJSONObject("words_result");
			Iterator it = jsonObject2.keys();
			VatDataBean vatDataBean=new VatDataBean();
			while(it.hasNext()){
				String key=(String)it.next();
				Object value=jsonObject2.get(key.toString());
				//System.out.println(key+": "+value);
				if(key.equals("AmountInWords")) {
					String AmountInWordsValue=value.toString();
					vatDataBean.setAmountInWords(AmountInWordsValue);
				}else if(key.equals("SellerAddress")) {
					String SellerAddressValue=value.toString();
					vatDataBean.setSellerAddress(SellerAddressValue);
				}else if (key.equals("NoteDrawer")) {
					String NoteDrawerValue=value.toString();
					vatDataBean.setNoteDrawer(NoteDrawerValue);
				}else if (key.equals("TotalTax")) {
					String TotalTaxValue=value.toString();
					vatDataBean.setTotalTax(TotalTaxValue);
				}else if (key.equals("CheckCode")) {
					String CheckCodeValue=value.toString();
					vatDataBean.setCheckCode(CheckCodeValue);
				}else if (key.equals("InvoiceCode")) {
					String InvoiceCodeValue=value.toString();
					vatDataBean.setInvoiceCode(InvoiceCodeValue);
				}else if (key.equals("InvoiceDate")) {
					String InvoiceDateValue=value.toString();
					vatDataBean.setInvoiceDate(InvoiceDateValue);
				}else if (key.equals("Checker")) {
					String CheckerValue=value.toString();
					vatDataBean.setChecker(CheckerValue);
				}else if (key.equals("TotalAmount")) {
					String TotalAmountValue=value.toString();
					vatDataBean.setTotalAmount(TotalAmountValue);
				}else if (key.equals("PurchaserName")) {
					String PurchaserNameValue=value.toString();
					vatDataBean.setPurchaserName(PurchaserNameValue);
				}else if (key.equals("InvoiceType")) {
					String InvoiceTypeValue=value.toString();
					vatDataBean.setInvoiceType(InvoiceTypeValue);
				}else if (key.equals("Payee")) {
					String PayeeValue=value.toString();
					vatDataBean.setPayee(PayeeValue);
				}else if (key.equals("SellerName")) {
					String SellerNameValue=value.toString();
					vatDataBean.setSellerName(SellerNameValue);
				}else if (key.equals("CommodityName")) {
					String CommodityNameValue=value.toString();
					vatDataBean.setCommodityName(CommodityNameValue);
				}
				
				
			}
			VatBeanList.add(vatDataBean);
			System.out.println(vatDataBean);
			
		}
		String FilePath="E:/business/recognition/BaiDuApi/ExcelResult/003/VatInvoice.xlsx";
		String[] excelTitle= {"AmountInWords","SellerAddress","NoteDrawer",
				"TotalTax","CheckCode","InvoiceCode","InvoiceDate","Checker","TotalAmount",
				"PurchaserName","InvoiceType","Payee","SellerName","CommodityName"};
		String sheetName="003";
		WriteToExcel writeToExcel=new WriteToExcel();
		writeToExcel.writetoExcel(FilePath, excelTitle, VatBeanList, sheetName);
		System.out.println("All done!");
	}
	
	public static List<String> pictureNameTransform(File[] files){
		List<String> absolutePictureName=new ArrayList<String>();
		for(File file:files) {
			absolutePictureName.add(file.toString().replace("\\", "/"));
		}
		return absolutePictureName;
	}
	
	
	//图像转byte数组
	public static byte[] image2byte(String path){
		 byte[] data = null;
		 FileImageInputStream input = null;
		 try {
			 //图片输入
			 input = new FileImageInputStream(new File(path));
			 ByteArrayOutputStream output = new ByteArrayOutputStream();
			 byte[] buf = new byte[1024];
			 int numBytesRead = 0;
			 while ((numBytesRead = input.read(buf)) != -1) {
			      output.write(buf, 0, numBytesRead);
			      }
			      data = output.toByteArray();
			      output.close();
			      input.close();
			  
		}  catch (FileNotFoundException ex1) {
		      ex1.printStackTrace();
	    }
	    catch (IOException ex1) {
	      ex1.printStackTrace();
	    }
		return data;
	}

}
