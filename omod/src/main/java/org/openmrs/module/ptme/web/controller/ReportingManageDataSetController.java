package org.openmrs.module.ptme.web.controller;

import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ReportingDataset;
import org.openmrs.module.ptme.ReportingIndicator;
import org.openmrs.module.ptme.ReportingReport;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.forms.DatasetForm;
import org.openmrs.module.ptme.forms.GetDataSetFromFrom;
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

        if (delId != 0){
            ReportingDataset dataset = getPreventTransmissionService().getDatasetById(delId);
            if (dataset != null) {
                if (getPreventTransmissionService().voidReport(delId) != null) {
                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Ensemble de données supprimé avec succès !");
                }
            }
        }

        if (!add.isEmpty()){
            mode = "form";
        }

        if (datasetId != null){
            mode = "form";
        }

        if (mode.equals("form")) {
            DatasetForm datasetForm = new DatasetForm();

            List<ReportingIndicator> availableIndicators = getPreventTransmissionService().getAllIndicators(false);
            Set<ReportingIndicator> selectedIndicators = new HashSet<ReportingIndicator>();

            if (datasetId != null){
                selectedIndicators = getPreventTransmissionService().getDatasetById(datasetId).getReportingIndicators();
                datasetForm.getReportingDataSet(getPreventTransmissionService().getDatasetById(datasetId));
            }

            if (selectedIndicators != null) {
                for (ReportingIndicator sri : selectedIndicators) {
                    if (availableIndicators.contains(sri)) {
                        availableIndicators.remove(sri);
                    }
                }
            }
            modelMap.addAttribute("datasetForm", datasetForm);
            modelMap.addAttribute("availableIndicatorList", availableIndicators);
            modelMap.addAttribute("selectedIndicators", selectedIndicators);
        }

        if (mode.equals("list")) {
            GetDataSetFromFrom getDataSetFromFrom = new GetDataSetFromFrom();
            modelMap.addAttribute("getDataSetFromFrom", getDataSetFromFrom);
            modelMap.addAttribute("dataSets", getPreventTransmissionService().getAllDatasets(false));
        }

        modelMap.addAttribute("mode", mode);
    }

    @RequestMapping(value = "/module/ptme/reportDataSet.form", method = RequestMethod.POST)
    public String onSubmitDataSet(HttpServletRequest request,
                                  ModelMap modelMap,
                                  @RequestParam(required = false, defaultValue = "") Integer datasetId,
                                  DatasetForm datasetForm,
                                  BindingResult result) {

        if (!Context.isAuthenticated()){
            return null;
        }

        if(!result.hasErrors()) {
            HttpSession session = request.getSession();

            Boolean hasErrors = false;
            ReportingDataset reportingDataset;

            if (datasetForm.getDatasetId() != null) {
                reportingDataset = getPreventTransmissionService().getDatasetById(datasetForm.getDatasetId());
            } else {
                reportingDataset = new ReportingDataset();
            }

            reportingDataset = datasetForm.getReportingDataset(reportingDataset);

            if (getPreventTransmissionService().saveReportingDataset(reportingDataset) != null) {
                if (datasetForm.getDatasetId() != null) {
                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Dataset mis à jour avec succès !");
                } else {
                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Indicateur sauvegardé avec succès !");
                }
            }

            modelMap.addAttribute("mode", "list");
            return "redirect:/module/ptme/reportDataSet.form";
        }

        return null;
    }

}
