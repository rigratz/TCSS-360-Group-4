
/**
 * This is an employee of a non-profit
 * @author tempest
 *
 */
public class NonProfitEmployee extends User {
	
	/** Name of the non-profit organization. */
	private String myOrganizationName;

	/**
	 * * Constructs a Non-Profit Employee
	 * @param theName
	 * @param theContact
	 */
	public NonProfitEmployee(String theName, String theContact, String theOrganizationName) {
		super(theName, theContact);
		
		myOrganizationName = theOrganizationName; 
	}

	public Auction scheduleAuction(String theNonProfit, int theMonth, int theDay, int theYear, 
			int theStartTime, int theEndTime) {
		return new Auction(theNonProfit, theMonth, theDay, theYear, theStartTime, theEndTime);
	}

	public String getMyOrganizationName() {
		return myOrganizationName;
	}

	public void setMyOrganizationName(String theOrganizationName) {
		myOrganizationName = theOrganizationName;
	}	
}
