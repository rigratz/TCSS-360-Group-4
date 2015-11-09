/*
 * TCSS 360 Group 4
 * AuctionCentral
 * 
 * Still very much a work in progress, just wanted to get something up ther on Master right now.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is the main driver class for the AuctionCentral program.
 * @author Riley Gratzer
 */
public class AuctionCentral {

	private static Scanner input;
	private static CalendarClass auctionCalendar;
	private static List<User> appUsers;
	
	/**
	 * main method.
	 * @param args
	 */
	public static void main(String[] args) {
		input = new Scanner(System.in);
		try {
			initialize();
		} catch (FileNotFoundException e) {
			System.err.println("No such file!");
			e.printStackTrace();
		}
		//initialMenu();
	}

	/**
	 * This is the initial menu.
	 */
	public static void initialMenu() {
		System.out.println("\nWelcome to AuctionCentral!\n--------------------------");
		System.out.println("\nPlease select your User type\n");
		System.out.println("1. AuctionCentral Employee\n2. Non-Profit Organization\n3. Bidder");
		int selection = input.nextInt();
		switch (selection) {
			case 1: employeeMenu(); break;
			case 2: npoMenu(); break;
			case 3: bidderMenu(); break;
			default: System.out.println("Invalid selection!"); initialMenu(); break;
		}
	}
	/**
	 * This is the menu of employee actions.
	 */
	public static void employeeMenu() {
		//appUser = new Employee();
		System.out.println("\nHello, Employee! What would you like to do?");
		System.out.println("\n1. View Monthly Calendar\n2. View Auction Details\n3. Log Out");
		int selection = input.nextInt();
		switch (selection) {
			//case 1: appUser.viewCalendar(); break;
			//case 2: appUser.viewAuction(); break;
			case 3: System.out.println("Logging out... Good-bye!"); initialMenu(); break;
			default: System.out.println("Invalid selection!"); employeeMenu(); break;
		}
	}
	/**
	 * This is the menu of NPO actions.
	 */
	public static void npoMenu() {
		//appUser = new NonProfit();
		System.out.println("\nHello, Non-Profit Organization! What would you like to do?");
		System.out.println("\n1. Schedule Auction\n2. Enter Auction Information");
		System.out.println("3. Edit Auction Information\n4. Enter Item Information");
		System.out.println("5. Edit Item Information\n6. Log Out");
		int selection = input.nextInt();
		switch (selection) {
			//case 1: appUser.scheduleAuction(); break;
			//case 2: appUser.enterAuctionInfo(); break;
			//case 3: appUser.editAuctionInfo(); break;
			//case 4: appUser.enterItemInfo(); break;
			//case 5: appUser.editItemInfo(); break;
			case 6: System.out.println("Logging out... Good-bye!"); initialMenu(); break;
			default: System.out.println("Invalid selection!"); npoMenu(); break;
		}
	}
	/**
	 * This is the menu for bidder actions.
	 */
	public static void bidderMenu() {
		//appUser = new Bidder();
		System.out.println("\nHello, Bidder! What would you like to do?");
		System.out.println("\n1. Choose Auction\n2. Bid on Item in Auction");
		System.out.println("3. Change Existing Bid\n4. Log Out");
		int selection = input.nextInt();
		switch (selection) {
			//case 1: appUser.chooseAuction(); break;
			//case 2: appUser.bid(); break;
			//case 3: appUser.changeBid(); break;
			case 4: System.out.println("Logging out... Good-bye!"); initialMenu(); break;
			default: System.out.println("Invalid selection!"); npoMenu(); break;
		}
	}
	
	/**
	 * This method initializes the system at start-up.
	 * @throws FileNotFoundException if the file does not exist.
	 */
	private static void initialize() throws FileNotFoundException {
		auctionCalendar = new CalendarClass();
		Scanner auctionScan = new Scanner(new File("auctions.txt"));
		Scanner userScan = new Scanner(new File("users.txt"));
		Scanner pr;
		String readLine = "";
		
		String auctionName = "";
		String itemName = "";
		List<String> auctions = new ArrayList<String>();
		List<String> items = new ArrayList<String>();
		List<Double> bids = new ArrayList<Double>();
		
		while (auctionScan.hasNextLine()) {
			readLine = auctionScan.nextLine();
			pr = new Scanner(readLine);
			while (pr.hasNext()) {
				auctionName = pr.next();
				while (pr.hasNext()) {
					itemName = pr.next();
					while (pr.hasNextDouble()) {
						bids.add(pr.nextDouble());
					}
					items.add(itemName);
				}
				auctions.add(auctionName);
			}
			
		}
		System.out.println("" + auctions + items + bids);
		
		String name = "";
		String userType = "";
		while (userScan.hasNextLine()) {
			readLine = userScan.nextLine();
			name = readLine.substring(0, readLine.indexOf(' '));
			userType = readLine.substring(readLine.indexOf(' ') + 1);
//			switch (userType) {
//				case "employee": appUsers.add(new Employee(name)); break;
//				case "npo": appUsers.add(new nonProfit(name)); break;
//				case "bidder": appUsers.add(new Bidder(name)); break;
//				default: break;
//			}
			System.out.println(name + " - " + userType);
		}
		
	}
}
