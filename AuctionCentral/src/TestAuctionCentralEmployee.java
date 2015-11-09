import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestAuctionCentralEmployee {

	private User testAuctionCentralEmployee;
	private AuctionCentralEmployee testAuctionCentralEmployee2;
	
	@Before
	public void setUp() {
		testAuctionCentralEmployee = new AuctionCentralEmployee("Bob", "(555)1234567");
		testAuctionCentralEmployee2 = new AuctionCentralEmployee("Sally", "(012)9876543");
	}

	@Test
	public void testViewCalendar() {
		fail("Not yet implemented");
	}

}
