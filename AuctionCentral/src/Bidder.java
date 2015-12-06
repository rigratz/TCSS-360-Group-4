
import java.util.HashMap;
import java.util.Map;

/**
 * @author tempest
 *
 */
public class Bidder extends User {

	private static final long serialVersionUID = 601320092448220174L;
	private Map<String, Double> myBids; // bidder's list of bids
	
	/**
	 * * Constructs a Bidder
	 * @param theName
	 * @param theContact
	 */
	public Bidder(String theName, String theContact) {
		super(theName, theContact);
		myBids = new HashMap<String, Double>();
	}
	
	/**
	 * * Constructs a Bidder with map of bids
	 * @param theName
	 * @param theContact
	 */
	public Bidder(String theName, String theContact, Map<String, Double> theBids) {
		super(theName, theContact);
		myBids = theBids;
	}

	/**
	 * Place bid and add it to the list of bidder's bids.
	 * @param theItem
	 * @param theBid
	 */
	public boolean placeBid(Item theItem, double theBid) {
		if (theItem.bid(getMyName(), theBid)) {
			myBids.put(theItem.getMyName(), theBid);
			return true;
		}
		return false;
	}
	
	/**
	 * If bid is removed, remove it from the list of bidder's bids.
	 * @param theItem
	 * @param theBid
	 * @return
	 */
	public boolean cancelBid(Item theItem) {
		if (theItem.removeBid(getMyName())) {
			myBids.remove(theItem.getMyName(), myBids.get(theItem.getMyName()));
			return true;
		}
		return false;
	}
	
	/**
	 * Change bid by placing a new bid.
	 * @param theItem
	 * @param theBid
	 */
	// this is the same as placeBid because of the way Item's bid
	// method works
	public boolean changeBid(Item theItem, double theBid) {
		if (theItem.bid(getMyName(), theBid)) {
			myBids.put(theItem.getMyName(), theBid);
			return true;
		}
		return false;
	}

	public Map<String, Double> getMyBids() {
		return myBids;
	}

}
