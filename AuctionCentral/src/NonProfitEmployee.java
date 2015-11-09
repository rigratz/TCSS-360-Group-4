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

	private void scheduleAuction(Auction theAuction, String theName, String theDate) {
		new Auction(theName, theDate);
	}
	
	// I think this isn't necessary?
	private void addAuctionInfo() {
		
	}
	
	private void editAuctionName(Auction theAuction, String theAuctionName) {
		theAuction.setName(theAuctionName);
	}
	
	private void editAuctionDate(Auction theAuction, String theAuctionDate) {
		theAuction.setMyDate(theAuctionDate);
	}
	
	private void addAuctionItem(Auction theAuction, String theItemName, double theStartingBid) {
		theAuction.addItem(new Item(theItemName, theStartingBid));
	}
	
	private void editAuctionItemName(Item theItem, String theName) {
		theItem.setMyName(theName);
	}
	
	private void editAuctionItemStartingBid(Item theItem, double theStartingBid) {
		theItem.setMyStartingBid(theStartingBid);
	}

	public String getMyOrganizationName() {
		return myOrganizationName;
	}

	public void setMyOrganizationName(String theOrganizationName) {
		myOrganizationName = theOrganizationName;
	}


	
}
