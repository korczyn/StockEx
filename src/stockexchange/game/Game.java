package stockexchange.game;

import stockexchange.data.StockExchange;
import stockexchange.sides.Client;
import stockexchange.strategy.BuyCheapestStrategy;

public class Game {
	
	public static void main(String[] args){
		
		StockExchange hq = StockExchange.INSTANCE;
		Client client = new Client();
		
		BuyCheapestStrategy strategy = new BuyCheapestStrategy(hq, client);
		strategy.play();
	}
}
