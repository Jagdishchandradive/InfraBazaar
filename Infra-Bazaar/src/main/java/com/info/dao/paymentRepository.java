package com.info.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.model.payment;

@Repository
public interface paymentRepository extends JpaRepository<payment, Integer>{

}
