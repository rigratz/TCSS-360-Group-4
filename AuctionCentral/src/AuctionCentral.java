/*
 * TCSS 360 Group 4
 * AuctionCentral
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the main driver class for the AuctionCentral program.
 * 
 * @author Riley Gratzer
 */
public class AuctionCentral {

	private static final int EMPLOYEE_UI = 0;
	private static final int NPO_UI = 1;
	private static final int BIDDER_UI = 2;
	
	private static BufferedReader myInput;
	private static CalendarClass myAuctionCalendar;
	private static List<AbstractUI> userInterfaces;

	private static List<User> myUsers;
	private static User myUser;
	
	/**
	 * main method.
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		myInput = new BufferedReader(new InputStreamReader(System.in));
		myAuctionCalendar = new CalendarClass();
	
		myAuctionCalendar.insertAuctions(deserializeAuctions());
		myUsers = deserializeUsers();
			
		userInterfaces = new ArrayList<AbstractUI>();
		userInterfaces.add(new ACEmployeeUI(myInput, myAuctionCalendar));
		userInterfaces.add(new NonProfitUI(myInput, myAuctionCalendar));
		userInterfaces.add(new BidderUI(myInput, myAuctionCalendar));
		
//		myUsers = new ArrayList<User>();
//		myUsers.add(new AuctionCentralEmployee("riley", "emp@ac.com"));
//		myUsers.add(new NonProfitEmployee("npo1", "n@npo1.org", "Org1"));
//		myUsers.add(new NonProfitEmployee("npo2", "n@npo1.org", "Org2"));
//		myUsers.add(new NonProfitEmployee("npo3", "n@npo1.org", "Org3"));
//		myUsers.add(new NonProfitEmployee("npo4", "n@npo1.org", "Org4"));
//		myUsers.add(new NonProfitEmployee("npo5", "n@npo1.org", "Org5"));
//		myUsers.add(new NonProfitEmployee("npo6", "n@npo1.org", "Org6"));
//		myUsers.add(new NonProfitEmployee("npo7", "n@npo1.org", "Org7"));
//		myUsers.add(new NonProfitEmployee("npo8", "n@npo1.org", "Org8"));
//		myUsers.add(new NonProfitEmployee("npo9", "n@npo1.org", "Org9"));
//		myUsers.add(new NonProfitEmployee("npo10", "n@npo1.org", "Org10"));
//		myUsers.add(new NonProfitEmployee("npo11", "n@npo1.org", "Org11"));
//		myUsers.add(new NonProfitEmployee("npo12", "n@npo1.org", "Org12"));
//		myUsers.add(new NonProfitEmployee("npo13", "n@npo1.org", "Org13"));
//		myUsers.add(new NonProfitEmployee("npo14", "n@npo1.org", "Org14"));
//		myUsers.add(new NonProfitEmployee("npo15", "n@npo1.org", "Org15"));
//		myUsers.add(new NonProfitEmployee("npo16", "n@npo1.org", "Org16"));
//		myUsers.add(new NonProfitEmployee("npo17", "n@npo1.org", "Org17"));
//		myUsers.add(new NonProfitEmployee("npo18", "n@npo1.org", "Org18"));
//		myUsers.add(new NonProfitEmployee("npo19", "n@npo1.org", "Org19"));
//		myUsers.add(new NonProfitEmployee("npo20", "n@npo1.org", "Org20"));
//		myUsers.add(new NonProfitEmployee("npo21", "n@npo1.org", "Org21"));
//		myUsers.add(new NonProfitEmployee("npo22", "n@npo1.org", "Org22"));
//		myUsers.add(new NonProfitEmployee("npo23", "n@npo1.org", "Org23"));
//		myUsers.add(new NonProfitEmployee("npo24", "n@npo1.org", "Org24"));
//		myUsers.add(new NonProfitEmployee("npo25", "n@npo1.org", "Org25"));
//		myUsers.add(new NonProfitEmployee("npo26", "n@npo1.org", "Org26"));

		initialMenu();
//		serializeAuctions();
//		serializeUsers();
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
				case 3: serializeAuctions();
						serializeUsers(); break;
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
					userInterfaces.get(EMPLOYEE_UI).menu(myUser);
				} else if (myUser instanceof NonProfitEmployee) {
					userInterfaces.get(NPO_UI).menu(myUser);
				} else if (myUser instanceof Bidder) {
					userInterfaces.get(BIDDER_UI).menu(myUser);
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
					userInterfaces.get(EMPLOYEE_UI).menu(myUser);
					break;
				case 2:
					System.out.println("\nPlease type in the name of your organization:\n");
					organizationName = readString();
					newUser = new NonProfitEmployee(userName, contact, organizationName);
					myUsers.add(newUser);
					myUser = newUser;
					userInterfaces.get(NPO_UI).menu(myUser);
					break;
				case 3: newUser = new Bidder(userName, contact);
					myUsers.add(newUser);
					myUser = newUser;
					userInterfaces.get(BIDDER_UI).menu(myUser);
					break;
				default: 
					System.out.println("Invalid selection, please try Account creation again");
					break;
			}
		} else {
			System.out.println("User Name already taken.\n");
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
	
// SERIALIZATION METHODS

	/**
	 * Writes serialized auction data to external file.
	 */
	public static void serializeAuctions() {
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("testFullAuct.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         for (Auction auction : myAuctionCalendar.getListOfAuctions()) {
	        	 out.writeObject(auction);
	         }
	         for (Auction auction : myAuctionCalendar.getListOfPastAuctions()) {
	        	 out.writeObject(auction);
	         }
	         out.close();
	         fileOut.close();
	         //System.out.println("Serialized data is saved in auctions.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	/**
	 * Writes serialized user data to external file.
	 */
	public static void serializeUsers() {
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("testFullUs.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         for (User user : myUsers) {
	        	 out.writeObject(user);
	         }
	         out.close();
	         fileOut.close();
	         //System.out.println("Serialized data is saved in users.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	/**
	 * Reads serialized auction data from an external file.
	 */
	public static ArrayList<Auction> deserializeAuctions() {
		ArrayList<Auction> tempList = new ArrayList<Auction>();
		try
	      {
	         FileInputStream fileIn = new FileInputStream("testFullAuct.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         Auction tempAuction = null;
	         

	         while (fileIn.available() > 0) {
	        	 tempAuction = (Auction) in.readObject();
	        	 tempList.add(tempAuction);
	        	 
	         }
	         
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	      }
		//System.out.println(tempList);
		return tempList;
	}
	
	/**
	 * Reads serialized user data from an external file.
	 */
	public static List<User> deserializeUsers() {
		List<User> tempList = new ArrayList<User>();
		try
	      {
	         FileInputStream fileIn = new FileInputStream("testFullUs.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         User tempUser = null;
	         

	         while (fileIn.available() > 0) {
	        	 tempUser = (User) in.readObject();
	        	 tempList.add(tempUser);
	        	 
	         }
	         
	         in.close();
	         fileIn.close();
//	         for (User a : tempList) {
//	        	 System.out.println(a.getMyContact());
//	        	 System.out.println(a.getMyName());
//	        	 System.out.println(a.getClass());
//	         }
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	      }
		//System.out.println(tempList);
		return tempList;
	}
}

