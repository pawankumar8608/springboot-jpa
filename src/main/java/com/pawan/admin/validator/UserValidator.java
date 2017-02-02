package com.pawan.admin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.pawan.admin.model.User;
import com.pawan.admin.service.UserService;

@Component
public class UserValidator implements Validator{

	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> arg0) {
		   return User.class.equals(arg0);
	}

	@Override
	public void validate(Object object, Errors errors) {
		
		User user = (User) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty");
		if(user.getUserName().length() < 6 || user.getUserName().length() > 32){
			errors.rejectValue("userName", "Size.userForm.userName");
		}
		
		if(userService.findByUserName(user.getUserName()) != null){
			errors.rejectValue("userName", "Duplicate.userForm.userName");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if(user.getPassword().length() < 8 || user.getPassword().length() > 32){
			errors.rejectValue("password", "Size.userForm.password");
		}
		
		if(!user.getPasswordConfirm().equals(user.getPassword())){
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}
		   
	}

}
