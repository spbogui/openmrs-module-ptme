package org.openmrs.module.ptme.utils;

import java.util.Date;
import java.util.List;

public class ReportIndicatorValues {
    private Date reportStartDate;
    private Date reportEndDate;
    private String locationUuid;
    private Date generationDate;
    private List<ReportRunIndicatorValue> reportRunIndicatorValues;

    public ReportIndicatorValues() {
    }

    public Date getReportStartDate() {
        return reportStartDate;
    }

    public void setReportStartDate(Date reportStartDate) {
        this.reportStartDate = reportStartDate;
    }

    public Date getReportEndDate() {
        return reportEndDate;
    }

    public void setReportEndDate(Date reportEndDate) {
        this.reportEndDate = reportEndDate;
    }

    public String getLocationUuid() {
        return locationUuid;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate) {
        this.generationDate = generationDate;
    }

    public void setLocationUuid(String locationUuid) {
        this.locationUuid = locationUuid;
    }

    public List<ReportRunIndicatorValue> getReportRunIndicatorValues() {
        return reportRunIndicatorValues;
    }

    public void setReportRunIndicatorValues(List<ReportRunIndicatorValue> reportRunIndicatorValues) {
        this.reportRunIndicatorValues = reportRunIndicatorValues;
    }
}
