package com.bridgelabz.hotelreservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class HotelReservation {

    ArrayList<Hotel> HotelList = new ArrayList<>();

    /*Function to print Welcome message*/
    public boolean printWelcomeMessage() {
        System.out.println("Welcome to Hotel Reservation System");
        return true;
    }

    /*Function to add hotel Name and Regular Rate to HotelList*/
    public void addHotelDetails(String hotelName, int weekDayRate, int weekendRate) {
        Hotel hotel = new Hotel(hotelName, weekDayRate, weekendRate);
        HotelList.add(hotel);
    }

    /*Function to find Cheapest Hotel for Regular Customer for Given Date Range*/
    public ArrayList<String> findCheapestHotelForRegularCustomer(String arrival, String checkout) {
        LocalDate arrivalDate = convertStringToDate(arrival);
        LocalDate checkoutDate = convertStringToDate(checkout);
        ArrayList<String> cheapestHotelNameList = new ArrayList<>();
        int minRate = Integer.MAX_VALUE;
        for (Hotel hotel : HotelList) {
            LocalDate start = arrivalDate;
            LocalDate end = checkoutDate.plusDays(1);
            int hotelRent = 0;
            while (!(start.equals(end))) {

                int day = start.getDayOfWeek().getValue();

                if (day == 6 || day == 7){
                    hotelRent = hotelRent + hotel.getWeekendRate();
                    System.out.println(hotelRent);
                }
                else{
                    hotelRent = hotelRent + hotel.getWeekDayRate();
                    System.out.println(hotelRent);
                }
                start = start.plusDays(1);
            }
            if (hotelRent <= minRate) {
                minRate = hotelRent;
                cheapestHotelNameList.add(hotel.getHotelName());
            }
        }
        for (String hotel: cheapestHotelNameList){
            System.out.println("Hotel Name: "+hotel+" Total Rate $"+minRate);
        }
        return cheapestHotelNameList;
    }
    /*Convert String into LocalDate*/
    public LocalDate convertStringToDate(String dateString) {
        LocalDate date = null;
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("ddMMMyyyy");
        try {
            date = LocalDate.parse(dateString, dateTimeFormat);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
