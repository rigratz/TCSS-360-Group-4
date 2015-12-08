import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestAuction {

	private Auction testAuction;
	
	@Before
	public void setup() {
		testAuction = new Auction("testNonProfit", 10, 5, 2016, 5, 6);
		Item testLamp = new Item("Lamp", 3.5);
		testAuction.addItem(testLamp);
	}
	
	@Test
	public void testAddItemNameIsCorrect() {
		List<Item> items = testAuction.getMyItems();
		assertEquals(items.get(0).getMyName(), "Lamp");
	}
	
	@Test
	public void testAddItemStartingBidIsCorrect() {
		List<Item> items = testAuction.getMyItems();
		assertTrue(items.get(0).getMyStartingBid() == 3.5);
	}
}
