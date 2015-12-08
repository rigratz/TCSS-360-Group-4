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
	         new FileOutputStream("testWeekFullAuct.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         for (Auction auction : myAuctionCalendar.getListOfAuctions()) {
	        	 out.writeObject(auction);
	         }
	         for (Auction auction : myAuctionCalendar.getListOfPastAuctions()) {
	        	 out.writeObject(auction);
	         }
	         out.close();
	         fileOut.close();
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
	         FileInputStream fileIn = new FileInputStream("testWeekFullAuct.ser");
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
		return tempList;
	}
	
	/**
	 * Reads serialized user data from an external file.
	 */
	public static List<User> deserializeUsers() {
		List<User> tempList = new ArrayList<User>();
		try {
	         FileInputStream fileIn = new FileInputStream("testFullUs.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         User tempUser = null;
	         

	         while (fileIn.available() > 0) {
	        	 tempUser = (User) in.readObject();
	        	 tempList.add(tempUser);
	        	 
	         }
	         
	         in.close();
	         fileIn.close();

	      } catch(IOException i) {
	         i.printStackTrace();
	      } catch(ClassNotFoundException c) {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	      }

		return tempList;
	}
}

