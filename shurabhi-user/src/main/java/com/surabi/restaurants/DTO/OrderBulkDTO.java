package com.surabi.restaurants.DTO;


import com.surabi.restaurants.Enum.City;
import io.swagger.annotations.ApiModelProperty;

public class OrderBulkDTO {
    @ApiModelProperty(example = "1")
    private int menuID;
    @ApiModelProperty(example = "2")
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
