package com.login.almc.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.login.almc.domain.Login;
import com.login.almc.domain.Role;
import com.login.almc.domain.User;
import com.login.almc.exceptions.UserNotFoundException;


public interface UserRepository extends JpaRepository<User, Long> {

	int LIMIT_MIN = 0;
	int LIMIT_MAX = 10;
	
	@Query("SELECT l FROM Login l WHERE l.user.id = ?1 ORDER BY loginDate")
	List<Login> getLastLoginsByUserId(long userId, Pageable pageable);
	
	@Modifying
	@Query("UPDATE User u SET u.isAdd = ?1 WHERE u.id = ?2")
	void updateUser(boolean isAdd, long id);
	
	@Query("SELECT u FROM User u WHERE u.role != 'ADMIN'")
	List<User> getUsersWithoutAdmins();
		
	@Query("SELECT u FROM User u WHERE u.role = ?1")
	List<User> getUsersByRole(Role role);
	
	@Query("SELECT u FROM User u WHERE u.isAdd = ?1")
	List<User> getUsersNotAdded(boolean isAdd);
			
	User getUserByEmail(String email) throws UserNotFoundException;
	
	User getUserById(long userId);
	
	User findByNombreUser(String nombreUser) throws UserNotFoundException;
	
}
