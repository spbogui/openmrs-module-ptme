package org.openmrs.module.ptme.utils;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "report")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportIndicatorValues {
    @XmlAttribute(name = "startDate")
    private Date reportStartDate;
    @XmlAttribute(name = "endDate")
    private Date reportEndDate;
    @XmlAttribute(name = "location")
    private String locationUuid;
    @XmlAttribute(name = "generationDate")
    private Date generationDate;

    //private List<ReportRunIndicatorValue> reportRunIndicatorValues;
    @XmlElement(name = "dataSet")
    private List<ReportDataSetIndicatorRun> reportDataSetIndicatorRuns;

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

//    public List<ReportRunIndicatorValue> getReportRunIndicatorValues() {
//        return reportRunIndicatorValues;
//    }
//
//    public void setReportRunIndicatorValues(List<ReportRunIndicatorValue> reportRunIndicatorValues) {
//        this.reportRunIndicatorValues = reportRunIndicatorValues;
//    }

    public List<ReportDataSetIndicatorRun> getReportDataSetIndicatorRuns() {
        return reportDataSetIndicatorRuns;
    }

    public void setReportDataSetIndicatorRuns(List<ReportDataSetIndicatorRun> reportDataSetIndicatorRuns) {
        this.reportDataSetIndicatorRuns = reportDataSetIndicatorRuns;
    }
}
