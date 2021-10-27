package com.surabi.restaurants.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
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
    public String bulkOrder(List<OrderBulkDTO> orderBulkDTO)  {
        String url="http://localhost:9998/surabi/users/Order";
        List<OrderBulkDTO> orderList = new ArrayList<>();
        for(OrderBulkDTO orderBulkDTO1:orderBulkDTO ){
            OrderBulkDTO orderBulkDTO2= new OrderBulkDTO();
            orderBulkDTO2.setMenuID(orderBulkDTO1.getMenuID());
            orderBulkDTO2.setQty(orderBulkDTO1.getQty());
            orderList.add(orderBulkDTO2);
        }
        RestTemplate restTemplate = new RestTemplate();
        //String requestJson = "{\"queriedQuestion\":\"Is there pain in your hand?\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Gson gson = new Gson();
        String json = gson.toJson(orderList);
        System.out.println(json);
        HttpEntity<String> entity = new HttpEntity<String>(json,headers);
        String answer = restTemplate.postForObject(url, entity, String.class);
        return answer;
    }
    public String checkOut(int orderID) {
        String url="http://localhost:9998/surabi/users/CheckOut?orderId="+orderID;
        return template.getForObject(url, String.class);

    }
}
