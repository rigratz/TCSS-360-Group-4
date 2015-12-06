import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestNonProfitEmployee {

	private NonProfitEmployee testNPEmp;
	private CalendarClass testCalendar;
	
	@Before
	public void setUp() {
		testNPEmp = new NonProfitEmployee("Jim", "(234)567-8900", "Dunder Mifflin");
		testCalendar = new CalendarClass();
	}

	@Test
	public void testScheduleAuction() {
		Auction testAuction = testNPEmp.scheduleAuction("Dunder Mifflin", 02, 19, 2016, 12, 14);
		testCalendar.addAuction(testAuction);
		assertEquals(testCalendar.getListOfAuctions().get(0).toString(), "Dunder Mifflin-2/19/2016 Start: 12 End: 14");
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
