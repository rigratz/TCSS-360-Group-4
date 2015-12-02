import java.io.BufferedReader;


public class ACEmployeeUI extends AbstractUI {

	public ACEmployeeUI(BufferedReader theInput, CalendarClass theCalendar) {
		super(theInput, theCalendar);
	}

	/**
	 * Main menu for an AuctionCentral Employee.
	 */
	@Override
	public void menu(User theUser) {
		setUser(theUser);
		int selection = 0;
		
		while (selection != 3) {
			System.out.println("\nHello, Employee! What would you like to do?");
			System.out.println("\n1. View Monthly Calendar\n2. View Auction Details\n3. Log Out");
			selection = readInt();
			switch (selection) {
				case 1: viewMonthlyCalendar(); break;
				case 2: viewAuctionDetails(); break;
				case 3: System.out.println("Logging out... Good-bye!"); break;
				default: System.out.println("Invalid selection!"); break;
			}
		}
		AuctionCentral.saveAndQuit();
	}
	
	/**
	 * Displays monthly auction information for an AuctionCentral Employee.
	 */
	public void viewMonthlyCalendar() {
		
		StringBuilder toPrint = new StringBuilder();
		toPrint.append("Past Auctions\n-------------\n");
		for (int i = 0; i < myAuctionCalendar.getListOfPastAuctions().size(); i++) {
			toPrint.append(myAuctionCalendar.getListOfPastAuctions().get(i).getMyName());
			toPrint.append("\n");
		}
		toPrint.append("\nFuture Auctions\n---------------\n");
		for (int i = 0; i < myAuctionCalendar.getListOfAuctions().size(); i++) {
			toPrint.append(myAuctionCalendar.getListOfAuctions().get(i).getMyName());
			toPrint.append("\n");
		}
		System.out.println(toPrint); 
	}
	
	/**
	 * Menu for AuctionCentral Employee to view the details of a selected auction.
	 */
	public void viewAuctionDetails() {
		int selection;
		System.out.println("Please select an auction:\n");
		System.out.println(getIndexedAuctions());
		selection = readInt() - 1;
		Auction auction = myAuctionCalendar.getListOfAuctions().get(selection);
		
		StringBuilder details = new StringBuilder();
		details.append("NonProfit Name - ");
		details.append(auction.getMyNonProfit());
		details.append("\nDate - ");
		details.append(auction.getMyMonth() + "/" + auction.getMyDay() + "/" + auction.getMyYear());
		details.append("\nScheduled Time - From ");
		details.append(auction.getMyStartTime());
		details.append(" until ");
		details.append(auction.getMyEndTime());
		details.append("\n\nItem List\n---------\n");
		for (int i = 0; i < auction.getMyItems().size(); i++) {
			details.append(auction.getMyItems().get(i).getMyName());
			details.append("\n     Starting Bid = ");
			details.append(auction.getMyItems().get(i).getMyStartingBid());
			details.append("\n\n");
		}
		System.out.println(details);
	}
}
