package com.surabi.restaurants.client;

import com.surabi.restaurants.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class GetDiscountClient {

    @Autowired
    private RestTemplate template;

    public double getDiscount(int amount, String code) {
        if (code.equals("GET50PER")) {
            String url = "http://localhost:9996/discount/getDiscount/" + amount + "/GET50PER";
            System.out.println(url);
            return template.getForObject(url, double.class);
        }
        else return amount;
    }
}
