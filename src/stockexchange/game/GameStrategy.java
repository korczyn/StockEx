package stockexchange.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stockexchange.data.StockExchange;
import stockexchange.model.Investment;
import stockexchange.model.MeanPrice;
import stockexchange.model.Stock;
import stockexchange.sides.Client;
import stockexchange.utils.Stats;

public class GameStrategy {

	private StockExchange se;
	private Client client;
	private List<Stock> stocks = new ArrayList<Stock>();
	private Map<String, MeanPrice> prices = new HashMap<>();
	private int daysClosed = 0;
	Stats stats = new Stats();

	public GameStrategy(StockExchange se, Client client) {
		this.se = se;
		this.client = client;
	}

	public Map<String, MeanPrice> updateMeanPrices(List<Stock> stocks, Map<String, MeanPrice> map) {
		Map<String, MeanPrice> res = new HashMap<>();
		for (Stock stock : stocks) {
			MeanPrice mp = map.get(stock.getCompanyName());
			mp.addValue(stock.getPrice());
			res.put(stock.getCompanyName(), mp);
		}
		return res;
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

	public Long calculateMax(Stock stock) {
		double price = stock.getPrice();
		double maxValue = client.getMoney();
		double commissionPercentage = 0.005;
		Long quantity = 1L;

		while ((quantity * price) + (quantity * price * commissionPercentage) < maxValue) {
			quantity++;
		}
		return quantity - 1;
	}
	
	public void buyMaxOfCheapestStock(List<Stock> list){
		Stock stock = getCheapest(list);
		Long max = calculateMax(stock);
		if(max > 0){
			client.orderBuyRequest(stock.getCompanyName(), max);
			stats.addStocksBought(max);
			stats.addBuy();
		}
	}

	public boolean priceIsHigherThenBefore(Investment inv, Stock stock, double percentage){
		if(stock.getPrice() > inv.getBuyingPrice() * percentage){
			return true;
		}
		return false;
	}
	
	public void sellAllWithPercentageProfit(double percentage){
		int size = client.getPortfolio().size();
		int offset = 1;
		
		while(offset - 1 != size){
			Investment inv = client.getPortfolio().get(size - offset);
			if(priceIsHigherThenBefore(inv, se.getStockByName(inv.getCompanyName()), percentage)){
				client.orderSellRequest(inv.getId(), inv.getQuantity());
				stats.addStocksSold(inv.getQuantity());
				stats.addSell();
			}
			offset++;
		}
	}
	
	public void makeInvestments(){
		se.setNextDay();
		stocks = se.getTodayStockList();
		
		if(stocks.size() > 0){
			daysClosed = 0;
			System.out.println("start money " + client.getMoney());
			stats.maxMoney(client.getMoney());
			sellAllWithPercentageProfit(1.05);
			System.out.println("money after sell " + client.getMoney());
			stats.maxMoney(client.getMoney());
			buyMaxOfCheapestStock(stocks);
			System.out.println("money after buy " + client.getMoney());
			stats.maxMoney(client.getMoney());
		} else {
			daysClosed++;
			if(daysClosed == 3){
				stats.printStats();
				System.out.println("End money " + client.getMoney());
				System.exit(0);
			}
		}
		System.out.println();
		
	}
	
	public void play() {
		// init
//		stocks = se.getTodayStockList();
//		for (Stock s : stocks) {
//			prices.put(s.getCompanyName(), new MeanPrice(s.getPrice()));
//		}

		while(true){
			makeInvestments();
		}
		
//		client.orderSellRequest(1L, 1895L);
//		System.out.println(client.getMoney());
		
		// //calculate starting prices
		// for(int i = 0; i < 11; i++){
		// se.setNextDay();
		// stocks = se.getTodayStockList();
		// if(stocks.size() > 0){
		// prices = updateMeanPrices(stocks, prices);
		// }
		// }

	}
}
