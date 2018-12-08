package org.openmrs.module.ptme.web.controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.MotherFollowup;
import org.openmrs.module.ptme.MotherFollowupVisit;
import org.openmrs.module.ptme.PregnantPatient;
import org.openmrs.module.ptme.SerializedData;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.forms.*;
import org.openmrs.module.ptme.forms.validators.MotherFollowupFormValidator;
import org.openmrs.module.ptme.forms.validators.MotherFollowupPatientFormValidator;
import org.openmrs.module.ptme.utils.MotherFollowupCurrentlyOn;
import org.openmrs.module.ptme.utils.PregnantPatientToFollow;
import org.openmrs.module.ptme.utils.UsefullFunction;
import org.openmrs.module.ptme.xml.MotherFollowupXml;
import org.openmrs.module.ptme.xml.PregnantPatientXml;
import org.openmrs.web.WebConstants;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class MotherFollowupManageController {

    protected final Log log = LogFactory.getLog(getClass());

    @ModelAttribute("chosenLocation")
    public Location getChosenLocation(Integer locationId){
        if (locationId != null) {
            return Context.getLocationService().getLocation(locationId);
        } else {
            return Context.getLocationService().getLocation(Context.getAdministrationService().getGlobalProperty("default_location"));
        }
    }

    public Map<String, Object> getPatientInfo(Patient patient) {
        if (patient != null) {
            Map<String, Object> patientInfo= new HashMap<String, Object>();
            patientInfo.put("identifier", patient.getPatientIdentifier());
            patientInfo.put("birthDate", patient.getBirthdate());
            patientInfo.put("age", patient.getAge());
            patientInfo.put("familyName", patient.getFamilyName());
            patientInfo.put("givenName", patient.getGivenName());
            patientInfo.put("middleName", patient.getMiddleName());

            List<Obs> obsList = Context.getObsService().getLastNObservations(1,
                    patient,
                    Context.getConceptService().getConcept(163623), false);
            if(obsList.size() != 0)
                patientInfo.put("hivType", obsList.get(0).getValueCoded().getName());

            obsList = Context.getObsService().getLastNObservations(1,
                    patient,
                    Context.getConceptService().getConcept(164500), false);
            if(obsList.size() != 0)
                patientInfo.put("tel", obsList.get(0).getValueText());

            obsList = Context.getObsService().getLastNObservations(1,
                    patient, Context.getConceptService().getConcept(164501), false);
            if(obsList.size() != 0)
                patientInfo.put("cel", obsList.get(0).getValueText());
            obsList = Context.getObsService().getLastNObservations(1,
                    patient,
                    Context.getConceptService().getConcept(5596), false);
            if(obsList.size() != 0)
                patientInfo.put("outcomeProbableDate", obsList.get(0).getValueDate());
            obsList = Context.getObsService().getLastNObservations(1,
                    patient,
                    Context.getConceptService().getConcept(164588), false);
            if(obsList.size() != 0)
                patientInfo.put("hivCareBeginning", obsList.get(0).getValueDate());

            return patientInfo;
        }
        return null;
    }

    private PreventTransmissionService getPreventTransmissionService() {
        return Context.getService(PreventTransmissionService.class);
    }

    @RequestMapping(value = "/module/ptme/motherFollowup.form")
    public void manageForm(HttpServletRequest request,
                           ModelMap modelMap,
                           @RequestParam(required = false, defaultValue = "") String mode,
                           @RequestParam(required = false, defaultValue = "") Integer motherFollowupId,
                           @RequestParam(required = false, defaultValue = "") Integer motherFollowupVisitId,
                           @RequestParam(required = false, defaultValue = "") Integer delId,
                           @RequestParam(required = false, defaultValue = "") Integer delFollowupId,
                           @RequestParam(required = false, defaultValue = "") String hivCareNumber) {

        if(Context.isAuthenticated()) {

            HttpSession session = request.getSession();

            String success = "";
            String error = "";
            MessageSourceAccessor msa = null;

            MotherFollowupForm motherFollowupForm = new MotherFollowupForm();
            Collection<MotherFollowupVisit> motherFollowupVisits = null;
            PregnantPatient pregnantPatient = null;

            if(delId != null) {
                MotherFollowupVisit motherFollowupVisit = getPreventTransmissionService().getMotherFollowUpVisitById(delId);
                if (motherFollowupVisit != null) {
                    getPreventTransmissionService().removeMotherFollowupVisit(motherFollowupVisit);
                }
            }

            if(delFollowupId != null) {
                MotherFollowup motherFollowupToDelete = getPreventTransmissionService().getMotherFollowupById(delFollowupId);
                if(motherFollowupToDelete != null) {
                    for (MotherFollowupVisit motherFollowupVisitToDelete : motherFollowupToDelete.getMotherFollowupVisits()){
                        motherFollowupVisitToDelete.setVoided(true);
                        motherFollowupVisitToDelete.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
                        motherFollowupVisitToDelete.setVoidedBy(Context.getAuthenticatedUser());
                        getPreventTransmissionService().saveMotherFollowupVisit(motherFollowupVisitToDelete);
                    }
                    motherFollowupToDelete.setVoided(true);
                    motherFollowupToDelete.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
                    motherFollowupToDelete.setVoidedBy(Context.getAuthenticatedUser());

                    getPreventTransmissionService().saveMotherFollowup(motherFollowupToDelete);
                }
            }

            if (hivCareNumber.isEmpty()) {
                if(motherFollowupId == null){
                    FindPregnantPatientForm findPregnantPatientForm = new FindPregnantPatientForm();
                    modelMap.addAttribute("findPregnantPatientForm", findPregnantPatientForm);
                    if (!mode.isEmpty())
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez entrer le numéro de prise en charge de la patiente SVP !");
                    List<MotherFollowupCurrentlyOn> listMotherFollowupOn = getPreventTransmissionService().
                            getMotherFollowupList(null, null, "On", null, null);

                    modelMap.addAttribute("listMotherFollowupOn", listMotherFollowupOn);
                } else {
                    MotherFollowup motherFollowup = getPreventTransmissionService().getMotherFollowupById(motherFollowupId);
                    if (motherFollowup != null) {

                        if(motherFollowupVisitId != null) {
                            MotherFollowupVisit motherFollowupVisit = getPreventTransmissionService().getMotherFollowUpVisitById(motherFollowupVisitId);
                            if(motherFollowupVisit != null) {
                                motherFollowupForm.setMotherFollowupVisitId(motherFollowupVisitId);
                                motherFollowupForm.setVisitDate(motherFollowupVisit.getVisitDate());
                                motherFollowupForm.setContinuingArv(motherFollowupVisit.getContinuingArv());
                                motherFollowupForm.setContinuingCtx(motherFollowupVisit.getContinuingCtx());
                                motherFollowupForm.setGestationalAge(motherFollowupVisit.getGestationalAge());
                            }
                        }

                        motherFollowupVisits = motherFollowup.getMotherFollowupVisits();
                        pregnantPatient = motherFollowup.getPregnantPatient();
                        motherFollowupForm.setPregnantPatientId(motherFollowup.getPregnantPatient().getPregnantPatientId());
                        motherFollowupForm.setMotherFollowup(motherFollowup);

                        mode = "form";

                        modelMap.addAttribute("motherFollowupVisits", getPreventTransmissionService().getMotherFollowupVisitByPatientAndFollowup(motherFollowup));
                        modelMap.addAttribute("motherFollowupVisitsCount", motherFollowupVisits != null ? motherFollowupVisits.size() : 0);

                        motherFollowupForm.setPregnantPatientId(pregnantPatient.getPregnantPatientId());
                        modelMap.addAttribute("motherFollowupForm", motherFollowupForm);
                        modelMap.addAttribute("pregnantPatient", pregnantPatient);
                        modelMap.addAttribute("patientInfo", getPatientInfo(pregnantPatient.getPatient()));

                    } else {
                        List<MotherFollowupCurrentlyOn> listMotherFollowupOn = getPreventTransmissionService().
                                getMotherFollowupList(null, null, "On", null, null);

                        modelMap.addAttribute("listMotherFollowupOn", listMotherFollowupOn);
                    }
                }

            } else {

                //Check if patient exists
                pregnantPatient = getPreventTransmissionService().getPregnantPatientByHivCareNumber(hivCareNumber);

                if(pregnantPatient == null) {
                    FindPregnantPatientForm findPregnantPatientForm = new FindPregnantPatientForm();
                    findPregnantPatientForm.setHivCareNumber(hivCareNumber);
                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Aucune femme actuellement enceinte ne possède ce numéro");
                    modelMap.addAttribute("findPregnantPatientForm", findPregnantPatientForm);

                    List<MotherFollowupCurrentlyOn> listMotherFollowupOn = getPreventTransmissionService().
                            getMotherFollowupList(null, null, "On", null, null);

                    modelMap.addAttribute("listMotherFollowupOn", listMotherFollowupOn);
                } else {
//                    List<Patient> listPatients = Context.getPatientService().getPatientsByIdentifier(hivCareNumber, false);


                    Patient currentPatient = getPreventTransmissionService().getPatientByIdentifier(hivCareNumber);
                    FindPregnantPatientForm findPregnantPatientForm = new FindPregnantPatientForm();
                    if (currentPatient == null) {
                        findPregnantPatientForm.setHivCareNumber(hivCareNumber);
                        session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Cette femme enceinte n'est pas prise en charge sur le site !");
                        modelMap.addAttribute("findPregnantPatientForm", findPregnantPatientForm);

                        List<MotherFollowupCurrentlyOn> listMotherFollowupOn = getPreventTransmissionService().
                                getMotherFollowupList(null, null, "On", null, null);

                        modelMap.addAttribute("listMotherFollowupOn", listMotherFollowupOn);
                    } else {

                        if (getPreventTransmissionService().isDead(pregnantPatient.getPatient())) {

                            findPregnantPatientForm.setHivCareNumber(hivCareNumber);
                            session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Cette femme est décédées. Veuillez en choisir une autre SVP !");
                            modelMap.addAttribute("findPregnantPatientForm", findPregnantPatientForm);

                            List<MotherFollowupCurrentlyOn> listMotherFollowupOn = getPreventTransmissionService().
                                    getMotherFollowupList(null, null, "On", null, null);

                            modelMap.addAttribute("listMotherFollowupOn", listMotherFollowupOn);
                        } else if(getPreventTransmissionService().isTransfered(pregnantPatient.getPatient())) {
                            findPregnantPatientForm.setHivCareNumber(hivCareNumber);
                            session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Cette femme est transférées vers un autre site. Veuillez en choisir une autre SVP !");
                            modelMap.addAttribute("findPregnantPatientForm", findPregnantPatientForm);

                            List<MotherFollowupCurrentlyOn> listMotherFollowupOn = getPreventTransmissionService().
                                    getMotherFollowupList(null, null, "On", null, null);

                            modelMap.addAttribute("listMotherFollowupOn", listMotherFollowupOn);
                        }
                        else {
                            mode = "form";
                        }
                    }

                }

                if (mode.equals("form")){
                    MotherFollowup currentMotherFollowup = getPreventTransmissionService().getCurrentMotherFollowupByPregnantPatient(pregnantPatient);
                    if(currentMotherFollowup != null){
                        motherFollowupForm.setMotherFollowup(currentMotherFollowup);
                        motherFollowupVisits = currentMotherFollowup.getMotherFollowupVisits();
                    }

                    modelMap.addAttribute("motherFollowupVisits", getPreventTransmissionService().getMotherFollowupVisitByPatientAndFollowup(currentMotherFollowup));
                    modelMap.addAttribute("motherFollowupVisitsCount", motherFollowupVisits != null ? motherFollowupVisits.size() : 0);

                    assert pregnantPatient != null;
                    motherFollowupForm.setPregnantPatientId(pregnantPatient.getPregnantPatientId());

                    modelMap.addAttribute("motherFollowupForm", motherFollowupForm);
                    modelMap.addAttribute("pregnantPatient", pregnantPatient);
                    modelMap.addAttribute("patientInfo", getPatientInfo(pregnantPatient.getPatient()));
                } else {
                    List<MotherFollowupCurrentlyOn> listMotherFollowupOn = getPreventTransmissionService().
                            getMotherFollowupList(null, null, "On", null, null);

                    modelMap.addAttribute("listMotherFollowupOn", listMotherFollowupOn);
                }
            }


            modelMap.addAttribute("mode", mode);
        }
    }


    @RequestMapping(value = "/module/ptme/motherFollowup.form", method = RequestMethod.POST)
    public String onSubmit(HttpServletRequest request,
                           ModelMap modelMap,
                           @RequestParam(required = false, defaultValue = "") String hivCareNumber,
                           MotherFollowupForm motherFollowupForm,
                           BindingResult result
    ) {

        if(Context.isAuthenticated()){

            log.info(motherFollowupForm);

            HttpSession session = request.getSession();

            PregnantPatient pregnantPatient = getPreventTransmissionService().getPregnantPatientById(motherFollowupForm.getPregnantPatientId());

            modelMap.addAttribute("mode", "form");

            modelMap.addAttribute("patientInfo", getPatientInfo(pregnantPatient.getPatient()));

            MotherFollowup motherFollowup = null;

            MotherFollowupVisit motherFollowupVisit = null;

            if (motherFollowupForm.getMotherFollowupId() == null) {
                motherFollowup = motherFollowupForm.getMotherFollowup(new MotherFollowup());
            } else {
                motherFollowup = motherFollowupForm.getMotherFollowup(getPreventTransmissionService().getMotherFollowupById(motherFollowupForm.getMotherFollowupId()));
            }
            Collection<MotherFollowupVisit> motherFollowupVisits;
            if(motherFollowup.getMotherFollowupId() == null) {
                motherFollowupVisits = motherFollowup.getMotherFollowupVisits();
            } else {
                motherFollowupVisits = getPreventTransmissionService().getMotherFollowupVisitByPatientAndFollowup(motherFollowup);
            }

            modelMap.addAttribute("motherFollowupVisits", motherFollowupVisits);
            modelMap.addAttribute("motherFollowupVisitsCount", motherFollowupVisits != null ? motherFollowupVisits.size() : 0);

            new MotherFollowupFormValidator().validate(motherFollowupForm, result);

            if(!result.hasErrors()){

                Boolean hasErrors = false;
                Boolean insertVisit = true;

                if (motherFollowupForm.getArvStatusAtRegistering() == null) {
                    session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez sélectionner le statut ARV à l'enregistrement SVP !");
                    hasErrors = true;
                } else if (motherFollowupForm.getDeliveryType() != null && motherFollowupForm.getPregnancyOutcome() == null) {
                    session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez sélectionner l'issue de la grossesse SVP !");
                    hasErrors = true;
                } else {
                    if (motherFollowupForm.getMotherFollowupId() != null &&
//                            motherFollowup.getMotherFollowupVisits() != null &&
                            motherFollowupForm.getVisitDate() == null &&
                            motherFollowupForm.getGestationalAge() == null &&
                            motherFollowupForm.getContinuingArv() == null &&
                            motherFollowupForm.getContinuingCtx() == null) {
                        insertVisit = false;
                    } else if (motherFollowup.getMotherFollowupVisits() == null && motherFollowupForm.getVisitDate() == null){
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez renseigner les données de la visite de suivi de la patiente SVP !");
                        hasErrors = true;
                    } else {

                        if(motherFollowupForm.getVisitDate() == null) {
                            session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez renseigner la date de la visite SVP");
                            hasErrors = true;
                        } else if(motherFollowupForm.getVisitDate().after(new Date())) {
                            session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "La date de visite ne peut excéder la date actuelle !");
                            hasErrors = true;
                        } else {
                            MotherFollowupVisit motherFollowupVisit1 = getPreventTransmissionService().getPregnantPatientFollowupByDate(pregnantPatient.getPregnantPatientId(), motherFollowupForm.getVisitDate());

                            if(motherFollowupVisit1 != null ) {
                                if(motherFollowupForm.getMotherFollowupVisitId() != null) {
                                    if (!motherFollowupVisit1.getMotherFollowupVisitId().equals(motherFollowupForm.getMotherFollowupVisitId())){
                                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Cette patiente a déjà une viste à la date de visite renseignée");
                                        hasErrors = true;
                                    }
                                } else {
                                    session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Cette patiente a déjà une viste à la date de visite renseignée");
                                    hasErrors = true;
                                }
                            }

                        }
                    }
                }

                if (hasErrors) {
                    return null;
                } else {

                    motherFollowup = getPreventTransmissionService().saveMotherFollowup(motherFollowup);

                    if(insertVisit) {
                        if(motherFollowupForm.getMotherFollowupVisitId() == null ) {
                            motherFollowupVisit = motherFollowupForm.getMotherFollowupVisit(new MotherFollowupVisit());
                        } else {
                            motherFollowupVisit = motherFollowupForm.getMotherFollowupVisit(getPreventTransmissionService().getMotherFollowUpVisitById(motherFollowupForm.getMotherFollowupVisitId()));
                        }

                        motherFollowupVisit.setMotherFollowup(motherFollowup);
                        getPreventTransmissionService().saveMotherFollowupVisit(motherFollowupVisit);
                    }

                    XStream xStream = new XStream(new DomDriver());
                    xStream.registerConverter(new MotherFollowupXml());
                    xStream.alias("motherFollowup", MotherFollowup.class);

                    SerializedData data = getPreventTransmissionService().getSerializedDataByObjectUuid(motherFollowup.getUuid());
                    if (data == null) {
                        data = new SerializedData();
                    }

                    data.setObjectUuid(motherFollowup.getUuid());
                    data.setSerializedXmlData(xStream.toXML(motherFollowup));
                    data.setPackageName(MotherFollowup.class.getName());
                    getPreventTransmissionService().saveSerializedData(data);

                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Opération effectuée avec succès");

                    return "redirect:/module/ptme/motherFollowup.form?motherFollowupId=" + motherFollowup.getMotherFollowupId().toString();
                }

            }

        }

        return null;
    }

    @RequestMapping(value = "/module/ptme/motherFollowupManage.form")
    public void manage(ModelMap modelMap,
                       HttpServletRequest request,
                       @RequestParam(required = false, defaultValue = "") String startOrEnd,
                       @RequestParam(required = false, defaultValue = "0") Integer motherFollowupId,
                       @RequestParam(required = false, defaultValue = "") Date startDate,
                       @RequestParam(required = false, defaultValue = "") Date endDate,
                       @RequestParam(required = false, defaultValue = "") String status){
        if(Context.isAuthenticated()){

            HttpSession session = request.getSession();

            MotherFollowup motherFollowup = getPreventTransmissionService().getMotherFollowupById(motherFollowupId);

            String mode = "list";
            if (motherFollowup == null){

                ManageMotherFollowupForm manageMotherFollowupForm = new ManageMotherFollowupForm();
                manageMotherFollowupForm.setStartOrEnd("startDate");

                if (startDate !=null){
                    manageMotherFollowupForm.setStartDate(startDate);
                }
                if (endDate != null){
                    manageMotherFollowupForm.setEndDate(endDate);
                }

                List<MotherFollowupCurrentlyOn> listMotherFollowupOn = null;

                if (!status.isEmpty()){
                    listMotherFollowupOn = getPreventTransmissionService().
                            getMotherFollowupList(startDate, endDate, status, null, startOrEnd);

                    manageMotherFollowupForm.setStatus(status);
                    manageMotherFollowupForm.setStartOrEnd(startOrEnd);
                    manageMotherFollowupForm.setStartDate(startDate);
                    manageMotherFollowupForm.setEndDate(endDate);
                }

                modelMap.addAttribute("listMotherFollowupOn", listMotherFollowupOn);

                modelMap.addAttribute("manageMotherFollowupForm", manageMotherFollowupForm);
            } else {
                mode = "followup";

                modelMap.addAttribute("motherFollowupVisits", motherFollowup.getMotherFollowupVisits());
                modelMap.addAttribute("motherFollowupVisitsCount",motherFollowup.getMotherFollowupVisits().size());
                modelMap.addAttribute("motherFollowup", motherFollowup);
                modelMap.addAttribute("patientInfo", getPatientInfo(motherFollowup.getPregnantPatient().getPatient()));
                modelMap.addAttribute("hivCareNumber", motherFollowup.getPregnantPatient().getHivCareNumber());
                modelMap.addAttribute("motherFollowupId", motherFollowupId);

                boolean editable = true;
                MotherFollowup currentMotherFollowup = getPreventTransmissionService().getCurrentMotherFollowupByPregnantPatient(motherFollowup.getPregnantPatient());

                if (currentMotherFollowup != null){
                    editable = false;
                }
                modelMap.addAttribute("editable", editable);
            }

            modelMap.addAttribute("mode", mode);


        }

    }

    @RequestMapping(value = "/module/ptme/motherFollowupPatient.form")
    public void pregnantPatientFollowManage(ModelMap modelMap,
                                            HttpServletRequest request,
                                            @RequestParam(required = false, defaultValue = "") String pregnantNumber,
                                            @RequestParam(required = false, defaultValue = "") Integer pregnantPatientId,
                                            @RequestParam(required = false, defaultValue = "") Integer delId) {

        if(Context.isAuthenticated()) {
            String mode = "list";

            Boolean formMode = false;
            PregnantPatient pregnantPatient = null;


            if(!pregnantNumber.isEmpty()) {
                pregnantPatient = getPreventTransmissionService().getPregnantPatientByPregnantNumber(pregnantNumber);
                formMode = true;
            } else if(pregnantPatientId != null){
                pregnantPatient = getPreventTransmissionService().getPregnantPatientById(pregnantPatientId);
                formMode = true;
            }

            if(formMode) {

                mode = "form";
                MotherFollowupPatientForm motherFollowupPatientForm = new MotherFollowupPatientForm();
                if (pregnantPatient != null) {
                    if (pregnantPatient.getPatient() != null) {
                        motherFollowupPatientForm.setPregnantPatient(pregnantPatient);
                        motherFollowupPatientForm.setAge(pregnantPatient.getPatient().getAge());
                        motherFollowupPatientForm.setPregnantNumber(pregnantPatient.getPregnantNumber());
                        motherFollowupPatientForm.setFamilyName(pregnantPatient.getPatient().getFamilyName());
                        motherFollowupPatientForm.setGivenName(pregnantPatient.getPatient().getGivenName());
                        motherFollowupPatientForm.setHivCareNumber(pregnantPatient.getPatient().getPatientIdentifier().getIdentifier());
                        motherFollowupPatientForm.setPatientId(pregnantPatient.getPatient().getPatientId());
                    } else {
                        motherFollowupPatientForm.setPregnantPatient(pregnantPatient);
//                        motherFollowupPatientForm.setAge(pregnantPatient.getAge());
//                        motherFollowupPatientForm.setFamilyName(pregnantPatient.getFamilyName());
//                        motherFollowupPatientForm.setGivenName(pregnantPatient.getGivenName());
//                        motherFollowupPatientForm.setHivCareNumber(pregnantPatient.getHivCareNumber());
                    }
                    motherFollowupPatientForm.setPregnantPatientId(pregnantPatient.getPregnantPatientId());
                    motherFollowupPatientForm.setScreeningNumber(pregnantPatient.getScreeningNumber());
                } else {
                    motherFollowupPatientForm.setPregnantNumber(pregnantNumber);
                }
                modelMap.addAttribute("motherFollowupPatientForm", motherFollowupPatientForm);

            } else {
                List<PregnantPatientToFollow> pregnantPatientFollowupList = getPreventTransmissionService().getPregnantPatientFollowupList();

                FindPregnantPatientForm findPregnantPatientForm = new FindPregnantPatientForm();

                modelMap.addAttribute("pregnantPatientFollowupList", pregnantPatientFollowupList);
                modelMap.addAttribute("findPregnantPatientForm", findPregnantPatientForm);
            }

            modelMap.addAttribute("mode", mode);
        }
    }


    @RequestMapping(value = "/module/ptme/motherFollowupPatient.form", method = RequestMethod.POST)
    public String pregnantPatientFollowSubmit(ModelMap modelMap,
                                              HttpServletRequest request,
//                                              @RequestParam(required = false, defaultValue = "") String pregnantNumber,
                                              MotherFollowupPatientForm motherFollowupPatientForm,
                                              BindingResult result) {
        if(Context.isAuthenticated()) {

            HttpSession session = request.getSession();

            modelMap.addAttribute("mode", "form");

            new MotherFollowupPatientFormValidator().validate(motherFollowupPatientForm, result);

            if (!result.hasErrors()) {
                Patient patient = getPreventTransmissionService().getPatientByIdentifier(motherFollowupPatientForm.getHivCareNumber());

                PregnantPatient pregnantPatient = getPreventTransmissionService().getPregnantPatientByPregnantNumber(motherFollowupPatientForm.getPregnantNumber());

                if(patient == null) {
//                    if(motherFollowupPatientForm.getPregnantNumber().isEmpty()){
//                        session.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
//                                "Veuillez renseigner le numéro de prise en charge de la patiente s'il vous plait ");
//                    } else
                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
                            "Le numéro de prise en charge ne correspond pas à ce lui d'un patient du site !");
                    return null;
                } else {

                    PregnantPatient existingPregnantPatient = getPreventTransmissionService().getPregnantPatientByHivCareNumber(motherFollowupPatientForm.getHivCareNumber());

                    if (existingPregnantPatient != null) {
                        if(pregnantPatient != null) {
                            if (!pregnantPatient.getPregnantNumber().equals(existingPregnantPatient.getPregnantNumber())) {

                                session.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
                                        "Ce numéro de prise en charge appartient déjà à une femme enceinte : " + existingPregnantPatient.getPregnantNumber());
                                return null;
                            }
                        } else {
                            session.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
                                    "Ce numéro de prise en charge appartient déjà à une femme enceinte : " + existingPregnantPatient.getPregnantNumber());
                            return null;
                        }
                    }

                    if(!patient.getGender().isEmpty()) {
                        if(patient.getGender().equals("M")){
                            session.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
                                    "Ce numéro de prise en charge n'est pas celui d'une femme ");
                            return null;
                        }
                    }

                    if (pregnantPatient == null) {
                        pregnantPatient = motherFollowupPatientForm.getPregnantPatient(new PregnantPatient(), patient);
                    } else {
                        pregnantPatient = motherFollowupPatientForm.getPregnantPatient(pregnantPatient, patient);
                    }
//                    pregnantPatient.setPregnantNumber(motherFollowupPatientForm.getPregnantNumber());
                    if(getPreventTransmissionService().savePregnantPatient(pregnantPatient) != null) {
                        XStream xStream = new XStream(new DomDriver());
                        xStream.registerConverter(new PregnantPatientXml());
                        xStream.alias("pregnantPatient", PregnantPatient.class);

                        SerializedData ppData = getPreventTransmissionService().getSerializedDataByObjectUuid(pregnantPatient.getUuid());
                        if (ppData == null) {
                            ppData = new SerializedData();
                        }
                        ppData.setObjectUuid(pregnantPatient.getUuid());
                        ppData.setSerializedXmlData(xStream.toXML(pregnantPatient));
                        ppData.setPackageName(PregnantPatient.class.getName());
                        getPreventTransmissionService().saveSerializedData(ppData);
                    }

                    FindPregnantPatientForm findPregnantPatientForm = new FindPregnantPatientForm();

                    modelMap.addAttribute("findPregnantPatientForm", findPregnantPatientForm);
                    modelMap.addAttribute("mode", "list");

                    return "redirect:/module/ptme/motherFollowupPatient.form";
                }

            }
//            modelMap.addAttribute("mode", "form");

            return null;
        }

        return null;
    }

    @RequestMapping(value = "/module/ptme/motherFollowupFutures.form")
    public void manage(HttpServletRequest request,
                       ModelMap modelMap,
                       @RequestParam(required = false, defaultValue = "") Integer motherFollowupId){

        if(Context.isAuthenticated()){

            HttpSession session = request.getSession();

//            MotherFollowupForm motherFollowupForm = new MotherFollowupForm();

            MotherFollowup motherFollowup = getPreventTransmissionService().getMotherFollowupById(motherFollowupId);
            if (motherFollowup != null){

                modelMap.addAttribute("motherFollowupVisits", motherFollowup.getMotherFollowupVisits());
                modelMap.addAttribute("motherFollowupVisitsCount",motherFollowup.getMotherFollowupVisits().size());
                modelMap.addAttribute("motherFollowup", motherFollowup);
                modelMap.addAttribute("patientInfo", getPatientInfo(motherFollowup.getPregnantPatient().getPatient()));
                modelMap.addAttribute("hivCareNumber", motherFollowup.getPregnantPatient().getHivCareNumber());
                modelMap.addAttribute("motherFollowupId", motherFollowupId);

                boolean editable = true;
                if (motherFollowup.getPregnancyOutcome() != null){
                    editable = false;
                }
                modelMap.addAttribute("editable", editable);
            }

//            modelMap.addAttribute("motherFollowupForm",motherFollowupForm);

        }

    }

}
