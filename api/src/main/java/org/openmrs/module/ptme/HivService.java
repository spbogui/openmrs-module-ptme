package org.openmrs.module.ptme;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.openmrs.Location;

import javax.persistence.*;
import java.util.UUID;

@XStreamAlias("hivService")
@Entity
@Table(name = "ptme_hiv_service")
public class HivService extends PreventTransmissionAbstract {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "hiv_service_id")
    private Integer hivServiceId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "hiv_service_id", nullable = false)
    private Consultation consultation;

    @Column(nullable = false, name = "hiv_status_at_reception")
    private Integer hivStatusAtReception;
    @Column(nullable = false, name = "test_proposal")
    private Integer testProposal;
    @Column(nullable = false, name = "test_result")
    private Integer testResult;
    @Column(nullable = false, name = "result_announcement")
    private Integer resultAnnouncement;
    @Column(nullable = false, name = "arv_discount")
    private Integer arvDiscount;
    @Column(name = "child_arv_prophylaxis")
    private Integer childArvProphylaxis;
    @Column(name = "arv_status")
    private Integer arvStatus;
    @Column(name = "arv_treatment")
    private Integer arvTreatment;
//    @Column(name = "spousal_screening")
//    private Integer spousalScreening;
//    @Column(name = "spousal_screening_result")
//    private Integer spousalScreeningResult;
    @ManyToOne
    @JoinColumn(nullable = false, name = "location_id")
    private Location location;

    @Override
    public Integer getId() {
        return getHivServiceId();
    }

    @Override
    public void setId(Integer integer) {
        setHivServiceId(integer);
    }

    public HivService() {
        setUuid(UUID.randomUUID().toString());
    }

    public Integer getHivServiceId() {
        return hivServiceId;
    }

    public void setHivServiceId(Integer hivServiceId) {
        this.hivServiceId = hivServiceId;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
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

    public Integer getChildArvProphylaxis() {
        return childArvProphylaxis;
    }

    public void setChildArvProphylaxis(Integer childArvProphylaxis) {
        this.childArvProphylaxis = childArvProphylaxis;
    }

    public Integer getArvStatus() {
        return arvStatus;
    }

    public void setArvStatus(Integer arvStatus) {
        this.arvStatus = arvStatus;
    }

    public Integer getArvTreatment() {
        return arvTreatment;
    }

    public void setArvTreatment(Integer arvTreatment) {
        this.arvTreatment = arvTreatment;
    }

//    public Integer getSpousalScreening() {
//        return spousalScreening;
//    }
//
//    public void setSpousalScreening(Integer spousalScreening) {
//        this.spousalScreening = spousalScreening;
//    }
//
//    public Integer getSpousalScreeningResult() {
//        return spousalScreeningResult;
//    }
//
//    public void setSpousalScreeningResult(Integer spousalScreeningResult) {
//        this.spousalScreeningResult = spousalScreeningResult;
//    }

    public Integer getHivStatusAtReception() {
        return hivStatusAtReception;
    }

    public void setHivStatusAtReception(Integer hivStatusAtReception) {
        this.hivStatusAtReception = hivStatusAtReception;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
