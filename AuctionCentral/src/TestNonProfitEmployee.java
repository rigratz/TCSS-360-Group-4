import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestNonProfitEmployee {

	private NonProfitEmployee testNPEmp;
	
	@Before
	public void setUp() {
		testNPEmp = new NonProfitEmployee("Jim", "(234)567-8900", "Dunder Mifflin");
	}

	@Test
	public void testScheduleAuction() {
		testNPEmp.scheduleAuction("Dunder Mifflin", 02, 19, 2016, 12, 14);
		//assertEquals();
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
