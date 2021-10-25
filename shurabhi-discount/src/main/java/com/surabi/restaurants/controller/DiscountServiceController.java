package com.surabi.restaurants.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Samarth Narula
 */

@RestController
@RequestMapping("/discount")
public class DiscountServiceController {

    @GetMapping("/getDiscount/{price}/{code}")
    public double getDiscount(@PathVariable int price, @PathVariable String code) {
        if (code.equals("GET50PER")) {
            return price * 50 / 100;
        }
        else return price;
    }
}

