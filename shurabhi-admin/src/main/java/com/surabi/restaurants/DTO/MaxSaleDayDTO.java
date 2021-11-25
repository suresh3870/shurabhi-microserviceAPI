package com.surabi.restaurants.DTO;

public class MaxSaleDayDTO {
    private double amount;
    private int day;
    private String month;

    public MaxSaleDayDTO(double amount, int day, String month) {
        this.amount = amount;
        this.day = day;
        this.month = month;
    }

    public MaxSaleDayDTO() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
