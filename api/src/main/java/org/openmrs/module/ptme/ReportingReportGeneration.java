package org.openmrs.module.ptme;

import org.openmrs.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "ReportingReportGeneration")
@Table(name = "ptme_reporting_report_generation")
public class ReportingReportGeneration extends PreventTransmissionAbstract {
    public static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(ReportingReportGeneration.class);

    @Id
    @GeneratedValue
    @Column(name = "generation_id")
    private Integer generationId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "generation_date", nullable = false)
    private Date generationDate;
    @Column(name = "report_period_start_date", nullable = false)
    private Date reportPeriodStartDate;
    @Column(name = "report_period_end_date", nullable = false)
    private Date reportPeriodEndDate;
    @Column(name = "report_location", nullable = false)
    private Location reportLocation;
    @ManyToOne
    @JoinColumn(nullable = false, name = "report_id")
    private ReportingReport report;
    @Column(name = "content_generated", nullable = false)
    private byte[] contentGenerated;
    @Column(name = "saved", nullable = false)
    private Boolean saved = false;

    public ReportingReportGeneration() {
    }

    @Override
    public Integer getId() {
        return this.getGenerationId();
    }

    @Override
    public void setId(Integer integer) {
        this.setGenerationId(integer);
    }

    public Integer getGenerationId() {
        return generationId;
    }

    public void setGenerationId(Integer generationId) {
        this.generationId = generationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate) {
        this.generationDate = generationDate;
    }

    public Date getReportPeriodStartDate() {
        return reportPeriodStartDate;
    }

    public void setReportPeriodStartDate(Date reportPeriodStartDate) {
        this.reportPeriodStartDate = reportPeriodStartDate;
    }

    public Date getReportPeriodEndDate() {
        return reportPeriodEndDate;
    }

    public void setReportPeriodEndDate(Date reportPeriodEndDate) {
        this.reportPeriodEndDate = reportPeriodEndDate;
    }

    public Location getReportLocation() {
        return reportLocation;
    }

    public void setReportLocation(Location reportLocation) {
        this.reportLocation = reportLocation;
    }

    public ReportingReport getReport() {
        return report;
    }

    public void setReport(ReportingReport report) {
        this.report = report;
    }

    public byte[] getContentGenerated() {
        return contentGenerated;
    }

    public void setContentGenerated(byte[] contentGenerated) {
        this.contentGenerated = contentGenerated;
    }

    public Boolean getSaved() {
        return saved;
    }

    public void setSaved(Boolean saved) {
        this.saved = saved;
    }
}
