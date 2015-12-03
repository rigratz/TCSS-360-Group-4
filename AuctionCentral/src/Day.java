import java.util.List;

/**
 * This is a private Day class.
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
	 * @param todaysAuctions, a list of auctions today.
	 */
	public Day(List<Auction> todaysAuctions) {
		this.todaysAuctions = todaysAuctions;
	}
	/**
	 * This is getter for number of auctions today.
	 * @return the current amount of auctions today.
	 */
	public int getNumberOfAuctions() {
		return todaysAuctions.size();
	}
	/**
	 * This is getter for start time of the auction today.
	 * @return start time of the auction.
	 */
	public int getStartTime() {
		return startTime;
	}
	/**
	  * This is getter for end time of the auction today.
	 * @return end time of the auction.
	 */
	public int getEndTime() {
		return endTime;
	}
	/**
	 * This method sets the start time of the auction.
	 * @param startTime of the auction to set.
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	/**
	 * This method sets the end time of the auction.
	 * @param endTime of the auction to set.
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	/**
	 * This method sets auction for this day.
	 * @param auction to set at this day.
	 */
	public void setTodaysAuctions(Auction auction) {
		todaysAuctions.add(auction);
	}
	/**
	 * This is getter for auctions today.
	 * @return list of auctions held today.
	 */
	public List<Auction> getTodaysAuctions() {
		return todaysAuctions;
	}
}