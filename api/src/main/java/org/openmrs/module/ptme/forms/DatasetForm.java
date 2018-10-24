package org.openmrs.module.ptme.forms;

import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ReportingDataset;
import org.openmrs.module.ptme.ReportingDatasetIndicator;
import org.openmrs.module.ptme.ReportingIndicator;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.utils.UsefullFunction;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class DatasetForm {

    private Integer datasetId;
    private Set<String> indicatorList;
    private String name;
    private String description;
    private String code;

    public DatasetForm() {
    }

    public Integer getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Integer datasetId) {
        this.datasetId = datasetId;
    }

    public Set<String> getIndicatorList() {
        return indicatorList;
    }

    public void setIndicatorList(Set<String> indicatorList) {
        this.indicatorList = indicatorList;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void getReportingDataSet(ReportingDataset rd) {
        this.setDatasetId(rd.getDatasetId());
        this.setName(rd.getName());
        this.setDescription(rd.getDescription());
        for (ReportingIndicator ri :
                rd.getReportingIndicators()) {
            String indicatorString = ri.getIndicatorId().toString() + "||" + ri.getName();
            this.getIndicatorList().add(indicatorString);
        }
        this.setCode(rd.getCode());
    }

    public ReportingDataset getReportingDataset(ReportingDataset reportingDataset) {
        reportingDataset.setDatasetId(this.getDatasetId());
        reportingDataset.setName(this.getName());
        reportingDataset.setDescription(this.getDescription());
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
        if (this.getIndicatorList().size() != 0) {
            Set<ReportingDatasetIndicator> listReportingDatasetIndicators;
            Set<ReportingIndicator> reportingIndicators = reportingDataset.getReportingIndicators();
            for (String indicatorString : this.getIndicatorList()) {
                String values[] = indicatorString.split("||");
                ReportingIndicator reportingIndicator = Context.getService(PreventTransmissionService.class).getIndicatorById(Integer.parseInt(values[0]));
                if (reportingIndicator != null && reportingIndicators.contains(reportingIndicator)) {
                    reportingIndicators.add(reportingIndicator);
                }
            }

            reportingDataset.setReportingIndicators(reportingIndicators);
        }
        return reportingDataset;
    }
}
