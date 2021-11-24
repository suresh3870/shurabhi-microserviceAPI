package com.surabi.restaurants.DTO;

public class OrderDetailsDTO {
    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(int ORDER_ID, String ORDER_DATE, String USERNAME, int QUANTITY, double UNIT_PRICE, double ITEM_TOTALPRICE, int MENU_ID) {
        this.ORDER_ID = ORDER_ID;
        this.ORDER_DATE = ORDER_DATE;
        this.USERNAME = USERNAME;
        this.QUANTITY = QUANTITY;
        this.UNIT_PRICE = UNIT_PRICE;
        this.ITEM_TOTALPRICE = ITEM_TOTALPRICE;
        this.MENU_ID = MENU_ID;
    }

    private int ORDER_ID;
    private String ORDER_DATE;
    private String USERNAME;
    private int QUANTITY;
    private double UNIT_PRICE;
    private double ITEM_TOTALPRICE;
    private int MENU_ID;

    public int getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(int ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public String getORDER_DATE() {
        return ORDER_DATE;
    }

    public void setORDER_DATE(String ORDER_DATE) {
        this.ORDER_DATE = ORDER_DATE;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public double getUNIT_PRICE() {
        return UNIT_PRICE;
    }

    public void setUNIT_PRICE(int UNIT_PRICE) {
        this.UNIT_PRICE = UNIT_PRICE;
    }

    public double getITEM_TOTALPRICE() {
        return ITEM_TOTALPRICE;
    }

    public void setITEM_TOTALPRICE(int ITEM_TOTALPRICE) {
        this.ITEM_TOTALPRICE = ITEM_TOTALPRICE;
    }

    public int getMENU_ID() {
        return MENU_ID;
    }

    public void setMENU_ID(int MENU_ID) {
        this.MENU_ID = MENU_ID;
    }
}
