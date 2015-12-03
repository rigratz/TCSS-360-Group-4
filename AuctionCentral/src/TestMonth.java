import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestMonth {
	private Month month;
	private List<Auction> testAuctions;
	private Auction auction;
	@Before
	public void setUp() throws Exception {
		testAuctions = new ArrayList<Auction>();
		month = new Month(30);
		auction = new Auction("Name", 12, 25, 2015, 12, 15);
	}

	@Test
	public void testGetDay() {
		assertEquals(month.getDay(5), month.getDay(5));
	}

	@Test
	public void testListOfAuctionsOfTheMonth() {
		month.getDay(5).setTodaysAuctions(auction);
		testAuctions.add(auction);
		assertEquals(month.listOfAuctions(), testAuctions);
	}

	@Test
	public void testGetMaxDaysIfCorrectNumberOfDays() {
		assertEquals(month.getMaxDays(), 30);
	}

	@Test
	public void testAvailableDaysList() {
		month.getDay(5).setTodaysAuctions(auction);
		month.getDay(5).setTodaysAuctions(auction);
		assertEquals(month.availableDaysList(), month.availableDaysList());
	}

}
