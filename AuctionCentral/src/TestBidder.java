import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;


public class TestBidder {
	
	private Bidder testBidder;
	private Bidder testBidderWithMap;
	private Map<String, Double> testBidsMap;
	private Item testItemPen;
	private Item testItemBook;

	@Before
	public void setUp() {
		testBidder = new Bidder("Pam", "(123)565-7654");
		testBidsMap = new HashMap<String, Double>();
		testBidsMap.put("phone", 444.99);
		testBidsMap.put("chair", 659.00);
		testBidderWithMap = new Bidder("Kevin", "(324)332-3333", testBidsMap);
		testItemPen = new Item("pen", 3.5);
		testItemBook = new Item("book", 35.99);
	}

	@Test
	public void testBidderPlaceBidLessThanStartingBid() {
		assertFalse(testBidder.placeBid(testItemPen, 2.0));
	}
	
	@Test
	public void testBidderWithMapPlaceBidLessThanStartingBid() {
		assertFalse(testBidderWithMap.placeBid(testItemBook, 2.0));
	}
	
	@Test
	public void testBidderPlaceBidGreaterThanStartingBid() {
		assertTrue(testBidder.placeBid(testItemPen, 4.0));
	}
	
	@Test
	public void testBidderWithMapPlaceBidGreaterThanStartingBid() {
		assertTrue(testBidderWithMap.placeBid(testItemBook, 45.0));
	}

	@Test
	public void testBidderCancelBidOnExistingBid() {
		testBidder.placeBid(testItemPen, 5.0);
		assertTrue(testBidder.cancelBid(testItemPen));
	}
	
	@Test
	public void testBidderWithMapCancelBidOnExistingBid() {
		testBidderWithMap.placeBid(testItemBook, 50.0);
		assertTrue(testBidderWithMap.cancelBid(testItemBook));
	}
	
	@Test
	public void testBidderCancelBidOnNonExistingBid() {
		assertFalse(testBidder.cancelBid(testItemPen));
	}
	
	@Test
	public void testBidderWithMapCancelBidOnNonExistingBid() {
		assertFalse(testBidderWithMap.cancelBid(testItemBook));
	}

	@Test
	public void testBidderChangeBidToLessThanStartingBid() {
		assertFalse(testBidder.changeBid(testItemPen, 1.0));
	}
	
	@Test
	public void testBidderWithMapChangeBidToLessThanStartingBid() {
		assertFalse(testBidderWithMap.changeBid(testItemBook, 1.0));
	}
	
	@Test
	public void testBidderChangeBidToGreaterThanStartingBid() {
		assertTrue(testBidder.changeBid(testItemPen, 4.55));
	}
	
	@Test
	public void testBidderWithMapChangeBidToGreaterThanStartingBid() {
		assertTrue(testBidderWithMap.changeBid(testItemBook, 40.55));
	}

	@Test
	public void testBidderGetMyBidsContainsKey() {
		testBidder.placeBid(testItemPen, 5.2);
		testBidder.placeBid(new Item("shoe", 30.0), 35.0);
		assertTrue(testBidder.getMyBids().containsKey("shoe"));	
	}
	
	@Test
	public void testBidderWithMapGetMyBidsContainsKey() {
		testBidderWithMap.placeBid(testItemBook, 5.2);
		testBidderWithMap.placeBid(new Item("table", 90.0), 115.0);
		assertTrue(testBidderWithMap.getMyBids().containsKey("table"));	
	}
	
	@Test
	public void testBidderGetMyBidsContainsValue() {
		testBidder.placeBid(testItemPen, 5.2);
		testBidder.placeBid(new Item("shoe", 30.0), 35.0);
		assertTrue(testBidder.getMyBids().containsValue(35.0));	
	}
	
	@Test
	public void testBidderWithMapGetMyBidsContainsValue() {
		testBidderWithMap.placeBid(testItemBook, 5.2);
		testBidderWithMap.placeBid(new Item("shoe", 30.0), 35.0);
		assertTrue(testBidderWithMap.getMyBids().containsValue(35.0));	
	}
	
	@Test
	public void testBidderGetMyBidsContainsKeyAfterCancelBid() {
		testBidder.placeBid(testItemPen, 5.2);
		testBidder.placeBid(new Item("shoe", 30.0), 35.0);
		testBidder.cancelBid(testItemPen);
		assertTrue(testBidder.getMyBids().containsKey("shoe"));
	}
	
	@Test
	public void testBidderWithMapGetMyBidsContainsKeyAfterCancelBid() {
		testBidderWithMap.placeBid(testItemBook, 5.2);
		testBidderWithMap.placeBid(new Item("shoe", 30.0), 35.0);
		testBidderWithMap.cancelBid(testItemBook);
		assertTrue(testBidderWithMap.getMyBids().containsKey("shoe"));
	}
	
	@Test
	public void testBidderGetMyBidsContainsValueAfterCancelBid() {
		testBidder.placeBid(testItemPen, 5.2);
		testBidder.placeBid(new Item("shoe", 30.0), 35.0);
		testBidder.cancelBid(testItemPen);
		assertTrue(testBidder.getMyBids().containsValue(35.0));
	}
	
	@Test
	public void testBidderWithMapGetMyBidsContainsValueAfterCancelBid() {
		testBidderWithMap.placeBid(testItemBook, 5.2);
		testBidderWithMap.placeBid(new Item("shoe", 30.0), 35.0);
		testBidderWithMap.cancelBid(testItemBook);
		assertTrue(testBidderWithMap.getMyBids().containsValue(35.0));
	}
	
	@Test
	public void testBidderGetMyBidsContainsKeyAfterChangeBid() {
		testBidder.placeBid(testItemPen, 5.2);
		testBidder.placeBid(new Item("shoe", 30.0), 35.0);
		testBidder.changeBid(testItemPen, 6.5);
		assertTrue(testBidder.getMyBids().containsKey("shoe"));
	}
	
	@Test
	public void testBidderWithMapGetMyBidsContainsKeyAfterChangeBid() {
		testBidderWithMap.placeBid(testItemBook, 5.2);
		testBidderWithMap.placeBid(new Item("shoe", 30.0), 35.0);
		testBidderWithMap.changeBid(testItemBook, 6.5);
		assertTrue(testBidderWithMap.getMyBids().containsKey("shoe"));
	}
	
	@Test
	public void testBidderGetMyBidsContainsValueAfterChangeBid() {
		testBidder.placeBid(testItemPen, 5.2);
		testBidder.placeBid(new Item("shoe", 30.0), 35.0);
		testBidder.changeBid(testItemPen, 6.5);
		assertTrue(testBidder.getMyBids().containsValue(35.0));	
	}
	
	@Test
	public void testBidderWithMapGetMyBidsContainsValueAfterChangeBid() {
		testBidderWithMap.placeBid(testItemBook, 5.2);
		testBidderWithMap.placeBid(new Item("shoe", 30.0), 35.0);
		testBidderWithMap.changeBid(testItemBook, 6.5);
		assertTrue(testBidderWithMap.getMyBids().containsValue(35.0));	
	}

}
