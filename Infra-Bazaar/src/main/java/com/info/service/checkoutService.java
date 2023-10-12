package com.info.service;

import com.info.dao.CheckoutRepository;
import com.info.model.checkout;

import org.springframework.beans.factory.annotation.Autowired;

public class checkoutService {
    
    @Autowired
    private CheckoutRepository checkoutRepository;

    public void save(checkout checkout) {
    
        checkoutRepository.save(checkout);
    }
}
