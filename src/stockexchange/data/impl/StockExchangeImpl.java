package stockexchange.data.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import stockexchange.data.StockExchange;
import stockexchange.model.Stock;
import stockexchange.utils.DateConverter;
import stockexchange.utils.FileDataReader;

public class StockExchangeImpl implements StockExchange{

	private HashMap<Date, Collection<Stock>> stocks = new HashMap<Date, Collection<Stock>>();
	private List<Stock> todayStocks = new ArrayList<Stock>();
	private FileDataReader fdr = new FileDataReader();
	private DateConverter dateConverter = new DateConverter();
	
	private Date currentDate;
	
	public StockExchangeImpl(String pathToDataFile){
		this.stocks = fdr.getMapOfAllStocks(pathToDataFile);
		this.currentDate = dateConverter.stringToDate("20130102");
		getTodayStockList();
	}
	
	
	public List<Stock> getTodayStockList(){
		todayStocks = (ArrayList<Stock>) stocks.get(currentDate);
		
		if(todayStocks == null){
			todayStocks = new ArrayList<Stock>();
		}
		
		return todayStocks;
	}
	
	public void setNextDay(){
		Calendar c = Calendar.getInstance(); 
		c.setTime(currentDate); 
		c.add(Calendar.DATE, 1);
		this.currentDate = c.getTime();
	}
	
	public Date getCurrentDate(){
		return currentDate;
	}

	public Stock getStockByName(String name) {
		for (Stock stock : todayStocks) {
			if(stock.getCompanyName().equalsIgnoreCase(name)){
				return stock;
			}
		}
		return null;
	}
	
}
