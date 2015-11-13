import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestNonProfitEmployee {

	private NonProfitEmployee testNPEmp;
	private CalendarClass testCalendar;
	private Auction testAuction;
	private Item testItem;
	
	@Before
	public void setUp() {
		testNPEmp = new NonProfitEmployee("Jim", "(234)567-8900", "Dunder Mifflin");
		testCalendar = new CalendarClass();
		testAuction = new Auction("DM", 12, 23, 2015, 2, 7);
		testItem = new Item("hat", 2.0);
	}

	@Test
	public void testScheduleAuction() {
		testCalendar.addAuction(testAuction);
		assertEquals(testCalendar.viewAuction("DM"), "DM-12/23/2015 Start: 2 End: 7\n"
				+ "List of Items/Starting Bids:\n");
	}
	
	@Test
	public void testEditAuctionName() {
		testAuction.setName("Dunder Mifflin");
		assertEquals(testAuction.getMyName(), "Dunder Mifflin-12/23/2015");
	}
	
	@Test
	public void testEditAuctionDay() {
		testAuction.setMyDay(5);
		assertEquals(testAuction.getMyDay(), 5);
		assertEquals(testAuction.getMyName(), "DM-12/5/2015");
	}
	
	@Test
	public void testEditAuctionMonth() {
		testAuction.setMyMonth(11);
		assertEquals(testAuction.getMyMonth(), 11);
		assertEquals(testAuction.getMyName(), "DM-11/23/2015");
	}
	
	@Test
	public void testEditAuctionYear() {
		testAuction.setMyYear(2016);
		assertEquals(testAuction.getMyYear(), 2016);
		assertEquals(testAuction.getMyName(), "DM-12/23/2016");
	}
	
	@Test
	public void testEditAuctionStartTime() {
		testAuction.setMyStartTime(6);
		assertEquals(testAuction.getMyStartTime(), 6);
		assertEquals(testAuction.toString(), "DM-12/23/2015 Start: 6 End: 7");
	}
	
	@Test
	public void testEditAuctionEndTime() {
		testAuction.setMyEndTime(3);
		assertEquals(testAuction.getMyEndTime(), 3);
		assertEquals(testAuction.toString(), "DM-12/23/2015 Start: 2 End: 3");
	}
	
	@Test
	public void testAddAuctionItem() {
		testAuction.addItem(testItem);
		assertEquals(testAuction.itemsToString(), "hat 2.0\n");
	}
	
	@Test
	public void testEditAuctionItemName() {
		testItem.setMyName("vest");
		assertEquals(testItem.getMyName(), "vest");
	}
	
	@Test
	public void testEditAuctionItemStartingBid() {
		testItem.setMyStartingBid(3.0);
		assertEquals(testItem.getMyStartingBid(), 3.0, 0.001);
	}
}
