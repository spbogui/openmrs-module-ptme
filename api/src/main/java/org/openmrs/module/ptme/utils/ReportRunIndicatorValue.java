package org.openmrs.module.ptme.utils;

import javax.xml.bind.annotation.*;

//@XmlRootElement(name = "indicator")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportRunIndicatorValue {

    @XmlAttribute(name = "uuid")
    private String indicatorUuid;
    @XmlAttribute(name = "code")
    private String code;
    @XmlElement(name = "value")
    private Integer value;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
