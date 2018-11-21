package org.openmrs.module.ptme.forms.validators;

import org.openmrs.Patient;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.PregnantPatient;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.forms.ChildForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Handler(supports = {ChildForm.class}, order = 50)
public class ChildFormValidator  implements Validator {
    @Override
    public boolean supports(Class c) {
        return c.equals(ChildForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ChildForm form = (ChildForm) o;

        if (form == null) {
            errors.reject("ptme", "general.error");
        } else {
//            ValidationUtils.rejectIfEmpty(errors, "motherHivCareNumber", "ptme.field.required");
            ValidationUtils.rejectIfEmpty(errors, "childFollowupNumber", "ptme.field.required");
            ValidationUtils.rejectIfEmpty(errors, "birthDate", "ptme.field.required");
            ValidationUtils.rejectIfEmpty(errors, "gender", "ptme.field.required");
            ValidationUtils.rejectIfEmpty(errors, "familyName", "ptme.field.required");
            ValidationUtils.rejectIfEmpty(errors, "givenName", "ptme.field.required");

            Pattern pattern = Pattern.compile("^[0-9]{4}/.{2}/[0-9]{2}/[0-9]{5}E[1-9]?$", Pattern.CASE_INSENSITIVE);

            if (!form.getChildFollowupNumber().isEmpty()){
                if(!pattern.matcher(form.getChildFollowupNumber()).matches()) {
                    errors.rejectValue("childFollowupNumber", "ptme.invalid.hiv.number");
                }

                if(!form.getMotherHivCareNumber().isEmpty()) {
                    Pattern pattern2 = Pattern.compile("^[0-9]{4}/.{2}/[0-9]{2}/[0-9]{5}E?$", Pattern.CASE_INSENSITIVE);
                    if(!pattern2.matcher(form.getMotherHivCareNumber()).matches()) {
                        errors.rejectValue("motherHivCareNumber", "ptme.invalid.hiv.number");
                    }
                    else if (form.getMotherHivCareNumber().equals(form.getChildFollowupNumber()) ) {
                        errors.rejectValue("motherHivCareNumber",null,  "Numéro de la mère identique à celui de l'enfant");
                    } else if(!form.getChildFollowupNumber().contains(form.getMotherHivCareNumber())) {
                        errors.rejectValue("motherHivCareNumber", "ptme.not.by.hiv.number");
                    } else {
                        Patient mother = Context.getService(PreventTransmissionService.class).getPatientByIdentifier(form.getMotherHivCareNumber());
                        if (mother == null){
                            errors.rejectValue("motherHivCareNumber", null, "Ce Numéro n'est pas celui d'un patient du site !");
                        } else {
                            if (mother.getGender().equals("M")) {
                                errors.rejectValue("motherHivCareNumber", null, "Ce Numéro n'est pas celui d'une femme !");
                            }
                        }
                    }
                }

            }
        }
    }
}
