/*
 * TCSS 360 Group 4
 * AuctionCentral
 * 
 * Still very much a work in progress, just wanted to get something up there on Master right now.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This is the main driver class for the AuctionCentral program.
 * @author Riley Gratzer
 */
public class AuctionCentral {

	private static Scanner myInput;
	private static CalendarClass myAuctionCalendar;
	private static ArrayList<Auction> myAuctions;
	private static List<User> myUsers;
	private static User myUser;
	
	private static String auctionsFormat, usersFormat;
	/**
	 * main method.
	 * @param args
	 */
	public static void main(String[] args) {
		myInput = new Scanner(System.in);
		try {
			initialize();
			//saveAndQuit();
		} catch (FileNotFoundException e) {
			System.err.println("No such file!");
			e.printStackTrace();
		}
		initialMenu();
		//saveAndQuit();
	}

	/**
	 * This is the log in menu.
	 */
	public static void initialMenu() {
		System.out.println("\nWelcome to AuctionCentral!\n--------------------------");
		System.out.println("\nWhat would you like to do?");
		System.out.println("\n1. Log In\n2. Create Account\n3. Exit\n");
		int selection = myInput.nextInt();
		switch (selection) {
			case 1: logInMenu(); break;
			case 2: createAccountMenu(); break;
			case 3: saveAndQuit(); break;
			default: System.out.println("Invalid selection!"); initialMenu(); break;
		}
	}
	
	/**
	 * Menu for user with account to log in
	 */
	public static void logInMenu() {
		System.out.println("\nPlease type your user name:\n");

		String selection = myInput.next();
		
		int index = 0;
		boolean found = false;
		for (int i = 0; i < myUsers.size(); i++) {
			if (myUsers.get(i).getMyName().equals(selection)) {
				found = true;
				index = i;
				break;
			}
		}
		if (found) {
			myUser = myUsers.get(index);
			if (myUser instanceof AuctionCentralEmployee) {
				employeeMenu();
			} else if (myUser instanceof NonProfitEmployee) {
				npoMenu();
			} else if (myUser instanceof Bidder) {
				bidderMenu();
			} else {
				System.out.println("Something failed...");
			}
		} else {
			System.out.println("Name not found, try again:\n");
			logInMenu();
		}
	}
	
	/**
	 * Menu for new user to create account.
	 */
	public static void createAccountMenu() {
		User newUser;
		String userName, organizationName, contact;
		int selectType;
		boolean taken = false;
		
		System.out.println("\nPlease type in your desired User Name:\n");
		userName = myInput.next();
		
		for (int i = 0; i < myUsers.size(); i++) {
			if (myUsers.get(i).getMyName().equals(userName)) {
				taken = true;
			}
		}
		
		if (!taken) {
			System.out.println("\nPlease type in your e-mail address:\n");
			contact = myInput.next();
			
			System.out.println("Please select your access privilages:\n");
			System.out.println("1. AuctionCentral Employee\n2. Non-Profit Organization\n3. Bidder\n");
			selectType = myInput.nextInt();
			
			switch (selectType) {
				case 1: newUser = new AuctionCentralEmployee(userName, contact);
					myUsers.add(newUser);
					myUser = newUser;
					employeeMenu();
					break;
				case 2:
					System.out.println("\nPlease type in the name of your organization:\n");
					organizationName = myInput.next();
					newUser = new NonProfitEmployee(userName, contact, organizationName);
					myUsers.add(newUser);
					myUser = newUser;
					npoMenu();
					break;
				case 3: newUser = new Bidder(userName, contact);
					myUsers.add(newUser);
					myUser = newUser;
					bidderMenu();
					break;
				default: 
					System.out.println("Invalid selection, please try Account creation again");
					createAccountMenu(); break;
			}
		} else {
			System.out.println("User Name already taken.\n");
			createAccountMenu();
		}
		
	}
	
	/**
	 * This is the menu of employee actions.
	 */
	public static void employeeMenu() {
		String auctionName ="";
		
		System.out.println("\nHello, Employee! What would you like to do?");
		System.out.println("\n1. View Monthly Calendar\n2. View Auction Details\n3. Log Out");
		int selection = myInput.nextInt();
		switch (selection) {
			case 1: System.out.println(myUser.viewCalendar(myAuctionCalendar)); 
			employeeMenu(); break;
			case 2: System.out.println("Please enter the name of the organization hosting\n the auction:\n");
				auctionName = myInput.next();
				System.out.println(myUser.viewAuction(myAuctionCalendar, auctionName)); 
				employeeMenu(); break;
			case 3: System.out.println("Logging out... Good-bye!"); initialMenu(); break;
			default: System.out.println("Invalid selection!"); employeeMenu(); break;
		}
	}
	
	/**
	 * This is the menu of NPO actions.
	 */
	public static void npoMenu() {
		System.out.println("\nHello, Non-Profit Organization! What would you like to do?");
		System.out.println("\n1. Schedule Auction\n2. Enter Auction Information");
		System.out.println("3. Edit Auction Information\n4. Enter Item Information");
		System.out.println("5. Edit Item Information\n6. Log Out");
		int selection = myInput.nextInt();
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
		String selectName = "";
		Auction selectedAuction = null;
		List<Item> selectedItemList = null;
		boolean found = false;
		int index = -1;
		//ArrayList<Auction> tempAuctions = null;
		
		System.out.println("\nHello, Bidder! What would you like to do?");
		System.out.println("\n1. Choose Auction\n2. Bid on Item in Auction");
		System.out.println("3. Change Existing Bid\n4. Cancel existing bid\n5. Log Out");
		int selection = myInput.nextInt();
		switch (selection) {
			case 1: System.out.println("Please enter the name of the organization hosting\n the auction:\n");
				selectName = myInput.next();
				System.out.println(myUser.viewAuction(myAuctionCalendar, selectName)); 
				found = false;
				for (int i = 0; i < myAuctions.size(); i++) {
					if (myAuctions.get(i).getMyNonProfit().equals(selectName)) {
						found = true;
						selectedAuction = myAuctions.get(i);
						selectedItemList = selectedAuction.getMyItems();
						break;
					}
				}
				if (found) {
					System.out.println("Auction selected.\n");
				} else {
					System.out.println("Auction not found...");
				}
				bidderMenu(); break;
		
			case 2: System.out.println("Please type the name one of the following items to bid on:\n");
				for (int i = 0; i < selectedItemList.size(); i++) {
					System.out.println(selectedItemList.get(i));
				}
				System.out.println();
				selectName = myInput.next();
				found = false;
				index = -1;
				for (int i = 0; i < selectedItemList.size(); i++) {
					if (selectedItemList.get(i).getMyName().equals(selectName)) {
						found = true;
						index = i;
						break;
					}
				}
				if (found) {
					System.out.println("Please enter bid amount:");
					double bid = myInput.nextDouble();
					((Bidder)myUser).placeBid(selectedItemList.get(index), bid);
					System.out.println("Bid placed successfully");
				} else {
					System.out.println("No such item found...");
				}
				bidderMenu(); break;
			
			case 3: System.out.println("Please type the name one of the following items to change bid on:\n");
				for (int i = 0; i < selectedItemList.size(); i++) {
					System.out.println(selectedItemList.get(i));
				}
				System.out.println();
				selectName = myInput.next();
				found = false;
				index = -1;
				for (int i = 0; i < selectedItemList.size(); i++) {
					if (selectedItemList.get(i).getMyName().equals(selectName)) {
						found = true;
						index = i;
						break;
					}
				}
				if (found) {
					System.out.println("Please enter new bid amount:");
					double bid = myInput.nextDouble();
					((Bidder)myUser).changeBid(selectedItemList.get(index), bid);
					System.out.println("Bid changed successfully");
				} else {
					System.out.println("No such item found...");
				}
				bidderMenu(); break;
			//Cancel bid
			case 4: 
				bidderMenu(); break;
			case 5: System.out.println("Logging out... Good-bye!"); initialMenu(); break;
			default: System.out.println("Invalid selection!"); npoMenu(); break;
		}
	}
	
	/**
	 * This method initializes the system at start-up.
	 * @throws FileNotFoundException if the file does not exist.
	 */
	private static void initialize() throws FileNotFoundException {
		
		Scanner auctionScan = new Scanner(new File("auctions.txt"));
		auctionsFormat = auctionScan.nextLine();
		System.out.println(auctionsFormat);
		Scanner userScan = new Scanner(new File("users.txt"));
		usersFormat = userScan.nextLine();
		
		Scanner pr;
		String readLine = "";
		
		String auctionName = "";
		int auctionStart = 0;
		int auctionEnd = 0;
		int month, date, year;
		//String[] months = {"January", "February", "March", "April", "May", "June", 
		//					"July", "August", "September", "October", "November", "December"};
		String itemName = "";
		double startingBid = 0;
		String bidder = "";
		double bid = 0;
		
		ArrayList<Auction> auctions = new ArrayList<Auction>();
		List<Item> items = new ArrayList<Item>();
		
		Map<String, Double> tempBids = new HashMap<String, Double>();
		
		while (auctionScan.hasNextLine()) {
			readLine = auctionScan.nextLine();
			pr = new Scanner(readLine);
			while (pr.hasNext()) {
				//Scan name
				auctionName = pr.next();
				//Scan month
				month = pr.nextInt();
				//Scan date
				date = pr.nextInt();
				//Scan year
				year = pr.nextInt();
				//auctionName += "-" + months[month - 1] + "-" + date +"-" + year;
				//Scan start time, end time
				auctionStart = pr.nextInt();
				auctionEnd = pr.nextInt();
				while (pr.hasNext()) {
					//Scan Item name and starting bid
					itemName = pr.next();
					startingBid = pr.nextDouble();
					while (pr.hasNextDouble()) {
						//Scan bid and name of bidder
						bid = pr.nextDouble();
						bidder = pr.next();
						tempBids.put(bidder, bid);
					}
					items.add(new Item(itemName, startingBid, tempBids));
					tempBids = new HashMap<String, Double>();
				}
				auctions.add(new Auction(auctionName, month, date, year, auctionStart, auctionEnd, items));
				items = new ArrayList<Item>();
			}
			
		}
		myAuctions = auctions;
		myAuctionCalendar = new CalendarClass();
		myAuctionCalendar.insertAuctions(myAuctions);
		//myAuctionCalendar = new CalendarClass(myAuctions);
		
		
		//myUsers = new HashMap<String, String>();
		myUsers = new ArrayList<User>();
		String name = "";
		String userType = "";
		String tempContact = "";
		String tempOrgName = "";
		User tempUser = null;
		Map<Item, Double> tempUserBids;
		while (userScan.hasNextLine()) {
			readLine = userScan.nextLine();

			//Parse username
			name = readLine.substring(0, readLine.indexOf(' '));
			readLine = readLine.substring(readLine.indexOf(' ') + 1);

			//Parse contact
			tempContact = readLine.substring(0, readLine.indexOf(' ') + 1);
			readLine = readLine.substring(readLine.indexOf(' ') + 1);
			
			//Parse user type
			userType = readLine;

			//If user is an NPO, string will need to be parsed further.
			if (userType.indexOf(' ') > 0) {
				tempOrgName = userType.substring(userType.indexOf(' ') + 1, userType.length());
				userType = userType.substring(0, userType.indexOf(' '));
			} else {
				userType = readLine;
			}
			
			switch(userType) {
				case "employee": tempUser = new AuctionCentralEmployee(name, tempContact); 
								myUsers.add(tempUser);break;
				case "npo": tempUser = new NonProfitEmployee(name, tempContact, tempOrgName); 
								myUsers.add(tempUser);break;
				case "bidder": 
					tempUserBids = new HashMap<Item, Double>();
					for (Auction a : myAuctions) {
						for (Item i : a.getMyItems()) {
							for (String key : i.getMyBids().keySet()) {
								if (key.equals(name)) {
									tempUserBids.put(i, i.getMyBids().get(key));
								}
							}
						}
					}
					System.out.println(tempUserBids);
					tempUser = new Bidder(name, tempContact); // , tempUserBids); 
					myUsers.add(tempUser); break;
				default: break;
			}
		}
		auctionScan.close();
		userScan.close();
	}
	
	/**
	 * This method saves any changes made to files upon end of program.
	 */
	public static void saveAndQuit() {
		//WRITE AUCTIONS FILE
		PrintStream output = null;
		try {
			output = new PrintStream(new File("auctions.txt"));
			
		} catch (FileNotFoundException e) {
			System.err.println("no file made");
			e.printStackTrace();
		}
		
		List<Item> tempItems;
		Map<String, Double> tempBids;
		
		output.println(auctionsFormat);
		for (int i = 0; i < myAuctions.size(); i++) {
			output.print(myAuctions.get(i).getMyNonProfit() + " " + myAuctions.get(i).getMyMonth() + 
					" " + myAuctions.get(i).getMyDay() + " " + myAuctions.get(i).getMyYear() + 
					" " + myAuctions.get(i).getMyStartTime() + " " + 
					myAuctions.get(i).getMyEndTime() + " ");
			tempItems = myAuctions.get(i).getMyItems();
			for (int j = 0; j < tempItems.size(); j++) {
				output.print(tempItems.get(j).getMyName() + " " + 
						tempItems.get(j).getMyStartingBid() + " ");
				tempBids = tempItems.get(j).getMyBids();
				for (String key : tempBids.keySet()) {
					output.print(tempBids.get(key) + " " + key + " ");
				}
			}
			output.println();
		}
		
		//WRITE USERS FILE
		try {
			output = new PrintStream(new File("users.txt"));
			
		} catch (FileNotFoundException e) {
			System.err.println("no file made");
			e.printStackTrace();
		}
		System.out.println(auctionsFormat);
		output.println(usersFormat);
		for (int i = 0; i < myUsers.size(); i++) {
			System.out.println(myUsers.get(i));
		}
		for (int i = 0; i < myUsers.size(); i++) {
			output.print(myUsers.get(i).getMyName() + " " + myUsers.get(i).getMyContact());
			User theUser = myUsers.get(i);
			if (theUser instanceof AuctionCentralEmployee) {
				output.println("employee");
			} else if (theUser instanceof NonProfitEmployee) {
				output.println("npo " + ((NonProfitEmployee) theUser).getMyOrganizationName());
			} else if (theUser instanceof Bidder) {
				output.println("bidder");
			} else {
				System.out.println("Something failed...");
			}
		}
	}
}

