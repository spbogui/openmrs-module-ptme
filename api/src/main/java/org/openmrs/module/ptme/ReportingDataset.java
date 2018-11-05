package org.openmrs.module.ptme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "ReportingDataset")
@Table(name = "ptme_reporting_dataset")
public class ReportingDataset extends PreventTransmissionAbstract {

    public static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(ReportingDataset.class);

    @Id
    @GeneratedValue
    @Column(name = "dataset_id")
    private Integer datasetId;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
//    @Column(name = "description")
//    private String description;
    @Column(name = "code", nullable = false)
    private String code;
//    @Column(name = "dataset_headers", nullable = false)
//    private String datasetHeaders;
//    @Column(name = "number_of_line")
//    private Integer numberOfLine;
//    @Column(name = "number_of_column")
//    private Integer numberOfColumn;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "reportingDataset")
//    private Set<ReportingDatasetIndicator> reportingDatasetIndicators;

    @ManyToMany
    @JoinTable(name = "ptme_reporting_dataset_indicator",
            joinColumns = @JoinColumn(name = "dataset_id"),
            inverseJoinColumns = @JoinColumn(name = "indicator_id"))
    public Set<ReportingIndicator> reportingIndicators;

    public ReportingDataset() {
    }

    @Override
    public Integer getId() {
        return this.getDatasetId();
    }

    @Override
    public void setId(Integer integer) {
        this.setDatasetId(integer);
    }

    public Integer getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Integer datasetId) {
        this.datasetId = datasetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

//    public String getDatasetHeaders() {
//        return datasetHeaders;
//    }
//
//    public void setDatasetHeaders(String datasetHeaders) {
//        this.datasetHeaders = datasetHeaders;
//    }
//
//    public Integer getNumberOfLine() {
//        return numberOfLine;
//    }
//
//    public void setNumberOfLine(Integer numberOfLine) {
//        this.numberOfLine = numberOfLine;
//    }
//
//    public Integer getNumberOfColumn() {
//        return numberOfColumn;
//    }
//
//    public void setNumberOfColumn(Integer numberOfColumn) {
//        this.numberOfColumn = numberOfColumn;
//    }

//    public Set<ReportingDatasetIndicator> getReportingDatasetIndicators() {
//        return reportingDatasetIndicators;
//    }
//
//    public void setReportingDatasetIndicators(Set<ReportingDatasetIndicator> reportingDatasetIndicators) {
//        this.reportingDatasetIndicators = reportingDatasetIndicators;
//    }

    public Set<ReportingIndicator> getReportingIndicators() {
        return reportingIndicators;
    }

    public void setReportingIndicators(Set<ReportingIndicator> reportingIndicators) {
        this.reportingIndicators = reportingIndicators;
    }
}
