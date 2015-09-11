package stockexchange.sides;

import java.util.ArrayList;
import java.util.List;

import stockexchange.model.BuyRequest;
import stockexchange.model.Investment;
import stockexchange.model.SellRequest;
import stockexchange.strategy.Strategy;


public class Client {

	private List<Investment> portfolio = new ArrayList<Investment>();
	Broker broker = new Broker();
	private double money;
	Strategy strategy;
	
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
		broker.buyStocks(new BuyRequest(companyName, quantity), this);
	}
	
	public void orderSellRequest(Long id, Long quantity){
		broker.sellStocks(new SellRequest(id, quantity), this);
	}
	
	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
	}
	
	public void strategyStart(){
		this.strategy.play();
	}
}
