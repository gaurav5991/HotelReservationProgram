package com.bridgelabz.hotelreservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class HotelReservation {
    ArrayList<Hotel> HotelList = new ArrayList<>();

    /*Function to print Welcome message*/
    public boolean printWelcomeMessage() {
        System.out.println("Welcome to Hotel Reservation System");
        return true;
    }

    /*Function to add hotel Name and Regular Rate to HotelList*/
    public void addHotelDetails(String hotelName, int weekDayRate, int weekendRate, int rating,int rewardWeekDayRate, int rewardWeekendRate) {
        Hotel hotel = new Hotel(hotelName, weekDayRate, weekendRate,rating,rewardWeekDayRate, rewardWeekendRate);
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
                }
                else{
                    hotelRent = hotelRent + hotel.getWeekDayRate();
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
    /*Calculate cost for regular customers*/
    public static long calculateRegularCost(Hotel hotel, LocalDate start, LocalDate end) {

        long totalCost = 0;
        end = end.plusDays(1);
        while (!(start.equals(end))) {

            int day = start.getDayOfWeek().getValue();

            if (day == 6 || day == 7)
                totalCost = totalCost + hotel.getWeekendRate();

            else
                totalCost = totalCost + hotel.getWeekDayRate();

            start = start.plusDays(1);

        }
        return totalCost;
    }
    /*Function to find Cheapest Best Rated Hotel for Regular Customer for Given Date Range*/
    public String findCheapestBestRatedHotelForRegularCustomer(String arrival, String checkout) {
        LocalDate arrivalDate = convertStringToDate(arrival);
        LocalDate checkoutDate = convertStringToDate(checkout);
        String cheapestHotel = "";
        int bestRating = 0;
        long minRate = HotelList.stream().map(hotel -> calculateRegularCost(hotel, arrivalDate, checkoutDate)).min(Long::compare).get();
        List<Hotel> minRateHotels = HotelList.stream().filter(hotel -> calculateRegularCost(hotel, arrivalDate, checkoutDate) == minRate).collect(Collectors.toList());
        Hotel BestRatedHotel = minRateHotels.stream().max(Comparator.comparingInt(Hotel::getRating)).get();
        cheapestHotel = BestRatedHotel.getHotelName();
        bestRating = BestRatedHotel.getRating();
        System.out.println("Hotel Name: "+cheapestHotel+", Rating: "+bestRating+" and Total Rate $"+minRate);
        return cheapestHotel;
    }

    /*Function to find Best Rated Hotel for Regular Customer for Given Date Range*/
    public String findBestRatedHotelForRegularCustomer(String arrival, String checkout) {
        LocalDate arrivalDate = convertStringToDate(arrival);
        LocalDate checkoutDate = convertStringToDate(checkout);
        int minRate = Integer.MAX_VALUE;
        int bestRating = 0;
        Hotel BestRatedHotel = null;
        for (Hotel hotel : HotelList) {
            LocalDate start = arrivalDate;
            LocalDate end = checkoutDate.plusDays(1);
            int hotelRent = 0;
            while (!(start.equals(end))) {

                int day = start.getDayOfWeek().getValue();

                if (day == 6 || day == 7){
                    hotelRent = hotelRent + hotel.getWeekendRate();
                }
                else{
                    hotelRent = hotelRent + hotel.getWeekDayRate();
                }
                start = start.plusDays(1);
            }
            if(hotel.getRating()>bestRating){
                bestRating = hotel.getRating();
                minRate = hotelRent;
                BestRatedHotel = hotel;
            }
        }
        System.out.println("Hotel Name: "+BestRatedHotel.getHotelName()+", Rating: "+bestRating+" and Total Rate $"+minRate);
        return BestRatedHotel.getHotelName();
    }

    /*Calculate cost for reward Customer*/
    public static long calculateRewardCost(Hotel hotel, LocalDate start, LocalDate end) {

        long totalCost = 0;
        end = end.plusDays(1);
        while (!(start.equals(end))) {

            int day = start.getDayOfWeek().getValue();

            if (day == 6 || day == 7)
                totalCost = totalCost + hotel.getRewardCustomerWeekEndRate();

            else
                totalCost = totalCost + hotel.getRewardCustomerWeekDayRate();

            start = start.plusDays(1);

        }
        return totalCost;
    }
    /*Function to find Best Rated Cheapest Hotel for Reward Customer for Given Date Range*/
    public String findBestRatedCheapestHotelForRewardCustomer(String arrival, String checkout) {
        LocalDate arrivalDate = convertStringToDate(arrival);
        LocalDate checkoutDate = convertStringToDate(checkout);
        String cheapestHotel = "";
        int bestRating = 0;
        long minRate = HotelList.stream().map(h -> calculateRewardCost(h, arrivalDate, checkoutDate)).min(Long::compare).get();
        List<Hotel> minRateHotels = HotelList.stream().filter(hotel -> calculateRewardCost(hotel, arrivalDate, checkoutDate) == minRate).collect(Collectors.toList());
        Hotel BestRatedHotel = minRateHotels.stream().max(Comparator.comparingInt(Hotel::getRating)).get();
        cheapestHotel = BestRatedHotel.getHotelName();
        bestRating = BestRatedHotel.getRating();
        System.out.println("Hotel Name: "+cheapestHotel+", Rating: "+bestRating+" and Total Rate $"+minRate);
        return cheapestHotel;
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

    /*Date Validation*/
    private static boolean validateDate(String date) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("ddMMMyyyy");
        try {
            LocalDate.parse(date,dateTimeFormat);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /*Main Method to take user input*/
    public static void main(String[] args) throws Exception {
        HotelReservation reservation = new HotelReservation();
        Scanner sc = new Scanner(System.in);;
        System.out.println("Welcome to Hotel Reservation System");
        while (true) {
            System.out.println("Enter the Hotel Name for Adding rates: ");
            String hotelName = sc.next();
            System.out.println("Enter Hotel Rating: ");
            int rating = sc.nextInt();
            System.out.println("Enter the Weekday rate: ");
            int weeklyRate = sc.nextInt();
            System.out.println("Enter the Weekend rate");
            int weekEndRate = sc.nextInt();
            System.out.println("Enter the Weekday rate for reward customer");
            int weeklyRateReward = sc.nextInt();
            System.out.println("Enter the Weekend rate for reward customer");
            int weekEndRateReward = sc.nextInt();
            reservation.addHotelDetails(hotelName, weeklyRate, weekEndRate,rating, weeklyRateReward, weekEndRateReward);
            System.out.println("Add more hotel- (Yes/No)");
            String choice = sc.next();
            if (choice.equalsIgnoreCase("Yes"))
                continue;
            else
                break;
        }
        System.out.println("Enter Arrival Date(DDMMMYYYY):- ");
        String arrivalDate = sc.next();
        System.out.println("Enter Checkout Date(DDMMMYYYY):- ");
        String checkoutDate = sc.next();
        if (!(validateDate(arrivalDate) && validateDate(checkoutDate)))
            throw new Exception("Invalid date");
        System.out.println("Enter the type of customer (Regular/Reward)");
        String customer = sc.next();
        if (customer.equalsIgnoreCase("Regular"))
            reservation.findCheapestBestRatedHotelForRegularCustomer(arrivalDate, checkoutDate);
        else if (customer.equalsIgnoreCase("Reward"))
            reservation.findBestRatedCheapestHotelForRewardCustomer(arrivalDate, checkoutDate);
        else
            throw new Exception("Wrong Customer type");

    }
}
