

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This is Calendar class that keeps track of all auctions.
 * @author Artsiom Vainilovich
 * @version 1.0
 */
public class CalendarClass {
	
	//this is a calendar
	private Calendar calendar2 = Calendar.getInstance();
	
	//this is a list of auctions
	private ArrayList<Auction> auctionList = new ArrayList<Auction>();
	
	//this is a board with four months
	private CalendarBoard board;
	
	/**
	 * This is a constructor method for the calendar.
	 */
	public CalendarClass() {
		//initialize the board with four months.
		board = new CalendarBoard();
	}

	/**
	 * This method adds an auction to the list of auctions.
	 * @param auction that is passed from the Auction class to be added to the list of auctions.
	 */
	public void addAuction(Auction auction) {
		//separating date and time from the auction object
		int year = auction.getMyYear();
		int month = auction.getMyMonth();
		int day = auction.getMyDay();
		int startTime = auction.getMyStartTime();
		int endTime = auction.getMyEndTime();
		
		//calculate offset to know where to add in our List
		int monthIndex = calculateOffset(month);

		//add auction if the day is empty
		if( board.getMonth(monthIndex).getDay(day).isEmpty()) {
			board.getMonth(monthIndex).getDay(day).setTodaysAuctions(auction.toString());
			board.getMonth(monthIndex).getDay(day).setStartTime(startTime);
			board.getMonth(monthIndex).getDay(day).setEndTime(endTime);
			
			auctionList.add(auction);
		}
		//add auction if we have at least one auction already.
		else if(isAvailable(month, day, startTime, endTime)) {
			//add a string representation of auction to the DAY
			board.getMonth(monthIndex).getDay(day).setTodaysAuctions(auction.toString());
			board.getMonth(monthIndex).getDay(day).setStartTime(startTime);
			board.getMonth(monthIndex).getDay(day).setEndTime(endTime);
			
			//add auction to the list
			auctionList.add(auction);
		}
		//if we have 2 auctions already prevent the addition of auciton.
		else {
			System.out.println("Please choose another date/time for: " + auction.toString());
		}
	}
	
	/**
	 * This is class for calculating offset, (how many months we have to skip in our List)
	 * @param month this is month we want to add
	 * @return integer depending on the outcome
	 */
	private int calculateOffset(int month) {
		//return 0 if our target month is a current month
		if(calendar2.get(Calendar.MONTH) == (month-1)) 
			return 0;
		//return the offset if our target month is bigger than our current month.
		else if (calendar2.get(Calendar.MONTH) < (month-1)) {
			int index = maximum(calendar2.get(Calendar.MONTH), month-1) - 
					minimum(calendar2.get(Calendar.MONTH), month-1);
			return index;
		}
		//return in all other cases (if target month is less than current month)
		else// (calendar2.get(Calendar.MONTH) > (month-1))
			return ((month -1 + 12) - calendar2.get(Calendar.MONTH));
	}
	
	/**
	 * This class calculates maximum month number.
	 * @param currentMonth this is a current month
	 * @param targetMonth this is a target month
	 * @return integer maximum month number
	 */
	private int maximum(int currentMonth, int targetMonth) {
		if (calendar2.get(Calendar.MONTH) > targetMonth)
			return calendar2.get(Calendar.MONTH);
		else return targetMonth;
	}
	
	/**
	 * This class calculates minimum month number.
	 * @param currentMonth this is a current month
	 * @param targetMonth this is a target month
	 * @return integer minimum month number
	 */
	private int minimum(int currentMonth, int targetMonth) {
		if (calendar2.get(Calendar.MONTH) < targetMonth)
			return calendar2.get(Calendar.MONTH);
		else return targetMonth;
	}
	
	/**
	 * This method checks if there is anymore spots in the day to add
	 * an auction and if the time specified works for the Auction Rules
	 * @param month this is a month to be looked by
	 * @param day this is a day to check availability
	 * @param startTime this is start time to keep track of
	 * @param endTime this is end time to keep track of
	 * @return true or false (available or not available)
	 */
	public boolean isAvailable(int month, int day, int startTime, int endTime) {
		//subtract month scheduled from current month to get offset in index
		int monthIndex = calculateOffset(month);
		
		//check if day is available
		boolean isDayAvailable = board.getMonth(monthIndex).getDay(day).getAvailability();
		//if day is not available --- exit
		if(!isDayAvailable) return isDayAvailable;
		
		//check if time is available
		boolean isTimeAvailable = true;
		if(board.getMonth(monthIndex).getDay(day).getEndTime() < startTime) {
			if(startTime - board.getMonth(monthIndex).getDay(day).getEndTime() < 2) {
				isTimeAvailable = false;
			} else {
				isTimeAvailable = true;
			}
		}
		else if (board.getMonth(monthIndex).getDay(day).getStartTime() > endTime) {
			if(board.getMonth(monthIndex).getDay(day).getStartTime() - endTime < 2) {
				isTimeAvailable = false;
			} else {
				isTimeAvailable = true;
			}
		}
		return isTimeAvailable;
	}
	
	/**
	 * This method makes a list of auctions.
	 * @return String with a list of auctions.
	 */
	public String getListOfAuctions() {
		//TODO need a toString() from Auction
		String listOfAuctions = "";
		for (int i = 0; i < auctionList.size(); i++) {
			String currentAuction = auctionList.get(i).toString() + "\n";
			listOfAuctions = listOfAuctions + currentAuction;
		}
		return listOfAuctions;
	}
	
	/**
	 * This method would return available days.
	 */
	public String getListOfDays(int month) {
		//TODO do I need a list of available days or just list unavailable days?
		return board.getMonth(calculateOffset(month)).toStringAvailableDays();
	}
	
	/**
	 * This method would return auctions in the current month
	 * @return 
	 */
	public String getMonth(int month) {
		//TODO Returns? the information about the month, auctions scheduled?
		return board.getMonth(calculateOffset(month)).toStringAuctions();
	}
	/**
	 * This method would return the auctions in the current day.
	 */
	public String getDay(int month, int day) {
		//TODO returns? the information about the day.
		return board.getMonth(calculateOffset(month)).getDay(day).toString();
	}
}
