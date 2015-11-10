import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestAuction {

	private Auction testAuction;
	
	@Before
	public void setup() {
		testAuction = new Auction("testNonProfit", 10, 5, 2016);
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
		assertEquals(testAuction.getMyName(), "OtherProfit-10/5/2016");
	}

	@Test
	public void testGetName() {
		assertEquals(testAuction.getMyName(), "testNonProfit-10/5/2016");
	}
	
	@Test
	public void testSetDate() {
		testAuction.setMyDate(10, 25, 2016);
		assertEquals(testAuction.getMyDate(), "10/25/2016");
		assertEquals(testAuction.getMyName(), "testNonProfit-10/25/2016");
	}
	
	@Test
	public void testGetDate() {
		assertEquals(testAuction.getMyDate(), "10/5/2016");
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
	
	@Test
	public void testGetDay() {
		assertEquals(testAuction.getMyDay(), 5);
	}
	
	@Test
	public void testSetDay() {
		testAuction.setMyDay(6);
		assertEquals(testAuction.getMyDay(), 6);
		assertEquals(testAuction.getMyDate(), "10/6/2016");
	}
	
	@Test
	public void testGetMonth() {
		assertEquals(testAuction.getMyMonth(), 10);
	}
	
	@Test
	public void testSetMonth() {
		testAuction.setMyMonth(6);
		assertEquals(testAuction.getMyMonth(), 6);
		assertEquals(testAuction.getMyDate(), "6/5/2016");
	}
	
	@Test
	public void testGetYear() {
		assertEquals(testAuction.getMyYear(), 2016);
	}
	
	@Test
	public void testSetYear() {
		testAuction.setMyYear(2015);
		assertEquals(testAuction.getMyYear(), 2015);
		assertEquals(testAuction.getMyDate(), "10/5/2015");
	}
}
