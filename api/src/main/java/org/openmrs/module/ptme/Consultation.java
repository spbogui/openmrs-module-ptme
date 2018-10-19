/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.ptme;

import java.util.Date;
import java.util.UUID;

import org.openmrs.*;

import javax.persistence.*;

/**
 * It is a model class. It should extend either {@link BaseOpenmrsObject} or {@link BaseOpenmrsMetadata}.
 */
@Entity(name = "Consultation")
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name="consultation_type")
@Table(name = "ptme_consultation")
public abstract class Consultation extends PreventTransmissionAbstract {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "consultation_id")
	private Integer consultationId;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "consultation_date")
	private Date consultationDate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, name = "pregnant_patient_id")
	private PregnantPatient pregnantPatient;

	@ManyToOne
	@JoinColumn(nullable = false, name = "location_id")
	private Location location;

	@OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL)
	private HivService hivService;

	public Consultation() {
		setUuid(UUID.randomUUID().toString());
//		setCreator(Context.getAuthenticatedUser());
//		setDateCreated(UsefullFunction.formatDateToddMMyyyy(new Date()));
//		setVoided(false);
//		String location = Context.getAdministrationService().getGlobalProperty("default_location");
//		setLocation(Context.getLocationService().getLocation(location));
	}

	@Override
	public Integer getId() {
		return consultationId;
	}
	
	@Override
	public void setId(Integer id) {
		this.consultationId = id;
	}

	public Integer getConsultationId() {
		return consultationId;
	}

	public void setConsultationId(Integer consultationId) {
		this.consultationId = consultationId;
	}

	public Date getConsultationDate() {
		return consultationDate;
	}

	public void setConsultationDate(Date consultationDate) {
		this.consultationDate = consultationDate;
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

	public HivService getHivService() {
		return hivService;
	}

	public void setHivService(HivService hivService) {
		this.hivService = hivService;
	}

}