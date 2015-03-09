package in.websnoox.tappTablet.dataReport.entity;

public class ItemisedDataReportItem {

	public String Name = "";
	public String Quantity = "";
	public String Amount = "";

	public String getName() { return Name; }
    public void setName(String name) { Name = name; } 
	public String getQuantity() { return Quantity; }
	public void setQuantity(String quantity) { Quantity = quantity; }
	public String getAmount() { return Amount; }
	public void setAmount(String amount) { Amount = amount; }
	
	public ItemisedDataReportItem(){}
	
	public ItemisedDataReportItem(String Name,String Quantity, String Amount){
		this.setName(Name);
		this.setQuantity(Quantity);
		this.setAmount(Amount);
	}
	

}
