package org.openmrs.module.ptme.web.controller;

import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ReportingDataset;
import org.openmrs.module.ptme.ReportingReport;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.forms.GetReportFromFrom;
import org.openmrs.module.ptme.forms.ReportForm;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ReportingManageController {

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

    @RequestMapping(value = "/module/ptme/reportManage.form")
    public void manage(HttpServletRequest request,
                       @RequestParam(required = false, defaultValue = "") String add,
                       @RequestParam(required = false, defaultValue = "0") Integer delId,
                       @RequestParam(required = false, defaultValue = "") Integer reportId,
                       ModelMap modelMap) {

        if (!Context.isAuthenticated()){
            return;
        }

        HttpSession session = request.getSession();

        String mode = "list";

        if (delId != 0){
            ReportingReport report = getPreventTransmissionService().getReportById(delId);
            if (report != null) {
                if (getPreventTransmissionService().voidReport(delId) != null) {
                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Rapport supprimé avec succès !");
                }
            }
        }

        if (!add.isEmpty()){
            mode = "form";
        }

        if (reportId != null) {
            mode = "form";
        }

        if (mode.equals("form")) {
            ReportForm reportForm = new ReportForm();

            List<ReportingDataset> availableDatasets = getPreventTransmissionService().getAllDatasets(false);
            Set<ReportingDataset> selectedDatasets = new HashSet<ReportingDataset>();

            if (reportId != null) {
                selectedDatasets = getPreventTransmissionService().getReportById(reportId).getReportingDatasets();
                reportForm.getReport(getPreventTransmissionService().getReportById(reportId));
            }

            if (selectedDatasets != null) {
                for (ReportingDataset sds : selectedDatasets) {
                    if (availableDatasets.contains(sds)) {
                        availableDatasets.remove(sds);
                    }
                }
            }

            modelMap.addAttribute("reportForm", reportForm);
            modelMap.addAttribute("availableDataSetList", availableDatasets);
            modelMap.addAttribute("selectedDataSets", selectedDatasets);
            modelMap.addAttribute("templateList", getPreventTransmissionService().getAllTemplates(false));
        }

        if (mode.equals("list")) {
            modelMap.addAttribute("getReportFromFrom", new GetReportFromFrom());
            modelMap.addAttribute("reports", getPreventTransmissionService().getAllReports(false));
        }
        modelMap.addAttribute("mode", mode);
    }

    @RequestMapping(value = "/module/ptme/reportManage.form", method = RequestMethod.POST)
    public String onSubmitDataSet(HttpServletRequest request,
                                  ModelMap modelMap,
                                  @RequestParam(required = false, defaultValue = "") Integer reportId,
                                  ReportForm reportForm,
                                  BindingResult result) {
        if (!Context.isAuthenticated()){
            return null;
        }

        if(!result.hasErrors()) {
            HttpSession session = request.getSession();

            Boolean hasErrors = false;

            ReportingReport report;

            if (reportForm.getReportId() != null) {
                report = getPreventTransmissionService().getReportById(reportId);
            } else {
                report = new ReportingReport();
            }

            report = reportForm.setReport(report);

            if (getPreventTransmissionService().saveReportingReport(report) != null) {
                if (reportForm.getReportId() != null) {
                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Rapport mis à jour avec succès !");
                } else {
                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Rapport sauvegardé avec succès !");
                }
            }
            return "redirect:/module/ptme/reportManage.form";
        }

        return null;
    }

}
