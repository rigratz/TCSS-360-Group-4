import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestItem {

	private Item testItem;
	
	@Before
	public void setup() {
		testItem = new Item("testLamp", 3.5);
	}
	
	@Test
	public void testBidLessThanStartingBid() {
		assertFalse(testItem.bid("Tim", 3.49));
	}
	
	@Test
	public void testBidEqualToStartingBid() {

		assertTrue(testItem.bid("Jim", 3.5));
	}
	
	@Test
	public void testBidGreaterThanStartingBid() {
		assertTrue(testItem.bidGreaterThanStartingBid(3.49));
	}
	
	@Test
	public void testOneBidPerbidder() {
		testItem.bid("Tim", 500);
		testItem.oneBidPerbidder("Tim");
		assertEquals(testItem.getMyBids().get("Tim"), null);
	}
}
