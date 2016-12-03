package com.login.stats.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.login.stats.domain.User;
import com.login.stats.exceptions.UserNotFoundException;
import com.login.stats.forms.PasswordForm;
import com.login.stats.security.PasswordUtil;
import com.login.stats.service.EmailService;
import com.login.stats.service.UserService;
import com.login.stats.validators.PasswordFormValidator;

@Controller
public class PasswordController {

	@Autowired
    private UserService userService;
	
	@Autowired
    private PasswordFormValidator passwordFormValidator;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PasswordUtil passwordUtil;

    @InitBinder(value = "passwordForm")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(passwordFormValidator);
    }
        
	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String getNewPassowrd(PasswordForm passwordForm) {
		return "password";
	}
	
	@RequestMapping(value = "/password", method = RequestMethod.POST)
    public String getNewPassowrd(@Valid @ModelAttribute("passwordForm") PasswordForm passwordForm, 
    		BindingResult result) {
		
        if (result.hasErrors()) {
            return "password";
        }
        
        try {
			performUpdatePassword(passwordForm);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return "redirect:/login?passwordSend";
    }
	
	private void performUpdatePassword(PasswordForm passwordForm) throws UserNotFoundException {
		String password = updatePassword(passwordForm);
		emailService.sendNewPassword(passwordForm.getEmail(), password);
	}
	
	private String updatePassword(PasswordForm passwordForm) throws UserNotFoundException {
		String newPassword = passwordUtil.generatePassword(6);
		User user = userService.getUserByEmail(passwordForm.getEmail());
		user.setPassword(passwordUtil.getEncryptPassword(newPassword));
		userService.updateUser(user);
		return newPassword;
	}
	
}
