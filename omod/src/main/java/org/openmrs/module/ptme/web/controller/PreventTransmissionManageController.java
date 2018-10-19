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
package org.openmrs.module.ptme.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.PregnantPatient;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.utils.UsefullFunction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * The main controller.
 */
@Controller
public class  PreventTransmissionManageController {
	
	protected final Log log = LogFactory.getLog(getClass());

	private PreventTransmissionService getPreventTransmissionService() {
		return Context.getService(PreventTransmissionService.class);
	}

	@RequestMapping(value = "/module/ptme/manage", method = RequestMethod.GET)
	public void manage(ModelMap model) {

//		PregnantPatient pregnantPatient = getPreventTransmissionService().getPregnantPatientById(1);
//		PregnantPatient pregnantPatient = getPreventTransmissionService().getPregnantPatientByPregnantNumber("0017/890/YOU/0000123");
//		List<PregnantPatient> pregnantPatients = getPreventTransmissionService().getAllPregnantPatient();

		/*PregnantPatient pregnantPatient1 = new PregnantPatient();
		pregnantPatient1.setVoided(false);
		pregnantPatient1.setAge(26);
		pregnantPatient1.setPregnantNumber("AZERTY/67654/POU/09876");
		pregnantPatient1.setFamilyName("Amelan");
		pregnantPatient1.setGivenName("Amelan");
		pregnantPatient1.setLocation(Context.getLocationService().getLocation(1));
		pregnantPatient1.setCreator(Context.getAuthenticatedUser());
		pregnantPatient1.setDateCreated(UsefullFunction.formatDateToddMMyyyy(new Date()));*/

//		getPreventTransmissionService().savePregnantPatient(pregnantPatient1);

//		log.info(pregnantPatient);

		model.addAttribute("user", Context.getAuthenticatedUser());
		model.addAttribute("motherFollowedAppointments", getPreventTransmissionService().getPregnantPatientsAppointment());
		model.addAttribute("motherFollowedAppointmentsMissed", getPreventTransmissionService().getPregnantPatientsAppointmentMissed());

		model.addAttribute("childFollowedAppointments", getPreventTransmissionService().getChildByAppointment());
		model.addAttribute("childFollowedAppointmentsMissed", getPreventTransmissionService().getChildByAppointmentMissed());
//		model.addAttribute("pregnantPatient", pregnantPatient);
	}
}
