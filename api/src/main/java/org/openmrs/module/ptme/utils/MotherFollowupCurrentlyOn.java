package org.openmrs.module.ptme.utils;

import java.io.Serializable;
import java.util.Date;

public class MotherFollowupCurrentlyOn implements Serializable{

    private Integer motherFollowupId;
    private String hivCareNumber;
    private String familyName;
    private String givenName;
    private Integer arvStatusAtRegistering;
    private Integer visitCount;
    private Date lastVisitDate;
    private Integer spousalScreeningResult;
    private Date spousalScreeningDate;
    private Integer pregnancyOutcome;
    private Integer deliveryType;
    private Date startDate;
    private Date endDate;
    private String status;

    public MotherFollowupCurrentlyOn() {
    }

    public Integer getMotherFollowupId() {
        return motherFollowupId;
    }

    public void setMotherFollowupId(Integer motherFollowupId) {
        this.motherFollowupId = motherFollowupId;
    }

    public String getHivCareNumber() {
        return hivCareNumber;
    }

    public void setHivCareNumber(String hivCareNumber) {
        this.hivCareNumber = hivCareNumber;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Integer getArvStatusAtRegistering() {
        return arvStatusAtRegistering;
    }

    public void setArvStatusAtRegistering(Integer arvStatusAtRegistering) {
        this.arvStatusAtRegistering = arvStatusAtRegistering;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Date getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(Date lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public Integer getSpousalScreeningResult() {
        return spousalScreeningResult;
    }

    public void setSpousalScreeningResult(Integer spousalScreeningResult) {
        this.spousalScreeningResult = spousalScreeningResult;
    }

    public Date getSpousalScreeningDate() {
        return spousalScreeningDate;
    }

    public void setSpousalScreeningDate(Date spousalScreeningDate) {
        this.spousalScreeningDate = spousalScreeningDate;
    }

    public Integer getPregnancyOutcome() {
        return pregnancyOutcome;
    }

    public void setPregnancyOutcome(Integer pregnancyOutcome) {
        this.pregnancyOutcome = pregnancyOutcome;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
