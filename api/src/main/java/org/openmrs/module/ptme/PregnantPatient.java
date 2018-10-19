package org.openmrs.module.ptme;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.openmrs.Location;
import org.openmrs.Patient;

import javax.persistence.*;

@XStreamAlias("pregnantPatient")
@Entity(name = "PregnantPatient")
@Table(name = "ptme_pregnant_patient")
public class PregnantPatient extends PreventTransmissionAbstract {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "pregnant_patient_id")
    private Integer pregnantPatientId;
    @Column(name = "family_name")
    private String familyName;
    @Column(name = "given_name")
    private String givenName;
    @Column(unique = true, nullable = false, name = "pregnant_number")
    private String pregnantNumber;
    @Column(name = "hiv_care_number")
    private String hivCareNumber;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @Column(name = "screening_number")
    private String screeningNumber;
    @Column(nullable = false, name = "age")
    private Integer age;
    @Column(name = "marital_status")
    private Integer maritalStatus;
    @Column(name = "spousal_screening")
    private Integer spousalScreening;
    @Column(name = "spousal_screening_result")
    private Integer spousalScreeningResult;
    @ManyToOne
    @JoinColumn(nullable = false, name = "location_id")
    private Location location;

    public PregnantPatient() {
    }

    @Override
    public Integer getId() {
        return getPregnantPatientId();
    }

    @Override
    public void setId(Integer integer) {
        setPregnantPatientId(integer);
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public Integer getSpousalScreening() {
        return spousalScreening;
    }

    public void setSpousalScreening(Integer spousalScreening) {
        this.spousalScreening = spousalScreening;
    }

    public Integer getSpousalScreeningResult() {
        return spousalScreeningResult;
    }

    public void setSpousalScreeningResult(Integer spousalScreeningResult) {
        this.spousalScreeningResult = spousalScreeningResult;
    }
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
