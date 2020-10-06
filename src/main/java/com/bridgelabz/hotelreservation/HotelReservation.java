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
        HotelList.add(new Hotel("Lakewood", 110));
        HotelList.add(new Hotel("Bridgewood", 150));
        HotelList.add(new Hotel("Ridgewood", 220));
    }

    public String findCheapestHotel(String arrival,String checkout){
        Date StartDate = convertStringToDate(arrival);
        Date EndDate = convertStringToDate(checkout);
        long Duration = EndDate.getTime()-StartDate.getTime();
        int Days = (int) TimeUnit.DAYS.convert(Duration,TimeUnit.MILLISECONDS);

        addHotelDetails();

        for (int hotel = 0; hotel < HotelList.size(); hotel++) {
            int newRate = HotelList.get(hotel).getRegularRate() * (Days+1);
            HotelList.get(hotel).setRegularRate(newRate);
        }
        int regularRate = HotelList.stream().min(Comparator.comparing(Hotel::getRegularRate)).get().getRegularRate();
        String hotelName = HotelList.stream().min(Comparator.comparing(Hotel::getRegularRate)).get().getHotelName();

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
