import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public abstract class AbstractUI {
	
	protected BufferedReader myInput;
	protected CalendarClass myAuctionCalendar;
	protected User myUser;
	
	public AbstractUI(BufferedReader theInput, CalendarClass theCalendar) {
		myInput = theInput;
		myAuctionCalendar = theCalendar;
	}
	
	/**
	 * When implemented, this method will launch the user's main menu.
	 * 
	 * @param theUser is the user of the program.
	 */
	abstract void menu(User theUser);
	
	/**
	 * Setter method for assigning a UI class a specific user.
	 */
	public void setUser(User theUser) {
		myUser = theUser;
	}
	/**
	 * Method to ensure number being read in myInput is an int.
	 * @return an int from input.
	 */
	public int readInt() {
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
	 * Method to ensure number being read in myInput is a double.
	 * @return a double from input.
	 */
	public double readDouble() {
		String read;
		try {
			read = myInput.readLine();
			double num = Double.parseDouble(read);
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
	 * Method to ensure input string is properly formatted.
	 * @return a String from input.
	 */
	public String readString() {
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
	
	/**
	 * Generates an indexed list of selectable auctions.
	 * @return a list of selectable auctions.
	 */
	public String getIndexedAuctions() {
		List<Auction> temp = myAuctionCalendar.getListOfAuctions();
		StringBuilder toReturn = new StringBuilder();
		for (int i = 0; i < temp.size(); i++) {
			toReturn.append(i + 1);
			toReturn.append(". ");
			toReturn.append(temp.get(i).getMyName());
			toReturn.append("\n");
		}
		return toReturn.toString();
	}
	
	/**
	 * Generates an indexed list of items available in an auction.
	 * 
	 * @return the indexed list of items in auction.
	 */
	public String getIndexedItems(Auction theAuction) {
		List<Item> temp = theAuction.getMyItems();
		StringBuilder toReturn = new StringBuilder();
		for (int i = 0; i < temp.size(); i++) {
			toReturn.append(i + 1);
			toReturn.append(". ");
			toReturn.append(temp.get(i).getMyName());
			toReturn.append(" - Starting Bid = ");
			toReturn.append(temp.get(i).getMyStartingBid());
			toReturn.append("\n");
		}
		return toReturn.toString();
	}
}
