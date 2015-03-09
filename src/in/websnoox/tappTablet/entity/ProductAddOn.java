package in.websnoox.tappTablet.entity;

public class ProductAddOn extends SaleAbleItem {

	
	public Product product;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof ProductAddOn) {
			return ((ProductAddOn) o).id == this.id;
		}
		return false;
	}

}
