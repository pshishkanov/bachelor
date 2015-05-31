package org.pshishkanov.sherlock.core.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
/**
 * Created by pshishkanov on 31/05/15.
 */
public class SourceCodeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SourceCode.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "sourceText", "sourceText.empty");
        ValidationUtils.rejectIfEmpty(errors, "language", "language.empty");
    }
}
