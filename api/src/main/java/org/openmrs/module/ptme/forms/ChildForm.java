package org.openmrs.module.ptme.forms;

import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.Child;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.utils.UsefullFunction;

import java.util.Date;

public class ChildForm {

    private Integer childId;
    private Integer motherPatientId;
    private String motherHivCareNumber;
    private Integer motherAge;
    private String motherName;
    private String childFollowupNumber;
    private Date birthDate;
    private String gender;
    private String familyName;
    private String givenName;
    private Integer patientId;

    public ChildForm() {
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public Integer getMotherPatientId() {
        return motherPatientId;
    }

    public void setMotherPatientId(Integer motherPatientId) {
        this.motherPatientId = motherPatientId;
    }

    public String getMotherHivCareNumber() {
        return motherHivCareNumber;
    }

    public void setMotherHivCareNumber(String motherHivCareNumber) {
        this.motherHivCareNumber = motherHivCareNumber;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getMotherAge() {
        return motherAge;
    }

    public void setMotherAge(Integer motherAge) {
        this.motherAge = motherAge;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void setChild(Child child) {
        this.setChildId(child.getChildId());
        this.setBirthDate(child.getBirthDate());
        this.setChildFollowupNumber(child.getChildFollowupNumber());
        this.setFamilyName(child.getFamilyName());
        this.setGivenName(child.getGivenName());
        this.setGender(child.getGender());
        if(child.getMother() != null) {
            this.setMotherPatientId(child.getMother().getPatientId());
            this.setMotherHivCareNumber(child.getMother().getPatientIdentifier().getIdentifier());
        }
    }

    public Child getChild(Child child) {
        child.setChildId(this.getChildId());
        if (!this.getMotherHivCareNumber().isEmpty()) {
            Patient mother = Context.getService(PreventTransmissionService.class).getPatientByIdentifier(this.getMotherHivCareNumber());
            if (mother != null) {
                this.setMotherPatientId(mother.getPatientId());
                child.setMother(mother);
            }
        }
        child.setFamilyName(this.getFamilyName());
        child.setGivenName(this.getGivenName());
        child.setGender(this.getGender());
        child.setChildFollowupNumber(this.getChildFollowupNumber());
        child.setBirthDate(this.getBirthDate());

        if (child.getCreator() == null){
            child.setCreator(Context.getAuthenticatedUser());
            child.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (this.getChildId() != null) {
            child.setChangedBy(Context.getAuthenticatedUser());
            child.setDateChanged(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if(child.getVoided()) {
            child.setVoidedBy(Context.getAuthenticatedUser());
            child.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if(child.getLocation() == null) {
            child.setLocation(getChosenLocation(null));
        }
        return child;
    }

    public Location getChosenLocation(Integer locationId){
        if (locationId != null) {
            return Context.getLocationService().getLocation(locationId);
        } else {
            return Context.getLocationService().getLocation(Context.getAdministrationService().getGlobalProperty("default_location"));
        }
    }
}
