package stockexchange.strategy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import stockexchange.data.StockExchange;
import stockexchange.data.impl.StockExchangeImpl;
import stockexchange.model.Investment;
import stockexchange.model.Stock;
import stockexchange.sides.Client;

public class BuyCheapesStrategyTest {

	StockExchange se;
	Client client;
	BuyCheapestStrategy strategy;
	
	@Before
	public void setUp(){
		se = new StockExchangeImpl("C:\\Users\\mkorczyn\\Desktop\\ZadanieGielda\\ZadanieGielda\\testData.csv");
		client = new Client();
		strategy = new BuyCheapestStrategy(se, client);
	}
	
	@Test
	public void testShouldReturnCheapestStock() {
		//given
		List<Stock> list = new ArrayList<Stock>();
		list.add(new Stock("cheap", null, 10.0));
		list.add(new Stock("cheaper", null, 5.0));
		//when
		Stock s = strategy.getCheapest(list);
		//then
		assertNotNull(s);
		assertEquals("cheaper", s.getCompanyName());
	}
	
	@Test
	public void testShouldReturnMaxQuanityOfActionsToBuy(){
		//given
		client.setMoney(1006);
		Stock s = new Stock("test", null, 10.0);
		//when
		Long quantity = strategy.calculateMax(s, client.getMoney());
		//then
		assertEquals(Long.valueOf(100), quantity);
	}
	
	@Test
	public void testShouldReturnTrurIfPriceIsHigherThenBeforeWithGivenPercentage(){
		//given
		Investment inv = new Investment(1L, "test", 150L, 10.0);
		Stock s = new Stock("test", null, 12.0);
		//when
		boolean priceIsHigher = strategy.priceIsHigherThenBefore(inv, s, 1.2);
		//then
		assertTrue(priceIsHigher);
	}

}
