package org.openmrs.module.ptme;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;
import java.util.Date;

@XStreamAlias("birthConsultation")
@Entity
//@DiscriminatorValue("Birth")
@Table(name = "ptme_birth")
public class Birth extends Consultation {

    private static final long serialVersionUID = 1L;

    @Column(name = "delivery_date", nullable = false)
    private Date deliveryDate;
    @Column(name = "home_birth", nullable = false)
    private Boolean homeBirth;
    @Column(name = "pregnancy_issue")
    private Integer pregnancyIssue;
    @Column(name = "child_state")
    private Integer childState;

    public Birth() {
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Boolean getHomeBirth() {
        return homeBirth;
    }

    public void setHomeBirth(Boolean homeBirth) {
        this.homeBirth = homeBirth;
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
}
