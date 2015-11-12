/**
 * Abstract User class
 * @author tempest
 * 11/8/2015
 */
public abstract class User {

	private String myName;		// name of user
	private String myContact;	// contact phone number

	/**
	 * @param myName
	 * @param myContact
	 */
	public User(String theName, String theContact) {
		myName = theName;
		myContact = theContact;
	}

	public String viewCalendar(CalendarClass theCalendar) {
		return theCalendar.getListOfAuctions();
	}
	
	public String viewAuction(CalendarClass theCalendar, String theAuctionName) {
		return theCalendar.viewAuction(theAuctionName);
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
