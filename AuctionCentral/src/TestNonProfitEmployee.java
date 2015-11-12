import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestNonProfitEmployee {

	private NonProfitEmployee testNPEmp;
	private CalendarClass testCalendar;
	private Auction testAuction;
	
	@Before
	public void setUp() {
		testNPEmp = new NonProfitEmployee("Jim", "(234)567-8900", "Dunder Mifflin");
		testCalendar = new CalendarClass();
		testAuction = new Auction("DM", 12, 23, 2015, 2, 7);
	}

	@Test
	public void testScheduleAuction() {
		testCalendar.addAuction(testAuction);
		assertEquals(testCalendar.viewAuction("DM"), "DM-12/23/2015");
	}
	
	@Test
	public void testEditAuctionName() {
		testAuction.setName("Dunder Mifflin");
		assertEquals(testAuction.getMyName(), "Dunder Mifflin-12/23/2015");
	}
	
//	@Test
//	public void testEditAuctionDay() {
//		testAuction.setMyDay(5);
//		assertEquals(testAuction.getMyDay(), 5);
//		assertEquals(testAuction.getMyName(), "DM-12/5/2015");
//	}
//	
//	@Test
//	public void testEditAuctionMonth() {
//		testAuction.setMyMonth(8);
//		assertEquals(testAuction.getMyMonth(), 8);
//		assertEquals(testAuction.getMyName(), "DM-8/23/2015");
//	}
//	
//	@Test
//	public void testEditAuctionYear() {
//		testAuction.setMyYear(1990);
//		assertEquals(testAuction.getMyYear(), 1990);
//		assertEquals(testAuction.getMyName(), "DM-12/23/1990");
//	}
//	
//	@Test
//	public void testEditAuctionStartTime() {
//		testAuction.setMyStartTime(6);
//	}
//	
//	@Test
//	public void testEditAuctionEndTime() {
//		testAuction.setMyEndTime(12);
//	}
//	
//	@Test
//	public void testAddAuctionItem() {
//		testAuction.addItem(new Item(theItemName, theStartingBid));
//	}
//	
//	@Test
//	public void testEditAuctionItemName() {
//		Item testItem = new Item("hat", 2.0);
//		testItem.setMyName("vest");
//		
//	}
//	
//	@Test
//	public void testEditAuctionItemStartingBid() {
//		theItem.setMyStartingBid(theStartingBid);
//	}
}
