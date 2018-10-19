package org.openmrs.module.ptme.forms.validators;

import org.openmrs.annotation.Handler;
import org.openmrs.module.ptme.forms.MotherFollowupPatientForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Handler(supports = {MotherFollowupPatientForm.class}, order = 50)
public class MotherFollowupPatientFormValidator implements Validator {
    @Override
    public boolean supports(Class c) {
        return c.equals(MotherFollowupPatientForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MotherFollowupPatientForm form = (MotherFollowupPatientForm) o;

        if(form == null) {
            errors.reject("ptme" , "general.error");
        } else {
            ValidationUtils.rejectIfEmpty(errors, "pregnantNumber", "ptme.field.required");
            ValidationUtils.rejectIfEmpty(errors, "hivCareNumber", "ptme.field.required");

            Pattern pattern = Pattern.compile("^[0-9]{4}/.{2}/[0-9]{2}/[0-9]{5}E?[1-9]?$", Pattern.CASE_INSENSITIVE);
            if (!form.getHivCareNumber().isEmpty()){
                if(!pattern.matcher(form.getHivCareNumber()).matches()) {
                    errors.rejectValue("hivCareNumber", "ptme.invalid.hiv.number");
                }
            }
        }
    }
}
