package stockexchange.model;

import java.util.Date;

public class Stock {
	String companyName;
	Date date;
	double price;
	
	public Stock(String companyName, Date date, double price) {
		super();
		this.companyName = companyName;
		this.date = date;
		this.price = price;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Date getDate() {
		return date;
	}

	public double getPrice() {
		return price;
	}
	
	@Override
	public String toString(){
		return "Company: " + this.companyName + " -- Price: $" + this.price + " -- (" + this.date + ")";
	}
}
