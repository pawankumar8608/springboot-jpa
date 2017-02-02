package com.pawan.admin.service;

import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.pawan.admin.model.User;
import com.pawan.admin.repository.RoleRepository;
import com.pawan.admin.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void save(User user) {
         user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));	
         user.setRoles(new HashSet<>(roleRepository.findAll()));
         userRepository.save(user);
	}

	@Override
	public User findByUserName(String userName) {
    	   return userRepository.findByUserName(userName);
	}

}
