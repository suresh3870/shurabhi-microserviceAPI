package com.surabi.restaurants.controller;

import com.surabi.restaurants.DTO.BillOrderDetailsDTO;
import com.surabi.restaurants.DTO.OrderBulkDTO;
import com.surabi.restaurants.DTO.OrderDetailsDTO;
import com.surabi.restaurants.DTO.OrderResponse;
import com.surabi.restaurants.Enum.OrderBy;
import com.surabi.restaurants.entity.Menu;
import com.surabi.restaurants.service.RestaurantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/surabi/users")
public class UsersController {

    @Autowired
    RestaurantsService restaurantsService;

    @GetMapping("/ListMenu")
    public List<Menu> viewMenu() {
        return restaurantsService.viewAllMenu();
    }

    @GetMapping("/MenuByID")
    public Optional<Menu> getItem(
            @RequestParam(value = "menuID", defaultValue = "1") int menuID) {
        return restaurantsService.getMenuById(menuID);
    }
    //@GetMapping("/Order")
    //public String order(int menuID, int qty) {
    //	 int orderID= restaurantsService.createOrder(menuID, qty);
    //	 if(orderID>0){
    //	 	return "Order "+orderID+" has been created successfully";}
    //	 return "Order not created as menu ID does not exist";
    //}

    @PostMapping(value = "/Order")
    public String order(@RequestBody List<OrderBulkDTO> orderBulkDTO) {
        return restaurantsService.createBulkItem(orderBulkDTO);
    }

    @GetMapping("/CheckOut")
    public OrderResponse checkOut(
            @RequestParam(value = "orderId", defaultValue = "1") int orderId) {
        return restaurantsService.checkOut(orderId);
    }

   // @GetMapping("/ViewBill")
   // public List<? extends Object> viewBill(int billID) {
     //   return restaurantsService.viewBillByID(billID);
    //}

    @GetMapping("/ViewMyBill")
    public BillOrderDetailsDTO ViewMyBill(
            @RequestParam(value = "billID", defaultValue = "1") int billID) {
        return restaurantsService.viewMyBill(billID);
    }

    @GetMapping("/All_orders")
    public List<OrderDetailsDTO> all_details() {
    return restaurantsService.getAllOrder();
    }

    @GetMapping("/OrderByDate")
    public List<OrderDetailsDTO> orderByDate(String date) {
        return restaurantsService.getOrderByDate(date);
    }

    @GetMapping("/OrderByPrice")
    public List<OrderDetailsDTO> orderByPrice(Double price) {
        return restaurantsService.getOrderByPrice(price);
    }

}
