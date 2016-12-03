package com.login.stats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.login.stats.domain.User;
import com.login.stats.exceptions.UserNotFoundException;
import com.login.stats.security.CurrentUser;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = null;
        try {
			user = userService.getUserByEmail(email);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			throw new UsernameNotFoundException(String.format("Nie znaleziono konta powiązanego z adresem %s.", email));
		}
        
        return new CurrentUser(user);
    }
}
