package org.openmrs.module.ptme;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.openmrs.Location;

import javax.persistence.*;
import java.util.Date;

@XStreamAlias("childFollowupVisit")
@Entity
@Table(name = "ptme_child_followup_visit")
public class ChildFollowupVisit extends PreventTransmissionAbstract {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "child_followup_visit_id")
    private Integer childFollowupVisitId;
    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;
    @Column(nullable = false, name = "visit_date")
    private Date visitDate;
    @Column(name = "modern_contraceptive_method")
    private Boolean modernContraceptiveMethod;
    @Column(nullable = false, name = "age_in_day")
    private Integer ageInDay;
    @Column(nullable = false, name = "age_in_month")
    private Integer ageInMonth;
    @Column(nullable = false, name = "age_in_week")
    private Integer ageInWeek;
    @Column(nullable = false, name = "eating_type")
    private Integer eatingType;
    @Column(name = "continuing_ctx")
    private Integer continuingCtx;
    @Column(name = "continuing_inh")
    private Integer continuingInh;
    @ManyToOne
    @JoinColumn(nullable = false, name = "location_id")
    private Location location;

    @Override
    public Integer getId() {
        return getChildFollowupVisitId();
    }

    @Override
    public void setId(Integer integer) {
        setChildFollowupVisitId(integer);
    }

    public ChildFollowupVisit() {
    }

    public Integer getChildFollowupVisitId() {
        return childFollowupVisitId;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
