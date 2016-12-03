package com.login.stats.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.login.stats.exceptions.UserNotFoundException;
import com.login.stats.forms.PasswordForm;
import com.login.stats.service.UserService;

@Component
public class PasswordFormValidator implements Validator {

	@Autowired
	private UserService userService;
	
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(PasswordForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	PasswordForm form = (PasswordForm) o;
        try {
			validatePasswords(errors, form);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void validatePasswords(Errors errors, PasswordForm form) throws UserNotFoundException {
        if (userService.getUserByEmail(form.getEmail()) == null){
            errors.rejectValue("email", "email_not_exists", "W bazie danych nie znajduje się podany e-mail.");
        }
    }

}
