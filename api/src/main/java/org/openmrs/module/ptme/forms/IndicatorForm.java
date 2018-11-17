package org.openmrs.module.ptme.forms;

import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ReportingIndicator;
import org.openmrs.module.ptme.utils.UsefullFunction;

import java.util.Date;

public class IndicatorForm {
    private String mode;
    private Integer indicatorId;
    private String name;
    private String description;
    private String indicatorSqlScript;
    private String templateCode;

    public IndicatorForm() {
    }

    public IndicatorForm(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(Integer indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndicatorSqlScript() {
        return indicatorSqlScript;
    }

    public void setIndicatorSqlScript(String indicatorSqlScript) {
        this.indicatorSqlScript = indicatorSqlScript;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public void setIndicator(ReportingIndicator indicator) {
        this.setIndicatorId(indicator.getIndicatorId());
        this.setName(indicator.getName());
        this.setDescription(indicator.getDescription());
        this.setIndicatorSqlScript(indicator.getIndicatorSqlScript());
        this.setTemplateCode(indicator.getTemplateCode());
    }

    public ReportingIndicator getIndicator(ReportingIndicator indicator) {
        indicator.setIndicatorId(this.getIndicatorId());
        indicator.setName(this.getName());
        indicator.setDescription(this.getDescription());
        indicator.setTemplateCode(this.getTemplateCode());
        indicator.setIndicatorSqlScript(this.getIndicatorSqlScript());

        if (indicator.getCreator() == null){
            indicator.setCreator(Context.getAuthenticatedUser());
            indicator.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (this.getIndicatorId() != null) {
            indicator.setChangedBy(Context.getAuthenticatedUser());
            indicator.setDateChanged(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if(indicator.getVoided()) {
            indicator.setVoidedBy(Context.getAuthenticatedUser());
            indicator.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }

        return indicator;
    }
}
