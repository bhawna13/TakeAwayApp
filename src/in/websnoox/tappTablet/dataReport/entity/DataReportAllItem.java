package in.websnoox.tappTablet.dataReport.entity;


public class DataReportAllItem {

	public String Time = "";
	public String OrderNo = "";
	public String VAT = "";
	public String Amount = "";
	public String PaymentType = "";
	public String Status = "";
	public String OrderType = "";
	public boolean isSelected=false; 
	public boolean doubleTabSelected= false;
	
	
	public boolean isSelected() { return isSelected; }
	public void setSelected(boolean isSelected) { this.isSelected = isSelected; }
	
	public String getTime() { return Time; }
	public void setTime(String time) { Time = time; }
	public String getOrderNo() { return OrderNo; }
	public void setOrderNo(String orderNo) { OrderNo = orderNo; }
	public String getVAT() { return VAT; }
	public void setVAT(String vAT) { VAT = vAT; }
	public String getAmount() { return Amount; }
	public void setAmount(String amount) { Amount = amount; }
	public void setPaymentType(String paymentType) { PaymentType = paymentType; }
	public String getPaymentType() { return PaymentType; }
	public String getStatus() { return Status; }
	public void setStatus(String status) { Status = status; }
	public String getOrderType() { return OrderType; }
	public void setOrderType(String orderType) { OrderType = orderType; }
	public boolean isDoubleTabSelected() { return doubleTabSelected; }
	public void setDoubleTabSelected(boolean doubleTabSelected) { this.doubleTabSelected = doubleTabSelected; }
	
	public DataReportAllItem() { }
	
	public DataReportAllItem(String Time , String OrderNo , String Vat ,String Amount, String PaymentType,String Status , String OrderType)
	{
		this.setTime(Time);
		this.setOrderNo(OrderNo);
		this.setVAT(Vat);
		this.setAmount(Amount);
		this.setPaymentType(PaymentType);
		this.setStatus(Status);
		this.setOrderType(OrderType);
		
	}
	

}
