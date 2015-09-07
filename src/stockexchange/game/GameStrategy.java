package stockexchange.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stockexchange.data.StockExchange;
import stockexchange.model.MeanPrice;
import stockexchange.model.Stock;
import stockexchange.sides.Client;

public class GameStrategy {

	private StockExchange se;
	private Client client;
	private List<Stock> stocks = new ArrayList<Stock>();
	private Map<String, MeanPrice> prices = new HashMap<>();

	public GameStrategy(StockExchange se, Client client) {
		this.se = se;
		this.client = client;
	}
	
	public Map<String, MeanPrice> updateMeanPrices(List<Stock> stocks, Map<String, MeanPrice> map){
		Map<String, MeanPrice> res = new HashMap<>();
		for (Stock stock : stocks) {
			MeanPrice mp = map.get(stock.getCompanyName());
			mp.addValue(stock.getPrice());
			res.put(stock.getCompanyName(), mp);
		}
		return res;
	}
	
	public void play() {
		//init
		stocks = se.getTodayStockList();
		for (Stock s : stocks) {
			prices.put(s.getCompanyName(), new MeanPrice(s.getPrice()));
		}
		
		//calculate starting prices
		for(int i = 0; i < 11; i++){
			se.setNextDay();
			stocks = se.getTodayStockList();
			if(stocks.size() > 0){
				prices = updateMeanPrices(stocks, prices);
			}
		}
		
		
		
		
		
	}
}
