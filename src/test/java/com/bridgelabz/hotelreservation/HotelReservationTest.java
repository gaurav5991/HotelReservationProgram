package com.bridgelabz.hotelreservation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HotelReservationTest {
    HotelReservation reservation = new HotelReservation();

    /*Test Case for print welcome method*/
    @BeforeClass
    public static void beforeClass() {
        HotelReservation reservation = new HotelReservation();
        boolean message = reservation.printWelcomeMessage();
        Assert.assertTrue(message);
    }
    /*Initialize all Hotel Details by calling addHotelDetails() Method*/
    @Before
    public void initialize(){
        reservation.addHotelDetails("Lakewood",110,90,3,80,80);
        reservation.addHotelDetails("Bridgewood",150, 50,4,110,50);
        reservation.addHotelDetails("Ridgewood",220, 150,5,100,40);
    }

    /*Test Case for finding cheapest Hotel for Regular Customer if all weekdays */
    @Test
    public void givenThreeHotelsForGivenDateRangeHavingAllWeekDaysShouldReturnLakewood() {
        Object[] hotelName = reservation.findCheapestHotelForRegularCustomer("10Sep2020", "11Sep2020").toArray();
        Object[] expectedHotelName = {"Lakewood"};
        Assert.assertArrayEquals(expectedHotelName,hotelName);
    }
    /*Test Case for finding cheapest Hotel for Regular Customer if both weekdays and weekend days are there */
    @Test
    public void givenThreeHotelsForGivenDateRangeHavingBothWeekDayAndWeekendShouldReturnLakewoodAndBridgewood() {
        Object[] hotelName = reservation.findCheapestHotelForRegularCustomer("11Sep2020", "12Sep2020").toArray();
        Object[] expectedHotelName = {"Lakewood","Bridgewood"};
        Assert.assertArrayEquals(expectedHotelName,hotelName);
    }
    /*Test Case for finding Best Rated cheapest Hotel for Regular Customer if all weekdays */
    @Test
    public void givenThreeHotelsForGivenDateRangeHavingAllWeekDaysHavingBestRatingAndCheapestShouldReturnLakewood() {
        String hotelName = reservation.findCheapestBestRatedHotelForRegularCustomer("10Sep2020", "11Sep2020");
        Assert.assertEquals("Lakewood",hotelName);
    }
    /*Test Case for finding Best Rated cheapest Hotel for Regular Customer if both weekdays and weekend days are there */
    @Test
    public void givenThreeHotelsForGivenDateRangeHavingBothWeekDayAndWeekendHavingBestRatingAndCheapestShouldReturnBridgewood() {
        String hotelName = reservation.findCheapestBestRatedHotelForRegularCustomer("11Sep2020", "12Sep2020");
        Assert.assertEquals("Bridgewood",hotelName);
    }
    /*Test Case for finding Best Rated Hotel for Regular Customer if all weekdays */
    @Test
    public void givenThreeHotelsForGivenDateRangeHavingAllWeekDaysHavingBestRatingShouldReturnLakewood() {
        String hotelName = reservation.findBestRatedHotelForRegularCustomer("10Sep2020", "11Sep2020");
        Assert.assertEquals("Ridgewood",hotelName);
    }
    /*Test Case for finding Best Rated Hotel for Regular Customer if both weekdays and weekend days are there */
    @Test
    public void givenThreeHotelsForGivenDateRangeHavingBothWeekDayAndWeekendHavingBestRatingShouldReturnBridgewood() {
        String hotelName = reservation.findBestRatedHotelForRegularCustomer("11Sep2020", "12Sep2020");
        Assert.assertEquals("Ridgewood",hotelName);
    }
    /*Test Case for finding Best Rated Hotel for Reward Customer if all weekdays */
    @Test
    public void givenThreeHotelsForGivenDateRangeHavingAllWeekDaysHavingBestRatingAndCheapestForRewardCustomerShouldReturnLakewood() {
        String hotelName = reservation.findBestRatedCheapestHotelForRewardCustomer("10Sep2020", "11Sep2020");
        Assert.assertEquals("Lakewood",hotelName);
    }
    /*Test Case for finding Best Rated Hotel for Reward Customer if both weekdays and weekend days are there */
    @Test
    public void givenThreeHotelsAsForGivenDateRangeHavingBothWeekDayAndWeekendHavingBestRatingAndCheapestForRewardCustomerShouldReturnBridgewood() {
        String hotelName = reservation.findBestRatedCheapestHotelForRewardCustomer("11Sep2020", "12Sep2020");
        Assert.assertEquals("Ridgewood",hotelName);
    }
}
