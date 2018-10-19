package org.openmrs.module.ptme.forms.validators;

import org.openmrs.annotation.Handler;
import org.openmrs.module.ptme.forms.ChildFollowupForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Handler(supports = {ChildFollowupForm.class}, order = 50)
public class ChildFollowupFormValidator implements Validator {
    @Override
    public boolean supports(Class c) {
        return c.equals(ChildFollowupForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ChildFollowupForm form = (ChildFollowupForm) o;

        if(form == null) {
            errors.reject("ptme", "general.error");
        } else {

        }
    }
}
