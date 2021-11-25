package com.surabi.restaurants.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.surabi.restaurants.DTO.BillOrderDetailsDTO;
import com.surabi.restaurants.DTO.OrderBulkDTO;
import com.surabi.restaurants.DTO.OrderResponse;
import com.surabi.restaurants.Enum.City;
import com.surabi.restaurants.config.RestTemplateConfig;
import com.surabi.restaurants.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
    public String bulkOrder(List<OrderBulkDTO> orderBulkDTO, City city)  {
        String url="http://localhost:9998/surabi/users/Order?city="+city.name();
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
    public OrderResponse checkOut(int orderID) {
        String url="http://localhost:9998/surabi/users/CheckOut?orderId="+orderID;
        return template.getForObject(url, OrderResponse.class);

    }
}
