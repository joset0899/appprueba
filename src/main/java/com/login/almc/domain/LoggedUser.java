package com.login.almc.domain;

import java.util.ArrayList;
import java.util.List;

public class LoggedUser {
	
	private String email;
	private List<String> dates;
	
	public LoggedUser(User user) {
		this.email = user.getEmail();
		setDatesByLogins(user.getLogins());
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getDates() {
		return dates;
	}

	public void setDates(List<String> dates) {
		this.dates = dates;
	}
	
	private void setDatesByLogins(List<Login> logins) {
		this.dates = new ArrayList<>();
		for(Login login : logins) {
			dates.add(login.getFormatLoginDate());
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Email: " + email + " Dates: " + dates;
	}

}
