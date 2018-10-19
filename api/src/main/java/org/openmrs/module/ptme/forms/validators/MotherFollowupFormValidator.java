package org.openmrs.module.ptme.forms.validators;

import org.openmrs.annotation.Handler;
import org.openmrs.module.ptme.forms.MotherFollowupForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Handler(supports = {MotherFollowupForm.class}, order = 50)
public class MotherFollowupFormValidator implements Validator {
    @Override
    public boolean supports(Class c) {
        return c.equals(MotherFollowupForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MotherFollowupForm form = (MotherFollowupForm) o;

        if(form == null) {
            errors.reject("ptme", "general.error");
        } else {
//            ValidationUtils.rejectIfEmpty(errors, "visitDate", "ptme.field.visit.date.required");
//            ValidationUtils.rejectIfEmpty(errors, "arvStatusAtRegistering", "ptme.field.arv.status.required");
        }
    }
}
