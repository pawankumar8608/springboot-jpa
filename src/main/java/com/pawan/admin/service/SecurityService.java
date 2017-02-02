package com.pawan.admin.service;

public interface SecurityService {
	
	public String findLoggedInUserName();
	
	public void autoLogin(String userName, String password);

}
