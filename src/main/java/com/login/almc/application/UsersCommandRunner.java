package com.login.almc.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.login.almc.domain.Role;
import com.login.almc.domain.User;
import com.login.almc.security.PasswordUtil;
import com.login.almc.service.UserService;

@Component
public class UsersCommandRunner implements CommandLineRunner {
	
	@Autowired
	private PasswordUtil passwordUtil;

	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... arg0) throws Exception {
		//addAdmin();
		//addUser();
	}
	
	private void addAdmin() {
		User admin = new User();
		admin.setEmail("jose@gmail.com");
		admin.setNombreUser("admin");
		admin.setPassword(passwordUtil.getEncryptPassword("123456"));
		admin.setAdd(true);
		Role role = new Role();
		role.setId(1);
		role.setTxtNombPerf("ADMIN");
		admin.setRole(role);
		userService.addUser(admin);
	}
	
	private void addUser() {
		
		User user = new User();
		user.setEmail("user@gmail.com");
		user.setNombreUser("user");
		user.setPassword(passwordUtil.getEncryptPassword("123456"));
		user.setAdd(false);
		Role role = new Role();
		role.setId(1);
		role.setTxtNombPerf("USER");
		user.setRole(role);
		userService.addUser(user);
	}

}
