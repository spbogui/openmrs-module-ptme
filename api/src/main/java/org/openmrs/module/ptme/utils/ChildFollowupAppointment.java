package org.openmrs.module.ptme.utils;

import java.util.Date;

public class ChildFollowupAppointment {

    private String childFollowupNumber;
    private Date lastVisitDate;
    private String familyName;
    private String givenName;
    private Integer numberOfVisit;
    private Date appointmentDate;
    private Integer passed;

    public ChildFollowupAppointment() {
    }

    public String getChildFollowupNumber() {
        return childFollowupNumber;
    }

    public void setChildFollowupNumber(String childFollowupNumber) {
        this.childFollowupNumber = childFollowupNumber;
    }

    public Date getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(Date lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
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

    public Integer getNumberOfVisit() {
        return numberOfVisit;
    }

    public void setNumberOfVisit(Integer numberOfVisit) {
        this.numberOfVisit = numberOfVisit;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Integer getPassed() {
        return passed;
    }

    public void setPassed(Integer passed) {
        this.passed = passed;
    }
}
