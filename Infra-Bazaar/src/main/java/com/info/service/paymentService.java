package com.info.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.info.dao.paymentRepository;
import com.info.model.checkout;
import com.info.model.payment;


public class paymentService {


	@Autowired
	private paymentRepository paymentRepository;
	

    public void save(payment payment) {
    
    	paymentRepository.save(payment);
    }
}
