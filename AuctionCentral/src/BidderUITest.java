import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;


public class BidderUITest {

	private BidderUI bidder;
	private CalendarClass auctionCalendar;
	
	@Before
	public void setUp() throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		auctionCalendar = new CalendarClass();
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(new Item("Red", 5.0));
		itemList.add(new Item("Green", 10.0));
		itemList.get(0).bid("Tester", 20.0);
		
		ArrayList<Auction> auctions = new ArrayList<Auction>();
		auctions.add(new Auction("GoodWill", 12, 25, 2015, 8, 10, itemList));
		auctionCalendar.insertAuctions(auctions);
		
		Map<String, Double> bids = new HashMap<String, Double>();
		bids.put("Red", 20.0);
		
		bidder = new BidderUI(input, auctionCalendar, bids);
		bidder.setUser(new Bidder("Tester", "email"));
	}

	@Test
	public void testGetItemFromListIfExists() {
		String expectedName = "Red";
		double expectedStartBid = 5.0;
		Item testItem = bidder.getItemFromList("Red");

		//No equals method for Item class, so I used multiple asserts
		//to check all of it's fields.
		assertEquals(expectedName, testItem.getMyName());
		assertEquals(expectedStartBid, testItem.getMyStartingBid(), 0.0000001);
	}
	
	@Test
	public void testGetItemFromListIfDoesntExist() {
		Item testItem = bidder.getItemFromList("Purple");

		assertNull(testItem);
	}
	
	@Test
	public void testGetIndexedBids() {
		String expected = "1. Red - Bid Amount = $20.00\n";
		assertEquals(expected, bidder.getIndexedBids());
	}
	@Test
	public void testGetBidSelectionOnActualBid() {
		String expected = "Red";
		assertEquals(expected, bidder.getBidSelection(0));
	}
	
	@Test
	public void testGetBidSelectionOnBadSelection() {
		String expected = "";
		assertEquals(expected, bidder.getBidSelection(100));
	}
	
	@Test
	public void testGetSelectedItem() {
		String expectedName = "Red";
		double expectedStartBid = 5.0;
		Item testItem = bidder.getSelectedItem("Red");
		
		//No equals method for Item class, so I used multiple asserts
		//to check all of it's fields.
		assertEquals(expectedName, testItem.getMyName());
		assertEquals(expectedStartBid, testItem.getMyStartingBid(), 0.0000001);
	}

}
