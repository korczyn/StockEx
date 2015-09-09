package stockexchange.sides;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import stockexchange.enums.TransactionType;
import stockexchange.model.BuyRequest;
import stockexchange.model.Investment;
import stockexchange.model.SellRequest;

public class BrokerTest {

	Broker broker;
	Client client;
	
	@Before
	public void setUp(){
		broker = new Broker();
		client = new Client();
	}
	
	@Test
	public void testShouldAddMoneyToClientWhenSell() {
		//given
		//when
		broker.chargeClient(100, TransactionType.SELL, client);
		//then
		assertEquals(10100, client.getMoney(), 0);
	}
	
	@Test
	public void testShouldSubtractMoneyFromClientWhenBuy() {
		//given
		//when
		broker.chargeClient(100, TransactionType.BUY, client);
		//then
		assertEquals(9900, client.getMoney(), 0);
	}

	@Test
	public void testShouldBuyAnnAddInvestementToPortfolio() {
		//given
		BuyRequest br = new BuyRequest("PKOBP", 100L);
		//when
		broker.buyStocks(br, client);
		//then
		assertEquals(1, client.getPortfolio().size());
		assertEquals("PKOBP", client.getPortfolio().get(0).getCompanyName());
		assertEquals(Long.valueOf(100), client.getPortfolio().get(0).getQuantity());
	}
	
	@Test
	public void testShouldUpdateInvestmentInPortfolioWhenSellingFewerStocksThanClientHave(){
		//given
		client.getPortfolio().add(new Investment(1L, "PKOBP", 100L, 10.0));
		//when
		broker.sellStocks(new SellRequest(1L, 50L), client);
		//then
		assertEquals(1, client.getPortfolio().size());
		assertEquals(Long.valueOf(50), client.getPortfolio().get(0).getQuantity());
	}
	
	@Test
	public void testShouldDeleteInvestmentFromPortfolioWhenSellingMoreStocksThanClientHave(){
		//given
		client.getPortfolio().add(new Investment(1L, "PKOBP", 100L, 10.0));
		//when
		broker.sellStocks(new SellRequest(1L, 150L), client);
		//then
		assertEquals(0, client.getPortfolio().size());
	}
	
	
	

}
