package com.login.almc.forms;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class LoginForm {

    
    @NotEmpty
    private String user;
    
    @NotEmpty
    @Length(min = 6)
    private String password;
	  
	
    public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}
	
    public void setPassword(String password) {
		this.password = password;
	}
    
}
