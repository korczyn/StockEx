package stockexchange.game;

import stockexchange.data.StockExchange;
import stockexchange.sides.Client;
import stockexchange.strategy.impl.BuyCheapestStrategy;

public class Game {
	
	public static void main(String[] args){
		
		StockExchange hq = StockExchange.INSTANCE;
		Client client = new Client();
		client.setStrategy(new BuyCheapestStrategy(hq, client, 1.10));
		client.strategyStart();
	}
}
