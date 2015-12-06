import java.util.ArrayList;
import java.util.List;

/**
 * This is a private class for the month.
 * @author Artsiom Vainilovich
 * @version 2.0
 */
public class Month {
	//this is a maximum number of days in the month
	private int maxDays;
	
	//this is a list of days in the month
	private ArrayList<Day> days = new ArrayList<Day>();
	private ArrayList<Day> availableDays = new ArrayList<Day>();
	private ArrayList<Auction> auctionsInMonth = new ArrayList<Auction>();
	
	/**
	 * This is a constructor method for month.
	 * @param monthNumber, the actual month number of the month.
	 * @param maxDays, maximum number of days in a month.
	 */
	public Month(int maxDays) {
		this.maxDays = maxDays;
		createDays();
	}
	/**
	 * This is a getter for the day.
	 * @param day to find the day by.
	 * @return the Day Object.
	 */
	public Day getDay(int day) {
		return days.get(day);
	}
	
	/**
	 * This method creates all(maximum) days for the month.
	 */
	protected void createDays() {
		for(int i = 1; i <= maxDays+1; i++) {
			days.add(new Day(new ArrayList<Auction>()));			
		}
	}
	/**
	 * This method returns a list of auctions in a month.
	 * @return list of auctions in the whole month, in all days of the month.
	 */
	public List<Auction> listOfAuctions() {
		for(int i = 0; i < days.size(); i++)
			if(days.get(i).getNumberOfAuctions() > 0)
				for(int j = 0; j < days.get(i).getNumberOfAuctions(); j++)
					auctionsInMonth.add(days.get(i).getTodaysAuctions().get(j));
		return auctionsInMonth;
	}
	/**
	 * This is getter for the maximum days in the current month.
	 * @return maximum amount of days in the month.
	 */
	public int getMaxDays() {
		return maxDays;
	}
	/**
	 * This method finds all days in the month that are available for scheduling an auction.
	 * @return a list of Day objects that are available for cheduling(have no or only 1 auction in it).
	 */
	public List<Day> availableDaysList() {
		for(int i = 1; i < days.size(); i++) {
			if(days.get(i).getNumberOfAuctions() < 2) availableDays.add(days.get(i));
		}
		return availableDays;
	}
}