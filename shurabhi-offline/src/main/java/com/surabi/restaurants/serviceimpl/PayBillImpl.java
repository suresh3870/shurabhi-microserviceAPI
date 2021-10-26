package com.surabi.restaurants.serviceimpl;

import com.surabi.restaurants.Enum.PaymentMode;
import com.surabi.restaurants.entity.Bill;
import com.surabi.restaurants.repository.BillRepository;
import com.surabi.restaurants.service.PayBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PayBillImpl implements PayBill {

    @Autowired
    BillRepository billRepository;
    @Override
    public String payBill(int billID, PaymentMode paymentMode) {
        if (billRepository.existsById(billID)) {
            Bill bill = billRepository.getById(billID);
            bill.setPaidBy(paymentMode.toString());
            billRepository.save(bill);
            return "Bill Paid successfully";
        }
        return "Bill does not exist";
    }
}
