package com.surabi.restaurants.DTO;

public class SaleDTO {
    private double amount;
    private String year;
    private String month;
    private String city;

    public SaleDTO(double amount, String year, String month, String city) {
        this.amount = amount;
        this.year = year;
        this.month = month;
        this.city = city;
    }

    public SaleDTO() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
