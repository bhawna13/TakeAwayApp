package in.websnoox.tappTablet.dataReport.entity;

public class OrderItem {
	
	public String Name="",Quantity="",Price="";
	public boolean selected= false;

	public String getName() { return Name; }
	public void setName(String name) { Name = name; }
	public String getQuantity() { return Quantity; }
	public void setQuantity(String quantity) { Quantity = quantity; }
	public String getPrice() { return Price; }
	public void setPrice(String price) { Price = price; }
	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) { this.selected = selected; }
	
	public OrderItem() { }
	
	public OrderItem(String Name,String Quantity, String Price){
		this.setName(Name);
		this.setQuantity(Quantity);
		this.setPrice(Price);
	}
	

}
