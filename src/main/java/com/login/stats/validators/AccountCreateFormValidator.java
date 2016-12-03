package com.login.stats.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.login.stats.exceptions.UserNotFoundException;
import com.login.stats.forms.AccountCreateForm;
import com.login.stats.service.UserService;

@Component
public class AccountCreateFormValidator implements Validator {

	@Autowired
	private UserService userService;
	
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(AccountCreateForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	AccountCreateForm form = (AccountCreateForm) o;
        try {
			validatePasswords(errors, form);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void validatePasswords(Errors errors, AccountCreateForm form) throws UserNotFoundException {
        if (!form.getPassword().equals(form.getPasswordRepeated())){
            errors.rejectValue("password", "no_match", "Hasła nie są identyczne.");
        }
        if (userService.getUserByEmail(form.getEmail()) != null){
            errors.rejectValue("email", "email_exists", "Podany e-mail istnieje w bazie danych.");
        }
    }

}
