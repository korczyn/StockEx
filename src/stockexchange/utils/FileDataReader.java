package stockexchange.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import stockexchange.model.Stock;

public class FileDataReader {
	
	private DateConverter dateConverter = new DateConverter();
	
	public List<Stock> getListOfAllStocks(String path){
		List<String> temp = readFile(path);
		return convertToStockList(temp);
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
