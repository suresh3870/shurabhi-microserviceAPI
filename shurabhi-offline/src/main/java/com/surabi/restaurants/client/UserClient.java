package com.surabi.restaurants.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.surabi.restaurants.DTO.BillDetailsDTO;
import com.surabi.restaurants.DTO.BillOrderDetailsDTO;
import com.surabi.restaurants.DTO.OrderBulkDTO;
import com.surabi.restaurants.config.RestTemplateConfig;
import com.surabi.restaurants.model.Menu;
import net.minidev.json.JSONObject;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class UserClient {

    @Autowired
    private RestTemplate template;
    @Autowired
    private RestTemplateConfig restTemplateConfig;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Menu> getMenuList() {
        String url = "http://localhost:9998/surabi/users/ListMenu";
        return template.getForObject(url, List.class);
    }

    public Menu getMenuByID(int menuID) {
        String url = "http://localhost:9998/surabi/users/MenuByID?menuID="+menuID;
        return template.getForObject(url, Menu.class);

    }
    public BillOrderDetailsDTO viewBill(int billID) {
        String url = "http://localhost:9998/surabi/users/ViewMyBill?billID="+billID;
        return template.getForObject(url, BillOrderDetailsDTO.class);

    }
    public String checkout(List<OrderBulkDTO> orderBulkDTO) throws URISyntaxException, JsonProcessingException {
        String url="http://localhost:9998/surabi/users/Order/";
        List<OrderBulkDTO> newEmployees = new ArrayList<>();
        newEmployees.add(new OrderBulkDTO(3, 3));
        newEmployees.add(new OrderBulkDTO(2, 3));
        return template.postForObject(
                "http://localhost:9998/surabi/users/Order/",
                newEmployees,
                String.class);
    }
}
