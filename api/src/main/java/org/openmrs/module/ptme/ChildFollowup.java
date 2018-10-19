package org.openmrs.module.ptme;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.openmrs.Location;

import javax.persistence.*;
import java.util.Date;

@XStreamAlias("childFollowup")
@Entity
@Table(name = "ptme_child_followup")
public class ChildFollowup extends PreventTransmissionAbstract {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer childFollowupId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "child_followup_id", nullable = false)
    private Child child;
    @Column(name = "arv_prophylaxis_given")
    private Integer arvProphylaxisGiven;
    @Column(name = "arv_prophylaxis_given_date")
    private Date arvProphylaxisGivenDate;
    @Column(name = "pcr1_sampling_date")
    private Date pcr1SamplingDate;
    @Column(name = "age_in_month_on_pcr1_sampling")
    private Integer ageInMonthOnPcr1Sampling;
    @Column(name = "age_in_week_on_pcr1_sampling")
    private Integer ageInWeekOnPcr1Sampling;
    @Column(name = "pcr1_result")
    private Integer pcr1Result;
    @Column(name = "pcr2_sampling_date")
    private Date pcr2SamplingDate;
    @Column(name = "age_in_month_on_pcr2_sampling")
    private Integer ageInMonthOnPcr2Sampling;
    @Column(name = "age_in_week_on_pcr2_sampling")
    private Integer ageInWeekOnPcr2Sampling;
    @Column(name = "pcr2_result")
    private Integer pcr2Result;
    @Column(name = "pcr3_sampling_date")
    private Date pcr3SamplingDate;
    @Column(name = "age_in_month_on_pcr3_sampling")
    private Integer ageInMonthOnPcr3Sampling;
    @Column(name = "age_in_week_on_pcr3_sampling")
    private Integer ageInWeekOnPcr3Sampling;
    @Column(name = "pcr3_result")
    private Integer pcr3Result;
    @Column(name = "ctx_initiation_date")
    private Date ctxInitiationDate;
    @Column(name = "age_in_month_on_ctx_initiation")
    private Integer ageInMonthOnCtxInitiation;
    @Column(name = "age_in_week_on_ctx_initiation")
    private Integer ageInWeekOnCtxInitiation;
    @Column(name = "inh_initiation_date")
    private Date inhInitiationDate;
    @Column(name = "age_in_month_on_inh_initiation")
    private Integer ageInMonthOnInhInitiation;
    @Column(name = "age_in_week_on_inh_initiation")
    private Integer ageInWeekOnInhInitiation;
    @Column(name = "hiv_serology1_date")
    private Date hivSerology1Date;
    @Column(name = "age_in_month_on_hiv_serology1")
    private Integer ageInMonthOnHivSerology1;
    @Column(name = "age_in_week_on_hiv_serology1")
    private Integer ageInWeekOnHivSerology1;
    @Column(name = "hiv_serology1_result")
    private Integer hivSerology1Result;
    @Column(name = "hiv_serology2_date")
    private Date hivSerology2Date;
    @Column(name = "age_in_month_on_hiv_serology2")
    private Integer ageInMonthOnHivSerology2;
    @Column(name = "age_in_week_on_hiv_serology2")
    private Integer ageInWeekOnHivSerology2;
    @Column(name = "hiv_serology2_result")
    private Integer hivSerology2Result;
    @Column(name = "followup_result")
    private Integer followupResult;
    @Column(name = "followup_result_date")
    private Date followupResultDate;
    @Column(name = "reference_location")
    private String referenceLocation;
    @ManyToOne
    @JoinColumn(nullable = false, name = "location_id")
    private Location location;

    @Override
    public Integer getId() {
        return getChildFollowupId();
    }

    @Override
    public void setId(Integer integer) {
        setChildFollowupId(integer);
    }

    public Integer getChildFollowupId() {
        return childFollowupId;
    }

    public void setChildFollowupId(Integer childFollowupId) {
        this.childFollowupId = childFollowupId;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
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

    public Integer getAgeInWeekOnHivSerology1() {
        return ageInWeekOnHivSerology1;
    }

    public void setAgeInWeekOnHivSerology1(Integer ageInWeekOnHivSerology1) {
        this.ageInWeekOnHivSerology1 = ageInWeekOnHivSerology1;
    }

    public Integer getAgeInMonthOnHivSerology1() {
        return ageInMonthOnHivSerology1;
    }

    public void setAgeInMonthOnHivSerology1(Integer ageInMonthOnHivSerology1) {
        this.ageInMonthOnHivSerology1 = ageInMonthOnHivSerology1;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getReferenceLocation() {
        return referenceLocation;
    }

    public void setReferenceLocation(String referenceLocation) {
        this.referenceLocation = referenceLocation;
    }
}
