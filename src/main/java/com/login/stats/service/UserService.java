package com.login.stats.service;

import java.util.List;

import com.login.stats.domain.Login;
import com.login.stats.domain.User;
import com.login.stats.exceptions.UserNotFoundException;

public interface UserService {
	
	void addUser(User user);	
	void deleteUser(long userId);
	void updateUser(User user);
	
	User getUserByEmail(String email) throws UserNotFoundException;
	User getUserById(long userId);
	
	List<User> getUsers();
	List<User> getUsersWithoutAdmins();
	List<User> getUsersNotAdded(boolean isAdd);
	
	List<Login> getLastTenLogins(long userId);

}
