package com.info.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.model.User;
//import com.info.model.checkout_details_dao;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);

//    void check_out(checkout_details_dao check_out);
}
