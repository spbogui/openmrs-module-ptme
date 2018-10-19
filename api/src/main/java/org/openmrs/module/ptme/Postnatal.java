package org.openmrs.module.ptme;



import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;

@XStreamAlias("postnatal")
@Entity
//@DiscriminatorValue("Postnatal")
@Table(name = "ptme_postnatal")
public class Postnatal extends Consultation {

    private static final long serialVersionUID = 1L;

    public Postnatal() {
    }

}
