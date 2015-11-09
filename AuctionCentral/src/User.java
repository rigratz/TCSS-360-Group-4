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
	private String viewCalendar(CalendarClass calendar) {
		calendar.getListOfAuctions();
	}
	
	private void viewAuction(Auction auction) {
		System.out.println("Auction Name: " + auction.getMyName() +
				" Auction Date: " + auction.getMyDate());
	}
	
	private void viewItem(Item item) {
		System.out.println("Item Name: " item.getMyName() + 
				" Starting Bid: " + item.getMyStartingBid());
	}
	
	// not sure what we want to do here
	private void logIn() {
		 
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public String getMyContact() {
		return myContact;
	}

	public void setMyContact(String myContact) {
		this.myContact = myContact;
	}

	
}
