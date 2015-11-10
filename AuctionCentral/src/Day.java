
import java.util.List;

/**
 * This is a class for days.
 * @author Artsiom Vainilovich
 * @version 1.0
 */
public class Day {
	//this is a day number of the month
	private int day;
	
	//this is a list of auctions today.
	private List<String> todaysAuctions;
	
	//this is a start time of the auction
	private int startTime;
	
	//this is a end time of the last auction
	private int endTime;
	
	/**
	 * This is a constructor method for the Day.
	 * @param day number of the month
	 * @param todaysAuctions auctions held today
	 * @param startTime when auction begins
	 * @param endTime when auction ends
	 */
	public Day(int day, List<String> todaysAuctions) {
		this.day = day;
		this.todaysAuctions = todaysAuctions;
	}
	
	/**
	 * This is a method that decides if we do not have space in the day.
	 * @return true or false, available or not available
	 */
	public boolean getAvailability() {
		//TODO needs further development
		//if there is at least one auction today already
		if(!todaysAuctions.isEmpty()) {
			//if there is more than one auction today
			if(todaysAuctions.size() > 1) {
				return false;
			}
			return true;
		}
		return true;
	}
	
	public boolean isEmpty() {
		if(todaysAuctions.isEmpty()) return true;
		else return false;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public int getEndTime() {
		return endTime;
	}
	
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public void setTodaysAuctions(String auction) {
		todaysAuctions.add(auction);
	}
	
	public String toString() {
		String info = new String();
		for (int i = 0; i < todaysAuctions.size(); i++) {
			info = info + todaysAuctions.get(i) + "\n";
		}
		return info;
	}
}