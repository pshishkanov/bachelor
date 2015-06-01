package org.pshishkanov.sherlock.web.validator;

import org.pshishkanov.sherlock.web.security.model.Account;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by pshishkanov on 31/05/15.
 */
public class AccountValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "password", "password.empty");
        ValidationUtils.rejectIfEmpty(errors, "username", "username.empty");
    }
}
