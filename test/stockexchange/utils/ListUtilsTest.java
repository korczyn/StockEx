package stockexchange.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import stockexchange.model.Investment;
import stockexchange.utils.ListUtils;

public class ListUtilsTest {

	ListUtils lu;
	List<Investment> list;
	
	@Before
	public void setUp(){
		lu = new ListUtils();
		list = new ArrayList<>();
	}
	
	@Test
	public void testShouldSetIdTo1IfListIsEmpty() {
		//given
		//when
		list.add(new Investment(lu.getNextId(list), "test", (long) 100, 35.80));
		//then
		assertNotNull(list);
		assertEquals(Long.valueOf(1), list.get(0).getId());
	}
	
	@Test
	public void testShouldSetIdToFiveIfIdsAreOneTwoAndFour(){
		//given
		list.add(new Investment(1L, "test", (long) 100, 35.80));
		list.add(new Investment(2L, "test", (long) 100, 35.80));
		list.add(new Investment(4L, "test", (long) 100, 35.80));
		//when
		list.add(new Investment(lu.getNextId(list), "test", (long) 100, 35.80));
		//then
		assertNotNull(list);
		assertEquals(Long.valueOf(5), list.get(3).getId());
	}
	
	@Test
	public void testShouldSetIdToFiveIfIdsAreNotInOrder(){
		//given
		list.add(new Investment(1L, "test", (long) 100, 35.80));
		list.add(new Investment(3L, "test", (long) 100, 35.80));
		list.add(new Investment(4L, "test", (long) 100, 35.80));
		list.add(new Investment(2L, "test", (long) 100, 35.80));
		//when
		list.add(new Investment(lu.getNextId(list), "test", (long) 100, 35.80));
		//then
		assertNotNull(list);
		assertEquals(Long.valueOf(5), list.get(4).getId());
	}

}
