package stockexchange.sides;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import stockexchange.data.StockExchange;
import stockexchange.data.impl.BuyRequest;
import stockexchange.data.impl.SellRequest;
import stockexchange.model.Investment;
import stockexchange.model.Stock;
import stockexchange.utils.ListUtils;

public class Broker {
	StockExchange stockExchange = StockExchange.INSTANCE;
	ListUtils listUtils = new ListUtils();
	List<Stock> todayStock = new ArrayList<Stock>();
	Client client;
	
	public void buyStocks(BuyRequest req, Client client){
		todayStock = stockExchange.getTodayStockList();
		for (Stock stock : todayStock) {
			if(stock.getCompanyName().equals(req.getCompanyName())){
				Investment inv = new Investment(listUtils.getNextId(client.getPortfolio()), req.getCompanyName(), req.getQuantity(), stock.getPrice());
				double value = req.getQuantity() * stock.getPrice();
				double commission = Math.round(value * 0.005);
				
				client.getPortfolio().add(inv);
				client.setMoney(client.getMoney() - value - commission);
				
				System.out.println("Company: " + req.getCompanyName() + ", quantity: " + req.getQuantity() + ", price: " + stock.getPrice() + ", value: " + value + " commission: " + commission);
			}
		}
	}
	
	public void sellStocks(SellRequest req, Client client){
		
		Iterator<Investment> i = client.getPortfolio().iterator();
		while(i.hasNext()){
			Investment inv = i.next();
			if(inv.getId().equals(req.getId())){
				Stock todayState = stockExchange.getStockByName(inv.getCompanyName());
				double value = 0;
				double commission = 0;
				
				if(req.getQuantity() >= inv.getQuantity()){
					value = inv.getQuantity() * todayState.getPrice();
					commission = Math.round(value * 0.005);
					client.setMoney(client.getMoney() + value - commission);
					i.remove();
				} else {
					value = req.getQuantity() * todayState.getPrice();
					commission = Math.round(value * 0.005);
					client.setMoney(client.getMoney() + value - commission);
					inv.setQuantity(inv.getQuantity() - req.getQuantity());
				}
				System.out.println("Investment: " + req.getId() + " quantity: " + req.getQuantity() + " value: " + value + " commission: " + commission);
			}
		}
	}
}
