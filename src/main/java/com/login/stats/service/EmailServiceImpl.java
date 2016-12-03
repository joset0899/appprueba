package com.login.stats.service;

import java.text.MessageFormat;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender mailSender;
    @Resource(name = "registerMessage")
    private SimpleMailMessage registerMessage;
    @Value(value="${mail.message.confirm.template}")
    private String confirmEmailTemplate;
    @Value(value="${mail.message.password.template}")
    private String passwordEmailTemplate;

    @Async
    public void sendConfirmationMail(String email, String link) {
        SimpleMailMessage message = getConfirmationEmailMessage(email, link);
        mailSender.send(message);
    }

	@Override
	public void sendNewPassword(String email, String password) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = getPasswordEmailMessage(email, password);
        mailSender.send(message);
	}
	
	private final SimpleMailMessage getConfirmationEmailMessage(String email, String link) {
    	registerMessage.setSubject("Aplikacja - potwierdzenie");
        registerMessage.setTo(email);
        registerMessage.setText(MessageFormat.format(confirmEmailTemplate, link));
				
        return registerMessage;
    }
	
	private final SimpleMailMessage getPasswordEmailMessage(String email, String password) {
    	registerMessage.setSubject("Aplikacja - nowe hasło");
        registerMessage.setTo(email);
        registerMessage.setText(MessageFormat.format(passwordEmailTemplate, password));
				
        return registerMessage;
    }
	
}
