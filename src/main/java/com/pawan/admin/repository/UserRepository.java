package com.pawan.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pawan.admin.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByUserName(String userName);

}
