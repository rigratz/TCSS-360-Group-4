import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestBidder {
	
	private Bidder testBidder;
	private Item testItem;

	@Before
	public void setUp() {
		testBidder = new Bidder("Pam", "(123)565-7654");
		testItem = new Item("pen", 3.5);
	}

	@Test
	public void testPlaceBidLessThanStartingBid() {
		assertFalse(testBidder.placeBid(testItem, 2.0));
	}
	
	@Test
	public void testPlaceBidGreaterThanStartingBid() {
		assertTrue(testBidder.placeBid(testItem, 4.0));
	}

	@Test
	public void testCancelBidOnExistingBid() {
		testBidder.placeBid(testItem, 5.0);
		assertTrue(testBidder.cancelBid(testItem));
	}
	
	@Test
	public void testCancelBidOnNonExistingBid() {
		assertFalse(testBidder.cancelBid(testItem));
	}

	@Test
	public void testChangeBidToLessThanStartingBid() {
		assertFalse(testBidder.changeBid(testItem, 1.0));
	}
	
	@Test
	public void testChangeBidToGreaterThanStartingBid() {
		assertTrue(testBidder.changeBid(testItem, 4.55));
	}

	@Test
	public void testGetMyBids() {
		testBidder.placeBid(testItem, 5.2);
		testBidder.placeBid(new Item("shoe", 30.0), 35.0);
		assertEquals(testBidder.getMyBids(), "pen 5.2\nshoe 35.0\n");
	}
	
	@Test
	public void testGetMyBidsAfterCancelBid() {
		testBidder.placeBid(testItem, 5.2);
		testBidder.placeBid(new Item("shoe", 30.0), 35.0);
		testBidder.cancelBid(testItem);
		assertEquals(testBidder.getMyBids(), "shoe 35.0\n");
	}
	
	@Test
	public void testGetMyBidsAfterChangeBid() {
		testBidder.placeBid(testItem, 5.2);
		testBidder.placeBid(new Item("shoe", 30.0), 35.0);
		testBidder.changeBid(testItem, 6.5);
		assertEquals(testBidder.getMyBids(), "pen 6.5\nshoe 35.0\n");
	}

}
