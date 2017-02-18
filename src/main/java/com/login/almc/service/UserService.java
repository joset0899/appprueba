package com.login.almc.service;

import java.util.List;

import com.login.almc.domain.Login;
import com.login.almc.domain.User;
import com.login.almc.exceptions.UserNotFoundException;

public interface UserService {
	
	void addUser(User user);	
	void deleteUser(long userId);
	void updateUser(User user);
	
	User getUserByEmail(String email) throws UserNotFoundException;
	User getUserByNombre(String nombreUser) throws UserNotFoundException;
	User getUserById(long userId);
	
	List<User> getUsers();
	List<User> getUsersWithoutAdmins();
	List<User> getUsersNotAdded(boolean isAdd);
	
	List<Login> getLastTenLogins(long userId);

}
