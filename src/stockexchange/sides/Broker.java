package stockexchange.sides;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import stockexchange.data.StockExchange;
import stockexchange.model.BuyRequest;
import stockexchange.model.Investment;
import stockexchange.model.SellRequest;
import stockexchange.model.Status;
import stockexchange.model.Stock;
import stockexchange.model.TransactionType;
import stockexchange.utils.ListUtils;

public class Broker {
	StockExchange stockExchange = StockExchange.INSTANCE;
	ListUtils listUtils = new ListUtils();
	List<Stock> todayStock = new ArrayList<Stock>();
	
	private boolean canAfford(double investmentValue, double money){
		if(investmentValue <= money){
			return true;
		} else {
			return false;
		}
	}
	
	private void chargeClient(double investmentValue, TransactionType t, Client client){
		if(t.equals(TransactionType.BUY)){
			client.setMoney(client.getMoney() - investmentValue);
		}
		if(t.equals(TransactionType.SELL)){
			client.setMoney(client.getMoney() + investmentValue);
		}
	}
	
	private double getInvestmentValue(double quantity, double price, TransactionType t){
		double value = quantity * price;
		double commission = Math.round(value * 0.005);
		if(t.equals(TransactionType.BUY)){
			System.out.println("Transaction: " + t + " quantity " + quantity + " price " + price + " value " + value + " commission " + commission);
			return value + commission;
		}
		if(t.equals(TransactionType.SELL)){
			System.out.println("Transaction: " + t + " quantity " + quantity + " price " + price + " value " + value + " commission " + commission);
			return value - commission;
		}
		return 0;
	}
	
	public Status buyStocks(BuyRequest req, Client client){
		todayStock = stockExchange.getTodayStockList();
		for (Stock stock : todayStock) {
			if(stock.getCompanyName().equalsIgnoreCase(req.getCompanyName())){
				Investment inv = new Investment(listUtils.getNextId(client.getPortfolio()), req.getCompanyName(), req.getQuantity(), stock.getPrice());
				double investmentValue = getInvestmentValue(req.getQuantity(), stock.getPrice(), TransactionType.BUY);
				if(canAfford(investmentValue, client.getMoney())){
					client.getPortfolio().add(inv);
					chargeClient(investmentValue, TransactionType.BUY, client);
					System.out.println("Company: " + req.getCompanyName());
					return Status.OK;
				} else {
					System.out.println("too poor");
					return Status.FAIL;
				}
			}
		}
		return Status.FAIL;
	}
	
	public Status sellStocks(SellRequest req, Client client){
		Iterator<Investment> i = client.getPortfolio().iterator();
		while(i.hasNext()){
			Investment inv = i.next();
			if(inv.getId().equals(req.getId())){
				Stock todayState = stockExchange.getStockByName(inv.getCompanyName());
				
				if(req.getQuantity() >= inv.getQuantity()){
					double investmentValue = getInvestmentValue(inv.getQuantity(), todayState.getPrice(), TransactionType.SELL);
					chargeClient(investmentValue, TransactionType.SELL, client);
					i.remove();
					System.out.println("Investment ID: " + req.getId());
					return Status.OK;
				} else {
					double investmentValue = getInvestmentValue(req.getQuantity(), todayState.getPrice(), TransactionType.SELL);
					chargeClient(investmentValue, TransactionType.SELL, client);
					inv.setQuantity(inv.getQuantity() - req.getQuantity());
					System.out.println("Investment ID: " + req.getId());
					return Status.OK;
				}
			}
		}
		return Status.FAIL;
	}
}
