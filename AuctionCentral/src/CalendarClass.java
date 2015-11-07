/*
 * This is a Calendar class.
 * Author Artsiom Vainilovich
 * 11.6.2015
 * Group 4 Project
 */

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
	private Board board;
	
	/**
	 * This is a constructor method for the calendar.
	 */
	public CalendarClass() {
		//initialize the board with four months.
		board = new Board();
	}

	/**
	 * This method adds an auction to the list of auctions.
	 * @param auction that is passed from the Auction class to be added to the list of auctions.
	 */
	public void addAuction(Auction auction) {
		//separating date and time from the auction object
		int year = auction.getYear();
		int month = auction.getMonth();
		int day = auction.getDay();
		int startTime = auction.getStartTime();
		int endTime = auction.getEndTime();
		
		//calculate offset to know where to add in our List
		int monthIndex = calculateOffset(month);

		//add a string representation of auction to the DAY
		board.listOfMonths.get(monthIndex).days.get(day).todaysAuctions.add(auction.toString());
		//add auction to the list
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
		
		//check if day is available
		boolean isDayAvailable = board.listOfMonths.get(monthIndex).days.get(day).isAvailable();
		
		//check if time is available
		boolean isTimeAvailable;
		if (startTime - board.listOfMonths.get(monthIndex).days.get(day).endTime < 2) {
			System.out.println("START TIME IS BAD: " + startTime);
			isTimeAvailable = false;
		}
		else {
			isTimeAvailable = true;
			board.listOfMonths.get(monthIndex).days.get(day).startTime = startTime;
			board.listOfMonths.get(monthIndex).days.get(day).endTime = endTime;
		}
		return isDayAvailable && isTimeAvailable;
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
	public void getListOfDays() {
		
	}
	/**
	 * This method would return a month
	 */
	public void getMonth() {
		
	}
	/**
	 * This method would return a day
	 */
	public void getDay() {
		
	}
	
	/**
	 * This is a board private class that keeps 4 months on it
	 * @author Artsiom Vainilovich
	 * @version 1.0
	 */
	private class Board {
		//this is a list of months.
		private ArrayList<Month> listOfMonths = new ArrayList<Month>();
		
		/**
		 * This is a constructor for board.
		 */
		public Board() {
			//this is a template calendar to get rid of at the end
			Calendar templateCalendar = Calendar.getInstance();
			//creates four months with a correct number of days.
			for (int i = 0; i < 4; i++) {
				//add month to the list
				listOfMonths.add(new Month(templateCalendar.get(Calendar.MONTH), 
						templateCalendar.getActualMaximum(templateCalendar.get(Calendar.DAY_OF_MONTH))));
				//increment month
				templateCalendar.add(Calendar.MONTH, 1);
			}
		}
		/**
		 * This is a private class for the month.
		 * @author Artsiom Vainilovich
		 * @version 1.0
		 */
		private class Month {
			//this is a month number (unused yet)
			private int monthNumber;
			
			//this is a maximum number of days in the month
			private int maxDays;
			
			//this is a list of days in the month
			private ArrayList<Day> days = new ArrayList<Day>();
			
			/**
			 * This is a constructor method for month.
			 * @param monthNumber month number on the calendar
			 * @param maxDays maximum number of days in month
			 */
			public Month(int monthNumber, int maxDays) {
				this.monthNumber = monthNumber;
				this.maxDays = maxDays;
				createDays();
			}
			
			/**
			 * This method creates all days for the month.
			 */
			private void createDays() {
				for(int i = 1; i <= maxDays; i++)
					days.add(new Day(i, new ArrayList<String>(), 00, 00));
			}
			
			/**
			 * This is a private class for day.
			 * @author Artsiom Vainilovich
			 * @version 1.0
			 */
			private class Day {
				//this is a day number of the month
				private int day;
				
				//this is a list of auctions today.
				private List<String> todaysAuctions;
				
				//this is a start time of an auction
				private int startTime;
				
				//this is a end time of an auction
				private int endTime;
				
				/**
				 * This is a constructor method for the Day.
				 * @param day number of the month
				 * @param todaysAuctions auctions held today
				 * @param startTime when auction begins
				 * @param endTime when auction ends
				 */
				public Day(int day, List<String> todaysAuctions, 
						int startTime, int endTime) {
					this.day = day;
					this.todaysAuctions = todaysAuctions;
					this.startTime = startTime;
					this.endTime = endTime;
				}
				
				/**
				 * This is a method that decides if we do not have space in the day.
				 * @return true or false, available or not available
				 */
				public boolean isAvailable() {
					//if there is at least one auction today already
					if(!todaysAuctions.isEmpty()) {
						//if there is more than one auction today
						if(todaysAuctions.size() > 1)
							return false;
						return true;
					}
					return true;
				}
			}
		}
	}
}
