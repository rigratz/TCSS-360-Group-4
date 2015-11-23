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
	public void testGetMyOrganizationName() {
		assertEquals(testNPEmp.getMyOrganizationName(), "Dunder Mifflin");
	}
	
	@Test
	public void testSetMyOrganizationName() {
		testNPEmp.setMyOrganizationName("DMI");
		assertEquals(testNPEmp.getMyOrganizationName(), "DMI");
	}
}
