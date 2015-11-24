import java.util.ArrayList;
import java.util.Calendar;

/**
 * This is a board private class that keeps 4 months on it
 * @author Artsiom Vainilovich
 * @version 2.0
 */
public class CalendarBoard {
	//this is a list of months.
	private ArrayList<Month> listOfMonths = new ArrayList<Month>();
	
	/**
	 * This is a constructor for board.
	 */
	public CalendarBoard() {
		//this is a template calendar to get rid of at the end
		Calendar templateCalendar = Calendar.getInstance();
		//creates four months with a correct number of days.
		for (int i = 0; i < 4; i++) {
			//add month to the list
			listOfMonths.add(new Month(templateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)));
			//increment month
			templateCalendar.add(Calendar.MONTH, 1);
		}
	}
	
	public Month getMonth(int monthIndex) {
		return listOfMonths.get(monthIndex);
	}
}