package com.login.almc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.login.almc.domain.Login;
import com.login.almc.domain.User;
import com.login.almc.exceptions.UserNotFoundException;
import com.login.almc.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	@Override
	public void deleteUser(long userId) {
		// TODO Auto-generated method stub
		userRepository.delete(userId);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public List<Login> getLastTenLogins(long userId) {
		// TODO Auto-generated method stub
		return userRepository.getLastLoginsByUserId(
				userId, new PageRequest(
						UserRepository.LIMIT_MIN, 
						UserRepository.LIMIT_MAX));
	}

	@Override
	public User getUserByEmail(String email) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.getUserByEmail(email);
		
		if (user == null) {
			throw new UserNotFoundException();
		}
		
		return user;
	}

	@Override
	public List<User> getUsersNotAdded(boolean isAdd) {
		// TODO Auto-generated method stub
		return userRepository.getUsersNotAdded(false);
	}

	@Override
	public User getUserById(long userId) {
		// TODO Auto-generated method stub
		return userRepository.getUserById(userId);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userRepository.updateUser(user.isAdd(), user.getId());
	}

	@Override
	public List<User> getUsersWithoutAdmins() {
		// TODO Auto-generated method stub
		return userRepository.getUsersWithoutAdmins();
	}

	@Override
	public User getUserByNombre(String nombreUser) throws UserNotFoundException {
		
		return userRepository.findByNombreUser(nombreUser);
	}

}
