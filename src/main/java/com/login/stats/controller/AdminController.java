package com.login.stats.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.login.stats.domain.User;
import com.login.stats.service.EmailService;
import com.login.stats.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@ModelAttribute("usersNotAdded")
	private List<User> getNotAddedUsers() {				
		return userService.getUsersNotAdded(false);
	}
	
	@ModelAttribute("users")
	private List<User> getUsers() {				
		return userService.getUsersWithoutAdmins();
	}
	
	@RequestMapping(value = "/admin/add", method = RequestMethod.GET)
	public String getUsersToAdd(@ModelAttribute("usersNotAdded") List<User> usersNotAdded) {		
		return "add";
	}
	
	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	public String getUsersToDelte(@ModelAttribute("users") List<User> users) {		
		return "delete";
	}
	
	@RequestMapping(value = "/admin/add/{userId}", method = RequestMethod.POST)
	public String addUser(@PathVariable long userId, HttpServletRequest request) {
		updateUser(userId);
		emailService.sendConfirmationMail(getEmail(userId), getLink(request));
		return "redirect:/admin/add";
	}
	
	@RequestMapping(value = "/admin/delete/{userId}", method = RequestMethod.POST)
	public String delteUser(@PathVariable long userId) {
		userService.deleteUser(userId);
		return "redirect:/admin/delete";
	}
	
	private String getLink(HttpServletRequest request) {
		String requestURL = request.getRequestURL().toString();
		String baseURL = requestURL.substring(0, requestURL.indexOf(
				request.getContextPath()) + request.getContextPath().length());
		
		return baseURL;
	}
	
	private String getEmail(long userId) {
		return userService.getUserById(userId).getEmail();
	}
	
	private void updateUser(long userId) {
		User user = userService.getUserById(userId);
		user.setAdd(true);
		userService.updateUser(user);
	}
			
}
