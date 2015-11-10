

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

		//add auction and set all of the fields.
		board.getMonth(monthIndex).getDay(day).setTodaysAuctions(auction.toString());
		board.getMonth(monthIndex).getDay(day).setStartTime(startTime);
		board.getMonth(monthIndex).getDay(day).setEndTime(endTime);
		
		auctionList.add(auction);
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
		
		//if the day is empty
		if(board.getMonth(monthIndex).getDay(day).isEmpty()) {
			//if previous action ended at the end of the previous day
			if(board.getMonth(monthIndex).getDay(day-1).getEndTime() == 23 && startTime < 1) {
				return false;
			} else if(board.getMonth(monthIndex).getDay(day-1).getEndTime() == 24 && startTime < 2) {
				return false;
			}
			else return true;
		}
			
		//check if day is available (so if there is one auction already or more.
		boolean isDayAvailable = board.getMonth(monthIndex).getDay(day).getAvailability();
		
		//if day is not available --- exit
		if(!isDayAvailable) return isDayAvailable;
		else {
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
	}
	
	/**
	 * This method makes a list of auctions.
	 * @return String with a list of auctions.
	 */
	public String getListOfAuctions() {
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
		return board.getMonth(calculateOffset(month)).toStringAvailableDays();
	}
	
	/**
	 * This method would return auctions in the current month
	 * @return 
	 */
	public String getMonth(int month) {
		return board.getMonth(calculateOffset(month)).toStringAuctions();
	}
	/**
	 * This method would return the auctions in the current day.
	 */
	public String getDay(int month, int day) {
		return board.getMonth(calculateOffset(month)).getDay(day).toString();
	}
	
	public void insertAuctions(ArrayList<Auction> auctionList) {
		this.auctionList = auctionList;
	}
	
	public String viewAuction(String auctionName) {
		String toReturn = new String();
		for(int i = 0; i < auctionList.size(); i++) {
			
			//TODO might be changed, dont see a point of this method for now.
			if(auctionName.equals(auctionList.get(i).getMyNonProfit())) {
				toReturn = auctionList.get(i).getMyName();
			}
		}
		if(toReturn.equals("")) toReturn = "Nothing was found.";
		return toReturn;
	}
	
	public boolean checkOrganization(String organizationName) {
		for(int i = 0; i < auctionList.size(); i++) {
			//implies it is not per 12 month period but per year (itself, number)
			if(organizationName.equals(auctionList.get(i).getMyNonProfit())) {
				return false;
			}
		}
		return true;
	}
}
