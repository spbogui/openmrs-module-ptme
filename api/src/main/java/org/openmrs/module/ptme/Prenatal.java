package org.openmrs.module.ptme;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;
import java.util.Date;

@XStreamAlias("prenatal")
@Entity
//@DiscriminatorValue("Prenatal")
@Table(name = "ptme_prenatal")
public class Prenatal extends Consultation {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 10, name = "rank")
    private String rank;

    @Column(nullable = false, name = "week_of_amenorrhea")
    private Integer weekOfAmenorrhea;

    @Column(name = "spousal_screening")
    private Integer spousalScreening;

    @Column(name = "spousal_screening_result")
    private Integer spousalScreeningResult;

    @Column(nullable = false, name = "appointment_date")
    private Date appointmentDate;

    public Prenatal() {
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getWeekOfAmenorrhea() {
        return weekOfAmenorrhea;
    }

    public void setWeekOfAmenorrhea(Integer weekOfAmenorrhea) {
        this.weekOfAmenorrhea = weekOfAmenorrhea;
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
}
