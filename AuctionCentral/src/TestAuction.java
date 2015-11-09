import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestAuction {

	private Auction testAuction;
	
	@Before
	public void setup() {
		testAuction = new Auction("testNonProfit", "October 5th 2016");
	}
	
	@Test
	public void testAddItem() {
		Item testLamp = new Item("Lamp", 3.5);
		testAuction.addItem(testLamp);
		List<Item> items = testAuction.getMyItems();
		assertEquals(items.get(0).getMyName(), "Lamp");
		assertTrue(items.get(0).getMyStartingBid() == 3.5);
	}
	
	@Test
	public void testSetName() {
		testAuction.setName("OtherProfit");
		assertEquals(testAuction.getMyName(), "OtherProfit-October 5th 2016");
	}

	@Test
	public void testGetName() {
		assertEquals(testAuction.getMyName(), "testNonProfit-October 5th 2016");
	}
	
	@Test
	public void testSetDate() {
		testAuction.setMyDate("October 25th 2016");
		System.out.println(testAuction.getMyDate());
		assertEquals(testAuction.getMyDate(), "October 25th 2016");
		assertEquals(testAuction.getMyName(), "testNonProfit-October 25th 2016");
	}
	
	@Test
	public void testGetDate() {
		assertEquals(testAuction.getMyDate(), "October 5th 2016");
	}
	
	@Test
	public void testSetItems() {
		Item testLamp = new Item("Lamp", 3.5);
		Item testLamp2 = new Item("Lamp2", 4.5);
		List<Item> items = new ArrayList<Item>();
		items.add(testLamp);
		items.add(testLamp2);
		testAuction.setMyItems(items);
		List<Item> itemsBack = testAuction.getMyItems();
		assertEquals(itemsBack.get(0).getMyName(), "Lamp");
		assertTrue(itemsBack.get(0).getMyStartingBid() == 3.5);
		testAuction.addItem(testLamp2);
		items = testAuction.getMyItems();
		assertEquals(itemsBack.get(1).getMyName(), "Lamp2");
		assertTrue(itemsBack.get(1).getMyStartingBid() == 4.5);
	}
	
	@Test
	public void testGetItems() {
		Item testLamp = new Item("Lamp", 3.5);
		Item testLamp2 = new Item("Lamp2", 4.5);
		testAuction.addItem(testLamp);
		List<Item> items = testAuction.getMyItems();
		assertEquals(items.get(0).getMyName(), "Lamp");
		assertTrue(items.get(0).getMyStartingBid() == 3.5);
		testAuction.addItem(testLamp2);
		items = testAuction.getMyItems();
		assertEquals(items.get(1).getMyName(), "Lamp2");
		assertTrue(items.get(1).getMyStartingBid() == 4.5);
	}
}
