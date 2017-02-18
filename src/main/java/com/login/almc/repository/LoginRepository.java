package com.login.almc.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.login.almc.domain.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

	@Query("SELECT COUNT(l) FROM Login l WHERE l.user.id = ?1")
	long getLoginCount(long userId);
	
	@Query("SELECT MIN(l.loginDate) FROM Login l WHERE l.user.id = ?1")
	Date getMinDateByUserId(long userId);
	
	@Query("SELECT l FROM Login l WHERE l.user.id = ?1")
	Login getLoginByUserId(long userId);
	
	@Query("SELECT l FROM Login l WHERE l.user.id = ?1 AND l.loginDate = ?2")
	Login getLoginByUserIdAndDate(long userId, Date date);
	
}
