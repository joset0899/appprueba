package com.login.stats.listeners;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.login.stats.domain.Login;
import com.login.stats.domain.User;
import com.login.stats.exceptions.UserNotFoundException;
import com.login.stats.service.LoginService;
import com.login.stats.service.UserService;

@Component
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent authEvent) {
		// TODO Auto-generated method stub
		User loginUser = null;
		try {
			loginUser = getUser(authEvent);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addNewLogin(loginUser);
	}	
		
	private void addNewLogin(User user) {
		Login login = new Login();
		login.setLoginDate(new Date());
		login.setUser(user);
		loginService.save(login);
	}
	
	private User getUser(InteractiveAuthenticationSuccessEvent authEvent) throws UserNotFoundException {
		UserDetails userDetails = (UserDetails) authEvent.getAuthentication().getPrincipal();
		return userService.getUserByEmail(userDetails.getUsername());
	}
}
