package com.login.almc.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.login.almc.domain.Role;
import com.login.almc.domain.User;

@SuppressWarnings("serial")
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;
   
    public CurrentUser(User user) throws NullPointerException, InternalAuthenticationServiceException {
        super(user.getNombreUser(), 
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
    			.createAuthorityList(user.getRole().getTxtNombPerf());
    	
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
