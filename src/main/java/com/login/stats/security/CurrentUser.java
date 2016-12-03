package com.login.stats.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.login.stats.domain.User;

@SuppressWarnings("serial")
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;
   
    public CurrentUser(User user) throws NullPointerException, InternalAuthenticationServiceException {
        super(user.getEmail(), 
        		user.getPassword(), 
        		user.isAdd(), 
        		true, 
        		true, 
        		true, 
        		getAuthorities(user));
        
        this.user = user;
    }
    
    private static List<? extends GrantedAuthority> getAuthorities(User user) {
    	List<? extends GrantedAuthority> authorities = AuthorityUtils
    			.createAuthorityList(user.getRole().toString());
    	
    	if (authorities == null) {
    		authorities = new ArrayList<>(); 
    	}
    	
    	return authorities;
    }

    public User getUser() {
        return user;
    }
	
    public Long getId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getRole();
    }
    
}
