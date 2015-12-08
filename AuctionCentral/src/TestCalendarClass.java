import static org.junit.Assert.assertEquals;
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
	CalendarClass cal, fullCal;
	Auction auction;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new ArrayList<Auction>();
		cal = new CalendarClass();
		fullCal = new CalendarClass();
		auction = new Auction("Good-Will", 12, 1, 2015, 8, 10);
		
		fullCal.addAuction(new Auction("Ebay", 12, 20, 2015, 9, 12));
		fullCal.addAuction(new Auction("E-bay", 12, 21, 2015, 9, 12));
		fullCal.addAuction(new Auction("Eb-ay", 12, 22, 2015, 9, 12));
		fullCal.addAuction(new Auction("Eba-y", 12, 23, 2015, 9, 12));
		fullCal.addAuction(new Auction("Ebay-", 12, 24, 2015, 9, 12));
		fullCal.addAuction(new Auction("E-bay-", 12, 25, 2015, 9, 12));
		fullCal.addAuction(new Auction("E-bay-", 12, 26, 2015, 9, 12));
		fullCal.addAuction(new Auction("Eb-ay-", 12, 27, 2015, 9, 12));
		fullCal.addAuction(new Auction("Eba-y-", 12, 28, 2015, 9, 12));
		fullCal.addAuction(new Auction("Ebay--", 12, 29, 2015, 9, 12));
		fullCal.addAuction(new Auction("Eb-ay--", 1, 1, 2016, 9, 12));
		fullCal.addAuction(new Auction("E-bay--", 1, 2, 2016, 9, 12));
		fullCal.addAuction(new Auction("Eb-ay--", 1, 3, 2016, 9, 12));
		fullCal.addAuction(new Auction("Eba-y--", 1, 4, 2016, 9, 12));
		fullCal.addAuction(new Auction("Ebay---", 1, 5, 2016, 9, 12));
		fullCal.addAuction(new Auction("Eba-y---", 1, 6, 2016, 9, 12));
		fullCal.addAuction(new Auction("E-bay---", 1, 7, 2016, 9, 12));
		fullCal.addAuction(new Auction("Eb-ay---", 1, 8, 2016, 9, 12));
		fullCal.addAuction(new Auction("Eba-y---", 1, 9, 2016, 9, 12));
		fullCal.addAuction(new Auction("Ebay----", 1, 10, 2016, 9, 12));
		fullCal.addAuction(new Auction("Ebay----", 1, 11, 2016, 9, 12));
		fullCal.addAuction(new Auction("E-bay----", 1, 12, 2016, 9, 12));
		fullCal.addAuction(new Auction("Eb-ay----", 1, 13, 2016, 9, 12));
		fullCal.addAuction(new Auction("Eba-y----", 1, 14, 2016, 9, 12));
		fullCal.addAuction(new Auction("Ebay-----", 1, 15, 2016, 9, 12));
		fullCal.addAuction(new Auction("Ebay-----", 1, 16, 2016, 9, 12));
	}

	@Test
	public void testAddAuction() {
		cal.addAuction(auction);
		list.add(auction);
		assertEquals(cal.getListOfAuctions(), list);
	}
	
	@Test
	public void testCalculateOffsetIfItsCurrentMonth() {
		assertEquals(cal.calculateOffset(12), 0);
	}
	
	@Test
	public void testCalculateOffsetIfItsNextMonth() {
		assertEquals(cal.calculateOffset(13), 1);
	}
	
	@Test
	public void testCalculateOffsetIfItspastMonth() {
		assertEquals(cal.calculateOffset(9), 9-1+12-11);
	}
	
	@Test
	public void testMaximumMonthIfFirstMonthIsSmaller() {
		assertEquals(cal.maximumMonth(12, 13), 13);
	}
	
	@Test
	public void testMaximumMonthIfFirstMonthIsBigger() {
		assertEquals(cal.maximumMonth(12, 10), 12); 
	}
	
	@Test
	public void testMaximumIfSecondNumberIsBigger() {
		assertEquals(cal.maximum(2, 3), 3);
	}
	
	@Test
	public void testMaximumIfSecondNumberIsSmaller() {
		assertEquals(cal.maximum(3, 2), 3);
	}
	
	@Test
	public void testMinimumIfSecondMonthIsBigger() {
		assertEquals(cal.minimum(11, 12), 11);
	}

	@Test
	public void testMinimumIfSecondMonthIsSmaller() {
		assertEquals(cal.minimum(10, 8), 8);
	}
	@Test
	public void testIsAvailableIfOffsetIsLessThanZero() {
		assertFalse(cal.isAvailable(-1, 15, 5, 8));
	}
	@Test
	public void testIsAvailableIfOffsetIsGreaterThanThree() {
		assertFalse(cal.isAvailable(4, 15, 5, 8));
	}
	@Test
	public void testIsAvailableIfOffsetIsGreaterThanZeroAndSmallerThanFour() {
		assertTrue(cal.isAvailable(12, 15, 5, 8));
	}
	@Test
	public void testIsAvailableIfDayHasMoreThanOneAuction() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 10));
		cal.addAuction(new Auction("E-bay", 12, 25, 2015, 15, 16));
		assertFalse(cal.isAvailable(12, 25, 9, 10));
	}
	@Test
	public void testIsAvailableIfDayHasLessThanOneAuction() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 10));
		assertFalse(cal.isAvailable(12, 25, 9, 10));
	}
	@Test
	public void testIsAvailableIfAuctionEndsAtElevenPMAndStartsEarlierThanOneAMNextDay() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 23));
		assertFalse(cal.isAvailable(12, 26, 0, 10));
	}
	@Test
	public void testIsAvailableIfAuctionEndsAtElevenPMAndStartsLaterThanOneAMNextDay() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 23));
		assertTrue(cal.isAvailable(12, 26, 2, 10));
	}
	@Test
	public void testIsAvailableIfAuctionEndsAtTwelvePMAndStartsEarlierThanTwoAMNextDay() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 24));
		assertFalse(cal.isAvailable(12, 26, 0, 10));
	}
	@Test
	public void testIsAvailableIfAuctionEndsAtTwelvePMAndStartsLaterThanTwoAMNextDay() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 24));
		assertTrue(cal.isAvailable(12, 26, 3, 10));
	}
	@Test
	public void testIsAvailableIfStartTimeIsLessThanTwoHoursToTheStartTimeAndEndTimeOfExistingAuction() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 12));
		assertFalse(cal.isAvailable(12, 25, 8, 13));
	}
	@Test
	public void testIsAvailableIfStartTimeIsLessThanTwoHoursToTheEndTimeOfExistingAuction() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 12));
		assertFalse(cal.isAvailable(12, 25, 13, 14));
	}
	@Test
	public void testIsAvailableIfStartTimeIsGreaterThanTwoHoursToTheEndTimeOfExistingAuction() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 12));
		assertTrue(cal.isAvailable(12, 25, 14, 15));
	}
	@Test
	public void testIsAvailableIfStartTimeIsGreaterThanTwoHoursToTheStartTimeAndEndTimeOfExistingAuction() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 12));
		assertTrue(cal.isAvailable(12, 25, 3, 4));
	}
	@Test
	public void testIsAvailableIfEndTimeIsLessThanTwoHoursToTheStartTimeOfExistingAuction() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 12));
		assertFalse(cal.isAvailable(12, 25, 3, 8));
	}
	@Test
	public void testIsAvailableIfEndTimeIsGreaterThanTwoHoursToTheStartTimeOfExistingAuction() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 12));
		assertTrue(cal.isAvailable(12, 25, 3, 7));
	}
	@Test
	public void testIsAvailableIfEndTimeIsLessThanTwoHoursToTheEndTimeOfExistingAuction() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 12));
		assertFalse(cal.isAvailable(12, 25, 13, 16));
	}
	@Test
	public void testIsAvailableIfEndTimeIsGreaterThanTwoHoursToTheEndTimeOfExistingAuction() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 12));
		assertTrue(cal.isAvailable(12, 25, 14, 16));
	}
	@Test
	public void testIsAvailableIfStartTimeIsLessThanTwoHoursToTheStartTimeAndLessThanFromTheEndTimeOfExistingAuction() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 12));
		assertFalse(cal.isAvailable(12, 25, 7, 14));
	}
	
	@Test
	public void testBelowWeekLimitIfOffsetIsLessThanZero() {
		assertFalse(cal.belowWeekLimit(4, 15));
	}
	@Test
	public void testBelowWeekLimitIfMonthIsNegative() {
		assertFalse(cal.belowWeekLimit(-2, 15));
	}
	@Test
	public void testBelowWeekLimitIfOffsetIsGreaterThanZeroAndSmallerThanFour() {
		assertTrue(cal.belowWeekLimit(12, 15));
	}
	@Test
	public void testBelowWeekLimitIfThereIsMoreThanFourAucitons() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 12));
		cal.addAuction(new Auction("E-bay", 12, 26, 2015, 9, 12));
		cal.addAuction(new Auction("Eb-ay", 12, 27, 2015, 9, 12));
		cal.addAuction(new Auction("Eba-y", 12, 28, 2015, 9, 12));
		cal.addAuction(new Auction("Ebay-", 12, 29, 2015, 9, 12));
		assertFalse(cal.belowWeekLimit(12, 27));
	}
	@Test
	public void testBelowWeekLimitIfThereIsLessThanFourAucitons() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 12));
		assertTrue(cal.belowWeekLimit(12, 27));
	}
	
	@Test
	public void testBelowMaxAuctionsIfMoreThanMaximumAmountOfAuctions() {
		assertFalse(fullCal.belowMaxAuctions());
	}
	@Test
	public void testBelowMaxAuctionsIfLessThanMaximumAmountOfAuctions() {
		cal.addAuction(new Auction("Ebay", 12, 25, 2015, 9, 12));
		cal.addAuction(new Auction("E-bay", 12, 26, 2015, 9, 12));
		assertTrue(cal.belowMaxAuctions());
	}
	
	@Test
	public void testBelowMaxDaysToFutureIfYearIsTooFarAndItsMoreThanTwelveMonthToTheFuture() {
		assertFalse(cal.belowMaxDaysToFuture(12, 25, 2016));
	}
	@Test
	public void testBelowMaxDaysToFutureIfYearIsTooFarButItsLessThanTwelveMonthToTheFuture() {
		assertFalse(cal.belowMaxDaysToFuture(10, 25, 2016));
	}
	@Test
	public void testBelowMaxDaysToFutureIfYearIsTooFarButANdMonthIsNegative() {
		assertFalse(cal.belowMaxDaysToFuture(-1, 25, 2017));
	}
	@Test
	public void testBelowMaxDaysToFutureIfItsCurrentYearAndItsLessThanTwelveMonthToTheFuture() {
		assertTrue(cal.belowMaxDaysToFuture(12, 25, 2015));
	}
	@Test
	public void testBelowMaxDaysToFutureIfDateIsGreaterThanInCurrentMonth() {
		assertFalse(cal.belowMaxDaysToFuture(3, 30, 2016));
	}
	@Test
	public void testBelowMaxDaysToFutureIfDateIsLessThanInCurrentMonth() {
		assertTrue(cal.belowMaxDaysToFuture(3, 1, 2016));
	}
	
	@Test
	public void testGetListOfPastAuctions() {
		Auction auct = new Auction("E-bay", 11, 26, 2015, 9, 12);
		list.add(auct);
		cal.insertAuctions(list);
		assertEquals(cal.getListOfPastAuctions(), list);
	}
	
	@Test
	public void testGetListOfDaysGetter() {
		//this doesn't need to be tested
		cal.getListOfDays(12);
	}
	@Test
	public void testGetMonthGetter() {
		//this doesn't need to be tested 
		cal.getMonth(12);
	}
	@Test
	public void testGetDayGetter() {
		//this doesn't need to be tested 
		cal.getDay(12, 25);
	}
	
	@Test
	public void testInsertAuctionsIfMonthIsNegativeShouldBeInPastAucitons() {
		Auction auc = new Auction("Ebay", -1, 25, 2015, 9, 12);
		list.add(auc);
		cal.insertAuctions(list);
		assertEquals(cal.getListOfPastAuctions(), list);
	}
	@Test
	public void testInsertAuctionsIfMonthIsInTheAvailableRange() {
		Auction auc = new Auction("Ebay", 12, 25, 2015, 9, 12);
		list.add(auc);
		cal.insertAuctions(list);
		assertEquals(cal.getListOfAuctions(), list);
	}
	@Test
	public void testInsertAuctionsIfMonthIsNotACurrentMonth() {
		Auction auc = new Auction("Ebay", 1, 25, 2015, 9, 12);
		list.add(auc);
		cal.insertAuctions(list);
		list.remove(auc);
		assertEquals(cal.getListOfAuctions(), list);
	}
	@Test
	public void testInsertAuctionsIfDayIsHigherThanCurrentDay() {
		Auction auc = new Auction("Ebay", 12, 28, 2015, 9, 12);
		list.add(auc);
		cal.insertAuctions(list);
		assertEquals(cal.getListOfAuctions(), list);
	}
	@Test
	public void testInsertAuctionsIfDayIsLowerThanCurrentDay() {
		Auction auc = new Auction("Ebay", 12, 1, 2015, 9, 12);
		list.add(auc);
		cal.insertAuctions(list);
		assertEquals(cal.getListOfPastAuctions(), list);
	}
	@Test
	public void testInsertAuctionsIfDayIsLowerThanCurrentDayAndMonthIsCurrentMonth() {
		Auction auc = new Auction("Ebay", 12, 1, 2015, 9, 12);
		list.add(auc);
		cal.insertAuctions(list);
		assertEquals(cal.getListOfPastAuctions(), list);
	}
	@Test
	public void testInsertAuctionsIfDayIsHigherThanCurrentDayAndIfCalendarHasMoreThanMaximumAuctions() {
		ArrayList<Auction> emptyList = new ArrayList<Auction>();
		Auction auc = new Auction("-Ebay", 12, 25, 2015, 9, 12);
		list.add(auc);
		fullCal.insertAuctions(list);
		assertEquals(fullCal.getListOfPastAuctions(), emptyList);
	}
	@Test
	public void testInsertAuctionsIfMoreThanMaximumDaysToFuture() {
		ArrayList<Auction> emptyList = new ArrayList<Auction>();
		Auction auc = new Auction("-----Ebay", 5, 5, 2017, 15, 16);
		emptyList.add(auc);
		cal.insertAuctions(emptyList);
		assertEquals(cal.getListOfAuctions(), list);
	}
	@Test
	public void testInsertAuctionsIfMonthIsHigherThanCurrentMonthAndIfCalendarHasMoreThanMaximumAuctions() {
		ArrayList<Auction> emptyList = new ArrayList<Auction>();
		Auction auc = new Auction("-Ebay", 1, 25, 2015, 9, 12);
		list.add(auc);
		fullCal.insertAuctions(list);
		assertEquals(fullCal.getListOfPastAuctions(), emptyList);
	}
	
	@Test
	public void testCheckOrganizationIfThereIsAlreadyOrganizationWithTheSameName() {
		Auction auc = new Auction("-Ebay", 12, 25, 2015, 9, 12);
		list.add(auc);
		cal.insertAuctions(list);
		assertFalse(cal.checkOrganization("-Ebay"));
	}
	@Test
	public void testCheckOrganizationIfThereIsNoOrganizationWithSuchName() {
		Auction auc = new Auction("-Ebay", 12, 25, 2015, 9, 12);
		list.add(auc);
		cal.insertAuctions(list);
		assertTrue(cal.checkOrganization("-Ebay-"));
	}
	
	@Test
	public void testRemoveAuctionFromTheAuctionListIfThereIsAuctionLikeThat() {
		Auction auc = new Auction("-Ebay", 12, 25, 2015, 9, 12);
		Auction auc2 = new Auction("--Ebay", 12, 27, 2015, 9, 12);
		cal.addAuction(auc);
		cal.addAuction(auc2);
		cal.removeAuction(auc2);
		list.add(auc);
		assertEquals(cal.getListOfAuctions(), list);
	}
	@Test
	public void testRemoveAuctionFromTheAuctionListIfThereIsNoAuctionsLikeThat() {
		Auction auc = new Auction("-Ebay", 12, 25, 2015, 9, 12);
		Auction auc2 = new Auction("--Ebay", 12, 27, 2015, 9, 12);
		list.add(auc);
		cal.removeAuction(auc2);
		list.remove(0);
		assertEquals(cal.getListOfAuctions(), list);
	}
	@Test
	public void testRemoveAuctionFromTheDayAuctionsIfThereIsMoreThanOneAuction() {
		Auction auc = new Auction("-Ebay", 12, 25, 2015, 9, 12);
		Auction auc2 = new Auction("--Ebay", 12, 25, 2015, 15, 20);
		list.add(auc);
		cal.addAuction(auc);
		cal.addAuction(auc2);
		cal.removeAuction(auc2);
		assertEquals(cal.getDay(12, 25), list);
	}
	@Test
	public void testRemoveAuctionFromTheDayAuctionsIfThereIsNoAuctions() {
		Auction auc = new Auction("-Ebay", 12, 25, 2015, 9, 12);
		cal.removeAuction(auc);
		assertEquals(cal.getDay(12, 25), list);
	}
}
