import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestUser {

	private User testBidder;
	private User testAuctionCentralEmployee;
	private User testNonProfitEmployee;
	
	@Before
	public void setUp() {
		testBidder = new Bidder("George", "(222)432-7543");		
		testAuctionCentralEmployee = new AuctionCentralEmployee("Louis", "(325)432-6543");
		testNonProfitEmployee = new NonProfitEmployee("Mary", "(222)124-4532", "ABC Group");
	}

	@Test
	public void testViewCalendar() {

	}
	
	@Test
	public void testViewAuction() {
		Auction testAuction = new Auction("ABC Group", "December 1, 2015");
		assertEquals(testBidder.viewAuction(testAuction), "Auction Name: ABC Group-December 1, 2015 "
				+ "Auction Date: December 1, 2015");
		
		assertEquals(testAuctionCentralEmployee.viewAuction(testAuction), "Auction Name: ABC Group-December 1, 2015 "
				+ "Auction Date: December 1, 2015");
		
		assertEquals(testNonProfitEmployee.viewAuction(testAuction), "Auction Name: ABC Group-December 1, 2015 "
				+ "Auction Date: December 1, 2015");
	}
	
	@Test
	public void testViewItem() {
		Item testItem = new Item("Shoe", 75.00);
		assertEquals(testBidder.viewItem(testItem), "Item Name: Shoe Starting Bid: $75.00");
		assertEquals(testAuctionCentralEmployee.viewItem(testItem), "Item Name: Shoe Starting Bid: $75.00");
		assertEquals(testNonProfitEmployee.viewItem(testItem), "Item Name: Shoe Starting Bid: $75.00");
	}
	
	@Test
	public void testGetMyName() {
		assertEquals(testBidder.getMyName(), "George");
		assertEquals(testAuctionCentralEmployee.getMyName(), "Louis");
		assertEquals(testNonProfitEmployee.getMyName(), "Mary");
	}
	
	@Test
	public void testSetMyName() {
		testBidder.setMyName("Lisa");
		assertEquals(testBidder.getMyName(), "Lisa");
		
		testAuctionCentralEmployee.setMyName("Mark");
		assertEquals(testAuctionCentralEmployee.getMyName(), "Mark");
		
		testNonProfitEmployee.setMyName("Buzz");
		assertEquals(testNonProfitEmployee.getMyName(), "Buzz");
	}

	@Test
	public void testGetMyContact() {
		assertEquals(testBidder.getMyContact(), "(222)432-7543");
		
		assertEquals(testAuctionCentralEmployee.getMyContact(), "(325)432-6543");
		
		assertEquals(testNonProfitEmployee.getMyContact(), "(222)124-4532");
	}

	@Test
	public void testSetMyContact() {
		testBidder.setMyContact("(434)444-4444");
		assertEquals(testBidder.getMyContact(), "(434)444-4444");
		
		testAuctionCentralEmployee.setMyContact("(434)322-4004");
		assertEquals(testAuctionCentralEmployee.getMyContact(), "(434)322-4004");
		
		testNonProfitEmployee.setMyContact("(000)000-0000");
		assertEquals(testNonProfitEmployee.getMyContact(), "(000)000-0000");
	}
}