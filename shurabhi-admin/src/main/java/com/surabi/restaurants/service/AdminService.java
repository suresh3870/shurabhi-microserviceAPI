package com.surabi.restaurants.service;

import com.surabi.restaurants.DTO.BillDTO;
import com.surabi.restaurants.DTO.MaxSaleDayDTO;
import com.surabi.restaurants.DTO.SaleDTO;
import com.surabi.restaurants.entity.User;

import java.util.List;

public interface AdminService {

    String CreateUser(User user);

    String UpdateUser(User user);

    String deleteUser(String userName);

    double totalSellByMonth(int monthID, int year);

    List<BillDTO>  viewTodayBills();

    List<SaleDTO> viewSaleByCity();

    List<MaxSaleDayDTO> viewSaleByMonth();

    List<MaxSaleDayDTO> viewMaxSaleInAMonth();
}
