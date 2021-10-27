package com.surabi.restaurants.controller;

import com.surabi.restaurants.DTO.BillDetailsDTO;
import com.surabi.restaurants.DTO.BillOrderDetailsDTO;
import com.surabi.restaurants.DTO.OrderBulkDTO;
import com.surabi.restaurants.entity.Menu;
import com.surabi.restaurants.response.APIResponse;
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
    public Optional<Menu> getItem(int menuID) {
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
    public String checkOut(int orderId) {
        return restaurantsService.checkOut(orderId);
    }

   // @GetMapping("/ViewBill")
   // public List<? extends Object> viewBill(int billID) {
     //   return restaurantsService.viewBillByID(billID);
    //}

    @GetMapping("/ViewMyBill")
    public BillOrderDetailsDTO ViewMyBill(int billID) {
        return restaurantsService.viewMyBill(billID);
    }

    //@GetMapping("/MyDetails")
    //public String mydetails() {
    //	return UserLoggedDetailsImpl.getMyDetails();
    //}

}
