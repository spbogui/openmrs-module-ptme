package org.openmrs.module.ptme.utils;

public class ReportRunIndicatorValue {
    private Integer value;
    private String indicatorUuid;

    public ReportRunIndicatorValue() {
    }

    public ReportRunIndicatorValue(Integer value, String indicatorUuid) {
        this.value = value;
        this.indicatorUuid = indicatorUuid;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getIndicatorUuid() {
        return indicatorUuid;
    }

    public void setIndicatorUuid(String indicatorUuid) {
        this.indicatorUuid = indicatorUuid;
    }
}
