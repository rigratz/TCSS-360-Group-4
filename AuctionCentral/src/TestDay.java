import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class TestDay {
	
	private Day testDay;
	private List<Auction> testAuctions;

	@Before
	public void setUp() {
		testAuctions = new ArrayList<Auction>();
		testDay = new Day(testAuctions);
	}

	@Test
	public void testGetNumberOfAuctionsBeforeSetTodaysAuctions() {
		assertEquals(testDay.getNumberOfAuctions(), 0);
	}
	
	@Test
	public void testGetNumberOfAuctionsAfterSetTodaysAuctions() {
//		testDay.setTodaysAuctions("one");
		assertEquals(testDay.getNumberOfAuctions(), 1);
	}

	@Test
	public void testSetTodaysAuctions() {
//		testDay.setTodaysAuctions("one");
		assertEquals(testDay.getNumberOfAuctions(), 1);
	}
}
