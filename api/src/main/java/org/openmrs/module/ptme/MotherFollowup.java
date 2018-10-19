package org.openmrs.module.ptme;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.openmrs.Location;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@XStreamAlias("motherFollowup")
@Entity
@Table(name = "ptme_mother_followup")
public class MotherFollowup extends PreventTransmissionAbstract {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "mother_followup_id")
    private Integer motherFollowupId;
    @ManyToOne
    @JoinColumn(name = "pregnant_patient_id", nullable = false)
    private PregnantPatient pregnantPatient;
    @Column(nullable = false, name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(nullable = false, name = "arv_status_at_registering")
    private String arvStatusAtRegistering;
    @Column(name = "estimated_delivery_date")
    private Date estimatedDeliveryDate;
    @Column(name = "spousal_screening_result")
    private Integer spousalScreeningResult;
    @Column(name = "spousal_screening_date")
    private Date spousalScreeningDate;
    @Column(name = "pregnancy_outcome")
    private Integer pregnancyOutcome;
    @Column(name = "delivery_type")
    private Integer deliveryType;
    @ManyToOne
    @JoinColumn(nullable = false, name = "location_id")
    private Location location;
    @OneToMany(mappedBy = "motherFollowup")
    private Collection<MotherFollowupVisit> motherFollowupVisits;

    @Override
    public Integer getId() {
        return getMotherFollowupId();
    }

    @Override
    public void setId(Integer integer) {
        setMotherFollowupId(integer);
    }

    public MotherFollowup() {
    }

    public Integer getMotherFollowupId() {
        return motherFollowupId;
    }

    public void setMotherFollowupId(Integer motherFollowupId) {
        this.motherFollowupId = motherFollowupId;
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

    public PregnantPatient getPregnantPatient() {
        return pregnantPatient;
    }

    public void setPregnantPatient(PregnantPatient pregnantPatient) {
        this.pregnantPatient = pregnantPatient;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Collection<MotherFollowupVisit> getMotherFollowupVisits() {
        return motherFollowupVisits;
    }

    public void setMotherFollowupVisits(Collection<MotherFollowupVisit> motherFollowupVisits) {
        this.motherFollowupVisits = motherFollowupVisits;
    }
}
