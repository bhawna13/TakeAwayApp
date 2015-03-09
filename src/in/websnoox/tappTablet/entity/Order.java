package in.websnoox.tappTablet.entity;



import java.util.ArrayList;
import java.util.HashMap;

public class Order {
	public int orderId;
	public int TableMove;
	public String tableNumNew;
	public String tableNumold;
	public String orderPrependName;
	public float amount;
	public String table_id;
	public long startTimeStamp;
	public long finishTimeStamp;
	public String restuarant_id;
	public String order_type;
	public String orderJson;
	public int itemsEnabledFrom;
	public float netprice;
	public float grossprice;
	public float TotalPrice;
	public String service_name;
	public String date;
	public HashMap<String, String> addedAddressMap = new HashMap<>();
	public float discounttotal;
	public float discountper;
	public float serviceCharge;
	public String vatno;
	public String vatTotal;
	public String leadtime;
	public String loginame;
	public boolean isSelectedToDelete;
	//public String fee = Util.numberfornmat(0);
	public String Username;
	public String OrderTime;

	public String address;
	public String distance;

	/*public String phoneNumber;
	public String change =Util.numberfornmat(0);
	public String userAmount = Util.numberfornmat(0);
	public String userCashAmount = Util.numberfornmat(0);
	public String userCardAmount = Util.numberfornmat(0);
	public String amountDue = Util.numberfornmat(0);*/

	public String title;
	public String status;
	public String postalCode;
	public String payment_Mode;
	public int position = 0;
	public ArrayList<SaleAbleItem> item = new ArrayList<SaleAbleItem>();
	// public String services;

}
