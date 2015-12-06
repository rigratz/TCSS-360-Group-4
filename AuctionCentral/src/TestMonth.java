import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestMonth {
	private Month testMonth;
	private List<Auction> testAuctions;
	private Auction testAuction;
	
	@Before
	public void setUp() throws Exception {
		testMonth = new Month(30);
		testAuctions = new ArrayList<Auction>();
		testAuction = new Auction("Name", 12, 25, 2015, 12, 15);
	}

	@Test
	public void testGetDay() {
		assertEquals(testMonth.getDay(5).getNumberOfAuctions(), 0);
	}
	
	@Test
	public void testCreateDays() {
		testMonth.createDays();
		assertEquals(testMonth.getMaxDays(), 30);
	}

	@Test
	public void testListOfAuctionsOfTheMonth() {
		testMonth.getDay(5).setTodaysAuctions(testAuction);
		testAuctions.add(testAuction);
		assertEquals(testMonth.listOfAuctions(), testAuctions);
	}

	@Test
	public void testGetMaxDaysIfCorrectNumberOfDays() {
		assertEquals(testMonth.getMaxDays(), 30);
	}

	@Test
	public void testAvailableDaysList() {
		testMonth.getDay(5).setTodaysAuctions(testAuction);
		testMonth.getDay(5).setTodaysAuctions(testAuction);
		assertEquals(testMonth.availableDaysList(), testMonth.availableDaysList());
	}

}
