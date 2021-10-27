package com.surabi.restaurants.controller;

import com.surabi.restaurants.DTO.BillOrderDetailsDTO;
import com.surabi.restaurants.DTO.OrderBulkDTO;
import com.surabi.restaurants.DTO.OrderResponse;
import com.surabi.restaurants.Enum.PaymentMode;
import com.surabi.restaurants.client.GetDiscountClient;
import com.surabi.restaurants.client.UserClient;
import com.surabi.restaurants.entity.BookSeat;
import com.surabi.restaurants.entity.FeedBack;
import com.surabi.restaurants.model.Menu;
import com.surabi.restaurants.service.BookSeatService;
import com.surabi.restaurants.service.FeedBackService;
import com.surabi.restaurants.service.PayBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class OfflineController {

    @Autowired
    UserClient userClient;

    @Autowired
    BookSeatService bookSeatService;

    @Autowired
    PayBill payBill;

    @Autowired
    FeedBackService feedBackService;

    @Autowired
    GetDiscountClient getDiscountClient;

    @GetMapping(value = "/getMenuList")
    public List<Menu> getMenuList() {
        return userClient.getMenuList();
    }

    @GetMapping("/getMenuByID/{menuID}")
    public Menu getMenuByID(int menuID) { return userClient.getMenuByID(menuID); }

    @GetMapping("/getDiscount")
    public double getDiscount(int amount, String code) {
        return getDiscountClient.getDiscount(amount,code);
    }

    @GetMapping("/viewBill")
    public BillOrderDetailsDTO viewBill(int billID) {
        return userClient.viewBill(billID);
    }

    @PostMapping(value = "/Order")
    public String bulkOrder(@RequestBody List<OrderBulkDTO> orderBulkDTO) {
        return userClient.bulkOrder(orderBulkDTO);
    }

    @PostMapping(value = "/BookSeat")
    public String bookSeat(BookSeat bookSeat) throws ParseException {
        return bookSeatService.bookSeat(bookSeat);
    }

    @PostMapping(value = "/GiveFeedback")
    public String feedback(FeedBack feedBack) {
        return feedBackService.CreateFeedback(feedBack);
    }

    @PostMapping(value = "/Payment")
    public String payBill(int billID, PaymentMode paymentMode) {
        return payBill.payBill(billID,paymentMode);
    }

    @GetMapping(value = "/CheckOut")
    public OrderResponse CheckOut(int orderID) {
        return userClient.checkOut(orderID);
    }
}

