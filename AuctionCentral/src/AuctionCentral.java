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
					userInterfaces.get(0).menu(myUser);
				} else if (myUser instanceof NonProfitEmployee) {
					userInterfaces.get(1).menu(myUser);
				} else if (myUser instanceof Bidder) {
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
					userInterfaces.get(0).menu(myUser);
					break;
				case 2:
					System.out.println("\nPlease type in the name of your organization:\n");
					organizationName = readString();
					newUser = new NonProfitEmployee(userName, contact, organizationName);
					myUsers.add(newUser);
					myUser = newUser;
					userInterfaces.get(1).menu(myUser);
					break;
				case 3: newUser = new Bidder(userName, contact);
					myUsers.add(newUser);
					myUser = newUser;
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

