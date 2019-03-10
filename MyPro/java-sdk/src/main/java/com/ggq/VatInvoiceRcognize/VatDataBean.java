package com.ggq.VatInvoiceRcognize;

/**
 * @author AUGUSTRUSH
 * 存放增值税发票识别信息的基础类
 */
public class VatDataBean {
	String AmountInWords;
	String SellerAddress;
	String NoteDrawer;
	String TotalTax;
	String CheckCode;
	String InvoiceCode;
	String InvoiceDate;
	String Checker;
	String TotalAmount;
	String PurchaserName;
	String InvoiceType;
	String Payee;
	String SellerName;
	String CommodityName;//商品名称
	public String getAmountInWords() {
		return AmountInWords;
	}
	public void setAmountInWords(String amountInWords) {
		AmountInWords = amountInWords;
	}
	public String getSellerAddress() {
		return SellerAddress;
	}
	public void setSellerAddress(String sellerAddress) {
		SellerAddress = sellerAddress;
	}
	public String getNoteDrawer() {
		return NoteDrawer;
	}
	public void setNoteDrawer(String noteDrawer) {
		NoteDrawer = noteDrawer;
	}
	public String getTotalTax() {
		return TotalTax;
	}
	public void setTotalTax(String totalTax) {
		TotalTax = totalTax;
	}
	public String getCheckCode() {
		return CheckCode;
	}
	public void setCheckCode(String checkCode) {
		CheckCode = checkCode;
	}
	public String getInvoiceCode() {
		return InvoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		InvoiceCode = invoiceCode;
	}
	public String getInvoiceDate() {
		return InvoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		InvoiceDate = invoiceDate;
	}
	public String getChecker() {
		return Checker;
	}
	public void setChecker(String checker) {
		Checker = checker;
	}
	public String getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		TotalAmount = totalAmount;
	}
	public String getPurchaserName() {
		return PurchaserName;
	}
	public void setPurchaserName(String purchaserName) {
		PurchaserName = purchaserName;
	}
	public String getInvoiceType() {
		return InvoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		InvoiceType = invoiceType;
	}
	public String getPayee() {
		return Payee;
	}
	public void setPayee(String payee) {
		Payee = payee;
	}
	public String getSellerName() {
		return SellerName;
	}
	public void setSellerName(String sellerName) {
		SellerName = sellerName;
	}
	public String getCommodityName() {
		return CommodityName;
	}
	public void setCommodityName(String commodityName) {
		CommodityName = commodityName;
	}
	
	
	
}
