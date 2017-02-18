package com.login.almc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.login.almc.domain.User;
import com.login.almc.exceptions.UserNotFoundException;
import com.login.almc.security.CurrentUser;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public CurrentUser loadUserByUsername(String nombreUser) throws UsernameNotFoundException {
        User user = null;
        try {
			user = userService.getUserByNombre(nombreUser);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			throw new UsernameNotFoundException(String.format("Usuario no registrado %s.", nombreUser));
		}
        
        return new CurrentUser(user);
    }
}
