package stockexchange.utils;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import stockexchange.utils.DateConverter;

public class DateConverterTest {

	DateConverter dc;
	
	@Before
	public void init(){
		dc = new DateConverter();
	}
	
	@Test
	public void testShouldConvertStringToDate() throws ParseException {
		//given
		String dateString = "20130102";
		//when
		Date date = dc.stringToDate(dateString);
		//then
		assertEquals("Wed Jan 02 00:00:00 CET 2013", date.toString());
	}
}
