
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

	public void editAuctionName(Auction theAuction, String theAuctionName) {
		theAuction.setName(theAuctionName);
	}
	
	public void editAuctionDay(Auction theAuction, int theDay) {
		theAuction.setMyDay(theDay);
	}
	
	public void editAuctionMonth(Auction theAuction, int theMonth) {
		theAuction.setMyMonth(theMonth);
	}
	
	public void editAuctionYear(Auction theAuction, int theYear) {
		theAuction.setMyYear(theYear);
	}
	
	public void editAuctionStartTime(Auction theAuction, int theStartTime) {
		theAuction.setMyStartTime(theStartTime);
	}
	
	public void editAuctionEndTime(Auction theAuction, int theEndTime) {
		theAuction.setMyEndTime(theEndTime);
	}
	
	public void addAuctionItem(Auction theAuction, String theItemName, double theStartingBid) {
		theAuction.addItem(new Item(theItemName, theStartingBid));
	}
	
	public void editAuctionItemName(Item theItem, String theName) {
		theItem.setMyName(theName);
	}
	
	public void editAuctionItemStartingBid(Item theItem, double theStartingBid) {
		theItem.setMyStartingBid(theStartingBid);
	}

	public String getMyOrganizationName() {
		return myOrganizationName;
	}

	public void setMyOrganizationName(String theOrganizationName) {
		myOrganizationName = theOrganizationName;
	}	
}
