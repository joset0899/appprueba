package com.login.almc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.login.almc.domain.Role;
import com.login.almc.domain.User;
import com.login.almc.forms.AccountCreateForm;
import com.login.almc.security.PasswordUtil;
import com.login.almc.service.UserService;
import com.login.almc.validators.AccountCreateFormValidator;

@Controller
public class AccountController {

	@Autowired
    private UserService userService;
	
	@Autowired
    private AccountCreateFormValidator accountCreateFormValidator;
	
	@Autowired
	private PasswordUtil passwordUtil;

    @InitBinder(value = "accountCreateForm")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(accountCreateFormValidator);
    }
        
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String getAccount(AccountCreateForm accountCreateForm) {
		return "account";
	}
	
	@RequestMapping(value = "/account",method = RequestMethod.POST)
    public String getAccount(@Valid @ModelAttribute("accountCreateForm") AccountCreateForm accountCreateForm, 
    		BindingResult result) {
		 
        if (result.hasErrors()) {
            return "account";
        }
        
        addNewUser(accountCreateForm);
        
        return "redirect:/login?confirmAccount";
    }
	
	private void addNewUser(AccountCreateForm accountCreateForm) {
		userService.addUser(getNewUser(accountCreateForm));
	}
	
	private User getNewUser(AccountCreateForm accountCreateForm) {
		User user = new User();
        user.setEmail(accountCreateForm.getEmail());
        user.setPassword(getPasswordToSave(accountCreateForm));
        
        //user.setRole(accountCreateForm.getRole());
        user.setAdd(false);
        return user;
	}
	
	private String getPasswordToSave(AccountCreateForm accountCreateForm) {
		return passwordUtil.getEncryptPassword(accountCreateForm.getPassword());
	}
	
}
