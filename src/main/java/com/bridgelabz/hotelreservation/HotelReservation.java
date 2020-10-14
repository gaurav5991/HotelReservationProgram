package com.bridgelabz.hotelreservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class HotelReservation {
    ArrayList<Hotel> HotelList = new ArrayList<>();

    /*Function to print Welcome message*/
    public boolean printWelcomeMessage(){
        System.out.println("Welcome to Hotel Reservation System");
        return true;
    }

    /*Function to add hotel Name and Regular Rate to HotelList*/
    public void addHotelDetails(String hotelName, int weekDayRate, int weekendRate, int rating, int rewardWeekDayRate, int rewardWeekendRate) {
        Hotel hotel = new Hotel(hotelName, weekDayRate, weekendRate, rating, rewardWeekDayRate, rewardWeekendRate);
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

                if (day == 6 || day == 7) {
                    hotelRent = hotelRent + hotel.getWeekendRate();
                } else {
                    hotelRent = hotelRent + hotel.getWeekDayRate();
                }
                start = start.plusDays(1);
            }
            if (hotelRent <= minRate) {
                minRate = hotelRent;
                cheapestHotelNameList.add(hotel.getHotelName());
            }
        }
        for (String hotel : cheapestHotelNameList) {
            System.out.println("Hotel Name: " + hotel + " Total Rate $" + minRate);
        }
        return cheapestHotelNameList;
    }

    /*Function to find Cheapest Best Rated Hotel for Regular Customer for Given Date Range*/
    public String findCheapestBestRatedHotelForRegularCustomer(String arrival, String checkout) {
        LocalDate arrivalDate = convertStringToDate(arrival);
        LocalDate checkoutDate = convertStringToDate(checkout);
        Hotel cheapestHotel = null;
        int minRate = Integer.MAX_VALUE;
        int bestRating = 0;
        for (Hotel hotel : HotelList) {
            LocalDate start = arrivalDate;
            LocalDate end = checkoutDate.plusDays(1);
            int hotelRent = 0;
            while (!(start.equals(end))) {

                int day = start.getDayOfWeek().getValue();

                if (day == 6 || day == 7) {
                    hotelRent = hotelRent + hotel.getWeekendRate();
                } else {
                    hotelRent = hotelRent + hotel.getWeekDayRate();
                }
                start = start.plusDays(1);
            }
            if (hotelRent < minRate) {
                minRate = hotelRent;
                cheapestHotel = hotel;
                bestRating = hotel.getRating();
            }
            if (hotelRent == minRate && hotel.getRating() > bestRating) {
                bestRating = hotel.getRating();
                cheapestHotel = hotel;
            }
        }
        System.out.println("Hotel Name: " + cheapestHotel.getHotelName() + ", Rating: " + bestRating + " and Total Rate $" + minRate);
        return cheapestHotel.getHotelName();
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

                if (day == 6 || day == 7) {
                    hotelRent = hotelRent + hotel.getWeekendRate();
                } else {
                    hotelRent = hotelRent + hotel.getWeekDayRate();
                }
                start = start.plusDays(1);
            }
            if (hotel.getRating() > bestRating) {
                bestRating = hotel.getRating();
                minRate = hotelRent;
                BestRatedHotel = hotel;
            }
        }
        System.out.println("Hotel Name: " + BestRatedHotel.getHotelName() + ", Rating: " + bestRating + " and Total Rate $" + minRate);
        return BestRatedHotel.getHotelName();
    }

    /*Function to find Best Rated Cheapest Hotel for Reward Customer for Given Date Range*/
    public String findBestRatedCheapestHotelForRewardCustomer(String arrival, String checkout) {
        LocalDate arrivalDate = convertStringToDate(arrival);
        LocalDate checkoutDate = convertStringToDate(checkout);
        int minRate = Integer.MAX_VALUE;
        int bestRating = 0;
        Hotel cheapestHotel = null;
        for (Hotel hotel : HotelList) {
            LocalDate start = arrivalDate;
            LocalDate end = checkoutDate.plusDays(1);
            int hotelRent = 0;
            while (!(start.equals(end))) {

                int day = start.getDayOfWeek().getValue();

                if (day == 6 || day == 7) {
                    hotelRent = hotelRent + hotel.getRewardCustomerWeekEndRate();
                } else {
                    hotelRent = hotelRent + hotel.getRewardCustomerWeekDayRate();
                }
                start = start.plusDays(1);
            }
            if (hotelRent < minRate) {
                minRate = hotelRent;
                cheapestHotel = hotel;
                bestRating = hotel.getRating();
            }
            if (hotelRent == minRate && hotel.getRating() > bestRating) {
                bestRating = hotel.getRating();
                cheapestHotel = hotel;
            }
        }
        System.out.println("Hotel Name: " + cheapestHotel.getHotelName() + ", Rating: " + bestRating + " and Total Rate $" + minRate);
        return cheapestHotel.getHotelName();
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

    /*Validate Date */
    private static boolean validateDate(String date) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("ddMMMyyyy");
        try {
            LocalDate.parse(date, dateTimeFormat);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /* Get User Input */
    public static  void main(String []args) throws Exception {
        System.out.println("Welcome to Hotel Reservation System");
        Scanner sc = new Scanner(System.in);
        HotelReservation reservation = new HotelReservation();
        while (true) {
            System.out.println("Enter the Hotel Name for Adding rates: ");
            String hotelName = sc.nextLine();
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
            reservation.addHotelDetails(hotelName, weeklyRate, weekEndRate, rating, weeklyRateReward, weekEndRateReward);
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
