package com.login.almc.advice;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.login.almc.security.CurrentUser;
import com.login.almc.security.Role;

@ControllerAdvice
public class CurrentUserControllerAdvice {
	
    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication) {
        return (authentication == null) 
        				? null 
        				: (CurrentUser) authentication.getPrincipal();
    }
    
    @ModelAttribute("userRole")
    public Role getRole(Authentication authentication) {
        if(authentication == null) {
        	return null;
        }
        
        for(GrantedAuthority authority : authentication.getAuthorities()) {
        	if(authority.getAuthority().equals(Role.ADMIN.toString())) {
        		System.out.println("ADMIN");
        		return Role.ADMIN;
        	}
        }
        
        return Role.USER;
    }

}
