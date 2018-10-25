package org.openmrs.module.ptme.web.controller;

import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.forms.DatasetForm;
import org.openmrs.module.ptme.forms.GetDataSetFromFrom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ReportingManageDataSetController {

    private PreventTransmissionService getPreventTransmissionService() {
        return Context.getService(PreventTransmissionService.class);
    }

    @ModelAttribute("chosenLocation")
    public Location getChosenLocation(Integer locationId){
        if (locationId != null) {
            return Context.getLocationService().getLocation(locationId);
        } else {
            return Context.getLocationService().getLocation(Context.getAdministrationService().getGlobalProperty("default_location"));
        }
    }

    @RequestMapping(value = "/module/ptme/reportDataSet.form")
    public void manage(HttpServletRequest request,
                       @RequestParam(required = false, defaultValue = "") String add,
                       @RequestParam(required = false, defaultValue = "0") Integer delId,
                       @RequestParam(required = false, defaultValue = "") Integer datasetId,
                       ModelMap modelMap) {

        if (!Context.isAuthenticated()){
            return;
        }

        HttpSession session = request.getSession();

        String mode = "list";

        if (!add.isEmpty()){
            mode = "form";
        }

        if (datasetId != null){
            mode = "form";
        }

        if (mode.equals("form")) {
            DatasetForm datasetForm = new DatasetForm();
            modelMap.addAttribute("datasetForm", datasetForm);
        }
        if (mode.equals("list")) {
            GetDataSetFromFrom getDataSetFromFrom = new GetDataSetFromFrom();
            modelMap.addAttribute("getDataSetFromFrom", getDataSetFromFrom);
        }

        modelMap.addAttribute("mode", mode);
    }

}
