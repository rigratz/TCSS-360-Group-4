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
	 * @param monthNumber month number on the calendar
	 * @param maxDays maximum number of days in month
	 */
	public Month(int maxDays) {
		this.maxDays = maxDays;
		createDays();
	}
	public Day getDay(int day) {
		return days.get(day);
	}
	
	/**
	 * This method creates all days for the month.
	 */
	private void createDays() {
		for(int i = 1; i <= maxDays+1; i++) {
			days.add(new Day(new ArrayList<Auction>()));			
		}
	}
	
//	public String toStringAuctions() {
//		String monthInfo = new String();
//		for(int i = 0; i < days.size(); i++) {
//			monthInfo += days.get(i);
//		}
//		return monthInfo;
//	}
	public List<Auction> listOfAuctions() {
		for(int i = 0; i < days.size(); i++) {
			if(days.get(i).getNumberOfAuctions() > 0) {
				for(int j = 0; j < days.get(i).getNumberOfAuctions(); j++) {
					auctionsInMonth.add(days.get(i).getTodaysAuctions().get(j));
				}				
			}
		}
		return auctionsInMonth;
	}
	
	public int getMaxDays() {
		return maxDays;
	}
	
	public List<Day> availableDaysList() {
		for(int i = 1; i < days.size(); i++) {
			if(days.get(i).getNumberOfAuctions() > 1) {
				//Do nothing
			} else {
				availableDays.add(days.get(i));
			}
		}
		return availableDays;
	}
}