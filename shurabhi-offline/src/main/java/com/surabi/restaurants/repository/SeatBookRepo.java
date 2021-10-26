package com.surabi.restaurants.repository;

import com.surabi.restaurants.entity.AuditLog;
import com.surabi.restaurants.entity.BookSeat;
import com.surabi.restaurants.entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatBookRepo extends JpaRepository<BookSeat, Integer> {


}