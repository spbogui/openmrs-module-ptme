package org.openmrs.module.ptme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ptme_reporting_report")
public class ReportingReport extends PreventTransmissionAbstract {

    public static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(ReportingReport.class);

    @Id
    @GeneratedValue
    @Column(name = "report_id")
    private Integer reportId;
    @Column(name = "report_label", nullable = false, unique = true)
    private String reportLabel;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false, name = "reporting_template_id")
    private ReportingTemplate template;

    @ManyToMany
    @JoinTable(name = "ptme_reporting_report_dataset",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "dataset_id"))
    private Set<ReportingDataset> reportingDatasets;

    public ReportingReport() {
    }

    @Override
    public Integer getId() {
        return this.getReportId();
    }

    @Override
    public void setId(Integer integer) {
        this.setReportId(integer);
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getReportLabel() {
        return reportLabel;
    }

    public void setReportLabel(String reportLabel) {
        this.reportLabel = reportLabel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ReportingDataset> getReportingDatasets() {
        return reportingDatasets;
    }

    public void setReportingDatasets(Set<ReportingDataset> reportingDatasets) {
        this.reportingDatasets = reportingDatasets;
    }

    public ReportingTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ReportingTemplate template) {
        this.template = template;
    }

    public Set<ReportingDataset> getReportingIndicators() {
        return reportingDatasets;
    }

    public void setReportingIndicators(Set<ReportingDataset> reportingDatasets) {
        this.reportingDatasets = reportingDatasets;
    }
}
