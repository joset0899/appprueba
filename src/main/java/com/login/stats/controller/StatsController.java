package com.login.stats.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.login.stats.domain.LoggedUser;
import com.login.stats.domain.User;
import com.login.stats.exceptions.UserNotFoundException;
import com.login.stats.security.CurrentUser;
import com.login.stats.security.Role;
import com.login.stats.service.UserService;

@Controller
public class StatsController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("users")
	private List<LoggedUser> getLoggedUsers(Authentication authentication) {
		List<LoggedUser> loggedUsers = new ArrayList<>();
		
		if(authentication == null) {
			return null;
		}
		
		try {
			loggedUsers = selectUsers(loggedUsers, getUser(authentication));
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loggedUsers;
	}
	
	@RequestMapping("/")
	public String getUsers(@ModelAttribute("users") List<LoggedUser> users) {		
		return "stats";
	}
	
	private List<LoggedUser> selectUsers(List<LoggedUser> loggedUsers, User ownerUser) {
		if(ownerUser.getRole() == Role.ADMIN) {
			List<User> users = userService.getUsers();
			loggedUsers = new ArrayList<>(getLoggedUsers(users));
		} else {
			LoggedUser loggedUser = new LoggedUser(ownerUser);
			loggedUsers.add(loggedUser);
		}
		
		return loggedUsers;
	}
	
	private User getUser(Authentication authentication) throws UserNotFoundException {
		String email = ((CurrentUser) authentication.getPrincipal()).getUsername();
		return userService.getUserByEmail(email);
	}
	
	private List<LoggedUser> getLoggedUsers(List<User> users) {
		List<LoggedUser> loggedUsers = new ArrayList<>();
		for(User user : users) {
			loggedUsers.add(new LoggedUser(user));
		}
		return loggedUsers;
	}
	
}
