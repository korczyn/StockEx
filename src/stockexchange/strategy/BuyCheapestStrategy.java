package stockexchange.strategy;

import java.util.ArrayList;
import java.util.List;

import stockexchange.data.StockExchange;
import stockexchange.model.Investment;
import stockexchange.model.Stats;
import stockexchange.model.Stock;
import stockexchange.sides.Client;

public class BuyCheapestStrategy {

	private StockExchange se;
	private Client client;
	private List<Stock> stocks = new ArrayList<Stock>();
	private int daysClosed = 0;
	Stats stats = new Stats();

	public BuyCheapestStrategy(StockExchange se, Client client) {
		this.se = se;
		this.client = client;
	}

	public Stock getCheapest(List<Stock> list) {
		double min = Integer.MAX_VALUE;
		Stock res = null;
		for (Stock stock : list) {
			if (stock.getPrice() < min) {
				min = stock.getPrice();
				res = stock;
			}
		}
		return res;
	}

	public Long calculateMax(Stock stock, double clientMoney) {
		double price = stock.getPrice();
		double commissionPercentage = 0.005;
		Long quantity = 1L;

		while ((quantity * price) + (quantity * price * commissionPercentage) < clientMoney) {
			quantity++;
		}
		return quantity - 1;
	}

	public void buyMaxOfCheapestStock(List<Stock> list, double clientMoney) {
		Stock stock = getCheapest(list);
		Long max = calculateMax(stock, clientMoney);
		if (max > 0) {
			client.orderBuyRequest(stock.getCompanyName(), max);
			stats.addStocksBought(max);
			stats.addBuy();
		}
	}

	public boolean priceIsHigherThenBefore(Investment inv, Stock stock, double percentage) {
		if (stock.getPrice() >= inv.getBuyingPrice() * percentage) {
			return true;
		}
		return false;
	}

	public void sellAllWithPercentageProfit(double percentage) {
		int size = client.getPortfolio().size();
		int offset = 1;

		while (offset - 1 != size) {
			Investment inv = client.getPortfolio().get(size - offset);
			if (priceIsHigherThenBefore(inv, se.getStockByName(inv.getCompanyName()), percentage)) {
				client.orderSellRequest(inv.getId(), inv.getQuantity());
				stats.addStocksSold(inv.getQuantity());
				stats.addSell();
			}
			offset++;
		}
	}

	public void makeInvestments() {
		se.setNextDay();
		stocks = se.getTodayStockList();

		if (stocks.size() > 0) {
			System.out.println(se.getCurrentDate());
			daysClosed = 0;
			stats.maxMoney(client.getMoney());
			sellAllWithPercentageProfit(1.14);
			stats.maxMoney(client.getMoney());
			buyMaxOfCheapestStock(stocks, client.getMoney());
			stats.maxMoney(client.getMoney());

			if (se.getCurrentDate().toString().equals("Thu Mar 28 00:00:00 CET 2013")) {
				sellAllWithPercentageProfit(0.90);
			}
		} else {
			daysClosed++;
			if (daysClosed == 3) {
				stats.maxMoney(client.getMoney());
				stats.printStats();
				System.out.println("End money " + client.getMoney());
				System.exit(0);
			}
		}

	}

	public void play() {

		while (true) {
			makeInvestments();
		}
	}
}
