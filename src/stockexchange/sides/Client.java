package stockexchange.sides;

import java.util.ArrayList;
import java.util.List;

import stockexchange.model.BuyRequest;
import stockexchange.model.Investment;
import stockexchange.model.SellRequest;

public class Client {

	private List<Investment> portfolio = new ArrayList<Investment>();
	Broker broker = new Broker();
	private double money;
	
	public Client() {
		this.money = 10000L;
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
	
	public void orderSellRequest(Long id, Long quantity){
		SellRequest request = new SellRequest(id, quantity);
		broker.sellStocks(request, this);
	}
}
