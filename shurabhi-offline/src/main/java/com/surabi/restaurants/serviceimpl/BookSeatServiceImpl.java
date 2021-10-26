package com.surabi.restaurants.serviceimpl;

import com.surabi.restaurants.entity.BookSeat;
import com.surabi.restaurants.entity.FeedBack;
import com.surabi.restaurants.entity.Orders;
import com.surabi.restaurants.repository.BillRepository;
import com.surabi.restaurants.repository.FeedBackRepo;
import com.surabi.restaurants.repository.SeatBookRepo;
import com.surabi.restaurants.repository.UserRepository;
import com.surabi.restaurants.service.BookSeatService;
import com.surabi.restaurants.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class BookSeatServiceImpl implements BookSeatService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SeatBookRepo seatBookRepo;


    @Override
    public String bookSeat(BookSeat bookSeat) throws ParseException {
        Date current = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        cal.add(Calendar.DAY_OF_YEAR,+2);
        Date twoDayafter= cal.getTime();

        if(bookSeat.getBookingDate().after(twoDayafter)){
            bookSeat.setBookingDate(bookSeat.getBookingDate());
            seatBookRepo.save(bookSeat);
            return "Booking confirmed";
    }
    else {return "Booking cannot prior 2 days!";}
}
}
