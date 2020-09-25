package org.openmrs.module.ptme.forms;

import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.*;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.utils.UsefullFunction;

import java.util.Date;
import java.util.UUID;

public class ConsultationForm {

    private Integer consultationId;
    private Date consultationDate;
    private Boolean voided;

    private Integer pregnantPatientId;
    private String pregnantNumber;
    private String screeningNumber;
    private String hivCareNumber;
    private Integer patientId;
    private Integer age;
    private String familyName;
    private String givenName;
    private Integer maritalStatus;

    private Integer hivServiceId;
    private Integer hivStatusAtReception;
    private Integer testProposal;
    private Integer testResult;
    private Integer resultAnnouncement;
    private Integer arvDiscount;

    private Integer arvStatus;
    private Integer childArvProphylaxis;
    private Integer pregnancyIssue;
    private Integer childState;

    private String prenatalRank;
    private Integer spousalScreening;
    private Integer spousalScreeningResult;
    private Integer weekOfAmenorrhea;
    private Date appointmentDate;

    private Boolean homeBirth;
    private Date deliveryDate;

    public ConsultationForm() {
    }

    public Integer getPregnantPatientId() {
        return pregnantPatientId;
    }

    public void setPregnantPatientId(Integer pregnantPatientId) {
        this.pregnantPatientId = pregnantPatientId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
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

    public Boolean getVoided() {
        return voided;
    }

    public void setVoided(Boolean voided) {
        this.voided = voided;
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

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getScreeningNumber() {
        return screeningNumber;
    }

    public void setScreeningNumber(String screeningNumber) {
        this.screeningNumber = screeningNumber;
    }

    public Integer getHivServiceId() {
        return hivServiceId;
    }

    public void setHivServiceId(Integer hivServiceId) {
        this.hivServiceId = hivServiceId;
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

    public Integer getArvStatus() {
        return arvStatus;
    }

    public void setArvStatus(Integer arvStatus) {
        this.arvStatus = arvStatus;
    }

    public Integer getChildArvProphylaxis() {
        return childArvProphylaxis;
    }

    public void setChildArvProphylaxis(Integer childArvProphylaxis) {
        this.childArvProphylaxis = childArvProphylaxis;
    }

    public Integer getPregnancyIssue() {
        return pregnancyIssue;
    }

    public void setPregnancyIssue(Integer pregnancyIssue) {
        this.pregnancyIssue = pregnancyIssue;
    }

    public Integer getChildState() {
        return childState;
    }

    public void setChildState(Integer childState) {
        this.childState = childState;
    }

    public String getPrenatalRank() {
        return prenatalRank;
    }

    public void setPrenatalRank(String prenatalRank) {
        this.prenatalRank = prenatalRank;
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

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Integer getWeekOfAmenorrhea() {
        return weekOfAmenorrhea;
    }

    public void setWeekOfAmenorrhea(Integer weekOfAmenorrhea) {
        this.weekOfAmenorrhea = weekOfAmenorrhea;
    }

    public Boolean getHomeBirth() {
        return homeBirth;
    }

    public void setHomeBirth(Boolean homeBirth) {
        this.homeBirth = homeBirth;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    public void setPregnantPatient(PregnantPatient pregnantPatient) {
        this.pregnantPatientId = pregnantPatient.getPregnantPatientId();
        this.pregnantNumber = pregnantPatient.getPregnantNumber();
        this.screeningNumber = pregnantPatient.getScreeningNumber();
        this.hivCareNumber = pregnantPatient.getHivCareNumber();
        if(pregnantPatient.getPatient() != null) {
            this.patientId = pregnantPatient.getPatient().getPatientId();
        }
        this.age = pregnantPatient.getAge();
        this.familyName = pregnantPatient.getFamilyName();
        this.givenName = pregnantPatient.getGivenName();
        this.maritalStatus = pregnantPatient.getMaritalStatus();
        this.spousalScreening = pregnantPatient.getSpousalScreening();
        this.spousalScreeningResult = pregnantPatient.getSpousalScreeningResult();
    }

    public PregnantPatient getPregnantPatient(PregnantPatient pregnantPatient) {
        pregnantPatient.setPregnantPatientId(this.pregnantPatientId);
        pregnantPatient.setPregnantNumber(this.pregnantNumber);
        pregnantPatient.setScreeningNumber(this.screeningNumber);
        pregnantPatient.setHivCareNumber(this.hivCareNumber);
        pregnantPatient.setAge(this.age);
        pregnantPatient.setFamilyName(this.familyName);
        pregnantPatient.setGivenName(this.givenName);
        if (!this.hivCareNumber.isEmpty()) {
            Patient patient = Context.getService(PreventTransmissionService.class).getPatientByIdentifier(getHivCareNumber());
            if(patient != null) {
                if (patient.getGender().equals("F")) {
                    pregnantPatient.setPatient(patient);
                    pregnantPatient.setFamilyName(patient.getFamilyName());
                    pregnantPatient.setGivenName(patient.getGivenName());
                    pregnantPatient.setAge(patient.getAge());
                }
            }
        }
        pregnantPatient.setMaritalStatus(this.maritalStatus);
        
        if (this.spousalScreening != null && (this.spousalScreening == 0 || this.spousalScreening == 1)) {
            pregnantPatient.setSpousalScreening(this.spousalScreening);
            pregnantPatient.setSpousalScreeningResult(this.spousalScreeningResult);
        }

        if(pregnantPatient.getLocation() == null) {
            pregnantPatient.setLocation(getChosenLocation(null));
        }
        if(pregnantPatient.getCreator() == null) {
            pregnantPatient.setCreator(Context.getAuthenticatedUser());
            pregnantPatient.setDateCreated(UsefullFunction.formatDateToddMMyyyy(new Date()));
        }
        if(pregnantPatient.getVoided() == null) {
            pregnantPatient.setVoided(false);
        } else if(pregnantPatient.getVoided()) {
            pregnantPatient.setVoidedBy(Context.getAuthenticatedUser());
            pregnantPatient.setDateVoided(UsefullFunction.formatDateToddMMyyyy(new Date()));
        }
        if (pregnantPatient.getUuid().isEmpty()) {
            pregnantPatient.setUuid(UUID.randomUUID().toString());
        }

        return pregnantPatient;
    }

    public void setHivService(HivService hivService) {
        this.setHivServiceId(hivService.getHivServiceId());
        this.setHivStatusAtReception(hivService.getHivStatusAtReception());
        this.setTestProposal(hivService.getTestProposal());
        this.testResult = hivService.getTestResult();
        this.resultAnnouncement = hivService.getResultAnnouncement();
        this.arvDiscount = hivService.getArvDiscount();
        this.arvStatus = hivService.getArvStatus();
        this.childArvProphylaxis = hivService.getChildArvProphylaxis();
//        this.spousalScreening = hivService.getSpousalScreening();
//        this.spousalScreeningResult = hivService.getSpousalScreeningResult();
    }

    public HivService getHivService(HivService hivService){
        hivService.setHivServiceId(this.getHivServiceId());
        hivService.setHivStatusAtReception(this.getHivStatusAtReception());
        hivService.setTestProposal(this.getTestProposal());
        hivService.setTestResult(this.getTestResult());
        hivService.setResultAnnouncement(this.getResultAnnouncement());
        hivService.setArvDiscount(this.getArvDiscount());
        hivService.setArvStatus(this.getArvStatus());
        hivService.setChildArvProphylaxis(this.getChildArvProphylaxis());
//        hivService.setSpousalScreening(this.getSpousalScreening());
//        hivService.setSpousalScreeningResult(this.getSpousalScreeningResult());
        if(hivService.getLocation() == null) {
            hivService.setLocation(getChosenLocation(null));
        }
        if(hivService.getCreator() == null) {
            hivService.setCreator(Context.getAuthenticatedUser());
            hivService.setDateCreated(UsefullFunction.formatDateToddMMyyyy(new Date()));
        }
        if(hivService.getVoided() == null) {

            hivService.setVoided(false);
        } else if(hivService.getVoided()) {

            hivService.setDateVoided(UsefullFunction.formatDateToddMMyyyy(new Date()));
            hivService.setVoidedBy(Context.getAuthenticatedUser());
        }

        return hivService;
    }

    public Location getChosenLocation(Integer locationId){
        if (locationId != null) {
            return Context.getLocationService().getLocation(locationId);
        } else {
            return Context.getLocationService().getLocation(Context.getAdministrationService().getGlobalProperty("default_location"));
        }
    }

    public void getPrenatalConsultation(Prenatal consultation) {
        this.setConsultationId(consultation.getConsultationId());
        this.setConsultationDate(consultation.getConsultationDate());
        this.setPrenatalRank(consultation.getRank());
        this.setWeekOfAmenorrhea(consultation.getWeekOfAmenorrhea());
        this.setAppointmentDate(consultation.getAppointmentDate());
        this.setHivService(consultation.getHivService());
        this.setPregnantPatient(consultation.getPregnantPatient());
        if(consultation.getConsultationId() == null) {
            this.setSpousalScreening(consultation.getSpousalScreening());
            this.setSpousalScreeningResult(consultation.getSpousalScreeningResult());
        }
    }

    public Prenatal setPrenatalConsultationValues(Prenatal consultation) {

        consultation.setConsultationId(this.getConsultationId());
        consultation.setConsultationDate(this.getConsultationDate());
        consultation.setRank(this.getPrenatalRank());
        consultation.setWeekOfAmenorrhea(this.getWeekOfAmenorrhea());
        consultation.setAppointmentDate(this.getAppointmentDate());
        consultation.setSpousalScreening(this.getSpousalScreening());
        consultation.setSpousalScreeningResult(this.getSpousalScreeningResult());
//        consultation.setHivService(this.getHivService());
//        consultation.setPregnantPatient(this.getPregnantPatient());
        if (consultation.getConsultationId() != null) {
            consultation.setDateChanged(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
            consultation.setChangedBy(Context.getAuthenticatedUser());
        } else {
            consultation.setCreator(Context.getAuthenticatedUser());
            consultation.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (consultation.getLocation() == null){
            consultation.setLocation(getChosenLocation(null));
        }
        return consultation;
    }

    public void getPostnatalConsultation(Postnatal consultation) {
        this.setConsultationId(consultation.getConsultationId());
        this.setConsultationDate(consultation.getConsultationDate());
        this.setHivService(consultation.getHivService());
        this.setPregnantPatient(consultation.getPregnantPatient());
    }

    public Postnatal setPostnatalConsultationValues (Postnatal consultation) {
        consultation.setConsultationId(this.getConsultationId());
        consultation.setConsultationDate(this.getConsultationDate());
        if (consultation.getConsultationId() != null) {
            consultation.setDateChanged(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
            consultation.setChangedBy(Context.getAuthenticatedUser());
        } else {
            consultation.setCreator(Context.getAuthenticatedUser());
            consultation.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (consultation.getLocation() == null){
            consultation.setLocation(getChosenLocation(null));
        }
        return consultation;
    }

    public void getBirthConsultation(Birth consultation) {
        this.setConsultationId(consultation.getConsultationId());
        this.setConsultationDate(consultation.getConsultationDate());
        this.setHivService(consultation.getHivService());
        this.setPregnantPatient(consultation.getPregnantPatient());
        this.setDeliveryDate(consultation.getDeliveryDate());
        this.setHomeBirth(consultation.getHomeBirth());
        this.setPregnancyIssue(consultation.getPregnancyIssue());
        this.setChildState(consultation.getChildState());
    }

    public Birth setBirthConsultationValues(Birth consultation) {
        consultation.setConsultationId(this.getConsultationId());
        consultation.setConsultationDate(this.getConsultationDate());
        consultation.setChildState(this.getChildState());
        consultation.setPregnancyIssue(this.getPregnancyIssue());
        consultation.setHomeBirth(this.getHomeBirth());
        consultation.setDeliveryDate(this.getDeliveryDate());

        if (consultation.getConsultationId() != null) {
            consultation.setDateChanged(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
            consultation.setChangedBy(Context.getAuthenticatedUser());
        } else {
            consultation.setCreator(Context.getAuthenticatedUser());
            consultation.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (consultation.getLocation() == null){
            consultation.setLocation(getChosenLocation(null));
        }
        return consultation;
    }
}
