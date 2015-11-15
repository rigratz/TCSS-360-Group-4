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
		int selection = 0;
		System.out.println("\nWelcome to AuctionCentral!\n--------------------------");
		while(selection != 3) {
			System.out.println("\nWhat would you like to do?");
			System.out.println("\n1. Log In\n2. Create Account\n3. Exit\n");
			selection = myInput.nextInt();
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
		while (!found) {
			System.out.println("\nPlease type your user name:\n");

			String selection = myInput.next();
		
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
		int selection = 0;
		
		while (selection != 3) {
			System.out.println("\nHello, Employee! What would you like to do?");
			System.out.println("\n1. View Monthly Calendar\n2. View Auction Details\n3. Log Out");
			selection = myInput.nextInt();
			switch (selection) {
				case 1: System.out.println(myUser.viewCalendar(myAuctionCalendar)); break;
				case 2: System.out.println("Please enter the name of the organization hosting\n the auction:\n");
					auctionName = myInput.next();
					System.out.println(myUser.viewAuction(myAuctionCalendar, auctionName)); break;
				case 3: System.out.println("Logging out... Good-bye!"); break;
				default: System.out.println("Invalid selection!"); break;
			}
		}
	}
	
	/**
	 * This is the menu of NPO actions.
	 */
	public static void npoMenu() {
		int selection = 0;
		Auction auct = null;
		if (hasAuction()) {
			auct = getAuction();
		}
		
		while (selection != 6) {
			System.out.println("\nHello, Non-Profit Organization! What would you like to do?");
			System.out.println("\n1. Schedule Auction\n2. Enter Auction Information");
			System.out.println("3. Edit Auction Information\n4. Enter Item Information");
			System.out.println("5. Edit Item Information\n6. Log Out");
			selection = myInput.nextInt();
			switch (selection) {
				case 1: if (auct == null) {
							scheduleAuctionMenu(); break;
						} else {
							System.out.println("You already have an auction scheduled.");
						} break;
				case 2:  if (hasAuction()) enterAuctionInfoMenu(); break;
				case 3: if (hasAuction()) {
							int innerSelect, newArg;
							System.out.println("What would you like to edit?\n");
							System.out.println("1. Day\n2: Month\n3. Year\n4. Start Time\n5. End Time\n");
							innerSelect = myInput.nextInt();
							switch (innerSelect) {
							case 1: System.out.println("Please enter new day:");
									newArg = myInput.nextInt(); 
									auct.setMyDay(newArg);break;
									
							case 2: System.out.println("Please enter new month:"); 
									newArg = myInput.nextInt(); 
									auct.setMyMonth(newArg); break;
							case 3: System.out.println("Please enter new year:"); 
									newArg = myInput.nextInt(); 
									auct.setMyYear(newArg); break;
							case 4: System.out.println("Please enter new start time:"); 
									newArg = myInput.nextInt(); 
									auct.setMyStartTime(newArg); break;
							case 5: System.out.println("Please enter new end time:"); 
									newArg = myInput.nextInt(); 
									auct.setMyEndTime(newArg); break;
							default: System.out.println("Incorrect input."); break;
							}
						} else {
							System.out.println("You have no auction to edit.");
						} break;
				case 4: if (hasAuction()) {
							String itemName = "";
							double itemStart = 0.0;
							
							System.out.println("Please enter the name of the item:\n");
							itemName = myInput.next();
							System.out.println("Please enter the starting bid amount for the item:\n");
							itemStart = myInput.nextDouble();
							auct.addItem(new Item(itemName, itemStart));
						} else {
							System.out.println("You have no auction to add items to.");
						} break;
				
				case 5: if (hasAuction()) {
							String itemName, itemNewName;
							Item editItem = null;
							double itemNewStart;
							int innerSelect;
							
							System.out.println("Which item would you like to edit?\n");
							System.out.println(auct.itemsToString());
							itemName = myInput.next();
							for (Item i : auct.getMyItems()) {
								if (itemName.equals(i.getMyName())) {
									editItem = i;
									break;
								}
							}
							if (editItem != null) {
								System.out.println("What would you like to change?\n");
								System.out.println("1. Item name\n2. Item starting bid");
								innerSelect = myInput.nextInt();
							
								switch (innerSelect) {
									case 1: System.out.println("Please enter new item name:\n");
										itemNewName = myInput.next();
										editItem.setMyName(itemNewName); break;
									case 2: System.out.println("Please enter new starting bid:\n");
										itemNewStart = myInput.nextDouble();
										editItem.setMyStartingBid(itemNewStart); break;
								}
							} else {
								System.out.println("Item does not exist.\n");
							}
						} break;
				case 6: System.out.println("Logging out... Good-bye!"); break;
				default: System.out.println("Invalid selection!"); break;
			}
		}
	}
	
	/**
	 * This is the menu for bidder actions.
	 */
	public static void bidderMenu() {
		int selection = 0;
		String selectName = "";
		String finder = "";
		Auction selectedAuction = null;
		List<Auction> auctionList = myAuctionCalendar.getListOfAuctions();
		List<Item> selectedItemList = null;
		Item selectedItem = null;
		boolean found = false;
		boolean previousBid = false;
		int index = -1;
		
		while (selection != 5) {
			System.out.println("\nHello, Bidder! What would you like to do?");
			System.out.println("\n1. Choose Auction\n2. Bid on Item in Auction");
			System.out.println("3. Change Existing Bid\n4. Cancel existing bid\n5. Log Out");
			selection = myInput.nextInt();
			switch (selection) {
				case 1: System.out.println("Please enter the name of the organization hosting\n the auction:\n");
					selectName = myInput.next();
					finder = myUser.viewAuction(myAuctionCalendar, selectName); 
					System.out.println(finder);
					if (!finder.equals("Nothing was found.")) {
						for (int i = 0; i < auctionList.size(); i++) {
							if (auctionList.get(i).getMyNonProfit().equals(selectName)) {
								selectedAuction = auctionList.get(i);
								selectedItemList = selectedAuction.getMyItems();
								break;
							}
						}
					}
					break;
		
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
							if (selectedItemList.get(i).getMyBids().containsKey(myUser.getMyName())) {
								previousBid = true;
							}
							break;
						}
					}
					if (found && !previousBid) {
						System.out.println("Please enter bid amount:");
						double bid = myInput.nextDouble();
						boolean valid = ((Bidder)myUser).placeBid(selectedItemList.get(index), bid);
						if (valid) {
							System.out.println("Bid placed successfully");
						} else {
							System.out.println("Not a valid bid");
						}
					} else if (found && previousBid) {
						System.out.println("You already have a pre-existing bid for this item.");
					} else {
						System.out.println("No such item found...");
					}
					break;
			
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
					break;
				
				case 4: System.out.println("You currently have bids on the following items:\n");
					System.out.println(((Bidder) myUser).getMyBids() + "\n");
					System.out.println("Which bid would you like to cancel?\n");
					selectName = myInput.next();
					selectedItem = getItemFromList(selectName);
					if (selectedItem != null && ((Bidder)myUser).cancelBid(selectedItem)) {
						System.out.println("Bid successfully canceled.");
					} else {
						System.out.println("No bid for selected item to cancel.");
					}
					break;
				case 5: System.out.println("Logging out... Good-bye!"); break;
				default: System.out.println("Invalid selection!"); break;
			}
		}
	}

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
					for (Auction a : myAuctions) {
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
			} else {
				System.out.println("Something failed...");
			}
		}
		
	}	
	

 //RANDOM HELPER METHODS
 	
	private static Item getItemFromList(String theName) {
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
	
	public static void scheduleAuctionMenu() {
		CalendarClass c = myAuctionCalendar;
		
		int month, day, year, start, end;
		System.out.println("Enter Month:\n");
		month = myInput.nextInt();
		System.out.println("Enter Day:\n");
		day = myInput.nextInt();
		System.out.println("Enter Year:\n");
		year = myInput.nextInt();
		System.out.println("Enter Start Time:\n");
		start = myInput.nextInt();
		System.out.println("Enter End Time:\n");
		end = myInput.nextInt();
		if (c.checkOrganization(((NonProfitEmployee)myUser).getMyOrganizationName())
				&& c.isAvailable(month, day, start, end)
				&& c.belowMaxAuctions()
				&& c.belowMaxDaysToFuture(month, day)
				&& c.belowWeekLimit(month, day)) {
			c.addAuction(((NonProfitEmployee)myUser).scheduleAuction(((NonProfitEmployee)myUser).getMyOrganizationName(), month, day, year, start, end));
			System.out.println("Auction Scheduled");
	
		} else {
			if (!c.checkOrganization(((NonProfitEmployee)myUser).getMyOrganizationName())) {
				System.out.println("Your organization already has an auction scheduled.\n");
			}
			if (!c.isAvailable(month, day, start, end)) {
				System.out.println("Selected time is not available.\n");
			}
			if (!c.belowMaxAuctions()) {
				System.out.println("Sorry, we have a full set of auctions right now.\n");
			}
			if (!c.belowMaxDaysToFuture(month, day)) {
				System.out.println("Selected time is too far in advance.\n");
			}
			if (!c.belowWeekLimit(month, day)) {
				System.out.println("Sorry, we are full for that particular week\n");
			}
		}
	}
	
	public static void enterAuctionInfoMenu() {
		System.out.println("Don't know what do do here.");
	}
	
	private static boolean hasAuction() {
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
	
	private static Auction getAuction() {
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

