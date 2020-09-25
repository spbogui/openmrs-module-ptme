package org.openmrs.module.ptme.web.controller;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ReportingDataset;
import org.openmrs.module.ptme.ReportingIndicator;
import org.openmrs.module.ptme.ReportingReportGeneration;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.forms.GetRunReportFromFrom;
import org.openmrs.module.ptme.forms.RunReportForm;
import org.openmrs.module.ptme.forms.validators.RunReportFormValidator;
import org.openmrs.module.ptme.utils.ReportDataSetIndicatorRun;
import org.openmrs.module.ptme.utils.ReportIndicatorValues;
import org.openmrs.module.ptme.utils.ReportRunIndicatorValue;
import org.openmrs.module.ptme.utils.UsefullFunction;
import org.openmrs.web.WebConstants;
import org.simpleframework.xml.transform.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class ReportingController {
    public static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(ReportingController.class);

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd_HHmmss");

    private static SimpleDateFormat dateFormatterDdMmYyyy = new SimpleDateFormat(
            "dd/MM/yyyy");

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
                                 @RequestParam(required = false, defaultValue = "") Integer reportExcelId,
                                 ModelMap modelMap) throws Exception{

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

        if (reportViewId != null || reportExcelId != null) {

            Integer id = reportExcelId != null ? reportExcelId : reportViewId;

            ReportingReportGeneration reportGeneration = getPreventTransmissionService().getGeneratedReportById(id);

            String xml = new String(reportGeneration.getContentGenerated(), "UTF-8");

            // System.out.println(xml);

            JAXBContext jaxbContext = JAXBContext.newInstance(ReportIndicatorValues.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();


            StringReader reader = new StringReader(xml);

            ReportIndicatorValues reportIndicatorValues = (ReportIndicatorValues)unmarshaller.unmarshal(reader);

            if (reportViewId != null) {

                mode = "view";

                List<ReportDataSetIndicatorRun> dataSetIndicatorRuns = new ArrayList<ReportDataSetIndicatorRun>();

                for (ReportDataSetIndicatorRun dataSetIndicatorRun : reportIndicatorValues.getReportDataSetIndicatorRuns()) {

                    String uuid = dataSetIndicatorRun.getDataSetUuid();

                    //System.out.println(uuid);

                    ReportingDataset dataset = getPreventTransmissionService().getDatasetByUuid(uuid);
                    dataSetIndicatorRun.setDataSetUuid(UsefullFunction.escapeHTML(dataset.getName()));

                    List<ReportRunIndicatorValue> reportRunIndicatorValues = new ArrayList<ReportRunIndicatorValue>();

                    for (ReportRunIndicatorValue indicatorValue : dataSetIndicatorRun.getReportRunIndicatorValues()) {
                        ReportingIndicator indicator = getPreventTransmissionService().getIndicatorByUuid(indicatorValue.getIndicatorUuid());
                        indicatorValue.setIndicatorUuid(UsefullFunction.escapeHTML(indicator.getName()));
                        reportRunIndicatorValues.add(indicatorValue);
                    }
                    dataSetIndicatorRun.setReportRunIndicatorValues(reportRunIndicatorValues);
                    dataSetIndicatorRuns.add(dataSetIndicatorRun);
                }
                reportIndicatorValues.setReportDataSetIndicatorRuns(dataSetIndicatorRuns);


                modelMap.addAttribute("reportGeneration", reportGeneration);
                modelMap.addAttribute("reportValue", reportIndicatorValues);
            }
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
            List<ReportingReportGeneration> reportGeneratedList = getPreventTransmissionService().getAllGeneratedReport(false);

            for (ReportingReportGeneration reportGeneration : reportGeneratedList) {
                if (!reportGeneration.getSaved()) {
                    Date generationDate = reportGeneration.getGenerationDate();
                    Date toDay = new Date();
                    long diffInMillis = Math.abs(generationDate.getTime() - toDay.getTime());
                    long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

                    if (diff > 3) {
                        getPreventTransmissionService().removeGeneratedReport(reportGeneration.getId());
                    }
                }
            }

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

        new RunReportFormValidator().validate(runReportForm, result);

        if(!result.hasErrors()) {
            HttpSession session = request.getSession();

            Boolean hasErrors = false;

            ReportingReportGeneration reportingReportGeneration = null;

            //System.out.println("************************************ Report ID = "+runReportForm.getReportId());

            String generatedReportXmlString = getPreventTransmissionService().getGeneratedReportXmlString(runReportForm.getReportPeriodStartDate(),
                    runReportForm.getReportPeriodEndDate(), runReportForm.getReportId(), runReportForm.getReportLocation());

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
        } else {
            modelMap.addAttribute("mode", "form");
            modelMap.addAttribute("locationList", Context.getLocationService().getAllLocations(false));
            modelMap.addAttribute("reportList", getPreventTransmissionService().getAllReports(false));
        }

        return null;

    }

    @RequestMapping("/module/ptme/reportExcelView.form")
    public ModelAndView exportExcel(@RequestParam("reportExcelId") Integer reportExcelId, HttpServletResponse response, HttpServletRequest request) throws IOException, JAXBException {

        ReportingReportGeneration reportGeneration = getPreventTransmissionService().getGeneratedReportById(reportExcelId);

        String xml = new String(reportGeneration.getContentGenerated(), "UTF-8");

        JAXBContext jaxbContext = JAXBContext.newInstance(ReportIndicatorValues.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(xml);

        ReportIndicatorValues reportIndicatorValues = (ReportIndicatorValues)unmarshaller.unmarshal(reader);


        try {

            InputStream is = new ByteArrayInputStream(reportGeneration.getReport().getTemplate().getContent());
            Workbook workbook = new XSSFWorkbook(is);
            is.close();

            Sheet sheet = workbook.getSheetAt(0);

            Boolean hasReportTitle = false;
            Boolean hasReportLocation = false;
            Boolean hasReportStartDate = false;
            Boolean hasReportEndDate = false;
            Boolean hasReportGenerationDate = false;
            Boolean hasReportMonth = false;

            int maxColumnLength = 0;

            for (ReportDataSetIndicatorRun dataSetIndicatorRun : reportIndicatorValues.getReportDataSetIndicatorRuns()) {
                for (ReportRunIndicatorValue indicatorValue : dataSetIndicatorRun.getReportRunIndicatorValues()) {

                    Iterator<Row> rowIterator = sheet.rowIterator();

                    while (rowIterator.hasNext()) {
                        Row row = rowIterator.next();
                        Iterator<Cell> cellIterator = row.cellIterator();
                        int cellNum = 0;

                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            cellNum += 1;

                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

                                if (cell.getStringCellValue().equals(indicatorValue.getCode())) {
                                    cell.setCellValue(indicatorValue.getValue());
                                    break;
                                }

                                if (!hasReportTitle) {
                                    if (cell.getStringCellValue().equals("reportTitle")) {
                                        cell.setCellValue(UsefullFunction.writeAccent(reportGeneration.getReport().getReportLabel()));
                                        hasReportTitle = true;
                                    }
                                }
                                if (!hasReportLocation) {
                                    if (cell.getStringCellValue().equals("reportLocation")) {
                                        cell.setCellValue(reportGeneration.getReportLocation().getName());
                                        hasReportLocation = true;
                                    }
                                }
                                if (!hasReportGenerationDate) {
                                    if (cell.getStringCellValue().equals("reportGenerationDate")) {
                                        cell.setCellValue(dateFormatterDdMmYyyy.format(reportGeneration.getGenerationDate()));
                                        hasReportGenerationDate = true;
                                    }
                                }
                                if (!hasReportStartDate) {
                                    if (cell.getStringCellValue().equals("reportStartDate")) {
                                        cell.setCellValue(dateFormatterDdMmYyyy.format(reportGeneration.getReportPeriodStartDate()));
                                        hasReportStartDate = true;
                                    }
                                }
                                if (!hasReportEndDate) {
                                    if (cell.getStringCellValue().equals("reportEndDate")) {
                                        cell.setCellValue(dateFormatterDdMmYyyy.format(reportGeneration.getReportPeriodEndDate()));
                                        hasReportEndDate = true;
                                    }
                                }
                                if (!hasReportMonth) {
                                    if (cell.getStringCellValue().equals("reportMonth")) {
                                        cell.setCellValue(reportGeneration.getName());
                                        hasReportMonth = true;
                                    }
                                }
                            }
                        }

                        if (maxColumnLength < cellNum)
                            maxColumnLength = cellNum;
                    }
                }
            }

            XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);

            workbook.setPrintArea(
                    0, //sheet index
                    0, //start column
                    maxColumnLength - 1, //end column
                    sheet.getFirstRowNum(), //start row
                    sheet.getLastRowNum() //end row
            );

            String filename = UsefullFunction.writeAccent(reportGeneration.getReport().getReportLabel()).replace(" ", "_") +
                    "_" +  reportGeneration.getName().replace(" ", "_") + "_" +
                    dateFormatter.format(new Date())+ ".xlsx";

            sheet.getPrintSetup().setPaperSize(XSSFPrintSetup.A4_PAPERSIZE);
            sheet.setDisplayGridlines(false);
            sheet.setPrintGridlines(false);

            /*FileOutputStream out = new FileOutputStream(new File(filename));
            workbook.write(out);
            out.close();*/

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition","attachment; filename=" + filename);
            response.setHeader("Pragma", "no-cache");

            workbook.write(response.getOutputStream());
            workbook.close();

        } catch (Exception e) {
            System.out.println("Exception " + e.getLocalizedMessage());
        }

        return null;

    }

}
