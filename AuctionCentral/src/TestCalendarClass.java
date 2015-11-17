import static org.junit.Assert.*;

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
	CalendarClass cal, fullCal, badCalendar, badCalendar2, emptyCalendar, emptyCal2, calPast;
	Auction auction, auction2, auction3, auction4, auction5,auction6;
	Auction auction7, auction8, auction9, auction10, auction11, auction12;
	Auction auction13, auction14, auction15, auction16, auction17, auction18, auction19;
	Auction auction20, lateAuction,lateAuction2 , earlyAuction, earlyAuction2;
	Auction auction21, auction22, auction23, auction24, auction25, auction26, auction27,
			auction28, auction29, auction30;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new ArrayList<Auction>();
		cal = new CalendarClass();
		emptyCalendar = new CalendarClass();
		emptyCal2 = new CalendarClass();
		calPast = new CalendarClass();
		auction = new Auction("Good-Will", 12, 1, 2015, 8, 10);
		emptyCal2.addAuction(auction);
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
		
		auction21 = new Auction("Good", 12, 4, 2015, 17, 23);
		auction22 = new Auction("E-y", 12, 3, 2015, 8, 14);
		
		auction23 = new Auction("GWill", 12, 2, 2015, 11, 12);
		auction24 = new Auction("Tby", 11, 30, 2015, 8, 10);
		
		auction25 = new Auction("Gosl", 12, 5, 2015, 9, 12);
		auction26 = new Auction("By", 12, 6, 2015, 14, 18);
		auction27 = new Auction("Bys", 12, 7, 2015, 14, 18);
		
		auction28 = new Auction("Hyt", 1, 4, 2015, 10, 18);
		auction29 = new Auction("Jui", 1, 4, 2015, 20, 22);
		
		auction30 = new Auction("Juli", 10, 8, 2015, 12, 18);
		
		badCalendar = new CalendarClass();
		lateAuction = new Auction("Bad", 11, 15, 2015, 23, 24);
		earlyAuction = new Auction("Bad2", 11, 16, 2015, 1, 4);
		
		lateAuction2 = new Auction("Bad3", 11, 15, 2015, 22, 23);
		earlyAuction2 = new Auction("Bad4", 11, 16, 2015, 0, 4);
		badCalendar.addAuction(lateAuction);
		
		badCalendar2 = new CalendarClass();
		badCalendar2.addAuction(lateAuction2);
		
		badCalendar.addAuction(auction7);
//		
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
		fullCal.addAuction(auction28);
		fullCal.addAuction(auction29);
		
		list.add(auction);
	}

	@Test
	public void testAddAuction() {
		//this is a setter
		cal.addAuction(auction);
	}
	
	@Test
	public void testCalculateOffset() {
		assertEquals(cal.calculateOffset(11), 0);
		assertEquals(cal.calculateOffset(12), 1);
		assertEquals(cal.calculateOffset(1), 2);
		assertEquals(cal.calculateOffset(2), 3);
		assertEquals(cal.calculateOffset(3), 4);
		assertEquals(cal.calculateOffset(10), 11);
	}
	@Test
	public void testMaximumMonth() {
		assertEquals(cal.maximumMonth(11, 12), 12);
		assertEquals(cal.maximumMonth(11, 7), 11);
	}
	@Test
	public void testMaximum() {
		assertEquals(cal.maximum(2, 3), 3);
		assertEquals(cal.maximum(3, 2), 3);
	}
	@Test
	public void testMinimum() {
		assertEquals(cal.minimum(11, 12), 11);
		assertEquals(cal.minimum(10, 8), 8);
	}

	@Test
	public void testIfDayAvailable() {
		assertFalse(cal.isAvailable(10, 5, 5, 15));
		assertTrue(cal.isAvailable(11, 5, 5, 15));
		assertTrue(cal.isAvailable(12, 5, 5, 15));
		assertTrue(cal.isAvailable(1, 5, 5, 15));
		assertTrue(cal.isAvailable(2, 5, 5, 15));
		assertFalse(cal.isAvailable(3, 5, 5, 15));
		
		assertTrue(fullCal.isAvailable(12, 5, 2, 5));
		assertTrue(fullCal.isAvailable(12, 5, 1, 5));
		assertTrue(cal.isAvailable(auction.getMyMonth(), auction.getMyDay(), 
				auction.getMyStartTime(), auction.getMyEndTime()));
		assertTrue(cal.isAvailable(auction6.getMyMonth(), auction6.getMyDay(), 
				auction6.getMyStartTime(), auction6.getMyEndTime()));
		assertTrue(fullCal.isAvailable(auction6.getMyMonth(), auction6.getMyDay(), 
				auction6.getMyStartTime(), auction6.getMyEndTime()));
		
		assertFalse(badCalendar.isAvailable(earlyAuction.getMyMonth(), earlyAuction.getMyDay(),
				earlyAuction.getMyStartTime(), earlyAuction.getMyEndTime()));
		
		assertFalse(badCalendar.isAvailable(earlyAuction.getMyMonth(), earlyAuction.getMyDay(),
				0, earlyAuction.getMyEndTime()));
		
		assertFalse(badCalendar.isAvailable(earlyAuction.getMyMonth(), earlyAuction.getMyDay(),
				1, earlyAuction.getMyEndTime()));
		
		assertTrue(badCalendar.isAvailable(earlyAuction.getMyMonth(), earlyAuction.getMyDay(),
				2, earlyAuction.getMyEndTime()));
		
		assertFalse(badCalendar2.isAvailable(earlyAuction2.getMyMonth(), earlyAuction2.getMyDay(),
				0, earlyAuction2.getMyEndTime()));
		
		assertTrue(badCalendar2.isAvailable(earlyAuction2.getMyMonth(), earlyAuction2.getMyDay(),
				1, earlyAuction2.getMyEndTime()));
		
		assertTrue(badCalendar2.isAvailable(earlyAuction2.getMyMonth(), earlyAuction2.getMyDay(),
				2, earlyAuction2.getMyEndTime()));
		
		assertTrue(cal.isAvailable(12, 3, 4, 5));
		
		assertTrue(fullCal.isAvailable(12, 3, 8, 14));
		
		fullCal.getListOfPastAuctions();
		
	}

	@Test
	public void testIfTimeAvailable() {
		assertTrue(fullCal.isAvailable(12, 4, 8, 10));
		assertFalse(fullCal.isAvailable(12, 3, 15, 20));
		assertTrue(fullCal.isAvailable(12, 3, 17, 20));
		
		assertTrue(fullCal.isAvailable(12, 3, 5, 6));
		assertFalse(fullCal.isAvailable(12, 3, 5, 7));
		
		assertFalse(fullCal.isAvailable(1, 4, 12, 16));
	}
	
	@Test
	public void testBelowWeekLimit() {
		assertTrue(emptyCalendar.belowWeekLimit(12, 4));
		assertFalse(fullCal.belowWeekLimit(12, 4));
	}
	
	@Test
	public void testBelowMaxAuctions() {
		assertFalse(fullCal.belowMaxAuctions());
		assertTrue(cal.belowMaxAuctions());
	}
	
	@Test
	public void testBelowMaxDayToFuture() {
		assertFalse(cal.belowMaxDaysToFuture(5, 20));
		assertFalse(cal.belowMaxDaysToFuture(5, 1));
		assertTrue(cal.belowMaxDaysToFuture(12, 1));
		assertFalse(cal.belowMaxDaysToFuture(2, 28));
		assertTrue(cal.belowMaxDaysToFuture(2, 1));
		assertFalse(cal.belowMaxDaysToFuture(1111, 1));
	}
	
	@Test
	public void testGetAllAuctions() {
		//this is getter
		assertEquals(emptyCal2.getAllAuctions(), auction.toString()+"\n");

		//this is getter too
		assertEquals(emptyCal2.getPastAuctions(), "Past Auctions:\n");
	}
	@Test
	public void testListOfAuctions() {
		fullCal.getListOfAuctions();
	}
	
	@Test
	public void testGetAuction() {
		assertEquals(fullCal.getAuction("NotGood"), null);
		assertEquals(fullCal.getAuction(auction.getMyName()), auction);
	}
	
	@Test
	public void testGetListOfDays() {
		//this is getter
		fullCal.getListOfDays(12);
	}
	
	@Test
	public void testGetMonthAndGetDay() {
		//this is getter
		fullCal.getMonth(12);
		fullCal.getDay(12, 4);
	}
	
	@Test
	public void testInsertAuction() {
		//this is just a setter
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
		assertFalse(fullCal.viewAuction("Good").equals(""));
		assertTrue(fullCal.viewAuction("NotNot").equals("Nothing was found."));
	}
	
	@Test
	public void testCheckOrganization() {
		assertFalse(fullCal.checkOrganization("Good-Will"));
		assertTrue(fullCal.checkOrganization("NotGood"));
	}
}
