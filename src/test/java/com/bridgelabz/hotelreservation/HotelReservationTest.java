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
        reservation.addHotelDetails("Lakewood",110,90);
        reservation.addHotelDetails("Bridgewood",150, 50);
        reservation.addHotelDetails("Ridgewood",220, 150);
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
}
