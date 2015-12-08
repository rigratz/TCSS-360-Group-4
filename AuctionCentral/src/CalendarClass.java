import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This is the Calendar class. This class takes care of all of the auctions that are scheduled. 
 * @author Artsiom Vainilovich
 * @version 5.0
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
	 * @param auction that is passed from the driver class to be added to the list of auctions.
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
		board.getMonth(monthIndex).getDay(day).setTodaysAuctions(auction);
		board.getMonth(monthIndex).getDay(day).setStartTime(startTime);
		board.getMonth(monthIndex).getDay(day).setEndTime(endTime);
		
		auctionList.add(auction);
	}
	
	/**
	 * This class is for calculating where our month belongs in our 0-4 array.
	 * It finds relationship(difference) between the current month and the month we want to add to.
	 * @param month that we want to find difference to from current month.
	 * @return integer between 0-4, the difference between current month and the target month.
	 */
	protected int calculateOffset(int month) {
		Calendar tempCalendar = calendar2;
		//return 0 if our target month is a current month
		if(tempCalendar.get(Calendar.MONTH) == (month-1)) return 0;
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
	 * This class compares two months and figures out which one is bigger.
	 * @param currentMonth in the calendar to compare to.
	 * @param targetMonth to be compared with the current month.
	 * @return largest(bigger) month number.
	 */
	protected int maximumMonth(int currentMonth, int targetMonth) {
		if (calendar2.get(Calendar.MONTH) > targetMonth)
			return calendar2.get(Calendar.MONTH)+1;
		else return targetMonth;
	}
	/**
	 * This class calculates what number is bigger.
	 * @param one, the first number to check.
	 * @param two, the second number to check.
	 * @return integer, maximum  number.
	 */
	protected int maximum(int one, int two) {
		if (one > two)
			return one;
		else return two;
	}
	
	/**
	 * This class calculates what month is smaller.
	 * @param currentMonth to be compared.
	 * @param targetMonth to be compared to the current month.
	 * @return integer smaller month number.
	 */
	protected int minimum(int currentMonth, int targetMonth) {
		if (currentMonth < targetMonth)
			return currentMonth;
		else return targetMonth;
	}
	
	/**
	 * This method checks if there is space in the day to add
	 * an auction and if there is time available to schedule an auction.
	 * @param month, month to check if its available at.
	 * @param day, day to check if its available.
	 * @param startTime, start time to check if its valid and available.
	 * @param endTime, end time to check if its valid and available.
	 * @return true if and only if the day and time is available and valid.
	 */
	public boolean isAvailable(int month, int day, int startTime, int endTime) {
		//subtract month scheduled from current month to get offset in index
		int monthIndex = calculateOffset(month);
		if(monthIndex < 0 || monthIndex > 3) return false;
		if(board.getMonth(monthIndex).getDay(day).getNumberOfAuctions() > 1) return false;
		if(board.getMonth(monthIndex).getDay(day-1).getEndTime() == 23 && startTime < 1) return false;
		if(board.getMonth(monthIndex).getDay(day-1).getEndTime() == 24 && startTime < 2) return false;
		if(board.getMonth(monthIndex).getDay(day).getNumberOfAuctions() == 0) return true;
		
		if(startTime > board.getMonth(monthIndex).getDay(day).getStartTime()-2 
				&& startTime < board.getMonth(monthIndex).getDay(day).getEndTime()+2) return false;
		if(endTime > board.getMonth(monthIndex).getDay(day).getStartTime()-2 
				&& endTime < board.getMonth(monthIndex).getDay(day).getEndTime()+2) return false;
		if(startTime <= board.getMonth(monthIndex).getDay(day).getStartTime()-2 
				&& endTime >= board.getMonth(monthIndex).getDay(day).getEndTime()+2) return false;
		return true;
	}
	
	/**
	 * This method removes auction from the auction list and from the auction list in a day.
	 * @param auct, auciton to be removed from the system
	 */
	public void removeAuction(Auction auct) {
		int monthIndex = calculateOffset(auct.getMyMonth());
		for (int i = 0; i < auctionList.size(); i++) {
			if (auctionList.get(i).getMyNonProfit().equals(auct.getMyNonProfit())) {
				auctionList.remove(i);
				
				List<Auction> list = board.getMonth(monthIndex).getDay(auct.getMyDay()).getTodaysAuctions();
				for (int j = 0; j < list.size(); j++) {
					if(list.get(j).getMyNonProfit().equals(auct.getMyNonProfit())) {
						board.getMonth(monthIndex).getDay(auct.getMyDay()).getTodaysAuctions().remove(j);
						break;
					}
				}
				break;
			}
		}
	}
	
	/**
	 * This method checks if we have too many auctions in a seven day period.
	 * @param month, month of the seven day period to check.
	 * @param day, day of the seven day period to check.
	 * @return true if and only if there is less auctions than our maximum allowed.
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
				if(monthIndex < 0 || monthIndex > 3) return false;
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
	 * This method calculates if we have less than maximum auctions in the 90 day period.
	 * @return true IFF we have less than maximum number of auctions allowed in the 90 day period.
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
	 * This method checks if we try to schedule an auction too far into future.
	 * @param month of the auction to check if its too far.
	 * @param day of the auction in the month to check if its too far.
	 * @param year, a year to check if its too far.
	 * @return true IFF pending auction is not further than the maximum number of days allowed.
	 */
	public boolean belowMaxDaysToFuture(int month, int day, int year) {
		Calendar tempCalendar = Calendar.getInstance();
		tempCalendar.add(Calendar.DAY_OF_MONTH, 90);
		int myMonthIndex = calculateOffset(month);
		
		if(year > calendar2.get(Calendar.YEAR) &&
				(myMonthIndex < 0 && myMonthIndex > 3)) return false;
		if(myMonthIndex < 0 || myMonthIndex > 3) return false;
		if(myMonthIndex == 3 && day > tempCalendar.get(Calendar.DAY_OF_MONTH)) return false;
		return true;
	}
	
	/**
	 * This method returns a list of past auctions.
	 * @return pastAuctionList a list that contains all past auctions.
	 */
	public List<Auction> getListOfPastAuctions() {
		return pastAuctionList;
	}
	
	/**
	 * This method returns a list of active auctions.
	 * @return aauctionList, a list with active, future auctions.
	 */
	public List<Auction> getListOfAuctions() {
		return auctionList;
	}
	/**
	 * This method would return all days in the requested month.
	 * @param month, a month to get days from.
	 * @return Month with all days. 
	 */
	public Month getListOfDays(int month) {
		return board.getMonth(calculateOffset(month));
	}
	
	/**
	 * This method would return auctions in the current month.
	 * @param month, a month to get the auctions from.
	 * @return list of active, future auctions in the whole month.
	 */
	public List<Auction> getMonth(int month) {
		return board.getMonth(calculateOffset(month)).listOfAuctions();
	}
	/**
	 * This method would return the auctions in the day specified.
	 * @param month, a month to get a day from.
	 * @param day, a day to get the auctions from.
	 * @return a list of auctions in the day specified.
	 */
	public List<Auction> getDay(int month, int day) {
		return board.getMonth(calculateOffset(month)).getDay(day).getTodaysAuctions();
	}
	
	/**
	 * This method inserts a list of auctions inserted from the driver class.
	 * @param auctionList, list of auctions sent from the driver class.
	 */
	public void insertAuctions(ArrayList<Auction> auctList) {
		for(int i = 0; i < auctList.size(); i++) {
			int monthIndex = calculateOffset(auctList.get(i).getMyMonth());
			if((monthIndex >= 0 && monthIndex < 4)) {
				if(monthIndex == 0) {
					if(auctList.get(i).getMyDay() >= calendar2.get(Calendar.DAY_OF_MONTH)) {
						if (isAvailable(auctList.get(i).getMyMonth(), auctList.get(i).getMyDay(), 
								auctList.get(i).getMyStartTime(), auctList.get(i).getMyEndTime())
								&& belowMaxAuctions() 
								&& belowMaxDaysToFuture(auctList.get(i).getMyMonth(), 
										auctList.get(i).getMyDay(), auctList.get(i).getMyYear())
								&& belowWeekLimit(auctList.get(i).getMyMonth(), auctList.get(i).getMyDay())) {
							addAuction(auctList.get(i));
						}
					}
					else pastAuctionList.add(auctList.get(i));
				}
				else {
					if (isAvailable(auctList.get(i).getMyMonth(), auctList.get(i).getMyDay(), 
						auctList.get(i).getMyStartTime(), auctList.get(i).getMyEndTime())
						&& belowMaxAuctions() && belowMaxDaysToFuture(auctList.get(i).getMyMonth(), 
								auctList.get(i).getMyDay(), auctList.get(i).getMyYear())
						&& belowWeekLimit(auctList.get(i).getMyMonth(), auctList.get(i).getMyDay())) {
						addAuction(auctList.get(i));
					}
				}
			}
			else pastAuctionList.add(auctList.get(i));
		}
	}
	
	/**
	 * This method checks organization on the matter of already existing auctions under their name.
	 * @param organizationName, organization name to check by in the list of auctions.
	 * @return true IFF the organization doesn't have any auctions in the current year.
	 */
	public boolean checkOrganization(String organizationName) {
		for(int i = 0; i < auctionList.size(); i++) {
			//implies it is not per 12 month period but per year.
			if(organizationName.equals(auctionList.get(i).getMyNonProfit())) {
				return false;
			}
		}
		return true;
	}
}
