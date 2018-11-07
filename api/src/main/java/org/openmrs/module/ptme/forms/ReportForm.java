package org.openmrs.module.ptme.forms;

import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ReportingDataset;
import org.openmrs.module.ptme.ReportingIndicator;
import org.openmrs.module.ptme.ReportingReport;
import org.openmrs.module.ptme.ReportingTemplate;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.utils.UsefullFunction;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ReportForm {

    private Integer reportId;
    private Set<String> selectedDataSetList;
    private Set<String> availableDataSetList;
    private String label;
    private String description;
    private Integer templateId;

    public ReportForm() {
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Set<String> getSelectedDataSetList() {
        return selectedDataSetList;
    }

    public void setSelectedDataSetList(Set<String> selectedDataSetList) {
        this.selectedDataSetList = selectedDataSetList;
    }

    public Set<String> getAvailableDataSetList() {
        return availableDataSetList;
    }

    public void setAvailableDataSetList(Set<String> availableDataSetList) {
        this.availableDataSetList = availableDataSetList;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public void getReport(ReportingReport report) {
        this.setReportId(report.getReportId());
        this.setLabel(report.getReportLabel());
        this.setTemplateId(report.getTemplate().getTemplateId());
        this.setDescription(report.getDescription());

    }

    public ReportingReport setReport(ReportingReport report) {
        report.setReportId(this.getReportId());
        report.setReportLabel(this.getLabel());
        report.setDescription(this.getDescription());

        ReportingTemplate template = Context.getService(PreventTransmissionService.class).getTemplateById(this.getTemplateId());
        if (template != null) {
            report.setTemplate(template);
        }

        if (report.getCreator() == null){
            report.setCreator(Context.getAuthenticatedUser());
            report.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (this.getTemplateId() != null) {
            report.setChangedBy(Context.getAuthenticatedUser());
            report.setDateChanged(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }

        if(report.getVoided()) {
            report.setVoidedBy(Context.getAuthenticatedUser());
            report.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (this.getSelectedDataSetList().size() != 0) {
            Set<ReportingDataset> reportingDatasets = new HashSet<ReportingDataset>();
            for (String dataSetString : this.getSelectedDataSetList()) {
                ReportingDataset reportingDataset = Context.getService(PreventTransmissionService.class).getDatasetById(Integer.parseInt(dataSetString));
                if (reportingDataset != null) {
                    if (reportingDatasets.isEmpty()) {
                        if (!reportingDatasets.contains(reportingDataset))
                            reportingDatasets.add(reportingDataset);
                    } else
                        reportingDatasets.add(reportingDataset);
                }
            }

            report.setReportingDatasets(reportingDatasets);
        }
        return report;
    }
}
