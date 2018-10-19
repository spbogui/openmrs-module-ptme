package org.openmrs.module.ptme.forms;

import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.MotherFollowup;
import org.openmrs.module.ptme.MotherFollowupVisit;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.utils.UsefullFunction;

import java.util.Date;

public class MotherFollowupForm {

    private Integer motherFollowupId;
    private Integer pregnantPatientId;
    private Date startDate;
    private Date endDate;
    private String arvStatusAtRegistering;
    private Date estimatedDeliveryDate;
    private Integer pregnancyOutcome;
    private Integer deliveryType;
    private Integer spousalScreeningResult;
    private Date spousalScreeningDate;

    private Integer motherFollowupVisitId;
    private Date visitDate;
    private Integer gestationalAge;
    private Integer continuingArv;
    private Integer continuingCtx;

    public MotherFollowupForm() {
    }

    public Integer getMotherFollowupId() {
        return motherFollowupId;
    }

    public void setMotherFollowupId(Integer motherFollowupId) {
        this.motherFollowupId = motherFollowupId;
    }

    public Integer getPregnantPatientId() {
        return pregnantPatientId;
    }

    public void setPregnantPatientId(Integer pregnantPatientId) {
        this.pregnantPatientId = pregnantPatientId;
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

    public String getArvStatusAtRegistering() {
        return arvStatusAtRegistering;
    }

    public void setArvStatusAtRegistering(String arvStatusAtRegistering) {
        this.arvStatusAtRegistering = arvStatusAtRegistering;
    }

    public Date getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
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

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getMotherFollowupVisitId() {
        return motherFollowupVisitId;
    }

    public void setMotherFollowupVisitId(Integer motherFollowupVisitId) {
        this.motherFollowupVisitId = motherFollowupVisitId;
    }

    public Integer getGestationalAge() {
        return gestationalAge;
    }

    public void setGestationalAge(Integer gestationalAge) {
        this.gestationalAge = gestationalAge;
    }

    public Integer getContinuingArv() {
        return continuingArv;
    }

    public void setContinuingArv(Integer continuingArv) {
        this.continuingArv = continuingArv;
    }

    public Integer getContinuingCtx() {
        return continuingCtx;
    }

    public void setContinuingCtx(Integer continuingCtx) {
        this.continuingCtx = continuingCtx;
    }

    public MotherFollowup getMotherFollowup(MotherFollowup motherFollowup) {
        motherFollowup.setMotherFollowupId(this.getMotherFollowupId());
        motherFollowup.setPregnantPatient(Context.getService(PreventTransmissionService.class).getPregnantPatientById(this.getPregnantPatientId()));
        if (this.getVisitDate() != null) {
            if(motherFollowup.getStartDate() == null) {
                motherFollowup.setStartDate(this.getVisitDate());
                motherFollowup.setEndDate(this.getVisitDate());
            } else {
                if (this.getVisitDate().before(motherFollowup.getStartDate())) {
                    motherFollowup.setStartDate(this.getVisitDate());
                } else if (this.getVisitDate().after(motherFollowup.getEndDate())) {
                    motherFollowup.setEndDate(this.getVisitDate());
                }
            }
        }

//        if (this.getPregnancyOutcome() != null){
//            motherFollowup.setEndDate(this.getVisitDate());
//        }

        motherFollowup.setArvStatusAtRegistering(this.getArvStatusAtRegistering());
        motherFollowup.setEstimatedDeliveryDate(this.getEstimatedDeliveryDate());
        motherFollowup.setPregnancyOutcome(this.getPregnancyOutcome());
        motherFollowup.setDeliveryType(this.getDeliveryType());
        motherFollowup.setSpousalScreeningResult(this.getSpousalScreeningResult());
        motherFollowup.setSpousalScreeningDate(this.getSpousalScreeningDate());
        if(motherFollowup.getCreator() == null) {
            motherFollowup.setCreator(Context.getAuthenticatedUser());
            motherFollowup.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (motherFollowup.getMotherFollowupId() != null) {
            motherFollowup.setChangedBy(Context.getAuthenticatedUser());
            motherFollowup.setDateChanged(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (motherFollowup.getLocation() == null) {
            motherFollowup.setLocation(getChosenLocation(null));
        }

        return motherFollowup;
    }

    public MotherFollowupVisit getMotherFollowupVisit(MotherFollowupVisit motherFollowupVisit) {
        motherFollowupVisit.setMotherFollowupVisitId(this.getMotherFollowupVisitId());
        motherFollowupVisit.setVisitDate(this.getVisitDate());
        if(this.getGestationalAge() != null)
            motherFollowupVisit.setGestationalAge(this.getGestationalAge());
        if(this.getContinuingArv() != null)
            motherFollowupVisit.setContinuingArv(this.getContinuingArv());
        if(this.getContinuingCtx() != null)
            motherFollowupVisit.setContinuingCtx(this.getContinuingCtx());

        if(motherFollowupVisit.getCreator() == null) {
            motherFollowupVisit.setCreator(Context.getAuthenticatedUser());
            motherFollowupVisit.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (motherFollowupVisit.getMotherFollowupVisitId() != null) {
            motherFollowupVisit.setChangedBy(Context.getAuthenticatedUser());
            motherFollowupVisit.setDateChanged(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (motherFollowupVisit.getLocation() == null) {
            motherFollowupVisit.setLocation(getChosenLocation(null));
        }
        return motherFollowupVisit;
    }

    public Location getChosenLocation(Integer locationId){
        if (locationId != null) {
            return Context.getLocationService().getLocation(locationId);
        } else {
            return Context.getLocationService().getLocation(Context.getAdministrationService().getGlobalProperty("default_location"));
        }
    }

    public void setMotherFollowup(MotherFollowup motherFollowup) {
        this.setMotherFollowupId(motherFollowup.getMotherFollowupId());
        this.setPregnantPatientId(motherFollowup.getPregnantPatient().getPregnantPatientId());
        this.setStartDate(motherFollowup.getStartDate());
        if (endDate != null){
            this.setEndDate(motherFollowup.getEndDate());
        }
        this.setArvStatusAtRegistering(motherFollowup.getArvStatusAtRegistering());
        this.setEstimatedDeliveryDate(motherFollowup.getEstimatedDeliveryDate());
        this.setPregnancyOutcome(motherFollowup.getPregnancyOutcome());
        this.setDeliveryType(motherFollowup.getDeliveryType());
        this.setSpousalScreeningResult(motherFollowup.getSpousalScreeningResult());
        this.setSpousalScreeningDate(motherFollowup.getSpousalScreeningDate());
    }
}
