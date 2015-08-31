package stockexchange.sides;

import java.util.ArrayList;
import java.util.List;

import stockexchange.data.impl.BuyRequest;
import stockexchange.data.impl.SellRequest;
import stockexchange.model.Investment;

public class Client {

	private List<Investment> portfolio = new ArrayList<Investment>();
	
	private double money = 10000L;
	Broker broker = new Broker();
	
	
	public Client() {
		
	}

	public List<Investment> getPortfolio() {
		return portfolio;
	}
	
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	public void orderBuyRequest(String companyName, Long quantity){
		BuyRequest request = new BuyRequest(companyName, quantity);
		broker.buyStocks(request, this);
	}
	
	public void orderSellRequest(int id, Long quantity){
		SellRequest request = new SellRequest(Long.valueOf(id), quantity);
		broker.sellStocks(request, this);
	}
}
