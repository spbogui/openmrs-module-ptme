package org.openmrs.module.ptme.web.controller;

import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ReportingIndicator;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.forms.GetIndicatorFromFrom;
import org.openmrs.module.ptme.forms.IndicatorFrom;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ReportingManageIndicatorController {

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

    @RequestMapping(value = "/module/ptme/reportIndicator.form")
    public void manage(HttpServletRequest request,
                       @RequestParam(required = false, defaultValue = "") String add,
                       @RequestParam(required = false, defaultValue = "") String delId,
                       @RequestParam(required = false, defaultValue = "") String indicatorId,
                       ModelMap modelMap) {

        if (!Context.isAuthenticated()){
            return;
        }

        HttpSession session = request.getSession();

        String mode = "list";

        if (!add.isEmpty()){
            mode = "form";
        }

        if (mode.equals("form")) {
            IndicatorFrom indicatorFrom = new IndicatorFrom();

            modelMap.addAttribute("indicatorForm", indicatorFrom);
        }

        if (mode.equals("list")){
            GetIndicatorFromFrom getIndicatorFromFrom = new GetIndicatorFromFrom();
            modelMap.addAttribute("getIndicatorFromFrom", getIndicatorFromFrom);
            modelMap.addAttribute("indicators", getPreventTransmissionService().getAllIndicators(false));
        }

        modelMap.addAttribute("pageName", "Indicator.jsp");
        modelMap.addAttribute("mode", mode);
    }

    @RequestMapping(value = "/module/ptme/reportIndicator.form", method = RequestMethod.POST)
    public String onSubmitIndicator(HttpServletRequest request,
                                  ModelMap modelMap,
                                  @RequestParam(required = false, defaultValue = "") Integer indicatorId,
                                  IndicatorFrom indicatorFrom,
                                  BindingResult result) {

        if (!Context.isAuthenticated()){
            return null;
        }

        if(!result.hasErrors()) {
            HttpSession session = request.getSession();

            Boolean hasErrors = false;

            ReportingIndicator indicator = null;
            if (indicatorFrom.getIndicatorId() == null) {
                indicator = indicatorFrom.getIndicator(new ReportingIndicator());
            } else {
                indicator = indicatorFrom.getIndicator(getPreventTransmissionService().getIndicatorById(indicatorFrom.getIndicatorId()));
            }

            if (getPreventTransmissionService().saveReportingIndicator(indicator) != null) {
                if (indicatorFrom.getIndicatorId() != null) {
                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Indicateur mis à jour avec succès !");
                } else {
                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Indicateur sauvegargé avec succès !");
                }
            }

            modelMap.addAttribute("mode", "list");
            return "redirect:/module/ptme/reportIndicator.form";
        }

        return null;
    }

}
