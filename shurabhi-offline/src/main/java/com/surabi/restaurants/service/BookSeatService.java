package com.surabi.restaurants.service;

import com.surabi.restaurants.DTO.BillDTO;
import com.surabi.restaurants.entity.BookSeat;
import com.surabi.restaurants.entity.FeedBack;
import com.surabi.restaurants.entity.User;

import java.text.ParseException;
import java.util.List;

public interface BookSeatService {

    String bookSeat(BookSeat bookSeat) throws ParseException;

}
