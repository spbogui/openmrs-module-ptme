package org.openmrs.module.ptme;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.openmrs.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@XStreamAlias("child")
@Entity
@Table(name = "ptme_child")
public class Child extends PreventTransmissionAbstract {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "child_id")
    private Integer childId;
    @ManyToOne
    @JoinColumn(name = "mother")
    private Patient mother;
    @Column(nullable = false, length = 15, unique = true, name = "child_followup_number")
    private String childFollowupNumber;
    @Column(nullable = false, name = "birth_date")
    private Date birthDate;
    @Column(nullable = false)
    private String gender;
    @Column(name = "family_name")
    private String familyName;
    @Column(name = "given_name")
    private String givenName;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(nullable = false, name = "location_id")
    private Location location;

    @XStreamImplicit
    @OneToMany(mappedBy = "child")
    private Collection<ChildFollowupVisit> childFollowupVisits;

    @OneToOne(mappedBy = "child")
    private ChildFollowup childFollowup;

    @Override
    public Integer getId() {
        return getChildId();
    }

    @Override
    public void setId(Integer integer) {
        setChildId(integer);
    }

    public Child() {
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public Patient getMother() {
        return mother;
    }

    public void setMother(Patient mother) {
        this.mother = mother;
    }

    public String getChildFollowupNumber() {
        return childFollowupNumber;
    }

    public void setChildFollowupNumber(String childFollowupNumber) {
        this.childFollowupNumber = childFollowupNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Collection<ChildFollowupVisit> getChildFollowupVisits() {
        return childFollowupVisits;
    }

    public void setChildFollowupVisits(Collection<ChildFollowupVisit> childFollowupVisits) {
        this.childFollowupVisits = childFollowupVisits;
    }

    public ChildFollowup getChildFollowup() {
        return childFollowup;
    }

    public void setChildFollowup(ChildFollowup childFollowup) {
        this.childFollowup = childFollowup;
    }
}
