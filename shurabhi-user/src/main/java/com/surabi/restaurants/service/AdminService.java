package com.surabi.restaurants.service;

import com.surabi.restaurants.DTO.BillDTO;
import com.surabi.restaurants.entity.User;

import java.util.List;

public interface AdminService {

    String CreateUser(User user);

    String UpdateUser(User user);

    String deleteUser(String userName);

    double totalSellByMonth(int monthID);

    List<BillDTO>  viewTodayBills();
}
