/**
 * Abstract User class
 * @author tempest
 * 11/8/2015
 */
public abstract class User {

	private String myName;		// name of user
	private String myContact;

	/**
	 * @param myName
	 * @param myContact
	 */
	public User(String theName, String theContact) {
		myName = theName;
		myContact = theContact;
	}

	// not sure how we are going to display the calendar on console
	public String viewCalendar(CalendarClass theCalendar) {
		return theCalendar.getListOfAuctions();
	}
	
	public String viewAuction(Auction theAuction) {
		return "Auction Name: " + theAuction.getMyName() +
				" Auction Date: " + theAuction.getMyDate();
	}
	
	public String viewItem(Item theItem) {
		return String.format("Item Name: %s Starting Bid: $%.2f",
				theItem.getMyName(), theItem.getMyStartingBid());
	}
	
	// not sure what we want to do here
	private void logIn() {
		 
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String theName) {
		myName = theName;
	}

	public String getMyContact() {
		return myContact;
	}

	public void setMyContact(String theContact) {
		myContact = theContact;
	}

	
}
