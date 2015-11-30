import java.io.BufferedReader;
import java.util.List;


public class NonProfitUI  extends AbstractUI {

	public NonProfitUI(BufferedReader theInput, CalendarClass theCalendar) {
		super(theInput, theCalendar);
	}

/*
* Menu Methods	
*/
	@Override
	public void menu(User theUser) {
		myUser = theUser;
		int selection = 0;
		Auction auct = null;
		
		
		while (selection != 5) {
			if (hasAuction()) {
				auct = getAuction();
			}
			System.out.println("\nHello, Non-Profit Organization! What would you like to do?");
			System.out.println("\n1. Schedule Auction");
			System.out.println("2. Edit Auction Information\n3. Enter Item Information");
			System.out.println("4. Edit Item Information\n5. Log Out");
			selection = readInt();
			switch (selection) {
				case 1: if (auct == null) {
							scheduleAuctionMenu();
						} else {
							System.out.println("You already have an auction scheduled.");
						} break;
				case 2: if (hasAuction()) {
							int innerSelect, newArg;
							System.out.println("What would you like to edit?\n");
							System.out.println("1. Day\n2: Month\n3. Year\n4. Start Time\n5. End Time\n");
							innerSelect = readInt();
							switch (innerSelect) {
							case 1: System.out.println("Please enter new day:");
									newArg = readInt();
									auct.setMyDay(newArg);break;
									
							case 2: System.out.println("Please enter new month:"); 
									newArg = readInt(); 
									auct.setMyMonth(newArg); break;
							case 3: System.out.println("Please enter new year:"); 
									newArg = readInt();
									auct.setMyYear(newArg); break;
							case 4: System.out.println("Please enter new start time:"); 
									newArg = readInt(); 
									auct.setMyStartTime(newArg); break;
							case 5: System.out.println("Please enter new end time:"); 
									newArg = readInt();
									auct.setMyEndTime(newArg); break;
							default: System.out.println("Incorrect input."); break;
							}
						} else {
							System.out.println("You have no auction to edit.");
						} break;
				case 3: if (hasAuction()) {
							String itemName = "";
							double itemStart = 0.0;
							
							System.out.println("Please enter the name of the item:\n");
							itemName = readString();
							System.out.println("Please enter the starting bid amount for the item:\n");
							itemStart = readDouble();
							auct.addItem(new Item(itemName, itemStart));
						} else {
							System.out.println("You have no auction to add items to.");
						} break;
				
				case 4: if (hasAuction()) {
							String itemName, itemNewName;
							Item editItem = null;
							double itemNewStart;
							int innerSelect;
							
							System.out.println("Which item would you like to edit?\n");
							System.out.println(auct.itemsToString());
							itemName = readString();
							for (Item i : auct.getMyItems()) {
								if (itemName.equals(i.getMyName())) {
									editItem = i;
									break;
								}
							}
							if (editItem != null) {
								System.out.println("What would you like to change?\n");
								System.out.println("1. Item name\n2. Item starting bid");
								innerSelect = readInt();
							
								switch (innerSelect) {
									case 1: System.out.println("Please enter new item name:\n");
										itemNewName = readString();
										editItem.setMyName(itemNewName); break;
									case 2: System.out.println("Please enter new starting bid:\n");
										itemNewStart = readDouble();
										editItem.setMyStartingBid(itemNewStart); break;
								}
							} else {
								System.out.println("Item does not exist.\n");
							}
						} break;
				case 5: System.out.println("Logging out... Good-bye!"); break;
				default: System.out.println("Invalid selection!"); break;
			}
		AuctionCentral.saveAndQuit();
		}
	}
	
	/**
	 * Menu for scheduling an auction.
	 */
	public void scheduleAuctionMenu() {
		CalendarClass c = myAuctionCalendar;
		
		int month, day, year, start, end;
		System.out.println("Enter Month:\n");
		month = readInt();
		System.out.println("Enter Day:\n");
		day = readInt();
		System.out.println("Enter Year:\n");
		year = readInt();
		System.out.println("Enter Start Time:\n");
		start = readInt();
		System.out.println("Enter End Time:\n");
		end = readInt();
		if (c.checkOrganization(((NonProfitEmployee)myUser).getMyOrganizationName())
				&& c.isAvailable(month, day, start, end)
				&& c.belowMaxAuctions()
				&& c.belowMaxDaysToFuture(month, day, year)
				&& c.belowWeekLimit(month, day)) {
			c.addAuction(((NonProfitEmployee)myUser).scheduleAuction(((NonProfitEmployee)myUser).getMyOrganizationName(), month, day, year, start, end));
			System.out.println("Auction Scheduled");
	
		} else {
			if (!c.checkOrganization(((NonProfitEmployee)myUser).getMyOrganizationName())) {
				System.out.println("Your organization already has an auction scheduled.\n");
			}
			if (!c.belowMaxDaysToFuture(month, day, year)) {
				System.out.println("Selected time is too far in advance.\n");
			}
			if (!c.belowMaxAuctions()) {
				System.out.println("Sorry, we have a full set of auctions right now.\n");
			}
			if (!c.belowWeekLimit(month, day)) {
				System.out.println("Sorry, we are full for that particular week\n");
			}
			if (!c.isAvailable(month, day, start, end)) {
				System.out.println("Selected time is not available.\n");
			}
		}
	}
	
	public void editActionMenu() {
		
	}
	public void enterItemMenu() {
		
	}
	public void editItemMenu() {
		
	}
	
/*
 * Helper Methods	
 */
	/**
	 * Checks if an NPO has an auction scheduled.
	 * 
	 * @return if NPO has an auction scheduled.
	 */
	public boolean hasAuction() {
		List<Auction> auctions = myAuctionCalendar.getListOfAuctions();
		
		boolean found = false;
		for (Auction a : auctions) {
			if (a.getMyNonProfit().equals(((NonProfitEmployee)myUser).getMyOrganizationName())) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	/**
	 * Gets an auction from a non-profit.
	 * 
	 * @return the desired auction.
	 */
	public Auction getAuction() {
		List<Auction> auctions = myAuctionCalendar.getListOfAuctions();
		Auction auct = null;
		
		for (Auction a : auctions) {
			if (a.getMyNonProfit().equals(((NonProfitEmployee)myUser).getMyOrganizationName())) {
				auct = a;
				break;
			}
		}
		return auct;
	}
}
