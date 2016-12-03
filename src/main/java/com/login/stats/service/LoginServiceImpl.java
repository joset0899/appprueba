package com.login.stats.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.stats.domain.Login;
import com.login.stats.repository.LoginRepository;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginRepository loginReposotory;

	@Override
	public void save(Login login) {
		// TODO Auto-generated method stub
		loginReposotory.save(login);
		delteOldLogins(login.getUser().getId());
	}
	
	private void delteOldLogins(long userId) {
		long loginCount = loginReposotory.getLoginCount(userId);
		if(loginCount > 10) {
			Date date = loginReposotory.getMinDateByUserId(userId);
			Login loginToDelete = loginReposotory.getLoginByUserIdAndDate(userId, date);
			loginReposotory.delete(loginToDelete);
		}
	}

}
