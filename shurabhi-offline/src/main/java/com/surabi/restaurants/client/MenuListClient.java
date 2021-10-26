package com.surabi.restaurants.client;

import com.surabi.restaurants.config.RestTemplateConfig;
import com.surabi.restaurants.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MenuListClient {

    @Autowired
    private RestTemplate template;
    @Autowired
    private RestTemplateConfig restTemplateConfig;

    public List<Menu> getMenuList() {
        String url = "http://localhost:9998/surabi/users/ListMenu";
        return template.getForObject(url, List.class);
    }

    public Menu getMenuByID(int menuID) {
        String url = "http://localhost:9998/surabi/users/MenuByID?menuID="+menuID;
        return template.getForObject(url, Menu.class);

    }
}
