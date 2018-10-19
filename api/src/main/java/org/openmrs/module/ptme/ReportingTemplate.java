package org.openmrs.module.ptme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity(name = "ReportingTemplate")
@Table(name = "ptme_reporting_template")
public class ReportingTemplate extends PreventTransmissionAbstract {
    public static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(ReportingTemplate.class);

    @Id
    @GeneratedValue
    @Column(name = "template_id")
    private Integer templateId;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "content", nullable = false)
    private byte[] content;

    public ReportingTemplate() {
    }

    public ReportingTemplate(String name) {
        this.name = name;
    }

    @Override
    public Integer getId() {
        return this.getTemplateId();
    }

    @Override
    public void setId(Integer integer) {
        this.setTemplateId(integer);
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
