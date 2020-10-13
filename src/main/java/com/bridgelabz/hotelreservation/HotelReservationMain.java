package com.bridgelabz.hotelreservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class HotelReservationMain {

    static Scanner sc = new Scanner(System.in);
    static HotelReservation reservation = new HotelReservation();

    private static boolean validateDate(String date) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("ddMMMyyyy");
        try {
            LocalDate.parse(date,dateTimeFormat);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
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
