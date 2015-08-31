package stockexchange.data;

import java.util.List;

import stockexchange.data.impl.StockExchangeImpl;
import stockexchange.model.Stock;

public interface StockExchange {
	
	StockExchange INSTANCE = new StockExchangeImpl("C:\\Users\\mkorczyn\\Desktop\\ZadanieGielda\\ZadanieGielda\\dane.csv");
	
	public List<Stock> getTodayStockList();
	public void setNextDay();
	public Stock getStockByName(String name);
}

