package com.login.almc.security;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean match(String password, String passwordDataBase) {
		return password.equals(passwordDataBase);
	}
	
	public String getEncryptPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	public String generatePassword(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        
        for(int i = 0; i < length; i++) {
            int value = new Random().nextInt(122 - 65) + 65;
            stringBuilder.append(Character.toString((char) value));
        }
        
        return stringBuilder.toString();
    }
	
}
