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
	private String viewCalendar(CalendarClass theCalendar) {
		return theCalendar.getListOfAuctions();
	}
	
	private void viewAuction(Auction theAuction) {
		System.out.println("Auction Name: " + theAuction.getMyName() +
				" Auction Date: " + theAuction.getMyDate());
	}
	
	private void viewItem(Item theItem) {
		System.out.println("Item Name: " + theItem.getMyName() + 
				" Starting Bid: " + theItem.getMyStartingBid());
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
