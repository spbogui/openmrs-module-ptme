package org.openmrs.module.ptme;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.openmrs.Location;

import javax.persistence.*;
import java.util.Date;

@XStreamAlias("motherFollowupVisit")
@Entity
@Table(name = "ptme_mother_followup_visit")
public class MotherFollowupVisit extends PreventTransmissionAbstract {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "mother_followup_visit_id")
    private Integer motherFollowupVisitId;
    @ManyToOne
    @JoinColumn(name = "mother_followup_id", nullable = false)
    private MotherFollowup motherFollowup;
    @Column(nullable = false, name = "visit_date")
    private Date visitDate;
    @Column(name = "gestational_age")
    private Integer gestationalAge;
    @Column(name = "continuing_arv")
    private Integer continuingArv;
    @Column(name = "continuing_ctx")
    private Integer continuingCtx;
    @ManyToOne
    @JoinColumn(nullable = false, name = "location_id")
    private Location location;

    @Override
    public Integer getId() {
        return getMotherFollowupVisitId();
    }

    @Override
    public void setId(Integer integer) {
        setMotherFollowupVisitId(integer);
    }

    public MotherFollowupVisit() {
    }

    public Integer getMotherFollowupVisitId() {
        return motherFollowupVisitId;
    }

    public void setMotherFollowupVisitId(Integer motherFollowupVisitId) {
        this.motherFollowupVisitId = motherFollowupVisitId;
    }

    public MotherFollowup getMotherFollowup() {
        return motherFollowup;
    }

    public void setMotherFollowup(MotherFollowup motherFollowup) {
        this.motherFollowup = motherFollowup;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
