package com.surabi.restaurants.service;

import com.surabi.restaurants.DTO.BillOrderDetailsDTO;
import com.surabi.restaurants.DTO.OrderBulkDTO;
import com.surabi.restaurants.DTO.OrderDetailsDTO;
import com.surabi.restaurants.DTO.OrderResponse;
import com.surabi.restaurants.Enum.OrderBy;
import com.surabi.restaurants.entity.Menu;
import com.surabi.restaurants.entity.Orders;

import java.util.List;
import java.util.Optional;

public interface RestaurantsService {

    List<Menu> viewAllMenu();

    Optional<Menu> getMenuById(int menuID);

    String createBulkItem(List<OrderBulkDTO> orderBulkDTO);

    List<Orders> getAllOrders();

    int createOrder(int menuID, int qty);

    OrderResponse checkOut(int orderId);

    public BillOrderDetailsDTO viewMyBill(int billID);

    String getUserDetails();

    List<OrderDetailsDTO> getAllOrder();

    List<OrderDetailsDTO> getOrderByDate(String date);

    List<OrderDetailsDTO> getOrderByPrice(Double price);
}