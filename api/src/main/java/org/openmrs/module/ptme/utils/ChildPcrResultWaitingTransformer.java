package org.openmrs.module.ptme.utils;

import java.util.Date;

public class ChildPcrResultWaitingTransformer {

    private String childFollowupNumber;
    private String familyName;
    private String givenName;
    private String motherContact;
    private Date samplingDate;
    private String pcrRank;

    public ChildPcrResultWaitingTransformer() {
    }

    public String getChildFollowupNumber() {
        return childFollowupNumber;
    }

    public void setChildFollowupNumber(String childFollowupNumber) {
        this.childFollowupNumber = childFollowupNumber;
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

    public String getMotherContact() {
        return motherContact;
    }

    public void setMotherContact(String motherContact) {
        this.motherContact = motherContact;
    }

    public Date getSamplingDate() {
        return samplingDate;
    }

    public void setSamplingDate(Date samplingDate) {
        this.samplingDate = samplingDate;
    }

    public String getPcrRank() {
        return pcrRank;
    }

    public void setPcrRank(String pcrRank) {
        this.pcrRank = pcrRank;
    }
}
