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
	public void testBid() {
		assertFalse(testItem.bid("Tim", 3.49));
		assertTrue(testItem.bid("Jim", 3.5));
		assertTrue(testItem.bid("Slim", 3.51));
	}
	
	@Test
	public void testGetName() {
		assertEquals(testItem.getMyName(), "testLamp");
	}
	
	@Test
	public void testSetName() {
		testItem.setMyName("testChair");
		assertEquals(testItem.getMyName(), "testChair");
	}
	
	@Test
	public void testGetStartingBid() {
		double testDouble = 3.5;
		assertTrue(testItem.getMyStartingBid() == testDouble);
	}
	
	@Test
	public void testSetStartingBid() {
		testItem.setMyStartingBid(5.678);
		double testDouble = 5.678;
		assertTrue(testItem.getMyStartingBid() == testDouble);
	}

	@Test
	public void testGetBids() {
		assertTrue(testItem.bid("Jim", 3.5));
		
		HashMap<String, Double> bids = (HashMap<String, Double>) testItem.getMyBids();
		assertEquals(bids.get("Jim"), new Double(3.5));
		assertTrue(testItem.bid("Jim", 4.77));
		assertEquals(bids.get("Jim"), new Double(4.77));
	}
	
	@Test
	public void testSetBids() {
		HashMap<String, Double> testBids = new HashMap<String, Double>();
		testBids.put("Jim", 3.5);
		testBids.put("Tim", 3.56);
		testBids.put("Slim", 13.5);
		testBids.put("Han Lim", 43.7);
		testItem.setMyBids(testBids);
		HashMap<String, Double> bids = (HashMap<String, Double>) testItem.getMyBids();
		assertEquals(bids.get("Jim"), new Double(3.5));
		assertEquals(bids.get("Tim"), new Double(3.56));
		assertEquals(bids.get("Slim"), new Double(13.5));
		assertEquals(bids.get("Han Lim"), new Double(43.7));
	}
}
