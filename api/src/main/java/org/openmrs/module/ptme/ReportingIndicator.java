package org.openmrs.module.ptme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Table(name = "ptme_reporting_indicator")
public class ReportingIndicator extends PreventTransmissionAbstract {

    public static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(ReportingIndicator.class);

    @Id
    @GeneratedValue
    @Column(name = "indicator_id")
    private Integer indicatorId;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "description", length = 225)
    private String description;
    @Column(name = "indicator_sql_script", nullable = false, unique = true, columnDefinition = "TEXT")
    private String indicatorSqlScript;
    @Column(name = "template_code", nullable = false, length = 10)
    private String templateCode;

    public ReportingIndicator() {
    }

    @Override
    public Integer getId() {
        return this.getIndicatorId();
    }

    @Override
    public void setId(Integer integer) {
        this.setIndicatorId(integer);
    }

    public Integer getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(Integer indicatorId) {
        this.indicatorId = indicatorId;
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

    public String getIndicatorSqlScript() {
        return indicatorSqlScript;
    }

    public void setIndicatorSqlScript(String indicatorSqlScript) {
        this.indicatorSqlScript = indicatorSqlScript;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
