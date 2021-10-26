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

    public double getDiscount() {
        String url = "http://localhost:9996/discount/getDiscount/1500/GET50PER";
        return template.getForObject(url, double.class);
    }

}
