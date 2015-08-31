package stockexchange.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import stockexchange.data.StockExchange;
import stockexchange.model.Investment;
import stockexchange.model.Stock;
import stockexchange.sides.Client;

public class Game {
	
	public static void main(String[] args){
		StockExchange hq = StockExchange.INSTANCE;
		List<Stock> stock = new ArrayList<Stock>();
		Client client = new Client();
		
		
		stock = hq.getTodayStockList();
		for (Stock stock2 : stock) {
			System.out.println(stock2.toString());
		}

		Scanner sc = new Scanner(System.in);
		while(true){
			String option = sc.nextLine();
			if(option.startsWith("buy")){
				String[] t = option.split(" ");
				client.orderBuyRequest(t[1], Long.valueOf(t[2]));
			}
			if(option.startsWith("sell")){
				String[] t = option.split(" ");
				client.orderBuyRequest(t[1], Long.valueOf(t[2]));
				client.orderSellRequest(Integer.parseInt(t[1]), Long.valueOf(t[2]));
			}
			if(option.equals("port")){
				System.out.println("Your investments-------------");
				List<Investment> portfolio = client.getPortfolio();
				for (Investment investment : portfolio) {
					System.out.println(investment.toString() + " price now: " + hq.getStockByName(investment.getCompanyName()).getPrice());
				}
			}
			if(option.equals("next")){
				hq.setNextDay();
				stock = hq.getTodayStockList();
				for (Stock stock2 : stock) {
					System.out.println(stock2.toString());
				}
			}
			if(option.equals("money")){
				System.out.println("Your money: " + client.getMoney());
			}
		}
	}
}
