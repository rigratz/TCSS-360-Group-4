import java.io.BufferedReader;
import java.util.List;

/**
 * This is the User Interface for Non Profit Organizations.
 * 
 * @author Riley Gratzer
 */
public class NonProfitUI  extends AbstractUI {

	public NonProfitUI(BufferedReader theInput, CalendarClass theCalendar) {
		super(theInput, theCalendar);
	}

/*
* Menu Methods	
*/
	/**
	 * This is the main menu for a NonProfit Employee.
	 */
	@Override
	public void menu(User theUser) {
		setUser(theUser);
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
							editAuctionMenu(auct);
						} else {
							System.out.println("You have no auction to edit.");
						} break;
				case 3: if (hasAuction()) {
							enterItemMenu(auct);
						} else {
							System.out.println("You have no auction to add items to.");
						} break;
				
				case 4: if (hasAuction()) {
							editItemMenu(auct);
							} else {
								System.out.println("Item does not exist.\n");
							} break;
				case 5: System.out.println("Logging out... Good-bye!"); break;
				default: System.out.println("Invalid selection!"); break;
			}
		}
	}
	
	/**
	 * Menu for scheduling an auction.
	 */
	public void scheduleAuctionMenu() {
		CalendarClass calendar = myAuctionCalendar;
		boolean scheduled = false;
		do {
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
			scheduled = isTimeAvailable(calendar, month, day, year, start, end);
			if (scheduled) {
				calendar.addAuction(((NonProfitEmployee)myUser).scheduleAuction(((NonProfitEmployee)myUser).getMyOrganizationName(), month, day, year, start, end));
				System.out.println("Auction Scheduled");
			}
		} while (!scheduled);
	}
	
	
	/**
	 * Menu to edit currently scheduled time for auction.
	 * @param auct
	 */
	public void editAuctionMenu(Auction auct) {
		CalendarClass calendar = myAuctionCalendar;
		int innerSelect, newArg;
		boolean edited = false;
		do {
			System.out.println(getAuctionDetails(auct));
			System.out.println("What would you like to edit?\n");
			System.out.println("1. Day\n2: Month\n3. Year\n4. Start Time\n5. End Time\n");
			innerSelect = readInt();
			for (int i = 0; i < myAuctionCalendar.getListOfAuctions().size(); i++) {
				if (myAuctionCalendar.getListOfAuctions().get(i).getMyNonProfit().equals(auct.getMyNonProfit())) {
					myAuctionCalendar.getListOfAuctions().remove(i);
					//System.out.println("deleted");
					break;
				}
			}
			switch (innerSelect) {
			case 1: System.out.println("Please enter new day:");
				newArg = readInt();
				edited = isTimeAvailable(calendar, auct.getMyMonth(), newArg, auct.getMyYear(), 
											auct.getMyStartTime(), auct.getMyEndTime());
				if (edited) {
					auct.setMyDay(newArg);
				}
				break;
				
			case 2: System.out.println("Please enter new month:"); 
				newArg = readInt(); 
				edited = isTimeAvailable(calendar, newArg, auct.getMyDay(), auct.getMyYear(),
											auct.getMyStartTime(), auct.getMyEndTime());
				if (edited) {
					auct.setMyMonth(newArg);
				}
				break;

			case 3: System.out.println("Please enter new year:"); 
				newArg = readInt();
				edited = isTimeAvailable(calendar, auct.getMyMonth(), auct.getMyDay(), 
										newArg, auct.getMyStartTime(), auct.getMyEndTime());
				if (edited) {
					auct.setMyYear(newArg);
				}
				break;

			case 4: System.out.println("Please enter new start time:"); 
				newArg = readInt(); 
				edited = isTimeAvailable(calendar, auct.getMyMonth(), auct.getMyDay(), 
										auct.getMyYear(), newArg, auct.getMyEndTime());
				if (edited) {
					auct.setMyStartTime(newArg);
				}
				break;

			case 5: System.out.println("Please enter new end time:"); 
				newArg = readInt();
				edited = isTimeAvailable(calendar, auct.getMyMonth(), auct.getMyDay(), 
										auct.getMyYear(), auct.getMyStartTime(), newArg);
				if (edited) {
					auct.setMyEndTime(newArg);
				}
				break;

			default: System.out.println("Incorrect input."); break;
			}
			myAuctionCalendar.getListOfAuctions().add(auct);
		} while (!edited);
		System.out.println("Edit successful!\n");
	}
	
	/**
	 * Menu used for a NonProfit to enter a new Item's info.
	 * @param auct
	 */
	public void enterItemMenu(Auction auct) {
		String itemName = "";
		double itemStart = 0.0;
		
		System.out.println("Please enter the name of the item:\n");
		itemName = readString();
		System.out.println("Please enter the starting bid amount for the item:\n");
		itemStart = readDouble();
		auct.addItem(new Item(itemName, itemStart));
	}
	/**
	 * Menu used for a NonProfit to edit an Item's information.
	 * @param auct
	 */
	public void editItemMenu(Auction auct) {
		String itemNewName;
		Item editItem = null;
		double itemNewStart;
		int innerSelect;
		int selection = 0;
		
		System.out.println("Which item would you like to edit?\n");
		System.out.println(getIndexedItems(auct));
		selection = readInt();
		
		editItem = auct.getMyItems().get(selection - 1);
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
				default: System.out.println("Invalid selection");
			}
		}
			
	}
	
/*
 * Helper Methods	
 */
	/**
	 * Checks if an NPO has an auction scheduled.
	 * 
	 * @return true iff NPO has an auction scheduled.
	 */
	public boolean hasAuction() {
		List<Auction> auctions = myAuctionCalendar.getListOfAuctions();
		
		boolean found = false;
		for (Auction auction : auctions) {
			if (auction.getMyNonProfit().equals(((NonProfitEmployee)myUser).getMyOrganizationName())) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	/**
	 * Gets an auction from a non-profit.
	 * 
	 * @return the auction, if this NonProfit has one scheduled.
	 */
	public Auction getAuction() {
		List<Auction> auctions = myAuctionCalendar.getListOfAuctions();
		Auction auctionToReturn = null;
		
		for (Auction auction : auctions) {
			if (auction.getMyNonProfit().equals(((NonProfitEmployee)myUser).getMyOrganizationName())) {
				auctionToReturn = auction;
				break;
			}
		}
		return auctionToReturn;
	}
	
	/**
	 * Method to show user when their auction is currently scheduled;
	 * 
	 * @return a string containing the Auction's current scheduled date/time.
	 */
	public String getAuctionDetails(Auction auction) {
		StringBuilder details = new StringBuilder();
		details.append("NonProfit Name - ");
		details.append(auction.getMyNonProfit());
		details.append("\nDate - ");
		details.append(auction.getMyMonth() + "/" + auction.getMyDay() + "/" + auction.getMyYear());
		details.append("\nScheduled Time - From ");
		details.append(auction.getMyStartTime());
		details.append(" until ");
		details.append(auction.getMyEndTime());
		details.append("\n");
		
		return details.toString();
	}
	
	/**
	 * Method to determine if a specific time is valid for Auction scheduling.
	 * 
	 * @return true iff no scheduling conflict according to business rules. If false, prints
	 * statement specifying cause of scheduling conflict.
	 */
	public boolean isTimeAvailable(CalendarClass calendar, int month, int day, int year, int start, int end) {
		if (calendar.belowMaxDaysToFuture(month, day, year)
				&& calendar.belowMaxAuctions()
				&& calendar.belowWeekLimit(month, day)
				&& calendar.isAvailable(month, day, start, end)
				&& start < end) {
			
			return true;
		} else {
			
			if (!calendar.belowMaxDaysToFuture(month, day, year)) {
				System.out.println("Selected time is too far in advance.\n");
			}
			else if (!calendar.belowMaxAuctions()) {
				System.out.println("Sorry, we have a full set of auctions right now.\n");
			}
			else if (!calendar.belowWeekLimit(month, day)) {
				System.out.println("Sorry, we are full for that particular week\n");
			}
			else if (!calendar.isAvailable(month, day, start, end)) {
				System.out.println("Selected time is not available.\n");
			}
			else if (!(start < end)) {
				System.out.println("Start time must be earlier than end time.\n");
			}
			return false;
		}
	}
}
