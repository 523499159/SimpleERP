package com.pojo;
		/**
		 * 
		 * @author SiVan
		 * @time 2017��4��24�� ����1:02:17
		 * TODO	��Ӧ����
		 */
public class Suppplier {

	private String SupplierId;
	

	private String SupplierName;
	private String SupplierLinkMan;
	private String SupplierPhone;
	private String SupplierWeChat;
	private String SupplierAddress;
	private String SupplierBankAc;
	public String getSupplierWeChat() {
		return SupplierWeChat;
	}
	public void setSupplierWeChat(String supplierWeChat) {
		SupplierWeChat = supplierWeChat;
	}
	public String getSupplierAddress() {
		return SupplierAddress;
	}
	public void setSupplierAddress(String supplierAddress) {
		SupplierAddress = supplierAddress;
	}
	public String getSupplierBankAc() {
		return SupplierBankAc;
	}
	public void setSupplierBankAc(String supplierBankAc) {
		SupplierBankAc = supplierBankAc;
	}
	public String getSupplierId() {
		return SupplierId;
	}
	public void setSupplierId(String supplierId) {
		SupplierId = supplierId;
	}
	public String getSupplierName() {
		return SupplierName;
	}
	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
	}
	public String getSupplierLinkMan() {
		return SupplierLinkMan;
	}
	public void setSupplierLinkMan(String supplierLinkMan) {
		SupplierLinkMan = supplierLinkMan;
	}
	public String getSupplierPhone() {
		return SupplierPhone;
	}
	public void setSupplierPhone(String supplierPhone) {
		SupplierPhone = supplierPhone;
	}
	
	@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.SupplierName;
		}
}
