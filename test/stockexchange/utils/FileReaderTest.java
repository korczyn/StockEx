package stockexchange.utils;

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
	public void testShouldLoadFile() {
		//given
		List<String> result = new ArrayList<String>();
		//when
		result = fdr.readFile("C:\\Users\\mkorczyn\\Desktop\\ZadanieGielda\\ZadanieGielda\\testData.csv");
		//then
		assertNotNull(result);
		assertEquals(10, result.size());
	}
	
	@Test
	public void testShouldReturnStockList(){
		//given
		List<String> stockStrings = new ArrayList<String>();
		List<Stock> stocks = new ArrayList<Stock>();
		//when
		stockStrings.add("PKOBP,20130102,37.35");
		stocks = fdr.convertToStockList(stockStrings);
		//then
		assertEquals(1, stocks.size());
		assertEquals("PKOBP", stocks.get(0).getCompanyName());
	}

}
