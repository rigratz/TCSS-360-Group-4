import java.util.HashMap;
import java.util.Map;

/* William Jensen
 * 11/6/15
 * This class represents an auction for the auction central program.
 */


public class Item {
	
	//fields
	private String myName;												//the name of the item
	private double myStartingBid;										//the starting bid of the item
	private Map<String, Double> myBids = new HashMap<String, Double>();	//HashMap for mapping users to bids
	
	//constructor
	public Item(String theName, double theStartingBid) {
		setMyName(theName);
		setMyStartingBid(theStartingBid);
	}
	
	//for bidding on an Item
	//THIS WILL REPLACE THE BIDDERS ORIGINAL BID because the bidder can only have one current bid on an item
	//returns false if the bid is less than the starting bid of the item
	//returns true if the bid is successfully added to the items map of bids
	public boolean Bid(String theBidder, double theBid) {
		if(theBid < myStartingBid) {
			return false;
		} else if(myBids.containsKey(theBidder)) {
			myBids.remove(theBidder);
		}
		myBids.put(theBidder, new Double(theBid));
		return true;
	}

	//Getters and setters for fields//
	
	//for changing the name of the item
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}

	//for changing the starting bid of the item
	public double getMyStartingBid() {
		return myStartingBid;
	}
	public void setMyStartingBid(double myStartingBid) {
		this.myStartingBid = myStartingBid;
	}

	//for getting the map of bids
	public Map<String, Double> getMyBids() {
		return myBids;
	}

	public void setMyBids(Map<String, Double> myBids) {
		this.myBids = myBids;
	}
	
	
}
