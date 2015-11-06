import java.util.Scanner;

//Testing
// new branch
public class AuctionCentral {

	private static Scanner input;
	private static Calendar auctionCalendar;
	private static User appUser;
	
	public static void main(String[] args) {
		input = new Scanner(System.in);
		initializeCalendar();
		initialMenu();

	}

	public static void initialMenu() {
		System.out.println("\nWelcome to AuctionCentral!\n--------------------------");
		System.out.println("\nPlease select your User type\n");
		System.out.println("1. AuctionCentral Employee\n2. Non-Profit Organization\n3. Bidder");
		int selection = input.nextInt();
		switch (selection) {
			case 1: employeeMenu(); break;
			case 2: npoMenu(); break;
			case 3: bidderMenu(); break;
			default: initialMenu(); break;
		}
	}
	
	public static void employeeMenu() {
		//appUser = new Employee();
		System.out.println("\nHello, Employee! What would you like to do?");
		System.out.println("\n1. View Calendar\n2. Other Thing");
		int selection = input.nextInt();
		switch (selection) {
			//Will do something.
			case 1: break;
			case 2: break;
			default: employeeMenu(); break;
		}
	}
	public static void npoMenu() {
		//appUser = new NonProfit();
		System.out.println("in 2");
	}
	public static void bidderMenu() {
		//appUser = new Bidder();
		System.out.println("in 3");
	}
	
	private static void initializeCalendar() {
		auctionCalendar = new Calendar();
	}
}
