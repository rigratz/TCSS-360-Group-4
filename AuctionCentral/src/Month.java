


import java.util.ArrayList;

/**
 * This is a private class for the month.
 * @author Artsiom Vainilovich
 * @version 2.0
 */
public class Month {
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
	public Day getDay(int day) {
		return days.get(day);
	}
	
	/**
	 * This method creates all days for the month.
	 */
	private void createDays() {
		for(int i = 1; i <= maxDays+1; i++) {
			days.add(new Day(i, new ArrayList<String>()));			
		}
	}
	
	public String toStringAuctions() {
		String monthInfo = new String();
		for(int i = 0; i < days.size(); i++) {
			monthInfo += days.get(i);
		}
		return monthInfo;
	}
	
	public String toStringAvailableDays() {
		String info = new String();
		for(int i = 1; i < days.size(); i++) {
			if(days.get(i).getNumberOfAuctions() == 0) {
				info += i + ": " + days.get(i) + " [+]" + "\n";
			} else if(days.get(i).getNumberOfAuctions() == 1) {
				info += i + ":  [+] " + days.get(i);
			}
		}
		return info;
	}
}