import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({ TestAuction.class, TestBidder.class, TestCalendarClass.class, TestDay.class, TestItem.class,
		TestNonProfitEmployee.class, TestUser.class, TestAuction.class, ACEmployeeUITest.class,
		BidderUITest.class, NonProfitUITest.class, TestMonth.class })

public class AllTests {

}
