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
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.utils.MotherFollowupAppointment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
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
		List<MotherFollowupAppointment> motherFollowupAppointments = new ArrayList<MotherFollowupAppointment>();

		for (MotherFollowupAppointment followupAppointment : getPreventTransmissionService().getPregnantPatientsAppointment()) {
			Patient patient = getPreventTransmissionService().getPatientByIdentifier(followupAppointment.getHivCareNumber());
			if (!getPreventTransmissionService().isTransferred(patient) && !getPreventTransmissionService().isDead(patient)
					&& !getPreventTransmissionService().isDeclaredNegative(patient)) {
				motherFollowupAppointments.add(followupAppointment);
			}
		}
		model.addAttribute("motherFollowedAppointments", motherFollowupAppointments);

		List<MotherFollowupAppointment> motherFollowupAppointmentsMissed = new ArrayList<MotherFollowupAppointment>();
		for (MotherFollowupAppointment followupAppointment : getPreventTransmissionService().getPregnantPatientsAppointmentMissed()) {
			Patient patient = getPreventTransmissionService().getPatientByIdentifier(followupAppointment.getHivCareNumber());
			if (!getPreventTransmissionService().isTransferred(patient) && !getPreventTransmissionService().isDead(patient)
					&& !getPreventTransmissionService().isDeclaredNegative(patient)) {
				motherFollowupAppointmentsMissed.add(followupAppointment);
			}
		}
		model.addAttribute("motherFollowedAppointmentsMissed", motherFollowupAppointmentsMissed);

//		model.addAttribute("user", Context.getAuthenticatedUser());
		model.addAttribute("childFollowedAppointments", getPreventTransmissionService().getChildByAppointment());
		model.addAttribute("childFollowedAppointmentsMissed", getPreventTransmissionService().getChildByAppointmentMissed());
		model.addAttribute("childFollowedPcrWaiting", getPreventTransmissionService().getChildPcrResultWaiting());

		model.addAttribute("childPcr1Appointment", getPreventTransmissionService().getChildByPcrAppointment(Context.getAdministrationService().getGlobalProperty("ptme.weekOfPCR1"), 1));
		model.addAttribute("childPcr2Appointment", getPreventTransmissionService().getChildByPcrAppointment(Context.getAdministrationService().getGlobalProperty("ptme.weekOfPCR2"), 2));
		model.addAttribute("childPcr3Appointment", getPreventTransmissionService().getChildByPcrAppointment(Context.getAdministrationService().getGlobalProperty("ptme.weekOfPCR3"), 3));
		model.addAttribute("childTestAppointment", getPreventTransmissionService().getChildByPcrAppointment(Context.getAdministrationService().getGlobalProperty("ptme.weekOfPCR3"), 4));
		model.addAttribute("childTestAppointmentM18", getPreventTransmissionService().getChildByPcrAppointment(Context.getAdministrationService().getGlobalProperty("ptme.weekOfPCR3"), 5));

//		model.addAttribute("encryptMSLS", Security.encrypt("MSLS"));
//		model.addAttribute("encryptPNLS", Security.encrypt("PNLS"));
//		model.addAttribute("encryptSuper", Security.encrypt("Super"));
//		model.addAttribute("encryptUser", Security.encrypt("User"));
	}
}
