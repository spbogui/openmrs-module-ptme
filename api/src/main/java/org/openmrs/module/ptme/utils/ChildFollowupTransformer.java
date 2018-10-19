package org.openmrs.module.ptme.utils;

import java.io.Serializable;
import java.util.Date;

public class ChildFollowupTransformer implements Serializable {

    private Integer childId;
    private String childFollowupNumber;
    private Date birthDate;
    private String familyName;
    private String givenName;
    private String gender;
    private String lastPcr;
    private Date lastPcrDate;
    private Integer lastPcrResult;
    private Date ctxInitiationDate;
    private Date inhInitiationDate;
    private String status;
    private Integer visitCount;
    private Date lastVisitDate;
    private Integer result;
    private Date resultDate;

    public ChildFollowupTransformer() {
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public String getChildFollowupNumber() {
        return childFollowupNumber;
    }

    public void setChildFollowupNumber(String childFollowupNumber) {
        this.childFollowupNumber = childFollowupNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastPcr() {
        return lastPcr;
    }

    public void setLastPcr(String lastPcr) {
        this.lastPcr = lastPcr;
    }

    public Date getLastPcrDate() {
        return lastPcrDate;
    }

    public void setLastPcrDate(Date lastPcrDate) {
        this.lastPcrDate = lastPcrDate;
    }

    public Integer getLastPcrResult() {
        return lastPcrResult;
    }

    public void setLastPcrResult(Integer lastPcrResult) {
        this.lastPcrResult = lastPcrResult;
    }

    public Date getCtxInitiationDate() {
        return ctxInitiationDate;
    }

    public void setCtxInitiationDate(Date ctxInitiationDate) {
        this.ctxInitiationDate = ctxInitiationDate;
    }

    public Date getInhInitiationDate() {
        return inhInitiationDate;
    }

    public void setInhInitiationDate(Date inhInitiationDate) {
        this.inhInitiationDate = inhInitiationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getResultDate() {
        return resultDate;
    }

    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }
}
