package org.openmrs.module.ptme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ReportingDatasetIndicator")
@Table(name = "ptme_reporting_dataset_indicator")
public class ReportingDatasetIndicator implements Serializable {

    public static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(ReportingDatasetIndicator.class);
    @Id
    @ManyToOne
    @JoinColumn(name = "dataset_id")
    private ReportingDataset reportingDataset;

    @Id
    @ManyToOne
    @JoinColumn(name = "indicator_id")
    private ReportingIndicator indicator;

    @Column(name = "label", nullable = false, unique = true, length = 255)
    private String label;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "dataset_line", nullable = false)
    private Integer datasetLine;
    @Column(name = "dataset_column", nullable = false, unique = true)
    private Integer datasetColumn;

    public ReportingDatasetIndicator() {
    }

    public ReportingDataset getReportingDataset() {
        return reportingDataset;
    }

    public void setReportingDataset(ReportingDataset reportingDataset) {
        this.reportingDataset = reportingDataset;
    }

    public ReportingIndicator getIndicator() {
        return indicator;
    }

    public void setIndicator(ReportingIndicator indicator) {
        this.indicator = indicator;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDatasetLine() {
        return datasetLine;
    }

    public void setDatasetLine(Integer datasetLine) {
        this.datasetLine = datasetLine;
    }

    public Integer getDatasetColumn() {
        return datasetColumn;
    }

    public void setDatasetColumn(Integer datasetColumn) {
        this.datasetColumn = datasetColumn;
    }
}
