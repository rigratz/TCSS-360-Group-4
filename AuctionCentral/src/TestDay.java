import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class TestDay {
	
	private Day testDay;
	private List<Auction> testAuctions;
	private Auction auction;

	@Before
	public void setUp() {
		testAuctions = new ArrayList<Auction>();
		testDay = new Day(testAuctions);
		auction = new Auction("Name", 12, 25, 2015, 12, 15);
	}

	@Test
	public void testGetNumberOfAuctionsBeforeSetTodaysAuctions() {
		assertEquals(testDay.getNumberOfAuctions(), 0);
	}
	
	@Test
	public void testGetNumberOfAuctionsAfterSetTodaysAuctions() {
		testDay.setTodaysAuctions(auction);
		assertEquals(testDay.getNumberOfAuctions(), 1);
	}
	
	@Test
	public void testSetAndGetStartTime() {
		testDay.setStartTime(5);
		assertEquals(testDay.getStartTime(), 5);
	}
	
	@Test
	public void testSetAndGetEndTime() {
		testDay.setEndTime(8);;
		assertEquals(testDay.getEndTime(), 8);
	}
	
	@Test
	public void testGetTodaysAuctions() {
		testDay.setTodaysAuctions(auction);
		assertEquals(testDay.getTodaysAuctions(), testAuctions);
	}
}
