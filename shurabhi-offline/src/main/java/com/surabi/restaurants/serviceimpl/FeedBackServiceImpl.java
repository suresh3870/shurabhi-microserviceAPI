package com.surabi.restaurants.serviceimpl;

import com.surabi.restaurants.entity.FeedBack;
import com.surabi.restaurants.entity.Orders;
import com.surabi.restaurants.repository.BillRepository;
import com.surabi.restaurants.repository.FeedBackRepo;
import com.surabi.restaurants.repository.UserRepository;
import com.surabi.restaurants.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Service
public class FeedBackServiceImpl implements FeedBackService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeedBackRepo feedBackRepo;
    @Override
    public String CreateFeedback(FeedBack feedBack) {
        Date date = new Date();
        feedBack.setFeedBackDate(date);
        feedBack.setFeedback1to5(feedBack.getFeedback1to5());
        feedBack.setOrdersID(1);
        feedBack.setFeedback(feedBack.getFeedback());
        feedBackRepo.save(feedBack);
        return "Feedback Saved!";
    }
}
