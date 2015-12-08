import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the User Interface for bidders.
 * 
 * @author Riley Gratzer
 */
public class BidderUI extends AbstractUI {
	
	private Auction myAuction;
	protected Map<String, Double> myBids;
	
	public BidderUI(BufferedReader theInput, CalendarClass theCalendar) {
		super(theInput, theCalendar);
		myBids = new HashMap<String, Double>();
	}
	
	public BidderUI(BufferedReader theInput, CalendarClass theCalendar, Map<String, Double> theBids) {
		super(theInput, theCalendar);
		myBids = theBids;
	}

/*
 * Menu Methods
 */
	
	/**
	 * Main menu for the BidderUI.
	 */
	@Override
	public void menu(User theUser) {
		setUser(theUser);
		int selection = 0;
		
		while (selection != 5) {
			System.out.println("\nHello, Bidder! What would you like to do?");
			System.out.println("\n1. Choose Auction\n2. Bid on Item in Auction");
			System.out.println("3. Change Existing Bid\n4. Cancel existing bid\n5. Log Out");
			selection = readInt();
			switch (selection) {
				case 1: chooseAuction(); break;
		
				case 2: if (myAuction != null) {
							bidMenu();
						} else {
							System.out.println("No Auction selected.");
						} 
						break;
			
				case 3: changeBidMenu(); break;
				
				case 4: cancelBidMenu(); break;
				
				case 5: System.out.println("Logging out... Good-bye!"); break;
				default: System.out.println("Invalid selection!"); break;
			}
		}
		
	}
	
	/**
	 * Menu for a user to select from all available auctions.
	 */
	public void chooseAuction() {
		int selection;
		System.out.println("Please select an auction:\n");
		System.out.println(getIndexedAuctions());
		selection = readInt() - 1;
		if (selection >= 0 && selection < myAuctionCalendar.getListOfAuctions().size()) {
			myAuction = myAuctionCalendar.getListOfAuctions().get(selection);
			System.out.println("Auction selected.");
		} else {
			System.out.println("Selection error. Try again.");
		}
	}
	
	/**
	 * Menu for a user to place a new bid on an item.
	 */
	public void bidMenu() {
		int selection = 0;
		boolean previousBid = false;
		if (!myAuction.getMyItems().isEmpty()){
		System.out.println("Please select one of the following items to bid on:\n");
		System.out.println(getIndexedItems(myAuction));
			
		selection = readInt() - 1;
		
		if (selection >= 0 && selection < myAuction.getMyItems().size()) {
			if (myAuction.getMyItems().get(selection).getMyBids().containsKey(myUser.getMyName())) {
				previousBid = true;
			}
		} else {
			System.out.println("Selection error.");
		}	
			
		if (!previousBid) {
			System.out.println("Please enter bid amount:");
			double bid = readDouble();
			boolean valid = ((Bidder)myUser).placeBid(myAuction.getMyItems().get(selection), bid);
			if (valid) {
				System.out.println("Bid placed successfully");
			} else {
				System.out.println("Not a valid bid");
			}
		} else {
			System.out.println("You already have a pre-existing bid for this item.");
		}
		} else {
			System.out.println("No items to bid on.");
		}
	}
	
	/**
	 * Menu for a user to change an existing bid.
	 */
	public void changeBidMenu() {
		int selection;
		String itemName, indexedBids;
		Item theItem;
		indexedBids = getIndexedBids();
		if (indexedBids.equals("")) {
			System.out.println("You currently have no bids placed.");
			return;
		}
		System.out.println("You currently have bids on the following items:\n");
		System.out.println(indexedBids);
		System.out.println("Which bid would you like to change?\n");
		
		selection = readInt() - 1;
		itemName = getBidSelection(selection);
		theItem = getSelectedItem(itemName);
		if (theItem != null) {
			System.out.println("Please enter new bid amount:");
			double newBid = readDouble();
			if (theItem.bid(myUser.getMyName(), newBid)) {
				System.out.println("Bid changed");
			} else {
				System.out.println("Bid could not be changed to specified amount.");
			}
		} else {
			System.out.println("No bid for selected item to change.");
		}
	}
	
	/**
	 * Menu for a bidder to cancel an existing bid.
	 */
	public void cancelBidMenu() {
		int selection;
		String itemName, indexedBids;
		Item theItem;
		indexedBids = getIndexedBids();
		
		if (indexedBids.equals("")) {
			System.out.println("You currently have no bids placed.");
			return;
		}
		System.out.println("You currently have bids on the following items:\n");
		
		
		System.out.println(indexedBids);
		System.out.println("Which bid would you like to cancel?\n");
		
		selection = readInt() - 1;
		itemName = getBidSelection(selection);
		theItem = getSelectedItem(itemName);
		if (theItem != null && ((Bidder)myUser).cancelBid(theItem)) {
			System.out.println("Bid successfully removed!");
		} else {
			System.out.println("Bid could not be removed.");
		}
	}
	
/*
 * Helper Methods
 */
	/**
	 * Retrieves an item from a list if it exists.
	 * 
	 * @param theName is the item to look for.
	 * @return the item if it exists (or null otherwise)
	 */
	public Item getItemFromList(String theName) {
		Item foundItem = null;
		List<Auction> list = myAuctionCalendar.getListOfAuctions();
		for(Auction auctions : list) {
			for (Item i : auctions.getMyItems()) {
				if (i.getMyName().equals(theName)) {
					foundItem = i;
					break;
				}
			}
		}
		return foundItem;
	}
	
	/**
	 * Returns an indexed strings of all of the current user's bids.
	 * @return an indexed string of bids to select.
	 */
	public String getIndexedBids() {
		myBids = new HashMap<String, Double>();
		for (Auction auction : myAuctionCalendar.getListOfAuctions()) {
			for (Item item : auction.getMyItems()) {
				if (item.getMyBids().containsKey(myUser.getMyName())) {
					//System.out.println(item.getMyName());
					myBids.put(item.getMyName(), item.getMyBids().get(myUser.getMyName()));
				}
			}
		}
		if (myBids.isEmpty()) {
			return "";
		}
		int i = 1;
		StringBuilder toReturn = new StringBuilder();
		for (String key : myBids.keySet()) {
			toReturn.append(i);
			toReturn.append(". ");
			toReturn.append(key);
			toReturn.append(" - Bid Amount = ");
			toReturn.append(myBids.get(key));
			toReturn.append("\n");
			i++;
		}
		return toReturn.toString();
	}
	
	/**
	 * Retrieves the name of an item to be bid on, based on a prior indexed input selection.
	 * @param theSelection is the index of the bid to be selected.
	 * @return the name of the item that was selected.
	 */
	public String getBidSelection(int theSelection) {
		int i = 0;
		String toReturn = "";
		for (String key : myBids.keySet()) {
			if (theSelection == i) {
				toReturn = key;
				break;
			}
			i++;
		}
		return toReturn;
	}
	
	/**
	 * May not be the best way to do this, in case of multiple items of the same name,
	 * but this is what I'm working with for now...
	 * 
	 * @param itemName is the name of the item you want to retrieve.
	 * @return the selected Item if it exists.
	 */
	public Item getSelectedItem(String itemName) {
		Item toReturn = null;
		
		for (Auction auction : myAuctionCalendar.getListOfAuctions()) {
			for (Item item : auction.getMyItems()) {
				if (item.getMyName().equals(itemName)) {
					toReturn = item;
					break;
				}
			}
		}
		return toReturn;
	}
}
