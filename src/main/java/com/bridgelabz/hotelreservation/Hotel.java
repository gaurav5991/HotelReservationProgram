package com.bridgelabz.hotelreservation;

public class Hotel {

    private String hotelName;
    private int WeekDayRate;
    private int WeekendRate;


    /*Parameterized Constrictor*/
    public Hotel(String hotelName, int weekDayRate, int weekendRate) {
        this.hotelName = hotelName;
        WeekDayRate = weekDayRate;
        WeekendRate = weekendRate;
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
}
