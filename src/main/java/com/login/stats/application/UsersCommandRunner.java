package com.login.stats.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.login.stats.domain.User;
import com.login.stats.security.PasswordUtil;
import com.login.stats.security.Role;
import com.login.stats.service.UserService;

@Component
public class UsersCommandRunner implements CommandLineRunner {
	
	@Autowired
	private PasswordUtil passwordUtil;

	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... arg0) throws Exception {
		addAdmin();
		addUser();
	}
	
	private void addAdmin() {
		User admin = new User();
		admin.setEmail("jrswitkowicz@gmail.com");
		admin.setPassword(passwordUtil.getEncryptPassword("123456"));
		admin.setAdd(true);
		admin.setRole(Role.ADMIN);
		userService.addUser(admin);
	}
	
	private void addUser() {
		User user = new User();
		user.setEmail("jrswtk@gmail.com");
		user.setPassword(passwordUtil.getEncryptPassword("123456"));
		user.setAdd(false);
		user.setRole(Role.USER);
		userService.addUser(user);
	}

}
