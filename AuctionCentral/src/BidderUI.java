import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class BidderUI extends AbstractUI {
	
	private Auction myAuction;
	
	public BidderUI(BufferedReader theInput, CalendarClass theCalendar) {
		super(theInput, theCalendar);
	}

/*
 * Menu Methods
 */
	
	@Override
	public void menu(User theUser) {
		myUser = theUser;
		int selection = 0;
//		String selectName = "";
//		String finder = "";
//		Auction selectedAuction = null;
//		List<Auction> auctionList = null;
//		List<Item> selectedItemList = null;
//		Item selectedItem = null;
//		boolean found = false;
//		boolean previousBid = false;
//		int index = -1;
		
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
		AuctionCentral.saveAndQuit();
		
	}
	
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
	
	public void bidMenu() {
		int selection = 0;
		boolean previousBid = false;
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
	}
	
	public void changeBidMenu() {
		int selection;
		String itemName;
		System.out.println("You currently have bids on the following items:\n");
		System.out.println(getIndexedBids());
		System.out.println("Which bid would you like to change?\n");
		
		selection = readInt() - 1;
		itemName = getBidSelection(selection);
		
		if (itemName != "") {
			System.out.println("Please enter new bid amount:");
			double newBid = readDouble();
			if (((Bidder)myUser).changeBid(((Bidder) myUser).getMyBids().get(itemName), newBid)) {
				System.out.println("Bid changed.");
			} else {
				System.out.println("Bid could not be changed to specified amount.");
			}
		} else {
			System.out.println("No bid for selected item to cancel.");
		}
	}
	public void cancelBidMenu() {
		 System.out.println("You currently have bids on the following items:\n");
			System.out.println(((Bidder) myUser).getMyBids() + "\n");
			System.out.println("Which bid would you like to cancel?\n");
			selectName = readString();
			selectedItem = getItemFromList(selectName);
			if (selectedItem != null && ((Bidder)myUser).cancelBid(selectedItem)) {
				System.out.println("Bid successfully canceled.");
			} else {
				System.out.println("No bid for selected item to cancel.");
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
	
	public String getIndexedBids() {
		Map<String, Double> temp = ((Bidder) myUser).getMyBids();
		int i = 1;
		StringBuilder toReturn = new StringBuilder();
		for (String key : temp.keySet()) {
			toReturn.append(i);
			toReturn.append(". ");
			toReturn.append(key);
			toReturn.append(" - Bid Amount = ");
			toReturn.append(temp.get(key));
			toReturn.append("\n");
			i++;
		}
		return toReturn.toString();
	}
	
	public String getBidSelection(int theSelection) {
		Map<String, Double> temp = ((Bidder) myUser).getMyBids();
		int i = 0;
		String toReturn = "";
		for (String key : temp.keySet()) {
			if (theSelection == i) {
				toReturn = key;
				break;
			}
			i++;
		}
		return toReturn;
	}
}
