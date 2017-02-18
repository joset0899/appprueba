package com.login.almc.service;

import org.springframework.scheduling.annotation.Async;

public interface EmailService {

	@Async
    void sendConfirmationMail(String email, String link);
	
	@Async
    void sendNewPassword(String email, String password);

}
