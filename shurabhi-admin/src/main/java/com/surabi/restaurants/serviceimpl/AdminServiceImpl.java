package com.surabi.restaurants.serviceimpl;

import com.surabi.restaurants.DTO.BillDTO;
import com.surabi.restaurants.entity.User;
import com.surabi.restaurants.repository.BillRepository;
import com.surabi.restaurants.repository.UserRepository;
import com.surabi.restaurants.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BillRepository billRepository;


    @Override
    public String CreateUser(User user) {
        if(!userRepository.existsById(user.getUsername())) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setEnabled(Boolean.TRUE);
            user.setPassword(encodedPassword);
            user.setUsername(user.getUsername());
            user.setAuthority(user.getAuthority());
            userRepository.save(user);
            return "User created successfully";
        }
        else {
            return "User "+user.getUsername()+ " already exist, please update if you wish to change user";
        }
    }

    @Override
    public String UpdateUser(User user) {
        if(userRepository.existsById(user.getUsername())){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "User " + user.getUsername() + " updated successfully";
        }
        else {
            return "User "+user.getUsername()+ " does not exist";
        }
    }

    @Override
    public String deleteUser(String userName) {
        if(userRepository.existsById(userName)) {
            userRepository.deleteById(userName);
            return userName + " deleted successfully from DB";
        }
        else {
            return "User "+userName+ " does not exist";
        }
    }

    @Override
    public double totalSellByMonth(int monthID) {
        Query nativeQuery = entityManager.createNativeQuery("select sum(bill_amount) from bill where extract(MONTH from bill_date)=?1");
        nativeQuery.setParameter(1, monthID);
        List amount = nativeQuery.getResultList();
        double amt= (double) amount.get(0);
        return amt;
    }

    @Override
    public List<BillDTO>  viewTodayBills() {
        Query nativeQuery = entityManager.createNativeQuery("select distinct b.BILLID as BILL_ID,  u.USERNAME as USERNAME, b.BILL_DATE as BILL_DATE ,b.BILL_AMOUNT as BILL_AMOUNT from  users u , BILL b, ORDERS o where  u.USERNAME=o.USERNAME  \n" +
                "and o.username=u.username\n" +
                "and CAST(b.BILL_DATE as DATE)=CURRENT_DATE","BillViewMapping" );
        List<BillDTO> list =  nativeQuery.getResultList();
        return list;
    }
}
