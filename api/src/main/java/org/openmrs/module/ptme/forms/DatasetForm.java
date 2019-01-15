package org.openmrs.module.ptme.forms;

import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ReportingDataset;
import org.openmrs.module.ptme.ReportingDatasetIndicator;
import org.openmrs.module.ptme.ReportingIndicator;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.utils.UsefullFunction;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DatasetForm {

    private Integer datasetId;
    private Set<String> selectedIndicatorList;
    private Set<String> availableIndicatorList;
    private String name;
//    private String description;
    private String code;

    public DatasetForm() {
    }

    public Integer getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Integer datasetId) {
        this.datasetId = datasetId;
    }

    public Set<String> getSelectedIndicatorList() {
        return selectedIndicatorList;
    }

    public void setSelectedIndicatorList(Set<String> selectedIndicatorList) {
        this.selectedIndicatorList = selectedIndicatorList;
    }

    public Set<String> getAvailableIndicatorList() {
        return availableIndicatorList;
    }

    public void setAvailableIndicatorList(Set<String> availableIndicatorList) {
        this.availableIndicatorList = availableIndicatorList;
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

    public void getReportingDataSet(ReportingDataset rd) {
        this.setDatasetId(rd.getDatasetId());
        this.setName(UsefullFunction.writeAccent(rd.getName()));
        this.setCode(rd.getCode());
//        this.setDescription(rd.getDescription());
//        for (ReportingIndicator ri :
//                rd.getReportingIndicators()) {
//            String indicatorString = "<option value=\"" +  ri.getIndicatorId().toString() + "\" selected=\"selected\">" + ri.getName() + "</option>";
//            this.getSelectedIndicatorList().add(indicatorString);
//        }
    }

    public ReportingDataset getReportingDataset(ReportingDataset reportingDataset) {
        reportingDataset.setDatasetId(this.getDatasetId());
        reportingDataset.setName(UsefullFunction.escapeHTML(this.getName()));
//        reportingDataset.setDescription(this.getDescription());
        reportingDataset.setCode(this.getCode());

        if (reportingDataset.getCreator() == null){
            reportingDataset.setCreator(Context.getAuthenticatedUser());
            reportingDataset.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (this.getDatasetId() != null) {
            reportingDataset.setChangedBy(Context.getAuthenticatedUser());
            reportingDataset.setDateChanged(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }

        if(reportingDataset.getVoided()) {
            reportingDataset.setVoidedBy(Context.getAuthenticatedUser());
            reportingDataset.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (this.getSelectedIndicatorList().size() != 0) {
//            Set<ReportingDatasetIndicator> listReportingDatasetIndicators;
            Set<ReportingIndicator> reportingIndicators = new HashSet<ReportingIndicator>();//reportingDataset.getReportingIndicators();
//            if (reportingIndicators == null) {
//                reportingIndicators = new HashSet<ReportingIndicator>();
//            }
            for (String indicatorString : this.getSelectedIndicatorList()) {
//                String values[] = indicatorString.split("||");
                ReportingIndicator reportingIndicator = Context.getService(PreventTransmissionService.class).getIndicatorById(Integer.parseInt(indicatorString));
                if (reportingIndicator != null) {
                    if (reportingIndicators.isEmpty()) {

                        if (!reportingIndicators.contains(reportingIndicator))
                            reportingIndicators.add(reportingIndicator);

                    } else
                        reportingIndicators.add(reportingIndicator);
                }
            }

            reportingDataset.setReportingIndicators(reportingIndicators);
        }
        return reportingDataset;
    }
}
