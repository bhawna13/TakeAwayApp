package in.websnoox.tappTablet.entity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Product extends SaleAbleItem {
	public String productNum;
	public int categoryId;
	public boolean isprint = true;
	public String vat = "";
	public int product_paid_status;
	public String product_name="";
	public String productid,flag;
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(Product other) {
		this._id = other._id;
		this.id = other.id;
		this.name = other.name;
		this.netamount = other.netamount;
		this.grossamount = other.grossamount;
		this.categoryId = other.categoryId;
		this.quantity = other.quantity;
		this.vat = other.vat;
		this.service = other.service;
		this.product_name=other.product_name;
		this.product_paid_status=other.product_paid_status;
		
		//for update JSON Tag
		this.productid=other.productid;
		this.flag=other.flag;
		
		if (other.addOns != null) {
			this.addOns = new ArrayList<ProductAddOn>(other.addOns);
		}
		this.selectedAddon = new LinkedHashSet<ProductAddOn>(
				other.selectedAddon);
		for (ProductAddOn ad : selectedAddon) {
			ad.product = this;
		}
		this.extraNote = other.extraNote;
	}

	public List<ProductAddOn> addOns;

	public LinkedHashSet<ProductAddOn> selectedAddon = new LinkedHashSet<ProductAddOn>();

	public List<ExtraNote> extraNote = new ArrayList<Product.ExtraNote>();
	public int positionInOrder;

	public static class ExtraNote extends SaleAbleItem {
		public Product product;
		public Order order;
		public boolean isPrint=true;
		public int categoryId;

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return id;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof ExtraNote) {
				return (id == ((ExtraNote) o).id);
			}
			return false;
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

}
