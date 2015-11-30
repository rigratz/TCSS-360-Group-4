/*
 * TCSS 360 Group 4
 * AuctionCentral
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This is the main driver class for the AuctionCentral program.
 * 
 * No JUnit testing, as this class relies on almost exclusively on user input 
 * and external classes and methods which have all been individually tested.
 * 
 * @author Riley Gratzer
 */
public class AuctionCentral {

	private static BufferedReader myInput;
	private static CalendarClass myAuctionCalendar;
	private static List<AbstractUI> userInterfaces;

	private static List<User> myUsers;
	private static User myUser;
	
	private static String auctionsFormat, usersFormat;
	/**
	 * main method.
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		myInput = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			initialize();
			userInterfaces = new ArrayList<AbstractUI>();
			userInterfaces.add(new ACEmployeeUI(myInput, myAuctionCalendar));
			userInterfaces.add(new NonProfitUI(myInput, myAuctionCalendar));
			userInterfaces.add(new BidderUI(myInput, myAuctionCalendar));
		} catch (FileNotFoundException e) {
			System.err.println("No such file!");
			e.printStackTrace();
		}
		initialMenu();
	}

	/**
	 * This is the log in menu.
	 * @throws IOException 
	 */
	public static void initialMenu() throws IOException {
		int selection = 0;
		System.out.println("\nWelcome to AuctionCentral!\n--------------------------");
		System.out.println("Proper input consists of a number when prompted \nfor one" 
								+ ", or a string with no spaces\n");
		while(selection != 3) {

			System.out.println("\nWhat would you like to do?");
			System.out.println("\n1. Log In\n2. Create Account\n3. Exit\n");
			selection = readInt();
			switch (selection) {
				case 1: logInMenu(); break;
				case 2: createAccountMenu(); break;
				case 3: saveAndQuit(); break;
				default: System.out.println("Invalid selection!"); break;
			}
		}
	}
	
	/**
	 * Menu for user with account to log in
	 */
	public static void logInMenu() {
		boolean found = false;
		String selection = null;
		System.out.println("\nPlease type your user name:\n");
		
		
		while (!found) {
			selection = readString();
			int index = 0;
		
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
					//employeeMenu();
					userInterfaces.get(0).menu(myUser);
				} else if (myUser instanceof NonProfitEmployee) {
					userInterfaces.get(1).menu(myUser);
					//npoMenu();
				} else if (myUser instanceof Bidder) {
					//bidderMenu();
					userInterfaces.get(2).menu(myUser);
				}
			} else {
				System.out.println("Name not found, try again:\n");
			}
			
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
		userName = null;

		System.out.println("\nPlease type in your desired User Name:\n");
		userName = readString();

		for (int i = 0; i < myUsers.size(); i++) {
			if (myUsers.get(i).getMyName().equals(userName)) {
				taken = true;
			}
		}
		if (!taken) {
			System.out.println("\nPlease type in your e-mail address:\n");
			contact = readString();
			
			System.out.println("Please select your access privilages:\n");
			System.out.println("1. AuctionCentral Employee\n2. Non-Profit Organization\n3. Bidder\n");
			selectType = readInt();
			
			switch (selectType) {
				case 1: newUser = new AuctionCentralEmployee(userName, contact);
					myUsers.add(newUser);
					myUser = newUser;
					//employeeMenu();
					userInterfaces.get(0).menu(myUser);
					break;
				case 2:
					System.out.println("\nPlease type in the name of your organization:\n");
					organizationName = readString();
					newUser = new NonProfitEmployee(userName, contact, organizationName);
					myUsers.add(newUser);
					myUser = newUser;
					//npoMenu();
					userInterfaces.get(1).menu(myUser);
					break;
				case 3: newUser = new Bidder(userName, contact);
					myUsers.add(newUser);
					myUser = newUser;
					//bidderMenu();
					userInterfaces.get(2).menu(myUser);
					break;
				default: 
					System.out.println("Invalid selection, please try Account creation again");
					 break;
			}
		} else {
			System.out.println("User Name already taken.\n");
		}
		
		
		
	}
	
//	/**
//	 * This is the menu of employee actions.
//	 */
//	public static void employeeMenu() {
//		String auctionName ="";
//		int selection = 0;
//		
//		while (selection != 3) {
//			System.out.println("\nHello, Employee! What would you like to do?");
//			System.out.println("\n1. View Monthly Calendar\n2. View Auction Details\n3. Log Out");
//			selection = myInput.nextInt();
//			switch (selection) {
//				case 1: 
//					System.out.println(myAuctionCalendar.getPastAuctions());
//					System.out.println("Future Auctions: \n" + myUser.viewCalendar(myAuctionCalendar)); break;
//				case 2: System.out.println("Please enter the name of the organization hosting\nthe auction:\n");
//					auctionName = myInput.next();
//					System.out.println(myUser.viewAuction(myAuctionCalendar, auctionName)); break;
//				case 3: System.out.println("Logging out... Good-bye!"); break;
//				default: System.out.println("Invalid selection!"); break;
//			}
//		}
//	}
	
//	/**
//	 * This is the menu of NPO actions.
//	 */
//	public static void npoMenu() {
//		int selection = 0;
//		Auction auct = null;
//		
//		
//		while (selection != 5) {
//			if (hasAuction()) {
//				auct = getAuction();
//			}
//			System.out.println("\nHello, Non-Profit Organization! What would you like to do?");
//			System.out.println("\n1. Schedule Auction");
//			System.out.println("2. Edit Auction Information\n3. Enter Item Information");
//			System.out.println("4. Edit Item Information\n5. Log Out");
//			selection = myInput.nextInt();
//			switch (selection) {
//				case 1: if (auct == null) {
//							scheduleAuctionMenu();
//						} else {
//							System.out.println("You already have an auction scheduled.");
//						} break;
//				case 2: if (hasAuction()) {
//							int innerSelect, newArg;
//							System.out.println("What would you like to edit?\n");
//							System.out.println("1. Day\n2: Month\n3. Year\n4. Start Time\n5. End Time\n");
//							innerSelect = myInput.nextInt();
//							switch (innerSelect) {
//							case 1: System.out.println("Please enter new day:");
//									newArg = myInput.nextInt(); 
//									auct.setMyDay(newArg);break;
//									
//							case 2: System.out.println("Please enter new month:"); 
//									newArg = myInput.nextInt(); 
//									auct.setMyMonth(newArg); break;
//							case 3: System.out.println("Please enter new year:"); 
//									newArg = myInput.nextInt(); 
//									auct.setMyYear(newArg); break;
//							case 4: System.out.println("Please enter new start time:"); 
//									newArg = myInput.nextInt(); 
//									auct.setMyStartTime(newArg); break;
//							case 5: System.out.println("Please enter new end time:"); 
//									newArg = myInput.nextInt(); 
//									auct.setMyEndTime(newArg); break;
//							default: System.out.println("Incorrect input."); break;
//							}
//						} else {
//							System.out.println("You have no auction to edit.");
//						} break;
//				case 3: if (hasAuction()) {
//							String itemName = "";
//							double itemStart = 0.0;
//							
//							System.out.println("Please enter the name of the item:\n");
//							itemName = readString();
//							System.out.println("Please enter the starting bid amount for the item:\n");
//							itemStart = myInput.nextDouble();
//							auct.addItem(new Item(itemName, itemStart));
//						} else {
//							System.out.println("You have no auction to add items to.");
//						} break;
//				
//				case 4: if (hasAuction()) {
//							String itemName, itemNewName;
//							Item editItem = null;
//							double itemNewStart;
//							int innerSelect;
//							
//							System.out.println("Which item would you like to edit?\n");
//							System.out.println(auct.itemsToString());
//							itemName = readString();
//							for (Item i : auct.getMyItems()) {
//								if (itemName.equals(i.getMyName())) {
//									editItem = i;
//									break;
//								}
//							}
//							if (editItem != null) {
//								System.out.println("What would you like to change?\n");
//								System.out.println("1. Item name\n2. Item starting bid");
//								innerSelect = myInput.nextInt();
//							
//								switch (innerSelect) {
//									case 1: System.out.println("Please enter new item name:\n");
//										itemNewName = readString();
//										editItem.setMyName(itemNewName); break;
//									case 2: System.out.println("Please enter new starting bid:\n");
//										itemNewStart = myInput.nextDouble();
//										editItem.setMyStartingBid(itemNewStart); break;
//								}
//							} else {
//								System.out.println("Item does not exist.\n");
//							}
//						} break;
//				case 5: System.out.println("Logging out... Good-bye!"); break;
//				default: System.out.println("Invalid selection!"); break;
//			}
//		saveAndQuit();
//		}
//	}
	
//	/**
//	 * This is the menu for bidder actions.
//	 */
//	public static void bidderMenu() {
//		int selection = 0;
//		String selectName = "";
//		String finder = "";
//		Auction selectedAuction = null;
//		List<Auction> auctionList = myAuctionCalendar.getListOfAuctions();
//		List<Item> selectedItemList = null;
//		Item selectedItem = null;
//		boolean found = false;
//		boolean previousBid = false;
//		int index = -1;
//		
//		while (selection != 5) {
//			System.out.println("\nHello, Bidder! What would you like to do?");
//			System.out.println("\n1. Choose Auction\n2. Bid on Item in Auction");
//			System.out.println("3. Change Existing Bid\n4. Cancel existing bid\n5. Log Out");
//			selection = myInput.nextInt();
//			switch (selection) {
//				case 1: System.out.println("Please enter the name of the organization hosting\nthe auction:\n");
//					System.out.println(myUser.viewCalendar(myAuctionCalendar));
//					selectName = readString();
//					finder = myUser.viewAuction(myAuctionCalendar, selectName); 
//					System.out.println(finder);
//					if (!finder.equals("Nothing was found.")) {
//						for (int i = 0; i < auctionList.size(); i++) {
//							if (auctionList.get(i).getMyNonProfit().equals(selectName)) {
//								selectedAuction = auctionList.get(i);
//								selectedItemList = selectedAuction.getMyItems();
//								break;
//							}
//						}
//					}
//					break;
//		
//				case 2: if (selectedItemList == null) {
//							System.out.println("No auction has been selected.\n");
//				} else if (selectedItemList.isEmpty()) {
//					System.out.println("No items currently up for auction.");
//						} else {
//							System.out.println("Please type the name one of the following items to bid on:\n");
//							for (int i = 0; i < selectedItemList.size(); i++) {
//								System.out.println(selectedItemList.get(i));
//							}
//							System.out.println();
//							selectName = readString();
//							found = false;
//							index = -1;
//							for (int i = 0; i < selectedItemList.size(); i++) {
//								if (selectedItemList.get(i).getMyName().equals(selectName)) {
//									found = true;
//									index = i;
//									if (selectedItemList.get(i).getMyBids().containsKey(myUser.getMyName())) {
//										previousBid = true;
//									}
//									break;
//								}
//							}
//					if (found && !previousBid) {
//						System.out.println("Please enter bid amount:");
//						double bid = myInput.nextDouble();
//						boolean valid = ((Bidder)myUser).placeBid(selectedItemList.get(index), bid);
//						if (valid) {
//							System.out.println("Bid placed successfully");
//						} else {
//							System.out.println("Not a valid bid");
//						}
//					} else if (found && previousBid) {
//						System.out.println("You already have a pre-existing bid for this item.");
//					} else {
//						System.out.println("No such item found...");
//					}
//					}
//					break;
//			
//				case 3:
//					System.out.println("You currently have bids on the following items:\n");
//					System.out.println(((Bidder) myUser).getMyBids() + "\n");
//					System.out.println("Which bid would you like to change?\n");
//					selectName = readString();
//					selectedItem = getItemFromList(selectName);
//					if (selectedItem != null) {
//						System.out.println("Please enter new bid amount:");
//						double newBid = myInput.nextDouble();
//						if (((Bidder)myUser).changeBid(selectedItem, newBid)) {
//							System.out.println("Bid changed.");
//						} else {
//							System.out.println("Bid could not be changed to specified amount.");
//						}
//					} else {
//						System.out.println("No bid for selected item to cancel.");
//					}
//					break;
//				
//				case 4: System.out.println("You currently have bids on the following items:\n");
//					System.out.println(((Bidder) myUser).getMyBids() + "\n");
//					System.out.println("Which bid would you like to cancel?\n");
//					selectName = myInput.next();
//					selectedItem = getItemFromList(selectName);
//					if (selectedItem != null && ((Bidder)myUser).cancelBid(selectedItem)) {
//						System.out.println("Bid successfully canceled.");
//					} else {
//						System.out.println("No bid for selected item to cancel.");
//					}
//					break;
//				case 5: System.out.println("Logging out... Good-bye!"); break;
//				default: System.out.println("Invalid selection!"); break;
//			}
//		}
//	}

//LOAD AND SAVE METHODS
	
	/**
	 * This method initializes the system at start-up.
	 * @throws FileNotFoundException if the file does not exist.
	 */
	private static void initialize() throws FileNotFoundException {
		
		Scanner auctionScan = new Scanner(new File("auctions.txt"));
		auctionsFormat = auctionScan.nextLine();
		Scanner userScan = new Scanner(new File("users.txt"));
		usersFormat = userScan.nextLine();
		
		Scanner pr;
		String readLine = "";
		
		String auctionName = "";
		int auctionStart = 0;
		int auctionEnd = 0;
		int month, date, year;
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

		myAuctionCalendar = new CalendarClass();
		myAuctionCalendar.insertAuctions(auctions);
		
		myUsers = new ArrayList<User>();
		String name = "";
		String userType = "";
		String tempContact = "";
		String tempOrgName = "";
		User tempUser = null;
		Map<String, Double> tempUserBids;
		while (userScan.hasNextLine()) {
			readLine = userScan.nextLine();

			//Parse username
			name = readLine.substring(0, readLine.indexOf(' '));
			readLine = readLine.substring(readLine.indexOf(' ') + 1);

			//Parse contact
			tempContact = readLine.substring(0, readLine.indexOf(' '));
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
					tempUserBids = new HashMap<String, Double>();
					for (Auction a : auctions) {
						for (Item i : a.getMyItems()) {
							for (String key : i.getMyBids().keySet()) {
								if (key.equals(name)) {
									tempUserBids.put(i.getMyName(), i.getMyBids().get(key));
								}
							}
						}
					}
					tempUser = new Bidder(name, tempContact, tempUserBids); 
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
		List<Auction> auctions = myAuctionCalendar.getListOfAuctions();
		List<Auction> past = myAuctionCalendar.getListOfPastAuctions();
		while (!past.isEmpty()) {
			auctions.add(past.get(0));
			past.remove(0);
		}
		
		output.println(auctionsFormat);
		for (int i = 0; i < auctions.size(); i++) {
			output.print(auctions.get(i).getMyNonProfit() + " " + auctions.get(i).getMyMonth() + 
					" " + auctions.get(i).getMyDay() + " " + auctions.get(i).getMyYear() + 
					" " + auctions.get(i).getMyStartTime() + " " + 
					auctions.get(i).getMyEndTime() + " ");
			tempItems = auctions.get(i).getMyItems();
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
		output.println(usersFormat);
		for (int i = 0; i < myUsers.size(); i++) {
			output.print(myUsers.get(i).getMyName() + " " + myUsers.get(i).getMyContact() + " ");
			User theUser = myUsers.get(i);
			if (theUser instanceof AuctionCentralEmployee) {
				output.println("employee");
			} else if (theUser instanceof NonProfitEmployee) {
				output.println("npo " + ((NonProfitEmployee) theUser).getMyOrganizationName());
			} else if (theUser instanceof Bidder) {
				output.println("bidder");
			}
		}
		
	}	
	

 //RANDOM HELPER METHODS
 	
//	/**
//	 * Retrieves an item from a list if it exists.
//	 * 
//	 * @param theName is the item to look for.
//	 * @return the item if it exists (or null otherwise)
//	 */
//	private static Item getItemFromList(String theName) {
//		Item foundItem = null;
//		List<Auction> list = myAuctionCalendar.getListOfAuctions();
//		for(Auction auctions : list) {
//			for (Item i : auctions.getMyItems()) {
//				if (i.getMyName().equals(theName)) {
//					foundItem = i;
//					break;
//				}
//			}
//		}
//		return foundItem;
//	}
	
//	/**
//	 * Menu for scheduling an auction.
//	 */
//	public static void scheduleAuctionMenu() {
//		CalendarClass c = myAuctionCalendar;
//		
//		int month, day, year, start, end;
//		System.out.println("Enter Month:\n");
//		month = myInput.nextInt();
//		System.out.println("Enter Day:\n");
//		day = myInput.nextInt();
//		System.out.println("Enter Year:\n");
//		year = myInput.nextInt();
//		System.out.println("Enter Start Time:\n");
//		start = myInput.nextInt();
//		System.out.println("Enter End Time:\n");
//		end = myInput.nextInt();
//		if (c.checkOrganization(((NonProfitEmployee)myUser).getMyOrganizationName())
//				&& c.isAvailable(month, day, start, end)
//				&& c.belowMaxAuctions()
//				&& c.belowMaxDaysToFuture(month, day, year)
//				&& c.belowWeekLimit(month, day)) {
//			c.addAuction(((NonProfitEmployee)myUser).scheduleAuction(((NonProfitEmployee)myUser).getMyOrganizationName(), month, day, year, start, end));
//			System.out.println("Auction Scheduled");
//	
//		} else {
//			if (!c.checkOrganization(((NonProfitEmployee)myUser).getMyOrganizationName())) {
//				System.out.println("Your organization already has an auction scheduled.\n");
//			}
//			else if (!c.belowMaxDaysToFuture(month, day, year)) {
//				System.out.println("Selected time is too far in advance.\n");
//			}
//			else if (!c.belowMaxAuctions()) {
//				System.out.println("Sorry, we have a full set of auctions right now.\n");
//			}
//			else if (!c.belowWeekLimit(month, day)) {
//				System.out.println("Sorry, we are full for that particular week\n");
//			}
//			else if (!c.isAvailable(month, day, start, end)) {
//				System.out.println("Selected time is not available.\n");
//			}
//		}
//	}
	
//	/**
//	 * Checks if an NPO has an auction scheduled.
//	 * 
//	 * @return if NPO has an auction scheduled.
//	 */
//	private static boolean hasAuction() {
//		List<Auction> auctions = myAuctionCalendar.getListOfAuctions();
//		
//		boolean found = false;
//		for (Auction a : auctions) {
//			if (a.getMyNonProfit().equals(((NonProfitEmployee)myUser).getMyOrganizationName())) {
//				found = true;
//				break;
//			}
//		}
//		return found;
//	}
	
//	/**
//	 * Gets an auction from a non-profit.
//	 * 
//	 * @return the desired auction.
//	 */
//	private static Auction getAuction() {
//		List<Auction> auctions = myAuctionCalendar.getListOfAuctions();
//		Auction auct = null;
//		
//		for (Auction a : auctions) {
//			if (a.getMyNonProfit().equals(((NonProfitEmployee)myUser).getMyOrganizationName())) {
//				auct = a;
//				break;
//			}
//		}
//		return auct;
//	}
	
	public static int readInt() {
		String read;
		try {
			read = myInput.readLine();
			int num = Integer.parseInt(read);
			return num;
		} catch (IOException e) {
			System.out.println("Input failed!\n");
			return -1;
		} catch (NumberFormatException e) {
			System.out.println("Not a valid selection!\n");
			return -1;
		}
	}
	/**
	 * This method makes sure any input is free of spaces to preserve file read/write format.
	 * @return the valid string.
	 */
	public static String readString() {
		boolean read = false;
		String input = "";
		while (!read) {
			try {
				input = myInput.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (input.contains(" ")) {
				System.out.println("Please do not use spaces. Type CamelCase or use underscores.");
				System.out.println("Try Again:");
				try {
					input = myInput.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			read = true;
		}
		return input;
	}
}

