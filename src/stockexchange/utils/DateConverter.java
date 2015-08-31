package stockexchange.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

	public Date stringToDate(String date) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
		Date d1;
		try {
			d1 = inputFormat.parse(date);
			SimpleDateFormat outputFormat = new SimpleDateFormat("dd MM yyyy");
			String outputDate = outputFormat.format(d1);
			Date result = outputFormat.parse(outputDate);
			return result;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String dateToString(Date date) {
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd MM yyyy");
		String result = outputFormat.format(date);
		return result;
	}
}
