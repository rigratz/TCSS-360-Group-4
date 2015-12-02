import java.util.List;

/**
 * This is a class for days.
 * @author Artsiom Vainilovich
 * @version 2.0
 */
public class Day {
	//this is a list of auctions today.
	private List<Auction> todaysAuctions;
	
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
	public Day(List<Auction> todaysAuctions) {
		this.todaysAuctions = todaysAuctions;
	}
	
	public int getNumberOfAuctions() {
		return todaysAuctions.size();
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
	
	public void setTodaysAuctions(Auction auction) {
		todaysAuctions.add(auction);
	}
	
	public List<Auction> getTodaysAuctions() {
		return todaysAuctions;
	}
}