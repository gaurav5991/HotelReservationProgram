package com.bridgelabz.hotelreservation;

import org.junit.BeforeClass;

public class HotelReservationTest {
    @BeforeClass
    public static void beforeClass() throws Exception {
        HotelReservation reservation = new HotelReservation();
        reservation.printWelcomeMessage();
    }
}
