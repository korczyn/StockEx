package stockexchange.data.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import stockexchange.data.StockExchange;
import stockexchange.model.Stock;

public class StockExchangeImplTest {

	StockExchange se;
	
	@Before
	public void setUp(){
		se = new StockExchangeImpl("C:\\Users\\mkorczyn\\Desktop\\ZadanieGielda\\ZadanieGielda\\testData.csv");
	}
	
	@Test
	public void testShouldReturnPKOBPWhenCallingTodayStockByCompanyName() {
		//given
		se.getTodayStockList();
		//when
		Stock s = se.getStockByName("PKOBP");
		//then
		assertNotNull(s);
		assertEquals("PKOBP", s.getCompanyName());
	}
	
	@Test
	public void testShouldLoadStockListAtCreation(){
		//given
		//when
		Stock s = se.getStockByName("PKOBP");
		//then
		assertNotNull(s);
		assertEquals("PKOBP", s.getCompanyName());
	}
	
	@Test
	public void testShouldReturnNullWhenCallingStockByCompanyNameAndCompanyDontExists(){
		//given
		se.getTodayStockList();
		//when
		Stock s = se.getStockByName("NASDAQ");
		//then
		assertNull(s);
	}
}
