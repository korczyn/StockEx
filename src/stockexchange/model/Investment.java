package stockexchange.model;

public class Investment {

	private Long id;
	private String companyName;
	private Long quantity;
	private Double buyingPrice;
	
	public Investment(Long id, String companyName, Long quantity, Double buyingPrice) {
		this.id = id;
		this.companyName = companyName;
		this.quantity = quantity;
		this.buyingPrice = buyingPrice;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(Double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString(){
		return "id: " + this.id + " company: " + this.companyName + " quantity: " + this.quantity + " buying price: " + this.buyingPrice;
	}
}
