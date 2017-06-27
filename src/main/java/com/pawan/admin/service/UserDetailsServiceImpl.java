package com.pawan.admin.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pawan.admin.model.Role;
import com.pawan.admin.model.User;
import com.pawan.admin.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User user = userRepository.findByUserName(userName);

		Set<GrantedAuthority> authorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
	}

	
}
