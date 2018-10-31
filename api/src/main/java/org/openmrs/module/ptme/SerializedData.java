package org.openmrs.module.ptme;

import org.openmrs.BaseOpenmrsObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "SerializedData")
@Table(name = "ptme_serialized_data")
public class SerializedData extends BaseOpenmrsObject implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "serialized_id")
    private Integer serializedId;
    @Column(name = "status")
    private String status;
    @Column(name = "serialized_xml_data")
    private String serializedXmlDate;
    @Column(name = "date_created")
    private Date dateCreated;
    @Column(name = "date_sent")
    private Date dateSent;
    @Column(name = "date_received")
    private Date dateReceived;

    @Override
    public Integer getId() {
        return getSerializedId();
    }

    @Override
    public void setId(Integer integer) {
        setSerializedId(integer);
    }

    public SerializedData() {
    }

    public Integer getSerializedId() {
        return serializedId;
    }

    public void setSerializedId(Integer serializedId) {
        this.serializedId = serializedId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSerializedXmlDate() {
        return serializedXmlDate;
    }

    public void setSerializedXmlDate(String serializedXmlDate) {
        this.serializedXmlDate = serializedXmlDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    @Basic
    @Access(AccessType.PROPERTY)
    @Column(name = "uuid", length = 38, unique = true, nullable = false)
    @Override
    public String getUuid() {
        return super.getUuid();
    }

    @Override
    public void setUuid(String uuid) {
        super.setUuid(uuid);
    }
}
