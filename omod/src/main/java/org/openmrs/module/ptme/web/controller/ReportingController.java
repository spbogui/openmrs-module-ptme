package org.openmrs.module.ptme.web.controller;

import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ReportingDataset;
import org.openmrs.module.ptme.ReportingIndicator;
import org.openmrs.module.ptme.ReportingReportGeneration;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.forms.GetRunReportFromFrom;
import org.openmrs.module.ptme.forms.RunReportForm;
import org.openmrs.module.ptme.utils.ReportDataSetIndicatorRun;
import org.openmrs.module.ptme.utils.ReportIndicatorValues;
import org.openmrs.module.ptme.utils.ReportRunIndicatorValue;
import org.openmrs.web.WebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportingController {
    public static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(ReportingController.class);

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

    @RequestMapping(value = "/module/ptme/report.form", method = RequestMethod.GET)
    public void manage(ModelMap modelMap) {

    }

    @RequestMapping(value = "/module/ptme/reportGenerate.form", method = RequestMethod.GET)
    public void reportGeneration(HttpServletRequest request,
                                 @RequestParam(required = false, defaultValue = "") String add,
                                 @RequestParam(required = false, defaultValue = "0") Integer delId,
                                 @RequestParam(required = false, defaultValue = "") Integer generationId,
                                 @RequestParam(required = false, defaultValue = "") Integer reportSaveId,
                                 @RequestParam(required = false, defaultValue = "") Integer reportViewId,
                                 ModelMap modelMap) throws Exception {

        if (!Context.isAuthenticated()){
            return;
        }

        HttpSession session = request.getSession();

        String mode = "list";

        if (delId != 0) {
            if (getPreventTransmissionService().removeGeneratedReport(delId)){
                session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Rapport supprimé avec succès !");
            }
        }

        if (reportViewId != null) {
            mode = "view";

            ReportingReportGeneration reportGeneration = getPreventTransmissionService().getGeneratedReportById(reportViewId);

            String xml = new String(reportGeneration.getContentGenerated(), "UTF-8");

            //System.out.println(xml);

            JAXBContext jaxbContext = JAXBContext.newInstance(ReportIndicatorValues.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(xml);

            ReportIndicatorValues reportIndicatorValues = (ReportIndicatorValues)unmarshaller.unmarshal(reader);
//            System.out.println("************************** Start Date : "+ reportIndicatorValues.getReportStartDate());
//            System.out.println("************************** End Date : "+ reportIndicatorValues.getReportEndDate());
//            System.out.println("************************** Generation Date : "+ reportIndicatorValues.getGenerationDate());

//            Document doc = UseFullFunction.obtenerDocumentDeByte(reportGeneration.getContentGenerated());
//            NodeList nList = doc.getElementsByTagName("indicator");

            List<ReportDataSetIndicatorRun> dataSetIndicatorRuns = new ArrayList<ReportDataSetIndicatorRun>();

            for (ReportDataSetIndicatorRun dataSetIndicatorRun : reportIndicatorValues.getReportDataSetIndicatorRuns()) {

                String uuid = dataSetIndicatorRun.getDataSetUuid();

                //System.out.println(uuid);

                ReportingDataset dataset = getPreventTransmissionService().getDatasetByUuid(uuid);
                dataSetIndicatorRun.setDataSetUuid(dataset.getName());

                List<ReportRunIndicatorValue> reportRunIndicatorValues = new ArrayList<ReportRunIndicatorValue>();

                for (ReportRunIndicatorValue indicatorValue : dataSetIndicatorRun.getReportRunIndicatorValues()) {
                    ReportingIndicator indicator = getPreventTransmissionService().getIndicatorByUuid(indicatorValue.getIndicatorUuid());
                    indicatorValue.setIndicatorUuid(indicator.getName());
                    reportRunIndicatorValues.add(indicatorValue);
                }
                dataSetIndicatorRun.setReportRunIndicatorValues(reportRunIndicatorValues);
                dataSetIndicatorRuns.add(dataSetIndicatorRun);
            }
            reportIndicatorValues.setReportDataSetIndicatorRuns(dataSetIndicatorRuns);


            modelMap.addAttribute("reportGeneration", reportGeneration);
            modelMap.addAttribute("reportValue", reportIndicatorValues);
        }

        if (reportSaveId != null) {
            ReportingReportGeneration reportGeneration = getPreventTransmissionService().getGeneratedReportById(reportSaveId);
            if (reportGeneration != null) {
                reportGeneration.setSaved(true);
                if (getPreventTransmissionService().saveGenerationReport(reportGeneration) != null) {
                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Rapport généré enregistré avec succès !");
                }
            }
        }

        if (!add.isEmpty()){
            mode = "form";
        }

        if (generationId != null) {
            mode = "form";
        }

        if (mode.equals("form")) {
            RunReportForm runReportForm = new RunReportForm();

            if (generationId != null) {
                runReportForm.getGeneratedReport(getPreventTransmissionService().getGeneratedReportById(generationId));
            }

            modelMap.addAttribute("runReportForm", runReportForm);
            modelMap.addAttribute("locationList", Context.getLocationService().getAllLocations(false));
            modelMap.addAttribute("reportList", getPreventTransmissionService().getAllReports(false));
        }

        if (mode.equals("list")) {
            modelMap.addAttribute("getRunReportFormForm", new GetRunReportFromFrom());
            modelMap.addAttribute("listGeneratedReports", getPreventTransmissionService().getAllGeneratedReport(false));
        }

        modelMap.addAttribute("mode", mode);

    }

    @RequestMapping(value = "/module/ptme/reportGenerate.form", method = RequestMethod.POST)
    public String onRunIndicator(HttpServletRequest request,
                                    ModelMap modelMap,
                                    @RequestParam(required = false, defaultValue = "") Integer generationId,
                                    RunReportForm runReportForm,
                                    BindingResult result) {

        if (!Context.isAuthenticated()){
            return null;
        }

        if(!result.hasErrors()) {
            HttpSession session = request.getSession();

            Boolean hasErrors = false;

            ReportingReportGeneration reportingReportGeneration = null;

            //System.out.println("************************************ Report ID = "+runReportForm.getReportId());

            String generatedReportXmlString = getPreventTransmissionService().getGeneratedReportXmlString(runReportForm.getReportPeriodStartDate(), runReportForm.getReportPeriodEndDate(), runReportForm.getReportId(), runReportForm.getReportLocation());

            //System.out.println(generatedReportXmlString);

            if (runReportForm.getGenerationId() != null) {
                reportingReportGeneration = runReportForm.setGeneratedReport(getPreventTransmissionService().getGeneratedReportById(runReportForm.getGenerationId()));
            } else {
                reportingReportGeneration = runReportForm.setGeneratedReport(new ReportingReportGeneration());
            }

            reportingReportGeneration.setContentGenerated(generatedReportXmlString.getBytes());
            //reportingReportGeneration.setReportLocation(Context.getLocationService().getDefaultLocation());

            if (getPreventTransmissionService().saveGenerationReport(reportingReportGeneration) != null) {
                session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Rapport généré avec succès !");
            }

            return "redirect:/module/ptme/reportGenerate.form";
        }

        return null;

    }
}
