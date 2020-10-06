package com.bridgelabz.hotelreservation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HotelReservationTest {
    @BeforeClass
    public static void beforeClass() throws Exception {
        HotelReservation reservation = new HotelReservation();
        reservation.printWelcomeMessage();
    }

    @Test
    public void shouldReturnLakeWood_whenAllRegularWeekdays() {
        HotelReservation reservation = new HotelReservation();
        String hotelName = reservation.findCheapestHotel("10Sep2020","11Sep2020");
        Assert.assertEquals("Lakewood", hotelName);
    }
}
