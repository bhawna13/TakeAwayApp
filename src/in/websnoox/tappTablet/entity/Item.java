package in.websnoox.tappTablet.entity;

import java.util.ArrayList;

public class Item implements Cloneable {

	// db generated
	public int _id;

	public int id;

	// for offline
	public static int ITEM_ID = 10000;

	public String name;
	public String service="";
	public float netamount;
	public float grossamount;

	public String vat;
	//public String services;
	
	//public ArrayList<Category> Subcategory=new ArrayList<Category>(); 
}
