package org.openmrs.module.ptme.utils;

import javax.xml.bind.annotation.*;
import java.util.List;

//@XmlRootElement(name = "dataSet")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportDataSetIndicatorRun {
    @XmlAttribute(name = "uuid")
    private String dataSetUuid;
    @XmlElement(name = "indicator")
    private List<ReportRunIndicatorValue> reportRunIndicatorValues;

    public ReportDataSetIndicatorRun() {
    }

    public String getDataSetUuid() {
        return dataSetUuid;
    }

    public void setDataSetUuid(String dataSetUuid) {
        this.dataSetUuid = dataSetUuid;
    }

    public List<ReportRunIndicatorValue> getReportRunIndicatorValues() {
        return reportRunIndicatorValues;
    }

    public void setReportRunIndicatorValues(List<ReportRunIndicatorValue> reportRunIndicatorValues) {
        this.reportRunIndicatorValues = reportRunIndicatorValues;
    }
}
