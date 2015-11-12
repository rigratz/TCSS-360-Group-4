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
	public void testPlaceBid() {
		assertFalse(testBidder.placeBid(testItem, 2.0));
		assertTrue(testBidder.placeBid(testItem, 4.0));
	}

	@Test
	public void testCancelBid() {
		testBidder.placeBid(testItem, 5.0);
		assertTrue(testBidder.cancelBid(testItem));
	}

	@Test
	public void testChangeBid() {
		assertFalse(testBidder.placeBid(testItem, 1.0));
		assertTrue(testBidder.placeBid(testItem, 4.55));
	}

	@Test
	public void testGetMyBids() {
		testBidder.placeBid(testItem, 5.2);
		testBidder.placeBid(new Item("shoe", 30.0), 35.0);
		assertEquals(testBidder.getMyBids(), "pen 5.2\nshoe 35.0\n");
		testBidder.cancelBid(testItem);
		assertEquals(testBidder.getMyBids(), "shoe 35.0\n");
	}

}
