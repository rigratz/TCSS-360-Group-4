import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class NonProfitUITest {

	private NonProfitUI npoNoAuction;
	private NonProfitUI npoWithAuction;
	private CalendarClass auctionCalendar;
	
	@Before
	public void setUp() throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		auctionCalendar = new CalendarClass();
		List<Item> itemList = null;
		
		ArrayList<Auction> auctions = new ArrayList<Auction>();
		auctions.add(new Auction("GoodWill", 12, 25, 2015, 8, 10, itemList));
		auctionCalendar.insertAuctions(auctions);
		
		npoWithAuction = new NonProfitUI(input, auctionCalendar);
		npoWithAuction.setUser(new NonProfitEmployee("Mr.Good", "email", "GoodWill"));
		
		npoNoAuction = new NonProfitUI(input, auctionCalendar);
		npoNoAuction.setUser(new NonProfitEmployee("Mr.Test", "email", "TestCorp"));;
	}

	@Test
	public void testHasAuctionOnNoAuctionScheduled() {
		assertFalse(npoNoAuction.hasAuction());
	}
	
	@Test
	public void testHasAuctionOnAuctionScheduled() {
		assertTrue(npoWithAuction.hasAuction());
	}
	
	@Test
	public void testGetAuctionOnNoAuctionScheduled() {
		Auction testAuction = npoNoAuction.getAuction();
		assertNull(testAuction);
	}
	
	@Test
	public void testGetAuctionOnAuctionScheduled() {
		String expectedName = "GoodWill";
		int expectedDay = 25;
		int expectedMonth = 12;
		int expectedYear = 2015;
		int expectedStart = 8;
		int expectedEnd = 10;
		
		Auction testAuction = npoWithAuction.getAuction();
		
		//No equals method for Auction, so must assert all fields separately.
		assertEquals(expectedName, testAuction.getMyNonProfit());
		assertEquals(expectedDay, testAuction.getMyDay());
		assertEquals(expectedMonth, testAuction.getMyMonth());
		assertEquals(expectedYear, testAuction.getMyYear());
		assertEquals(expectedStart, testAuction.getMyStartTime());
		assertEquals(expectedEnd, testAuction.getMyEndTime());
		assertNull(testAuction.getMyItems());
		
	}

}
