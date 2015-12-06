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
	public void testGetMyNameBidder() {
		assertEquals(testBidder.getMyName(), "George");
	}
	
	@Test
	public void testGetMyNameAuctionCentralEmployee() {
		assertEquals(testAuctionCentralEmployee.getMyName(), "Louis");
	}
	
	@Test
	public void testGetMyNameNonProfitEmployee() {
		assertEquals(testNonProfitEmployee.getMyName(), "Mary");
	}
	
	@Test
	public void testSetMyNameBidder() {
		testBidder.setMyName("Lisa");
		assertEquals(testBidder.getMyName(), "Lisa");
	}
	
	@Test
	public void testSetMyNameAuctionCentralEmployee() {
		testAuctionCentralEmployee.setMyName("Mark");
		assertEquals(testAuctionCentralEmployee.getMyName(), "Mark");
	}
	
	@Test
	public void testSetMyNameNonProfitEmployee() {
		testNonProfitEmployee.setMyName("Buzz");
		assertEquals(testNonProfitEmployee.getMyName(), "Buzz");
	}

	@Test
	public void testGetMyContactBidder() {
		assertEquals(testBidder.getMyContact(), "(222)432-7543");
	}
	
	@Test
	public void testGetMyContactAuctionCentralEmployee() {
		assertEquals(testAuctionCentralEmployee.getMyContact(), "(325)432-6543");
	}
	
	@Test
	public void testGetMyContactNonProfitEmployee() {
		assertEquals(testNonProfitEmployee.getMyContact(), "(222)124-4532");
	}

	@Test
	public void testSetMyContactBidder() {
		testBidder.setMyContact("(434)444-4444");
		assertEquals(testBidder.getMyContact(), "(434)444-4444");
	}
	
	@Test
	public void testSetMyContactAuctionCentralEmployee() {
		testAuctionCentralEmployee.setMyContact("(434)322-4004");
		assertEquals(testAuctionCentralEmployee.getMyContact(), "(434)322-4004");
	}
	
	@Test
	public void testSetMyContactNonProfitEmployee() {
		testNonProfitEmployee.setMyContact("(000)000-0000");
		assertEquals(testNonProfitEmployee.getMyContact(), "(000)000-0000");
	}
}
