package stockexchange.utils.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import stockexchange.model.Stock;
import stockexchange.utils.FileDataReader;

public class FileReaderTest {

	FileDataReader fdr;
	
	@Before
	public void init(){
		fdr = new FileDataReader();
	}
	
	@Test
	public void test() {
		List<String> result = new ArrayList<String>();
		result = fdr.readFile("C:\\Users\\mkorczyn\\Desktop\\ZadanieGielda\\ZadanieGielda\\testData.csv");
		assertNotNull(result);
		assertEquals(10, result.size());
	}
	
	@Test
	public void testShouldReturnStockList(){
		List<String> stockStrings = new ArrayList<String>();
		stockStrings.add("PKOBP,20130102,37.35");

		List<Stock> stocks = new ArrayList<Stock>();
		stocks = fdr.convertToStockList(stockStrings);
		
		assertEquals(1, stocks.size());
		assertEquals("PKOBP", stocks.get(0).getCompanyName());
	}

}
