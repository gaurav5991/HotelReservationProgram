package com.bridgelabz.hotelreservation;

public class Hotel {

    private String hotelName;
    private int WeekDayRate, WeekendRate, Rating, RewardCustomerWeekDayRate, RewardCustomerWeekEndRate;

    /*Parameterized Constrictor*/
    public Hotel(String hotelName, int weekDayRate, int weekendRate, int rating, int rewardCustomerWeekDayRate, int rewardCustomerWeekEndRate) {
        this.hotelName = hotelName;
        WeekDayRate = weekDayRate;
        WeekendRate = weekendRate;
        Rating = rating;
        RewardCustomerWeekDayRate = rewardCustomerWeekDayRate;
        RewardCustomerWeekEndRate = rewardCustomerWeekEndRate;
    }

    /*Getter And Setter Methods*/
    public String getHotelName() {
        return hotelName;
    }
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public int getWeekDayRate() {
        return WeekDayRate;
    }
    public void setWeekDayRate(int weekDayRate) {
        WeekDayRate = weekDayRate;
    }
    public int getWeekendRate() {
        return WeekendRate;
    }
    public void setWeekendRate(int weekendRate) {
        WeekendRate = weekendRate;
    }
    public int getRating() {
        return Rating;
    }
    public void setRating(int rating) {
        Rating = rating;
    }
    public int getRewardCustomerWeekDayRate() {
        return RewardCustomerWeekDayRate;
    }
    public void setRewardCustomerWeekDayRate(int rewardCustomerWeekDayRate) {
        RewardCustomerWeekDayRate = rewardCustomerWeekDayRate;
    }
    public int getRewardCustomerWeekEndRate() {
        return RewardCustomerWeekEndRate;
    }
    public void setRewardCustomerWeekEndRate(int rewardCustomerWeekEndRate) {
        RewardCustomerWeekEndRate = rewardCustomerWeekEndRate;
    }
}
