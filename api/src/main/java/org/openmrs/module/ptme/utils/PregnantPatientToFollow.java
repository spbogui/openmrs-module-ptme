package org.openmrs.module.ptme.utils;

import java.io.Serializable;

public class PregnantPatientToFollow implements Serializable {
    private Integer pregnantPatientId;
    private String familyName;
    private String givenName;
    private String pregnantNumber;
    private String hivCareNumber;
    private String screeningNumber;
    private Integer age;

    public PregnantPatientToFollow() {
    }

    public Integer getPregnantPatientId() {
        return pregnantPatientId;
    }

    public void setPregnantPatientId(Integer pregnantPatientId) {
        this.pregnantPatientId = pregnantPatientId;
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

    public String getPregnantNumber() {
        return pregnantNumber;
    }

    public void setPregnantNumber(String pregnantNumber) {
        this.pregnantNumber = pregnantNumber;
    }

    public String getHivCareNumber() {
        return hivCareNumber;
    }

    public void setHivCareNumber(String hivCareNumber) {
        this.hivCareNumber = hivCareNumber;
    }

    public String getScreeningNumber() {
        return screeningNumber;
    }

    public void setScreeningNumber(String screeningNumber) {
        this.screeningNumber = screeningNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
