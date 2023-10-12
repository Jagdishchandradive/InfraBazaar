package com.info.service;

import java.util.List;

import com.info.model.*;

public interface UserService {
	
	public User findByEmail(String email);
	
	public void save(User user);
	
	public void update(User user);
	
	public List<User> findAllUser();
	
	public void deleteUser(long userId);

}
