package stockexchange.data.impl;

import stockexchange.data.Request;

public class BuyRequest implements Request{

	private String companyName;
	private Long quantity;

	public BuyRequest(String companyName, Long quantity){
		this.companyName = companyName;
		this.quantity = quantity;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public Long getQuantity() {
		return quantity;
	}
}
