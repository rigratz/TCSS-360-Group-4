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
	private int myDay;									//Day of the Auction
	private int myMonth;								//Month the auction is in
	private int myYear;									//Year the auction is in
	private int myStartTime;							//Start time of the Auction
	private int myEndTime;								//End time of the auction
	private List<Item> myItems = new ArrayList<Item>();	//Items that have been added to the auction.
	
	//constructor
	public Auction(String theNonProfit, int theMonth, int theDay, int theYear, 
										int theStartTime, int theEndTime) {
		myNonProfit = theNonProfit;
		myDate = theMonth + "/" + theDay + "/" + theYear;
		setMyDay(theDay);
		setMyMonth(theMonth);
		setMyYear(theYear);
		myName = theNonProfit + "-" + myDate;
		myStartTime = theStartTime;
		myEndTime = theEndTime;
	}
	
	//constructor
	public Auction(String theNonProfit, int theMonth, int theDay, int theYear, 
										int theStartTime, int theEndTime, List<Item> theItems) {
		myNonProfit = theNonProfit;
		myDate = theMonth + "/" + theDay + "/" + theYear;
		setMyDay(theDay);
		setMyMonth(theMonth);
		setMyYear(theYear);
		myName = theNonProfit + "-" + myDate;
		myStartTime = theStartTime;
		myEndTime = theEndTime;
		myItems = theItems;
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
	public void setMyDate(int theMonth, int theDay, int theYear) {
		myDate = theMonth + "/" + theDay + "/" + theYear;
		myName = myNonProfit + "-" + myDate;
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
	public void setMyItems(List<Item> theItems) {
		myItems = theItems;
	}

	public int getMyDay() {
		return myDay;
	}

	public void setMyDay(int theDay) {
		myDay = theDay;
		myDate = myMonth + "/" + theDay + "/" + myYear;
	}

	public int getMyMonth() {
		return myMonth;
	}

	public void setMyMonth(int theMonth) {
		myMonth = theMonth;
		myDate = theMonth + "/" + myDay + "/" + myYear;
	}

	public int getMyYear() {
		return myYear;
	}

	public void setMyYear(int theYear) {
		myYear = theYear;
		myDate = myMonth + "/" + myDay + "/" + theYear;
	}

	public int getMyStartTime() {
		return myStartTime;
	}

	public void setMyStartTime(int theStartTime) {
		myStartTime = theStartTime;
	}

	public int getMyEndTime() {
		return myEndTime;
	}

	public void setMyEndTime(int theEndTime) {
		myEndTime = theEndTime;
	}
	
	//toString for Calendar and console I/O
	public String toString() {
		return myName + myStartTime + myEndTime;
	}
}
