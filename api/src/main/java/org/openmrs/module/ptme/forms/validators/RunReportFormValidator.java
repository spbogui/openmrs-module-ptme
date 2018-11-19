package org.openmrs.module.ptme.forms.validators;

import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ReportingIndicator;
import org.openmrs.module.ptme.ReportingReportGeneration;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.forms.IndicatorForm;
import org.openmrs.module.ptme.forms.RunReportForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

@Handler(supports = {RunReportForm.class}, order = 50)
public class RunReportFormValidator implements Validator {

    @Override
    public boolean supports(Class c) {
        return c.equals(RunReportForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RunReportForm form = (RunReportForm) o;

        if(form == null) {
            errors.reject("ptme", "general.error");
        } else {
            ValidationUtils.rejectIfEmpty(errors, "name", "ptme.field.required");
            ValidationUtils.rejectIfEmpty(errors, "reportPeriodStartDate", "ptme.field.required");
            ValidationUtils.rejectIfEmpty(errors, "reportPeriodEndDate", "ptme.field.required");
            ValidationUtils.rejectIfEmpty(errors, "reportLocation", "ptme.field.required");
            ValidationUtils.rejectIfEmpty(errors, "reportId", "ptme.field.required");

            if (form.getReportPeriodStartDate() != null && form.getReportPeriodEndDate() != null) {
                if (form.getReportPeriodStartDate().after(form.getReportPeriodEndDate())) {
                    errors.rejectValue("reportPeriodEndDate", null, "La date de début est après la date de fin");
                }
            }

            if (!form.getName().isEmpty()) {
                ReportingReportGeneration ind = Context.getService(PreventTransmissionService.class).getGeneratedReportByName(form.getName());

                if (ind != null) {

                    if (form.getGenerationId() == null) {
                        errors.rejectValue("name", null, "Un rapport généré a déjà ce nom");
                    } else {
                        if (!form.getGenerationId().equals(ind.getGenerationId())) {
                            errors.rejectValue("name", null, "Un rapport généré a déjà ce nom");
                        }
                    }

                }

            }

            if (!form.getReportLocation().isEmpty()) {
                if (Context.getService(PreventTransmissionService.class).getLocationByName(form.getReportLocation()) == null) {
                    errors.rejectValue("reportLocation", null, "Veuillez selectionner l'établissement dans la liste proposée SVP !");
                }
            }
        }
    }
}
