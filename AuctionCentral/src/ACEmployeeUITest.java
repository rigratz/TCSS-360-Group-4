import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class ACEmployeeUITest {

	private ACEmployeeUI employee;
	private CalendarClass auctionCalendar;
	
	@Before
	public void setUp() throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		auctionCalendar = new CalendarClass();
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(new Item("Red", 5.0));
		itemList.add(new Item("Green", 10.0));
		
		ArrayList<Auction> auctions = new ArrayList<Auction>();
		auctions.add(new Auction("GoodWill", 12, 25, 2015, 8, 10, itemList));
		auctionCalendar.insertAuctions(auctions);
		
		employee = new ACEmployeeUI(input, auctionCalendar);
	}

	@Test
	public void testGetIndexedAuctions() {
		String expected = "1. " + auctionCalendar.getListOfAuctions().get(0).getMyName() + "\n";
		assertEquals(expected, employee.getIndexedAuctions());
	}
	
	@Test
	public void testGetIndexedItems() {
		String expected = "1. Red - Starting Bid = 5.0\n2. Green - Starting Bid = 10.0\n";
		assertEquals(expected, employee.getIndexedItems(auctionCalendar.getListOfAuctions().get(0)));
	}

}
