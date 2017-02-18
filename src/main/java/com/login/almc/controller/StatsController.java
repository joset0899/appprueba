package com.login.almc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.login.almc.domain.LoggedUser;
import com.login.almc.domain.User;
import com.login.almc.exceptions.UserNotFoundException;
import com.login.almc.security.CurrentUser;
import com.login.almc.security.Role;
import com.login.almc.service.UserService;

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
		if("ADMIN".equals(ownerUser.getRole().getTxtNombPerf())) {
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
