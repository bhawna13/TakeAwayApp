package in.websnoox.tappTablet.dataReport.entity;

public class DataReportSelectionItem {

	private int position;
	private String name = "";

	
	public DataReportSelectionItem(int position , String name)
	{
		this.setPosition(position);;
		this.setName(name);
	}
	
	public int getPosition() { return position; }

	public void setPosition(int position) { this.position = position; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

}
