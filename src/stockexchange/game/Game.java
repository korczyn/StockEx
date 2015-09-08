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
		Client client = new Client();
		GameStrategy strategy = new GameStrategy(hq, client);
		
		strategy.play();
		
		
		
//		StockExchange hq = StockExchange.INSTANCE;
//		List<Stock> stocks = new ArrayList<Stock>();
//		Client client = new Client();
//		
//		
//		stocks = hq.getTodayStockList();
//		for (Stock stock : stocks) {
//			System.out.println(stock.toString());
//		}
//
//		Scanner sc = new Scanner(System.in);
//		while(true){
//			String option = sc.nextLine();
//			if(option.startsWith("buy")){
//				String[] t = option.split(" ");
//				client.orderBuyRequest(t[1], Long.valueOf(t[2]));
//			}
//			if(option.startsWith("sell")){
//				String[] t = option.split(" ");
////				client.orderBuyRequest(t[1], Long.valueOf(t[2]));
//				client.orderSellRequest(Long.valueOf(t[1]), Long.valueOf(t[2]));
//			}
//			if(option.equals("port")){
//				System.out.println("-------------Your investments-------------");
//				List<Investment> portfolio = client.getPortfolio();
//				for (Investment investment : portfolio) {
//					System.out.println(investment.toString() + " price now: " + hq.getStockByName(investment.getCompanyName()).getPrice());
//				}
//			}
//			if(option.equals("next")){
//				hq.setNextDay();
//				stocks = hq.getTodayStockList();
//				for (Stock stock2 : stocks) {
//					System.out.println(stock2.toString());
//				}
//			}
//			if(option.equals("money")){
//				System.out.println("Your money: " + client.getMoney());
//			}
//		}
	}
}
