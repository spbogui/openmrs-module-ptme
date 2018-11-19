package org.openmrs.module.ptme.forms.validators;

import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ReportingIndicator;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.forms.IndicatorForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

@Handler(supports = {IndicatorForm.class}, order = 50)
public class IndicatorFormValidator implements Validator {

    @Override
    public boolean supports(Class c) {
        return c.equals(IndicatorForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        IndicatorForm form = (IndicatorForm) o;

        if(form == null) {
            errors.reject("ptme", "general.error");
        } else {
            ValidationUtils.rejectIfEmpty(errors, "name", "ptme.field.required");
            ValidationUtils.rejectIfEmpty(errors, "indicatorSqlScript", "ptme.field.required");
            ValidationUtils.rejectIfEmpty(errors, "templateCode", "ptme.field.required");

            if (!form.getTemplateCode().isEmpty()){
                Pattern codePattern = Pattern.compile("^[A-Za-z][A-Za-z0-9]{1,5}", Pattern.CASE_INSENSITIVE);
                if (!codePattern.matcher(form.getTemplateCode()).matches()) {
                    errors.rejectValue("templateCode", "ptme.form.indicator.code.invalid");
                }
            }

            if (!form.getIndicatorSqlScript().isEmpty()) {
                if (!form.getIndicatorSqlScript().contains(":locationId")){
                    errors.rejectValue("indicatorSqlScript", "ptme.form.indicator.script.not.location.param");
                } else

                if ((!form.getIndicatorSqlScript().contains(":startDate")) && ((!form.getIndicatorSqlScript().contains(":endDate")) )){
                    errors.rejectValue("indicatorSqlScript", "ptme.form.indicator.script.not.date.param");
                } else

                if (form.getIndicatorSqlScript().toLowerCase().contains("update ") || form.getIndicatorSqlScript().toLowerCase().contains("insert ")) {
                    errors.rejectValue("indicatorSqlScript", "ptme.form.indicator.script.valid");
                } else {

                    Connection connection = null;
                    Statement stmt = null;

                    try {

                        Properties props = new Properties();
                        props.setProperty("driver.class", "com.mysql.jdbc.Driver");
                        props.setProperty("driver.url", Context.getRuntimeProperties().getProperty("connection.url"));
                        props.setProperty("user", Context.getRuntimeProperties().getProperty("connection.username"));
                        props.setProperty("password", Context.getRuntimeProperties().getProperty("connection.password"));

                        connection = DriverManager.getConnection(props.getProperty("driver.url"), props);

                        String sql = form.getIndicatorSqlScript();
                        sql = sql.replace(":locationId", "1");

                        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                        sql = sql.replace(":startDate", "'" + dateFormatter.format(new Date()) + "'");
                        sql = sql.replace(":endDate", "'" +  dateFormatter.format(new Date()) + "'");

                        stmt = connection.createStatement();
                        stmt.executeQuery(sql);

                    } catch (Exception e) {
//                        errors.rejectValue("indicatorSqlScript", e.getMessage());
                        errors.rejectValue("indicatorSqlScript", null, "Erreur SQL : " + e.getMessage());
                    } finally {
                        try {
                            if (stmt != null)
                                stmt.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        try {
                            if (connection != null) {
                                connection.close();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }


                }

            }

            if (!form.getName().isEmpty()) {
                ReportingIndicator ind = Context.getService(PreventTransmissionService.class).getIndicatorByName(form.getName());

                if (ind != null) {

                    if (form.getIndicatorId() == null) {
                        errors.rejectValue("name", "ptme.form.indicator.name.duplicated");
                    } else {
                        if (!form.getIndicatorId().equals(ind.getIndicatorId())) {
                            errors.rejectValue("name", "ptme.form.indicator.name.duplicated");
                        }
                    }

                }

            }

            if (!form.getTemplateCode().isEmpty()) {
                ReportingIndicator ind = Context.getService(PreventTransmissionService.class).getIndicatorByCode(form.getTemplateCode());

                if (ind != null) {

                    if (form.getIndicatorId() == null) {
                        errors.rejectValue("templateCode", null, "Ce dode est déjà utilisé par un autre indicateur");
                    } else {
                        if (!form.getIndicatorId().equals(ind.getIndicatorId())) {
                            errors.rejectValue("templateCode", null, "Ce dode est déjà utilisé par un autre indicateur");
                        }
                    }

                }

            }
        }
    }
}
