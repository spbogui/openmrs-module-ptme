package org.openmrs.module.ptme.forms;

import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.PregnantPatient;
import org.openmrs.module.ptme.utils.UsefullFunction;

import java.util.Date;

public class MotherFollowupPatientForm {

    private Integer pregnantPatientId;
    private String familyName;
    private String givenName;
    private String pregnantNumber;
    private String hivCareNumber;
    private Integer patientId;
    private String screeningNumber;
    private Integer age;
    private Integer maritalStatus;

    public MotherFollowupPatientForm() {
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

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
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

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public PregnantPatient getPregnantPatient(PregnantPatient pregnantPatient, Patient patient) {

        pregnantPatient.setPatient(patient);
        pregnantPatient.setPregnantNumber(this.getPregnantNumber());
        pregnantPatient.setHivCareNumber(patient.getPatientIdentifier().getIdentifier());
        pregnantPatient.setFamilyName(patient.getFamilyName());
        pregnantPatient.setGivenName(patient.getGivenName());
        pregnantPatient.setAge(patient.getAge());
        if(pregnantPatient.getCreator() == null) {
            pregnantPatient.setCreator(Context.getAuthenticatedUser());
            pregnantPatient.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if(pregnantPatient.getPregnantPatientId() != null) {
            pregnantPatient.setDateChanged(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
            pregnantPatient.setChangedBy(Context.getAuthenticatedUser());
        }
        if(pregnantPatient.getVoided()){
            pregnantPatient.setVoidedBy(Context.getAuthenticatedUser());
            pregnantPatient.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }

        pregnantPatient.setLocation(getChosenLocation(null));

        pregnantPatient.setVoided(false);
        return pregnantPatient;
    }

    public void setPregnantPatient(PregnantPatient pregnantPatient) {
        this.setPregnantPatientId(pregnantPatient.getPregnantPatientId());
        this.setPregnantNumber(pregnantPatient.getPregnantNumber());
        if(pregnantPatient.getPatient() != null)
            this.setPatientId(pregnantPatient.getPatient().getPatientId());
        this.setHivCareNumber(pregnantPatient.getHivCareNumber());
    }

    public Location getChosenLocation(Integer locationId){
        if (locationId != null) {
            return Context.getLocationService().getLocation(locationId);
        } else {
            return Context.getLocationService().getLocation(Context.getAdministrationService().getGlobalProperty("default_location"));
        }
    }
}
