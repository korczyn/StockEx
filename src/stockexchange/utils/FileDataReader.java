package stockexchange.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import stockexchange.model.Stock;

public class FileDataReader {
	
	private DateConverter dateConverter = new DateConverter();
	
	public HashMap<Date, Collection<Stock>> getMapOfAllStocks(String path){
		List<String> temp = readFile(path);
		List<Stock> stocksList = convertToStockList(temp);
		return convertListToMap(stocksList);
	}
	
	
	public List<Stock> convertToStockList(List<String> lines){
		List<Stock> res = new ArrayList<Stock>();
		try {
		
		for (String line : lines) {
			String[] tmp = line.split(",");
				res.add(new Stock(tmp[0], dateConverter.stringToDate(tmp[1]), Double.valueOf(tmp[2])));
		}
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public HashMap<Date, Collection<Stock>> convertListToMap(List<Stock> list){
		HashMap<Date, Collection<Stock>> map = new HashMap<Date, Collection<Stock>>();
		for (Stock stock : list) {
			if(map.containsKey(stock.getDate())){
				Collection<Stock> collection = map.get(stock.getDate());
				collection.add(stock);
				map.put(stock.getDate(), collection);
			} else {
				Collection<Stock> collection = new ArrayList<Stock>();
				collection.add(stock);
				map.put(stock.getDate(), collection);
			}
		}
		
//		String date = "20130104";
//		Collection<Stock> collection = map.get(dateConverter.stringToDate(date));
//		System.out.println(dateConverter.stringToDate(date));
//		System.out.println(collection.size());
		return map;
	}
	
	public List<String> readFile(String path){
		List<String> lines = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
		    String line = br.readLine();

		    while (line != null) {
		        lines.add(line);
		        line = br.readLine();
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return lines;
	}
}
