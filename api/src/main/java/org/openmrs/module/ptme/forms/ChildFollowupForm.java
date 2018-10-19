package org.openmrs.module.ptme.forms;


import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.Child;
import org.openmrs.module.ptme.ChildFollowup;
import org.openmrs.module.ptme.ChildFollowupVisit;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.utils.UsefullFunction;

import java.util.Date;

public class ChildFollowupForm {

    private Integer childId;
    private Integer childFollowupId;
    private Integer arvProphylaxisGiven;
    private Date arvProphylaxisGivenDate;
    private Date pcr1SamplingDate;
    private Integer ageInMonthOnPcr1Sampling;
    private Integer ageInWeekOnPcr1Sampling;
    private Integer pcr1Result;
    private Date pcr2SamplingDate;
    private Integer ageInMonthOnPcr2Sampling;
    private Integer ageInWeekOnPcr2Sampling;
    private Integer pcr2Result;
    private Date pcr3SamplingDate;
    private Integer ageInMonthOnPcr3Sampling;
    private Integer ageInWeekOnPcr3Sampling;
    private Integer pcr3Result;
    private Date ctxInitiationDate;
    private Integer ageInMonthOnCtxInitiation;
    private Integer ageInWeekOnCtxInitiation;
    private Date inhInitiationDate;
    private Integer ageInMonthOnInhInitiation;
    private Integer ageInWeekOnInhInitiation;
    private Date hivSerology1Date;
    private Integer ageInMonthOnHivSerology1;
    private Integer ageInWeekOnHivSerology1;
    private Integer hivSerology1Result;
    private Date hivSerology2Date;
    private Integer ageInMonthOnHivSerology2;
    private Integer ageInWeekOnHivSerology2;
    private Integer hivSerology2Result;
    private Integer followupResult;
    private Date followupResultDate;
    private String referenceLocation;
    private String hivCareNumber;

    private Integer childFollowupVisitId;
    private Date visitDate;
    private Boolean modernContraceptiveMethod;
    private Integer ageInDay;
    private Integer ageInMonth;
    private Integer ageInWeek;
    private Integer eatingType;
    private Integer continuingCtx;
    private Integer continuingInh;

    public ChildFollowupForm() {
    }

    public Integer getChildFollowupId() {
        return childFollowupId;
    }

    public void setChildFollowupId(Integer childFollowupId) {
        this.childFollowupId = childFollowupId;
    }

    public Integer getArvProphylaxisGiven() {
        return arvProphylaxisGiven;
    }

    public void setArvProphylaxisGiven(Integer arvProphylaxisGiven) {
        this.arvProphylaxisGiven = arvProphylaxisGiven;
    }

    public Date getArvProphylaxisGivenDate() {
        return arvProphylaxisGivenDate;
    }

    public void setArvProphylaxisGivenDate(Date arvProphylaxisGivenDate) {
        this.arvProphylaxisGivenDate = arvProphylaxisGivenDate;
    }

    public Date getPcr1SamplingDate() {
        return pcr1SamplingDate;
    }

    public void setPcr1SamplingDate(Date pcr1SamplingDate) {
        this.pcr1SamplingDate = pcr1SamplingDate;
    }

    public Integer getAgeInMonthOnPcr1Sampling() {
        return ageInMonthOnPcr1Sampling;
    }

    public void setAgeInMonthOnPcr1Sampling(Integer ageInMonthOnPcr1Sampling) {
        this.ageInMonthOnPcr1Sampling = ageInMonthOnPcr1Sampling;
    }

    public Integer getAgeInWeekOnPcr1Sampling() {
        return ageInWeekOnPcr1Sampling;
    }

    public void setAgeInWeekOnPcr1Sampling(Integer ageInWeekOnPcr1Sampling) {
        this.ageInWeekOnPcr1Sampling = ageInWeekOnPcr1Sampling;
    }

    public Integer getPcr1Result() {
        return pcr1Result;
    }

    public void setPcr1Result(Integer pcr1Result) {
        this.pcr1Result = pcr1Result;
    }

    public Date getPcr2SamplingDate() {
        return pcr2SamplingDate;
    }

    public void setPcr2SamplingDate(Date pcr2SamplingDate) {
        this.pcr2SamplingDate = pcr2SamplingDate;
    }

    public Integer getAgeInMonthOnPcr2Sampling() {
        return ageInMonthOnPcr2Sampling;
    }

    public void setAgeInMonthOnPcr2Sampling(Integer ageInMonthOnPcr2Sampling) {
        this.ageInMonthOnPcr2Sampling = ageInMonthOnPcr2Sampling;
    }

    public Integer getAgeInWeekOnPcr2Sampling() {
        return ageInWeekOnPcr2Sampling;
    }

    public void setAgeInWeekOnPcr2Sampling(Integer ageInWeekOnPcr2Sampling) {
        this.ageInWeekOnPcr2Sampling = ageInWeekOnPcr2Sampling;
    }

    public Integer getPcr2Result() {
        return pcr2Result;
    }

    public void setPcr2Result(Integer pcr2Result) {
        this.pcr2Result = pcr2Result;
    }

    public Date getPcr3SamplingDate() {
        return pcr3SamplingDate;
    }

    public void setPcr3SamplingDate(Date pcr3SamplingDate) {
        this.pcr3SamplingDate = pcr3SamplingDate;
    }

    public Integer getAgeInMonthOnPcr3Sampling() {
        return ageInMonthOnPcr3Sampling;
    }

    public void setAgeInMonthOnPcr3Sampling(Integer ageInMonthOnPcr3Sampling) {
        this.ageInMonthOnPcr3Sampling = ageInMonthOnPcr3Sampling;
    }

    public Integer getAgeInWeekOnPcr3Sampling() {
        return ageInWeekOnPcr3Sampling;
    }

    public void setAgeInWeekOnPcr3Sampling(Integer ageInWeekOnPcr3Sampling) {
        this.ageInWeekOnPcr3Sampling = ageInWeekOnPcr3Sampling;
    }

    public Integer getPcr3Result() {
        return pcr3Result;
    }

    public void setPcr3Result(Integer pcr3Result) {
        this.pcr3Result = pcr3Result;
    }

    public Date getCtxInitiationDate() {
        return ctxInitiationDate;
    }

    public void setCtxInitiationDate(Date ctxInitiationDate) {
        this.ctxInitiationDate = ctxInitiationDate;
    }

    public Integer getAgeInMonthOnCtxInitiation() {
        return ageInMonthOnCtxInitiation;
    }

    public void setAgeInMonthOnCtxInitiation(Integer ageInMonthOnCtxInitiation) {
        this.ageInMonthOnCtxInitiation = ageInMonthOnCtxInitiation;
    }

    public Integer getAgeInWeekOnCtxInitiation() {
        return ageInWeekOnCtxInitiation;
    }

    public void setAgeInWeekOnCtxInitiation(Integer ageInWeekOnCtxInitiation) {
        this.ageInWeekOnCtxInitiation = ageInWeekOnCtxInitiation;
    }

    public Date getInhInitiationDate() {
        return inhInitiationDate;
    }

    public void setInhInitiationDate(Date inhInitiationDate) {
        this.inhInitiationDate = inhInitiationDate;
    }

    public Integer getAgeInMonthOnInhInitiation() {
        return ageInMonthOnInhInitiation;
    }

    public void setAgeInMonthOnInhInitiation(Integer ageInMonthOnInhInitiation) {
        this.ageInMonthOnInhInitiation = ageInMonthOnInhInitiation;
    }

    public Integer getAgeInWeekOnInhInitiation() {
        return ageInWeekOnInhInitiation;
    }

    public void setAgeInWeekOnInhInitiation(Integer ageInWeekOnInhInitiation) {
        this.ageInWeekOnInhInitiation = ageInWeekOnInhInitiation;
    }

    public Date getHivSerology1Date() {
        return hivSerology1Date;
    }

    public void setHivSerology1Date(Date hivSerology1Date) {
        this.hivSerology1Date = hivSerology1Date;
    }

    public Integer getAgeInMonthOnHivSerology1() {
        return ageInMonthOnHivSerology1;
    }

    public void setAgeInMonthOnHivSerology1(Integer ageInMonthOnHivSerology1) {
        this.ageInMonthOnHivSerology1 = ageInMonthOnHivSerology1;
    }

    public Integer getAgeInWeekOnHivSerology1() {
        return ageInWeekOnHivSerology1;
    }

    public void setAgeInWeekOnHivSerology1(Integer ageInWeekOnHivSerology1) {
        this.ageInWeekOnHivSerology1 = ageInWeekOnHivSerology1;
    }

    public Integer getHivSerology1Result() {
        return hivSerology1Result;
    }

    public void setHivSerology1Result(Integer hivSerology1Result) {
        this.hivSerology1Result = hivSerology1Result;
    }

    public Date getHivSerology2Date() {
        return hivSerology2Date;
    }

    public void setHivSerology2Date(Date hivSerology2Date) {
        this.hivSerology2Date = hivSerology2Date;
    }

    public Integer getAgeInMonthOnHivSerology2() {
        return ageInMonthOnHivSerology2;
    }

    public void setAgeInMonthOnHivSerology2(Integer ageInMonthOnHivSerology2) {
        this.ageInMonthOnHivSerology2 = ageInMonthOnHivSerology2;
    }

    public Integer getAgeInWeekOnHivSerology2() {
        return ageInWeekOnHivSerology2;
    }

    public void setAgeInWeekOnHivSerology2(Integer ageInWeekOnHivSerology2) {
        this.ageInWeekOnHivSerology2 = ageInWeekOnHivSerology2;
    }

    public Integer getHivSerology2Result() {
        return hivSerology2Result;
    }

    public void setHivSerology2Result(Integer hivSerology2Result) {
        this.hivSerology2Result = hivSerology2Result;
    }

    public Integer getFollowupResult() {
        return followupResult;
    }

    public void setFollowupResult(Integer followupResult) {
        this.followupResult = followupResult;
    }

    public Date getFollowupResultDate() {
        return followupResultDate;
    }

    public void setFollowupResultDate(Date followupResultDate) {
        this.followupResultDate = followupResultDate;
    }

    public String getReferenceLocation() {
        return referenceLocation;
    }

    public void setReferenceLocation(String referenceLocation) {
        this.referenceLocation = referenceLocation;
    }

    public String getHivCareNumber() {
        return hivCareNumber;
    }

    public void setHivCareNumber(String hivCareNumber) {
        this.hivCareNumber = hivCareNumber;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public Integer getChildFollowupVisitId() {
        return childFollowupVisitId;
    }

    public void setChildFollowupVisitId(Integer childFollowupVisitId) {
        this.childFollowupVisitId = childFollowupVisitId;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Boolean getModernContraceptiveMethod() {
        return modernContraceptiveMethod;
    }

    public void setModernContraceptiveMethod(Boolean modernContraceptiveMethod) {
        this.modernContraceptiveMethod = modernContraceptiveMethod;
    }

    public Integer getAgeInDay() {
        return ageInDay;
    }

    public void setAgeInDay(Integer ageInDay) {
        this.ageInDay = ageInDay;
    }

    public Integer getAgeInMonth() {
        return ageInMonth;
    }

    public void setAgeInMonth(Integer ageInMonth) {
        this.ageInMonth = ageInMonth;
    }

    public Integer getAgeInWeek() {
        return ageInWeek;
    }

    public void setAgeInWeek(Integer ageInWeek) {
        this.ageInWeek = ageInWeek;
    }

    public Integer getEatingType() {
        return eatingType;
    }

    public void setEatingType(Integer eatingType) {
        this.eatingType = eatingType;
    }

    public Integer getContinuingCtx() {
        return continuingCtx;
    }

    public void setContinuingCtx(Integer continuingCtx) {
        this.continuingCtx = continuingCtx;
    }

    public Integer getContinuingInh() {
        return continuingInh;
    }

    public void setContinuingInh(Integer continuingInh) {
        this.continuingInh = continuingInh;
    }

    public void setChildFollowup(ChildFollowup childFollowup) {
        if (childFollowup.getChild() != null) {
            this.setChildId(childFollowup.getChild().getChildId());
            if(childFollowup.getChild().getPatient() != null) {
                this.setHivCareNumber(childFollowup.getChild().getPatient().getPatientIdentifier().getIdentifier());
            }
        }
        this.setChildFollowupId(childFollowup.getChildFollowupId());
        this.setArvProphylaxisGiven(childFollowup.getArvProphylaxisGiven());
        this.setArvProphylaxisGivenDate(childFollowup.getArvProphylaxisGivenDate());
        this.setPcr1SamplingDate(childFollowup.getPcr1SamplingDate());
        this.setAgeInWeekOnPcr1Sampling(childFollowup.getAgeInWeekOnPcr1Sampling());
        this.setAgeInMonthOnPcr1Sampling(childFollowup.getAgeInMonthOnPcr1Sampling());
        this.setPcr1Result(childFollowup.getPcr1Result());
        this.setPcr2SamplingDate(childFollowup.getPcr2SamplingDate());
        this.setAgeInWeekOnPcr2Sampling(childFollowup.getAgeInWeekOnPcr2Sampling());
        this.setAgeInMonthOnPcr2Sampling(childFollowup.getAgeInMonthOnPcr2Sampling());
        this.setPcr2Result(childFollowup.getPcr2Result());
        this.setPcr3SamplingDate(childFollowup.getPcr3SamplingDate());
        this.setAgeInWeekOnPcr3Sampling(childFollowup.getAgeInWeekOnPcr3Sampling());
        this.setAgeInMonthOnPcr3Sampling(childFollowup.getAgeInMonthOnPcr3Sampling());
        this.setPcr3Result(childFollowup.getPcr3Result());
        this.setCtxInitiationDate(childFollowup.getCtxInitiationDate());
        this.setAgeInMonthOnCtxInitiation(childFollowup.getAgeInMonthOnCtxInitiation());
        this.setAgeInWeekOnCtxInitiation(childFollowup.getAgeInWeekOnCtxInitiation());
        this.setInhInitiationDate(childFollowup.getInhInitiationDate());
        this.setAgeInMonthOnInhInitiation(childFollowup.getAgeInMonthOnInhInitiation());
        this.setAgeInWeekOnInhInitiation(childFollowup.getAgeInWeekOnInhInitiation());
        this.setHivSerology1Date(childFollowup.getHivSerology1Date());
        this.setAgeInMonthOnHivSerology1(childFollowup.getAgeInMonthOnHivSerology1());
        this.setAgeInWeekOnHivSerology1(childFollowup.getAgeInWeekOnHivSerology1());
        this.setHivSerology1Result(childFollowup.getHivSerology1Result());
        this.setHivSerology2Date(childFollowup.getHivSerology2Date());
        this.setAgeInMonthOnHivSerology2(childFollowup.getAgeInMonthOnHivSerology2());
        this.setAgeInWeekOnHivSerology2(childFollowup.getAgeInWeekOnHivSerology2());
        this.setHivSerology2Result(childFollowup.getHivSerology2Result());
        this.setFollowupResult(childFollowup.getFollowupResult());
        this.setFollowupResultDate(childFollowup.getFollowupResultDate());
        this.setReferenceLocation(childFollowup.getReferenceLocation());
    }

    public ChildFollowup getChildFollowup(ChildFollowup childFollowup) {
//        childFollowup.setChild(this.getChild());
        childFollowup.setChildFollowupId(this.getChildFollowupId());
        childFollowup.setArvProphylaxisGiven(this.getArvProphylaxisGiven());
        childFollowup.setArvProphylaxisGivenDate(this.getArvProphylaxisGivenDate());
        childFollowup.setPcr1SamplingDate(this.getPcr1SamplingDate());
        childFollowup.setAgeInWeekOnPcr1Sampling(this.getAgeInWeekOnPcr1Sampling());
        childFollowup.setAgeInMonthOnPcr1Sampling(this.getAgeInMonthOnPcr1Sampling());
        childFollowup.setPcr1Result(this.getPcr1Result());
        childFollowup.setPcr2SamplingDate(this.getPcr2SamplingDate());
        childFollowup.setAgeInWeekOnPcr2Sampling(this.getAgeInWeekOnPcr2Sampling());
        childFollowup.setAgeInMonthOnPcr2Sampling(this.getAgeInMonthOnPcr2Sampling());
        childFollowup.setPcr2Result(this.getPcr2Result());
        childFollowup.setPcr3SamplingDate(this.getPcr3SamplingDate());
        childFollowup.setAgeInWeekOnPcr3Sampling(this.getAgeInWeekOnPcr3Sampling());
        childFollowup.setAgeInMonthOnPcr3Sampling(this.getAgeInMonthOnPcr3Sampling());
        childFollowup.setPcr3Result(this.getPcr3Result());
        childFollowup.setCtxInitiationDate(this.getCtxInitiationDate());
        childFollowup.setAgeInMonthOnCtxInitiation(this.getAgeInMonthOnCtxInitiation());
        childFollowup.setAgeInWeekOnCtxInitiation(this.getAgeInWeekOnCtxInitiation());
        childFollowup.setInhInitiationDate(this.getInhInitiationDate());
        childFollowup.setAgeInMonthOnInhInitiation(this.getAgeInMonthOnInhInitiation());
        childFollowup.setAgeInWeekOnInhInitiation(this.getAgeInWeekOnInhInitiation());
        childFollowup.setHivSerology1Date(this.getHivSerology1Date());
        childFollowup.setAgeInMonthOnHivSerology1(this.getAgeInMonthOnHivSerology1());
        childFollowup.setAgeInWeekOnHivSerology1(this.getAgeInWeekOnHivSerology1());
        childFollowup.setHivSerology1Result(this.getHivSerology1Result());
        childFollowup.setHivSerology2Date(this.getHivSerology2Date());
        childFollowup.setAgeInMonthOnHivSerology2(this.getAgeInMonthOnHivSerology2());
        childFollowup.setAgeInWeekOnHivSerology2(this.getAgeInWeekOnHivSerology2());
        childFollowup.setHivSerology2Result(this.getHivSerology2Result());
        childFollowup.setFollowupResult(this.getFollowupResult());
        childFollowup.setFollowupResultDate(this.getFollowupResultDate());
        childFollowup.setReferenceLocation(this.getReferenceLocation());

//        if(!this.getHivCareNumber().isEmpty()){
//            Child child = childFollowup.getChild();
//            if(child.getPatient() == null){
//                child.setPatient(Context.getService(PreventTransmissionService.class).getPatientByIdentifier(this.getHivCareNumber()));
//                Context.getService(PreventTransmissionService.class).saveChild(child);
//            }
//        }

        if (childFollowup.getCreator() == null){
            childFollowup.setCreator(Context.getAuthenticatedUser());
            childFollowup.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }

        if (this.getChildId() != null) {
            childFollowup.setChangedBy(Context.getAuthenticatedUser());
            childFollowup.setDateChanged(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if(childFollowup.getVoided()) {
            childFollowup.setVoidedBy(Context.getAuthenticatedUser());
            childFollowup.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if(childFollowup.getLocation() == null) {
            childFollowup.setLocation(getChosenLocation(null));
        }
        return childFollowup;
    }

    public void setChildFollowupVisit(ChildFollowupVisit childFollowupVisit) {
        this.setChildFollowupVisitId(childFollowupVisit.getChildFollowupVisitId());
        this.setVisitDate(childFollowupVisit.getVisitDate());
        this.setModernContraceptiveMethod(childFollowupVisit.getModernContraceptiveMethod());
        this.setAgeInDay(childFollowupVisit.getAgeInDay());
        this.setAgeInMonth(childFollowupVisit.getAgeInMonth());
        this.setAgeInWeek(childFollowupVisit.getAgeInWeek());
        this.setEatingType(childFollowupVisit.getEatingType());
        this.setContinuingCtx(childFollowupVisit.getContinuingCtx());
        this.setContinuingInh(childFollowupVisit.getContinuingInh());
    }

    public ChildFollowupVisit getChildFollowupVisit(ChildFollowupVisit childFollowupVisit) {

        childFollowupVisit.setChildFollowupVisitId(this.getChildFollowupVisitId());
        childFollowupVisit.setVisitDate(this.getVisitDate());
        childFollowupVisit.setModernContraceptiveMethod(this.getModernContraceptiveMethod());
        childFollowupVisit.setAgeInDay(this.getAgeInDay());
        childFollowupVisit.setAgeInMonth(this.getAgeInMonth());
        childFollowupVisit.setAgeInWeek(this.getAgeInWeek());
        childFollowupVisit.setEatingType(this.getEatingType());
        childFollowupVisit.setContinuingCtx(this.getContinuingCtx());
        childFollowupVisit.setContinuingInh(this.getContinuingInh());

        if (childFollowupVisit.getCreator() == null){
            childFollowupVisit.setCreator(Context.getAuthenticatedUser());
            childFollowupVisit.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (this.getChildId() != null) {
            childFollowupVisit.setChangedBy(Context.getAuthenticatedUser());
            childFollowupVisit.setDateChanged(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if(childFollowupVisit.getVoided()) {
            childFollowupVisit.setVoidedBy(Context.getAuthenticatedUser());
            childFollowupVisit.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if(childFollowupVisit.getLocation() == null) {
            childFollowupVisit.setLocation(getChosenLocation(null));
        }
        return childFollowupVisit;
    }

    public Location getChosenLocation(Integer locationId){
        if (locationId != null) {
            return Context.getLocationService().getLocation(locationId);
        } else {
            return Context.getLocationService().getLocation(Context.getAdministrationService().getGlobalProperty("default_location"));
        }
    }
}
