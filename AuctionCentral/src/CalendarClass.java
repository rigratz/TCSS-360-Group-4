import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This is Calendar class that keeps track of all auctions.
 * @author Artsiom Vainilovich
 * @version 2.0
 */
public class CalendarClass {
	
	//this is a calendar
	private Calendar calendar2 = Calendar.getInstance();
	//this is a list of auctions
	private ArrayList<Auction> auctionList = new ArrayList<Auction>();
	private ArrayList<Auction> pastAuctionList = new ArrayList<Auction>();
	
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
	protected int calculateOffset(int month) {
		Calendar tempCalendar = calendar2;
		//return 0 if our target month is a current month
		if(tempCalendar.get(Calendar.MONTH) == (month-1)) 
			return 0;
		//return the offset if our target month is bigger than our current month.
		else if (tempCalendar.get(Calendar.MONTH) < (month-1)) {
			int index = maximumMonth(tempCalendar.get(Calendar.MONTH), month-1) - 
					minimum(tempCalendar.get(Calendar.MONTH), month-1);
			return index;
		}
		//return in all other cases (if target month is less than current month)
		else// (calendar2.get(Calendar.MONTH) > (month-1))
			return ((month -1 + 12) - tempCalendar.get(Calendar.MONTH));
	}
	
	/**
	 * This class calculates maximum month number.
	 * @param currentMonth this is a current month
	 * @param targetMonth this is a target month
	 * @return integer maximum month number
	 */
	protected int maximumMonth(int currentMonth, int targetMonth) {
		if (calendar2.get(Calendar.MONTH) > targetMonth)
			return calendar2.get(Calendar.MONTH)+1;
		else return targetMonth;
	}
	/**
	 * This class calculates maximum number.
	 * @param one this is a first number
	 * @param two this is a second number
	 * @return integer maximum  number
	 */
	protected int maximum(int one, int two) {
		if (one > two)
			return one;
		else return two;
	}
	
	/**
	 * This class calculates minimum month number.
	 * @param currentMonth this is a current month
	 * @param targetMonth this is a target month
	 * @return integer minimum month number
	 */
	protected int minimum(int currentMonth, int targetMonth) {
		if (currentMonth < targetMonth)
			return currentMonth;
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
		if(monthIndex >= 0 && monthIndex < 4) {
			if(board.getMonth(monthIndex).getDay(day).getNumberOfAuctions() == 0) {
				//if previous action ended at the end of the previous day
				if(board.getMonth(monthIndex).getDay(day-1).getEndTime() == 
						23 && 
						startTime < 1) {
					return false;
				} else if(board.getMonth(monthIndex).getDay(day-1).getEndTime() == 
						24 && 
						startTime < 2) {
					return false;
				}
				else return true;
			}
			
			//check if day is available (so if there is one auction already or more.
			int numberOfAuctions = board.getMonth(monthIndex).getDay(day).getNumberOfAuctions();
			
			//if day is not available --- exit
			if(numberOfAuctions == 2) return false;
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
			
		} else return false;
	}
	
	/**
	 * This method calculates if we have too many auctions in a week.
	 * @param month of the week
	 * @param day of the week
	 * @return true or false
	 */
	public boolean belowWeekLimit(int month, int day) {
		Calendar tempCalendar = Calendar.getInstance();
		tempCalendar.set(Calendar.MONTH, month-1);
		tempCalendar.set(Calendar.DAY_OF_MONTH, day);
		int numberOfAuctionsInWeek = 0;
		//go 7 days back and count from there
		
		tempCalendar.add(Calendar.DAY_OF_MONTH, -6);
		//count from that day any 7 rolling days to the current day
		for(int i = 0; i < 7; i++) {
			int temp = 0;
			for(int j = 0; j < 7; j++) {
				int monthIndex = calculateOffset(tempCalendar.get(Calendar.MONTH)+1);

				int currentDay = tempCalendar.get(Calendar.DAY_OF_MONTH);
				temp += board.getMonth(monthIndex)
						.getDay(currentDay).getNumberOfAuctions();
				tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
			}
			numberOfAuctionsInWeek = maximum(numberOfAuctionsInWeek, temp);
			tempCalendar.add(Calendar.DAY_OF_MONTH, -6);
		}
		if(numberOfAuctionsInWeek > 4) return false;
		else return true;
	}
	
	/**
	 * This method calculates if we have less than max auctions.
	 * @return true or false
	 */
	public boolean belowMaxAuctions() {
		int auctionsNum = 0;
		Calendar tempCalendar = Calendar.getInstance();
		tempCalendar.add(Calendar.DAY_OF_MONTH, 90);
		
		for(int i = 0; i < 90; i++) {
			int monthIndex = calculateOffset(tempCalendar.get(Calendar.MONTH)+1);
			auctionsNum += board.getMonth(monthIndex)
					.getDay(tempCalendar.get(Calendar.DAY_OF_MONTH))
					.getNumberOfAuctions();
			tempCalendar.add(Calendar.DAY_OF_MONTH, -1);
		}
		if(auctionsNum > 25) return false;
		else return true;
	}
	
	/**
	 * This method calculates if we trying to schedule auction too far into future.
	 * @param month of the auction
	 * @param day of the auction
	 * @return true or false
	 */
	public boolean belowMaxDaysToFuture(int month, int day, int year) {
		Calendar tempCalendar = Calendar.getInstance();
		tempCalendar.add(Calendar.DAY_OF_MONTH, 90);
		int myMonthIndex = calculateOffset(month);
		
		if(year > calendar2.get(Calendar.YEAR) &&
				(myMonthIndex >= 0 && myMonthIndex < 2)) {
			return false;
		}
		if(myMonthIndex >= 0 && myMonthIndex < 4) {
			if(myMonthIndex == 3) {
				if(day > tempCalendar.get(Calendar.DAY_OF_MONTH)) {
					return false;	
				}
				return true;
			}
			else return true;
		} else return false;
	}
	
	/**
	 * This method makes a list of auctions.
	 * @return String with a list of auctions.
	 */
	public String getAllAuctions() {
		String listOfAuctions = "";
		for(int i = 0; i < 4; i++) {
			for(int j = 1; j <= board.getMonth(i).getMaxDays(); j++) {
				if(board.getMonth(i).getDay(j).getNumberOfAuctions() > 0) {
					listOfAuctions += board.getMonth(i).getDay(j).toString();
				}
			}
		}
		return listOfAuctions;
	}
	/**
	 * This method returns a string of past auctions
	 * @return listOfPastAuctions of past auctions.
	 */
	public String getPastAuctions() {
		//add past auctions
		String listOfPastAuctions = "";
		listOfPastAuctions += "Past Auctions:\n";
		for(int i = 0; i < pastAuctionList.size(); i++) {
			listOfPastAuctions += pastAuctionList.get(i).toString() + "\n";
		}
		return listOfPastAuctions;
	}
	
	/**
	 * This method returns a list of past auctions
	 * @return pastAuctionList a list of past auctions
	 */
	public List<Auction> getListOfPastAuctions() {
		return pastAuctionList;
	}
	
	/**
	 * This method returns a list of auctions.
	 * @return a list of auctions
	 */
	public List<Auction> getListOfAuctions() {
		return auctionList;
	}
	
	/**
	 * This method shows the informatino of the auction.
	 * @param name of the auction
	 * @return the auction information
	 */
	public Auction getAuction(String name) {
		Auction auct = null;
		for (int i = 0; i < auctionList.size(); i++) {
			if(name.equals(auctionList.get(i).getMyName())) {
				auct = auctionList.get(i);
				break;
			}
		}
		return auct;
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
	
	/**
	 * This method inserts a list of auctions.
	 * @param auctionList list of auctions
	 */
	public void insertAuctions(ArrayList<Auction> auctionList) {
		for(int i = 0; i < auctionList.size(); i++) {
			int monthIndex = calculateOffset(auctionList.get(i).getMyMonth());
			if((monthIndex >= 0 && monthIndex < 4)) {
				if(monthIndex == 0) {
					if(auctionList.get(i).getMyDay() >= calendar2.get(Calendar.DAY_OF_MONTH)) {
						if (isAvailable(auctionList.get(i).getMyMonth(), 
								auctionList.get(i).getMyDay(), 
								auctionList.get(i).getMyStartTime(), 
								auctionList.get(i).getMyEndTime())
								&& belowMaxAuctions()
								&& belowMaxDaysToFuture(auctionList.get(i).getMyMonth(), 
										auctionList.get(i).getMyDay(), auctionList.get(i).getMyYear())
								&& belowWeekLimit(auctionList.get(i).getMyMonth(), 
										auctionList.get(i).getMyDay())) {
							addAuction(auctionList.get(i));
						}
					}
					else {
						pastAuctionList.add(auctionList.get(i));
					}
				}
				else if (isAvailable(auctionList.get(i).getMyMonth(), 
						auctionList.get(i).getMyDay(), 
						auctionList.get(i).getMyStartTime(), 
						auctionList.get(i).getMyEndTime())
						&& belowMaxAuctions()
						&& belowMaxDaysToFuture(auctionList.get(i).getMyMonth(), 
								auctionList.get(i).getMyDay(), auctionList.get(i).getMyYear())
						&& belowWeekLimit(auctionList.get(i).getMyMonth(), 
								auctionList.get(i).getMyDay())) {
					addAuction(auctionList.get(i));
				}
			}
			else {
				pastAuctionList.add(auctionList.get(i));
			}
		}
	}
	
	/**
	 * This method returns information about the auction
	 * @param name of the auction
	 * @return information about the auction
	 */
	public String viewAuction(String name) {
		String toReturn = new String();
		for(int i = 0; i < auctionList.size(); i++) {
			if(name.equals(auctionList.get(i).getMyNonProfit())) {
				toReturn = auctionList.get(i).toString() + "\nList of Items/Starting Bids:\n";
				toReturn += auctionList.get(i).itemsToString();
				return toReturn;
			}
		}
		if(toReturn.equals("")) toReturn = "Nothing was found.";
		return toReturn;
	}
	
	/**
	 * This method checks organization for validity.
	 * @param organizationName of the organization
	 * @return true or false.
	 */
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
