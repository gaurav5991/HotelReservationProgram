package com.bridgelabz.hotelreservation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class HotelReservation {

    ArrayList<Hotel> HotelList = new ArrayList<>();

    public void printWelcomeMessage(){
        System.out.println("Welcome to Hotel Reservation System");
    }

    public void addHotelDetails() {
        HotelList.add(new Hotel("Lakewood", 110,90));
        HotelList.add(new Hotel("Bridgewood", 150,50));
        HotelList.add(new Hotel("Ridgewood", 220,150));
    }

    public String findCheapestHotel(String arrival,String checkout){
        Date StartDate = convertStringToDate(arrival);
        Date EndDate = convertStringToDate(checkout);
        long Duration = EndDate.getTime()-StartDate.getTime();
        int Days = (int) TimeUnit.DAYS.convert(Duration,TimeUnit.MILLISECONDS);

        addHotelDetails();

        for (int hotel = 0; hotel < HotelList.size(); hotel++) {
            int newRate = HotelList.get(hotel).getWeekDayRate() * (Days+1);
            HotelList.get(hotel).setWeekDayRate(newRate);
        }
        int regularRate = HotelList.stream().min(Comparator.comparing(Hotel::getWeekDayRate)).get().getWeekDayRate();
        String hotelName = HotelList.stream().min(Comparator.comparing(Hotel::getWeekDayRate)).get().getHotelName();

        System.out.println("Hotel Name: "+hotelName+" Total Rate: $"+regularRate);

        return hotelName;

    }
    public Date convertStringToDate(String dateString){
        Date date = null;
        DateFormat df = new SimpleDateFormat("ddMMMyyyy");
        try{
            date = df.parse(dateString);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }

}
