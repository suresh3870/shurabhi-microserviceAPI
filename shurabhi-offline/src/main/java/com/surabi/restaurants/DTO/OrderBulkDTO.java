package com.surabi.restaurants.DTO;


public class OrderBulkDTO {
    private int menuID;
    private int qty;

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public OrderBulkDTO(int menuID, int qty) {
        this.menuID = menuID;
        this.qty = qty;
    }
}
