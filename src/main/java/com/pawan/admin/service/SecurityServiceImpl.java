package com.pawan.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@Override
	public String findLoggedInUserName() {
           Object object = SecurityContextHolder.getContext().getAuthentication().getDetails();
           if(object instanceof UserDetails){
        	  return ((UserDetails) object).getUsername();
           }
		      return null;
	}

	@Override
	public void autoLogin(String userName, String password) {
           UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
           UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
           authenticationManager.authenticate(token);
         
           if(token.isAuthenticated()){
        	 SecurityContextHolder.getContext().setAuthentication(token);
        	 logger.debug(String.format("Auto login %s successfully", userName));
         }
	}

}
