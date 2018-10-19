package org.openmrs.module.ptme.utils;

import java.io.Serializable;
import java.util.Date;

public class ConsultationWithType implements Serializable {
    private Integer consultationId;
    private String registerType;
    private Date consultationDate;
    private String pregnantNumber;
    private Integer age;
    private Integer hivStatusAtReception;
    private Integer testProposal;
    private Integer testResult;
    private Integer resultAnnouncement;
    private Integer arvDiscount;

    public ConsultationWithType() {
    }

    public Integer getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Integer consultationId) {
        this.consultationId = consultationId;
    }

    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(Date consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getPregnantNumber() {
        return pregnantNumber;
    }

    public void setPregnantNumber(String pregnantNumber) {
        this.pregnantNumber = pregnantNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHivStatusAtReception() {
        return hivStatusAtReception;
    }

    public void setHivStatusAtReception(Integer hivStatusAtReception) {
        this.hivStatusAtReception = hivStatusAtReception;
    }

    public Integer getTestProposal() {
        return testProposal;
    }

    public void setTestProposal(Integer testProposal) {
        this.testProposal = testProposal;
    }

    public Integer getTestResult() {
        return testResult;
    }

    public void setTestResult(Integer testResult) {
        this.testResult = testResult;
    }

    public Integer getResultAnnouncement() {
        return resultAnnouncement;
    }

    public void setResultAnnouncement(Integer resultAnnouncement) {
        this.resultAnnouncement = resultAnnouncement;
    }

    public Integer getArvDiscount() {
        return arvDiscount;
    }

    public void setArvDiscount(Integer arvDiscount) {
        this.arvDiscount = arvDiscount;
    }
}

