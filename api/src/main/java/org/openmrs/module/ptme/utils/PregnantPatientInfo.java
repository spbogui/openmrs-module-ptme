package org.openmrs.module.ptme.utils;

import java.io.Serializable;
import java.util.Date;

public class PregnantPatientInfo implements Serializable {
    private String identifier;
    private String name;
    private Date birthDate;
    private Integer age;
    private Date outcomeProbableDate;
    private Integer hivType;
    private String telephone;
    private String mobile;

    public PregnantPatientInfo() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getOutcomeProbableDate() {
        return outcomeProbableDate;
    }

    public void setOutcomeProbableDate(Date outcomeProbableDate) {
        this.outcomeProbableDate = outcomeProbableDate;
    }

    public Integer getHivType() {
        return hivType;
    }

    public void setHivType(Integer hivType) {
        this.hivType = hivType;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
