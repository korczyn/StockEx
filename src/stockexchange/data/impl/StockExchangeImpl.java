package stockexchange.data.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import stockexchange.data.StockExchange;
import stockexchange.model.Stock;
import stockexchange.utils.DateConverter;
import stockexchange.utils.FileDataReader;

public class StockExchangeImpl implements StockExchange{

	private List<Stock> stocks = new ArrayList<Stock>();
	private List<Stock> todayStocks = new ArrayList<Stock>();
	private FileDataReader fdr = new FileDataReader();
	private DateConverter dateConverter = new DateConverter();
	
	private Date currentDate;
	
	public StockExchangeImpl(String pathToDataFile){
		this.stocks = fdr.getListOfAllStocks(pathToDataFile);
		this.currentDate = dateConverter.stringToDate("20130102");
	}
	
	
	public List<Stock> getTodayStockList(){
		System.out.println("Today is " + currentDate);
		todayStocks.clear();
		for (Stock stock : stocks) {
			if(stock.getDate().equals(currentDate)){
				todayStocks.add(stock);
			}
		}
		
		if(todayStocks.size() == 0){
			System.out.println("Stock Exchange is closed today");
		}
		
		return todayStocks;
	}
	
	public void setNextDay(){
		Calendar c = Calendar.getInstance(); 
		c.setTime(currentDate); 
		c.add(Calendar.DATE, 1);
		this.currentDate = c.getTime();
	}


	@Override
	public Stock getStockByName(String name) {
		for (Stock stock : todayStocks) {
			if(stock.getCompanyName().equalsIgnoreCase(name)){
				return stock;
			}
		}
		return null;
	}
	
}
