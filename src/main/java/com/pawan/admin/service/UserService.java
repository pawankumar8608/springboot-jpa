package com.pawan.admin.service;

import com.pawan.admin.model.User;

public interface UserService {
	
	public void save(User user);
	
	public User findByUserName(String userName);

}
