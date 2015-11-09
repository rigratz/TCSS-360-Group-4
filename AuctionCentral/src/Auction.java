/* William Jensen
 * 11/6/15
 * This class represents an auction for the auction central program.
 */

import java.util.ArrayList;
import java.util.List;


public class Auction {
	
	//fields
	private String myNonProfit;							//name of the nonprofit
	private String myName; 								//name of auction
	private String myDate; 								//Date of auction 
	private List<Item> myItems = new ArrayList<Item>();	//Items that have been added to the auction.
	
	//constructor
	public Auction(String theNonProfit, String theDate) {
		myName = theNonProfit + "-" + theDate;
		myNonProfit = theNonProfit;
		myDate = theDate;
	}
	
	//For adding 1 item to the auction
	public void addItem(Item theItem) {
		myItems.add(theItem);
	}

	//Getters and setters for fields//
	
	//for changing the date of the auction
	public String getMyDate() {
		return myDate;
	}
	public void setMyDate(String theDate) {
		myDate = theDate;
		myName = myNonProfit + "-" + theDate;
	}
	
	//for setting the name of the auction
	public void setName(String theNonProfit) {
		myName = theNonProfit + "-" + myDate;
	}
	public String getMyName() {
		return myName;
	}
	
	//for editing the list of Items the auction currently contains
	public List<Item> getMyItems() {
		return myItems;
	}
	public void setMyItems(List<Item> myItems) {
		this.myItems = myItems;
	}
	
}
