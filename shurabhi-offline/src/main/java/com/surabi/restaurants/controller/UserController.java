package com.surabi.restaurants.controller;

import com.surabi.restaurants.client.GetDiscountClient;
import com.surabi.restaurants.client.MenuListClient;
import com.surabi.restaurants.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    MenuListClient menuListClient;

    @Autowired
    GetDiscountClient getDiscountClient;

    @GetMapping(value = "/getMenuList")
    public List<Menu> getMenuList() {
        return menuListClient.getMenuList();
    }


    @GetMapping("/getMenuByID/{menuID}")
    public Menu getMenuByID(int menuID) {
        return menuListClient.getMenuByID(menuID);
    }

    @GetMapping("/getDiscount")
    public double getDiscount() {
        return getDiscountClient.getDiscount();
    }
}

