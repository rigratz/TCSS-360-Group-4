import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Artiom
 *
 */
public class TestCalendarClass {
	ArrayList<Auction> list;
	CalendarClass cal, fullCal, badCalendar, badCalendar2, emptyCalendar;
	Auction auction, auction2, auction3, auction4, auction5,auction6;
	Auction auction7, auction8, auction9, auction10, auction11, auction12;
	Auction auction13, auction14, auction15, auction16, auction17, auction18, auction19;
	Auction auction20, lateAuction,lateAuction2 , earlyAuction, earlyAuction2;
	Auction auction21, auction22, auction23, auction24, auction25, auction26, auction27;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new ArrayList<Auction>();
		cal = new CalendarClass();
		emptyCalendar = new CalendarClass();
		auction = new Auction("Good-Will", 12, 1, 2015, 8, 10);
		auction2= new Auction("E-bay", 12, 4, 2015, 23, 24);
		
		auction3 = new Auction("Good", 12, 4, 2015, 17, 18);
		cal.addAuction(auction2);
		cal.addAuction(auction3);
		auction4= new Auction("E-y", 12, 3, 2015, 8, 14);
		
		auction5 = new Auction("GWill", 12, 2, 2015, 11, 12);
		auction6= new Auction("Tby", 11, 30, 2015, 8, 10);
		
		auction7 = new Auction("Gosl", 12, 5, 2015, 9, 12);
		auction8= new Auction("By", 12, 6, 2015, 14, 18);
		auction9= new Auction("Bys", 12, 7, 2015, 14, 18);
		
		//fake once
		auction10 = new Auction("Good-Will", 12, 1, 2015, 8, 10);
		auction11= new Auction("E-bay", 12, 4, 2015, 23, 24);
		
		auction12 = new Auction("Good", 12, 4, 2015, 17, 18);
		auction13 = new Auction("E-y", 12, 3, 2015, 8, 14);
		
		auction14 = new Auction("GWill", 12, 2, 2015, 11, 12);
		auction15 = new Auction("Tby", 11, 30, 2015, 8, 10);
		
		auction16 = new Auction("Gosl", 12, 5, 2015, 9, 12);
		auction17 = new Auction("By", 12, 6, 2015, 14, 18);
		auction18 = new Auction("Bys", 12, 7, 2015, 14, 18);
		
		auction19 = new Auction("Good-Will", 12, 1, 2015, 8, 10);
		auction20 = new Auction("E-bay", 12, 4, 2015, 23, 24);
		
		auction21 = new Auction("Good", 12, 4, 2015, 17, 18);
		auction22 = new Auction("E-y", 12, 3, 2015, 8, 14);
		
		auction23 = new Auction("GWill", 12, 2, 2015, 11, 12);
		auction24 = new Auction("Tby", 11, 30, 2015, 8, 10);
		
		auction25 = new Auction("Gosl", 12, 5, 2015, 9, 12);
		auction26 = new Auction("By", 12, 6, 2015, 14, 18);
		auction27 = new Auction("Bys", 12, 7, 2015, 14, 18);
		
		badCalendar = new CalendarClass();
		lateAuction = new Auction("Bad", 11, 15, 2015, 23, 24);
		earlyAuction = new Auction("Bad2", 11, 16, 2015, 1, 4);
		
		lateAuction2 = new Auction("Bad3", 11, 15, 2015, 22, 23);
		earlyAuction2 = new Auction("Bad4", 11, 16, 2015, 0, 4);
		badCalendar.addAuction(lateAuction);
		
		badCalendar2 = new CalendarClass();
		badCalendar2.addAuction(lateAuction2);
		
		badCalendar.addAuction(auction7);
		
		fullCal = new CalendarClass();
		fullCal.addAuction(auction);
		fullCal.addAuction(auction2);
		fullCal.addAuction(auction3);
		fullCal.addAuction(auction4);
		fullCal.addAuction(auction5);
		fullCal.addAuction(auction6);
		fullCal.addAuction(auction7);
		fullCal.addAuction(auction8);
		fullCal.addAuction(auction9);
		fullCal.addAuction(auction10);
		fullCal.addAuction(auction11);
		fullCal.addAuction(auction12);
		fullCal.addAuction(auction13);
		fullCal.addAuction(auction14);
		fullCal.addAuction(auction15);
		fullCal.addAuction(auction16);
		fullCal.addAuction(auction17);
		fullCal.addAuction(auction18);
		fullCal.addAuction(auction19);
		fullCal.addAuction(auction20);
		fullCal.addAuction(auction21);
		fullCal.addAuction(auction22);
		fullCal.addAuction(auction23);
		fullCal.addAuction(auction24);
		fullCal.addAuction(auction25);
		fullCal.addAuction(auction26);
		fullCal.addAuction(auction27);
		
		list.add(auction);
	}

	@Test
	public void testAddAuction() {
		cal.addAuction(auction);
	}
	
	@Test
	public void testCalculateOffset() {
		cal.calculateOffset(11);
		cal.calculateOffset(8);
	}
	@Test
	public void testMaximumMonth() {
		cal.maximumMonth(11, 12);
		cal.maximumMonth(8, 7);
	}
	@Test
	public void testMaximum() {
		cal.maximum(2, 3);
		cal.maximum(3, 2);
	}
	@Test
	public void testMinimum() {
		cal.minimum(11, 12);
		cal.minimum(10, 8);
	}

	@Test
	public void testIfDayAvailable() {
		cal.isAvailable(auction.getMyMonth(), auction.getMyDay(), 
				auction.getMyStartTime(), auction.getMyEndTime());
		cal.isAvailable(auction6.getMyMonth(), auction6.getMyDay(), 
				auction6.getMyStartTime(), auction6.getMyEndTime());
		fullCal.isAvailable(auction6.getMyMonth(), auction6.getMyDay(), 
				auction6.getMyStartTime(), auction6.getMyEndTime());
		
		badCalendar.isAvailable(earlyAuction.getMyMonth(), earlyAuction.getMyDay(),
				earlyAuction.getMyStartTime(), earlyAuction.getMyEndTime());
		
		badCalendar.isAvailable(earlyAuction.getMyMonth(), earlyAuction.getMyDay(),
				0, earlyAuction.getMyEndTime());
		
		badCalendar.isAvailable(earlyAuction.getMyMonth(), earlyAuction.getMyDay(),
				1, earlyAuction.getMyEndTime());
		
		badCalendar.isAvailable(earlyAuction.getMyMonth(), earlyAuction.getMyDay(),
				2, earlyAuction.getMyEndTime());
		
		badCalendar2.isAvailable(earlyAuction2.getMyMonth(), earlyAuction2.getMyDay(),
				0, earlyAuction2.getMyEndTime());
		
		badCalendar2.isAvailable(earlyAuction2.getMyMonth(), earlyAuction2.getMyDay(),
				1, earlyAuction2.getMyEndTime());
		
		badCalendar2.isAvailable(earlyAuction2.getMyMonth(), earlyAuction2.getMyDay(),
				2, earlyAuction2.getMyEndTime());
		
		cal.isAvailable(12, 4, 4, 5);
		cal.isAvailable(12, 3, 4, 5);
	}

	@Test
	public void testIfTimeAvailable() {
		fullCal.isAvailable(12, 4, 8, 10);
		fullCal.isAvailable(12, 3, 15, 20);
		fullCal.isAvailable(12, 3, 17, 20);
		
		fullCal.isAvailable(12, 3, 5, 6);
		fullCal.isAvailable(12, 3, 5, 7);
	}
	
	@Test
	public void testBelowWeekLimit() {
		fullCal.belowWeekLimit(12, 4);
		fullCal.belowWeekLimit(11, 20);
	}
	
	@Test
	public void testBelowMaxAuctions() {
		fullCal.belowMaxAuctions();
		assertFalse(fullCal.belowMaxAuctions());
		assertTrue(cal.belowMaxAuctions());
	}
	
	@Test
	public void testBelowMaxDayToFuture() {
		cal.belowMaxDaysToFuture(5, 20);
		cal.belowMaxDaysToFuture(5, 1);
		cal.belowMaxDaysToFuture(12, 1);
	}
	
	@Test
	public void testGetAllAuctions() {
		fullCal.getAllAuctions();
//		cal.getAllAuctions();
//		emptyCalendar.getAllAuctions();
	}
	
	@Test
	public void testListOfAuctions() {
		fullCal.getListOfAuctions();
	}
	
	@Test
	public void testGetAuction() {
		fullCal.getAuction("NotGood");
		fullCal.getAuction(auction.getMyName());
	}
	
	@Test
	public void testGetListOfDays() {
		fullCal.getListOfDays(12);
	}
	
	@Test
	public void testGetMonthAndGetDay() {
		fullCal.getMonth(12);
		fullCal.getDay(12, 4);
	}
	
	@Test
	public void testInsertAuction() {
		fullCal.insertAuctions(list);
		Auction auct = new Auction("GdsWill", 11, 2, 2015, 11, 18);
		Auction auct2 = new Auction("GdsWill", 10, 2, 2015, 11, 12);
		Auction auct3 = new Auction("GdssdfWill", 11, 20, 2015, 20, 22);
		Auction auct4 = new Auction("GdasfsgsWill", 3, 2, 2015, 11, 12);
		list.add(auct);
		list.add(auct2);
		list.add(auct3);
		list.add(auct4);
		fullCal.insertAuctions(list);
		emptyCalendar.insertAuctions(list);
	}
	
	@Test
	public void testViewAuctions() {
		fullCal.viewAuction("Good");
		fullCal.viewAuction("NotGood");
		assertFalse(fullCal.viewAuction("Good").equals(""));
		assertTrue(fullCal.viewAuction("NotNot").equals("Nothing was found."));
	}
	
	@Test
	public void testCheckOrganization() {
		fullCal.checkOrganization("Good");
		fullCal.checkOrganization("NotGood");
	}
}
