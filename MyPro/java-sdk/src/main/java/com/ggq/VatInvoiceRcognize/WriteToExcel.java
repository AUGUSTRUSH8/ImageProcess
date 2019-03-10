package com.ggq.VatInvoiceRcognize;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author AUGUSTRUSH
 * 把百度OCR识别到的文字信息结构化存储至excel文件当中去
 */
public class WriteToExcel {
	public void writetoExcel(String filePath,String[] excelTitle,List<VatDataBean> data,String sheetName) throws IOException {
		System.out.println("开始写入文件>>>>>>>>>>>>");
		Workbook workbook = null;
		if (filePath.toLowerCase().endsWith("xls")) {//2003
			workbook = new XSSFWorkbook();
		}
		else if(filePath.toLowerCase().endsWith("xlsx")){//2007
			workbook = new HSSFWorkbook();
		}
		else{
			System.out.println("The output file sufix is incorrect!");
			throw new IllegalArgumentException();//这里处理传入的文件后缀不对的
//			logger.debug("invalid file name,should be xls or xlsx");
		}
		//create sheet
		Sheet sheet = workbook.createSheet(sheetName);
		int rowIndex = 0;//标识位，用于标识sheet的行号
		//遍历数据集，将其写入excel中
		try {
			//写表头数据
			Row titleRow = sheet.createRow(rowIndex);
			for (int i = 0; i < excelTitle.length; i++) {
				System.out.println(excelTitle[i]);
				//创建表头单元格,填值
				titleRow.createCell(i).setCellValue(excelTitle[i]);
			}
			System.out.println("表头写入完成>>>>>>>>");
			rowIndex++;
			//循环写入主表数据
			for(Iterator<VatDataBean> VatIter=data.iterator();VatIter.hasNext();) {
				VatDataBean vatDataBean=VatIter.next();
				//create sheet row
				Row row = sheet.createRow(rowIndex);
				//create sheet coluum(单元格)
				Cell cell0 = row.createCell(0);
				if(vatDataBean.getAmountInWords()==null||vatDataBean.getAmountInWords()=="") {
					cell0.setCellValue("null");
				}else {
					cell0.setCellValue(vatDataBean.getAmountInWords());
				}
				
				Cell cell1 = row.createCell(1);
				if(vatDataBean.getSellerAddress()==null||vatDataBean.getSellerAddress()=="") {
					cell1.setCellValue("null");
				}else {
					cell1.setCellValue(vatDataBean.getSellerAddress());
				}
				
				Cell cell2=row.createCell(2);
				if(vatDataBean.getNoteDrawer()==null||vatDataBean.getNoteDrawer()=="") {
					cell2.setCellValue("null");
				}else {
					cell2.setCellValue(vatDataBean.getNoteDrawer());
				}
				
				Cell cell3=row.createCell(3);
				if(vatDataBean.getTotalTax()==null||vatDataBean.getTotalTax()=="") {
					cell3.setCellValue("null");
				}else {
					cell3.setCellValue(vatDataBean.getTotalTax());
				}
				
				Cell cell4=row.createCell(4);
				if (vatDataBean.getCheckCode()==null||vatDataBean.getCheckCode()=="") {
					cell4.setCellValue("null");
				}else {
					cell4.setCellValue(vatDataBean.getCheckCode());
				}
				
				Cell cell5=row.createCell(5);
				if(vatDataBean.getInvoiceCode()==null||vatDataBean.getInvoiceCode()=="") {
					cell5.setCellValue("null");
				}else {
					cell5.setCellValue(vatDataBean.getInvoiceCode());
				}
				
				Cell cell6=row.createCell(6);
				if(vatDataBean.getInvoiceDate()==null||vatDataBean.getInvoiceDate()=="") {
					cell6.setCellValue("null");
				}else {
					cell6.setCellValue(vatDataBean.getInvoiceDate());
				}
				
				Cell cell7=row.createCell(7);
				if (vatDataBean.getChecker()==null||vatDataBean.getChecker()=="") {
					cell7.setCellValue("null");
				}else {
					cell7.setCellValue(vatDataBean.getChecker());
				}
				
				Cell cell8=row.createCell(8);
				if (vatDataBean.getTotalAmount()==null||vatDataBean.getTotalAmount()=="") {
					cell8.setCellValue("null");
				}else {
					cell8.setCellValue(vatDataBean.getTotalAmount());
				}
				
				Cell cell9=row.createCell(9);
				if (vatDataBean.getPurchaserName()==null||vatDataBean.getPurchaserName()=="") {
					cell9.setCellValue("null");
				}else {
					cell9.setCellValue(vatDataBean.getPurchaserName());
				}
				
				Cell cell10=row.createCell(10);
				if (vatDataBean.getInvoiceType()==null||vatDataBean.getInvoiceType()=="") {
					cell10.setCellValue("null");
				}else {
					cell10.setCellValue(vatDataBean.getInvoiceType());
				}
				
				Cell cell11=row.createCell(11);
				if (vatDataBean.getPayee()==null||vatDataBean.getPayee()=="") {
					cell11.setCellValue("null");
				}else {
					cell11.setCellValue(vatDataBean.getPayee());
					System.out.println(vatDataBean.getPayee());
				}
				
				Cell cell12=row.createCell(12);
				if (vatDataBean.getSellerName()==null||vatDataBean.getSellerName()=="") {
					cell12.setCellValue("null");
				}else {
					cell12.setCellValue(vatDataBean.getSellerName());
				}
				
				Cell cell13=row.createCell(13);
				if (vatDataBean.getCommodityName()==null||vatDataBean.getCommodityName()=="") {
					cell13.setCellValue("null");
				}else {
					cell13.setCellValue(vatDataBean.getCommodityName());
				}
				rowIndex++;
			}
			System.out.println("主表数据写入完成>>>>>>>>");
			FileOutputStream fos = new FileOutputStream(filePath);
			workbook.write(fos);
			fos.close();
			workbook.close();
			System.out.println(filePath + "写入文件成功>>>>>>>>>>>");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
