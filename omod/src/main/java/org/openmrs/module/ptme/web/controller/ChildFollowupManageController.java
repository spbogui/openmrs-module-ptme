package org.openmrs.module.ptme.web.controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.*;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.Child;
import org.openmrs.module.ptme.ChildFollowup;
import org.openmrs.module.ptme.ChildFollowupVisit;
import org.openmrs.module.ptme.SerializedData;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.forms.*;
import org.openmrs.module.ptme.forms.validators.ChildFollowupFormValidator;
import org.openmrs.module.ptme.forms.validators.ChildFormValidator;
import org.openmrs.module.ptme.utils.ChildFollowupTransformer;
import org.openmrs.module.ptme.utils.UsefullFunction;
import org.openmrs.module.ptme.xml.ChildXml;
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
import java.util.*;
import java.util.regex.Pattern;

@Controller
public class ChildFollowupManageController {

    protected final Log log = LogFactory.getLog(getClass());


    private PreventTransmissionService getPreventTransmissionService() {
        return Context.getService(PreventTransmissionService.class);
    }

    public Map<String, Object> getPatientInfo(Patient patient) {
        if (patient != null) {
            Map<String, Object> patientInfo= new HashMap<String, Object>();
            patientInfo.put("identifier", patient.getPatientIdentifier());
            patientInfo.put("birthDate", patient.getBirthdate());
            patientInfo.put("age", patient.getAge());
            patientInfo.put("familyName", patient.getFamilyName());
            patientInfo.put("middleName", patient.getMiddleName());
            patientInfo.put("givenName", patient.getGivenName());

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

    @ModelAttribute("chosenLocation")
    public Location getChosenLocation(Integer locationId){
        if (locationId != null) {
            return Context.getLocationService().getLocation(locationId);
        } else {

//            return Context.getLocationService().getLocation(Context.getAdministrationService().getGlobalProperty("default_location"));
            return Context.getLocationService().getDefaultLocation();
        }
    }

    @RequestMapping(value = "/module/ptme/childFollowup.form")
    public void manage(HttpServletRequest request,
                       ModelMap modelMap,
//                       @RequestParam(required = false, defaultValue = "") String mode,
                       @RequestParam(required = false, defaultValue = "") Integer childFollowupId,
                       @RequestParam(required = false, defaultValue = "") Integer childFollowupVisitId,
                       @RequestParam(required = false, defaultValue = "") Integer delId,
                       @RequestParam(required = false, defaultValue = "") Integer delFollowupId,
                       @RequestParam(required = false, defaultValue = "") String childFollowupNumber) {

        if(Context.isAuthenticated()) {

            HttpSession session = request.getSession();

            String mode = "list";

            ChildFollowupForm childFollowupForm = new ChildFollowupForm();
            FindChildForm findChildForm = new FindChildForm();

            if(delId != null){
                ChildFollowupVisit childFollowupVisit = getPreventTransmissionService().getChildFollowupVisitById(delId);
                if (childFollowupVisit != null) {
                    childFollowupVisit.setVoided(true);
                    childFollowupVisit.setVoidedBy(Context.getAuthenticatedUser());
                    childFollowupVisit.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));

                    getPreventTransmissionService().deleteChildFollowupVisit(childFollowupVisit);
                    session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Suppression effectuées avec succès");
                }
            }

            if (delFollowupId != null){
                ChildFollowup childFollowupToDelete = getPreventTransmissionService().getChildFollowupById(delFollowupId);

                if(childFollowupToDelete != null) {
                    childFollowupToDelete.setVoidedBy(Context.getAuthenticatedUser());
                    childFollowupToDelete.setVoided(true);
                    childFollowupToDelete.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));

                    getPreventTransmissionService().saveChildFollowup(childFollowupToDelete);
                    for (ChildFollowupVisit childFollowupVisitToDelete : childFollowupToDelete.getChild().getChildFollowupVisits()){
                        childFollowupVisitToDelete.setVoidedBy(Context.getAuthenticatedUser());
                        childFollowupVisitToDelete.setVoided(true);
                        childFollowupVisitToDelete.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
                        getPreventTransmissionService().saveChildFollowupVisit(childFollowupVisitToDelete);
                    }
                }
            }

            Child child;

            if(childFollowupNumber.isEmpty()) {
                if (childFollowupId == null) {

                    findChildForm.setChildFollowupNumber(childFollowupNumber);
                    modelMap.addAttribute("findChildForm", findChildForm);
                    modelMap.addAttribute("currentChildFollowup", getPreventTransmissionService().getChildFollowupList("On", null, null));

                } else {
                    mode = "form";
                    child = getPreventTransmissionService().getChildByFollowupNumber(childFollowupNumber);
                    modelMap.addAttribute("currentChild", child);
                    modelMap.addAttribute("patientInfo", getPatientInfo(child.getMother()));
                    modelMap.addAttribute("childFollowupForm", childFollowupForm);
                }
            } else {

                child = getPreventTransmissionService().getChildByFollowupNumber(childFollowupNumber);

                if (child == null) {
                    findChildForm.setChildFollowupNumber(childFollowupNumber);
                    modelMap.addAttribute("findChildForm", findChildForm);
                    modelMap.addAttribute("currentChildFollowup", getPreventTransmissionService().getChildFollowupList("On", null, null));
                } else {
                    mode = "form";

                    childFollowupForm.setChildId(child.getChildId());

//                    ChildFollowup childFollowup = child.getChildFollowup();
                    ChildFollowup childFollowup = getPreventTransmissionService().getChildFollowupById(child.getChildId());

                    if (childFollowup != null){
                        childFollowupForm.setChildFollowup(childFollowup);
                    } else {
                        childFollowupForm.setChildFollowup(new ChildFollowup());
                    }

                    if(childFollowupVisitId != null) {
                        childFollowupForm.setChildFollowupVisit(getPreventTransmissionService().getChildFollowupVisitById(childFollowupVisitId));
                    }

                    modelMap.addAttribute("currentChild", child);
                    modelMap.addAttribute("patientInfo", getPatientInfo(child.getMother()));
                    modelMap.addAttribute("childFollowupForm", childFollowupForm);
                    modelMap.addAttribute("childFollowupVisitCount", child.getChildFollowupVisits().size());
                    modelMap.addAttribute("childFollowupVisits", getPreventTransmissionService().getChildFollowupVisitByChild(child.getChildId()));
                }
            }

            modelMap.addAttribute("mode", mode);

        }
    }

    @RequestMapping(value = "/module/ptme/childFollowup.form", method = RequestMethod.POST)
    public String onSubmitFollowup(HttpServletRequest request,
                                   ModelMap modelMap,
                                   @RequestParam(required = false, defaultValue = "") Integer childFollowupVisitId,
                                   ChildFollowupForm childFollowupForm,
                                   BindingResult result) {
        if(Context.isAuthenticated()) {
            new ChildFollowupFormValidator().validate(childFollowupForm, result);

            modelMap.addAttribute("mode", "form");

            if(!result.hasErrors()) {
                HttpSession session = request.getSession();

                Child child = getPreventTransmissionService().getChildById(childFollowupForm.getChildId());
                Patient existingChildPatient = null;

                modelMap.addAttribute("currentChild", child);
                modelMap.addAttribute("patientInfo", getPatientInfo(child.getMother()));
                modelMap.addAttribute("childFollowupForm", childFollowupForm);
                modelMap.addAttribute("childFollowupVisitCount", child.getChildFollowupVisits().size());
                modelMap.addAttribute("childFollowupVisits", child.getChildFollowupVisits());

                Boolean hasErrors = false;
                Boolean insertVisit = true;
                if(childFollowupForm.getChildFollowupVisitId() == null
                        && childFollowupForm.getVisitDate() == null
                        && childFollowupForm.getAgeInDay() == null
                        && childFollowupForm.getAgeInWeek() == null
                        && childFollowupForm.getAgeInMonth() == null
                        && childFollowupForm.getContinuingCtx() == null
                        && childFollowupForm.getContinuingInh() == null
                        && childFollowupForm.getModernContraceptiveMethod() == null
                        && childFollowupForm.getEatingType() == null
//                        && child.getChildFollowupVisits() != null
                        ) {

                    insertVisit = false;

                } else {

                    if(child.getChildFollowupVisits() == null && childFollowupForm.getVisitDate() == null) {
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez renseigner les données de la visite de suivi de l'enfant SVP !");
                        hasErrors = true;
                    } else if (childFollowupForm.getFollowupResult() != null && childFollowupForm.getFollowupResultDate() == null) {
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez renseigner la date du résultat final du suivi SVP");
                        hasErrors = true;
                    }  else if (childFollowupForm.getArvProphylaxisGivenDate() != null) {
                        if (childFollowupForm.getArvProphylaxisGivenDate().before(child.getBirthDate())) {
                            session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Vous avez saisi une date de remise de prophylaxie avant la naissance de l'enfant !");
                            hasErrors = true;
                        }
                        if (childFollowupForm.getArvProphylaxisGivenDate().after(new Date())) {
                            session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Vous avez saisi une date de remise de prophylaxie supérieur à ce jour !");
                            hasErrors = true;
                        }
                    } else if (childFollowupForm.getFollowupResult() == null && childFollowupForm.getFollowupResultDate() != null) {
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez renseigner le résultat final du suivi SVP");
                        hasErrors = true;
                    } else if (childFollowupForm.getFollowupResult() != null &&
                            (childFollowupForm.getFollowupResult() == 4 || childFollowupForm.getFollowupResult() == 5)) {
                        if (childFollowupForm.getReferenceLocation().isEmpty()) {
                            session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez renseigner le site de référence ou de transfert pour le résultat suivi SVP");
                            hasErrors = true;
                        }
                    } else if (!childFollowupForm.getReferenceLocation().isEmpty()) {
                        if (childFollowupForm.getFollowupResult() != 4 && childFollowupForm.getFollowupResult() != 5) {
                            session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez choisir entre référé et transféré pour le résultat suivi SVP");
                            hasErrors = true;
                        }
                    }  else if (!childFollowupForm.getHivCareNumber().isEmpty()) {
                        if (childFollowupForm.getFollowupResult() != 3) {
                            session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Résultat ne dit pas qu'il est positif veuillez le modifier SVP");
                            hasErrors = true;
                        } else {
                            String childFollowupNumberRegExp = Context.getAdministrationService().getGlobalProperty("ptme.motherFollowupNumberFormat");
                            Pattern pattern = Pattern.compile(childFollowupNumberRegExp, Pattern.CASE_INSENSITIVE);
                            if (!pattern.matcher(childFollowupForm.getHivCareNumber()).matches()) {
                                session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Le numéro d'identification pour le résultat est invalide, le modifier SVP");
                                hasErrors = true;
                            } else {
                                existingChildPatient = getPreventTransmissionService().getPatientByIdentifier(childFollowupForm.getHivCareNumber());
                                if (existingChildPatient != null) {
                                    if (!existingChildPatient.getGender().equals(child.getGender()) ||
                                            !existingChildPatient.getFamilyName().equals(child.getFamilyName()) ||
                                            !existingChildPatient.getGivenName().equals(child.getGivenName()) ||
                                            !existingChildPatient.getBirthdate().equals(child.getBirthDate())) {
                                        hasErrors = true;
                                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Le numéro d'identification pour le résultat est attribué à un autre patient SVP");
                                    }
                                }
                            }
                        }

                    } else if (childFollowupForm.getVisitDate() == null) {
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "La date de visite doit être renseignée SVP");
                        hasErrors = true;
                    } else {

                        ChildFollowupVisit existingChildFollowupVisit = getPreventTransmissionService().getChildFollowupVisitByChildAndDate(child.getChildId(), childFollowupForm.getVisitDate());
                        if (existingChildFollowupVisit != null) {
                            if (childFollowupForm.getChildFollowupVisitId() == null) {
                                session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Une viste de cette date a déjà été renseignée !");
                                hasErrors = true;
                            } else {
                                Integer existingChildFollowupVisitId = existingChildFollowupVisit.getChildFollowupVisitId();
                                if (!childFollowupVisitId.equals(existingChildFollowupVisitId)) {
                                    session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Une viste de cette date a déjà été renseignée !");
                                    hasErrors = true;
                                }
                            }
                        } else if (childFollowupForm.getVisitDate().after(new Date())) {
                            session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Une date de visite supérieure à ce jour ne peut être pris en compte !");
                            hasErrors = true;
                        } else if (childFollowupForm.getVisitDate().before(child.getBirthDate())) {
                            session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Vous avez entré une date de visite antérieure à la date de naissance de l'enfant !");
                            hasErrors = true;
                        } else if (childFollowupForm.getAgeInDay() == null &&
                                childFollowupForm.getAgeInWeek() == null &&
                                childFollowupForm.getAgeInMonth() == null) {
                            session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez renseigner l'âge de l'enfant au moment de la visite SVP");
                            hasErrors = true;
                        } else if (childFollowupForm.getEatingType() == null) {
                            session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez renseigner le type d'alimentation visite SVP");
                            hasErrors = true;
                        }
                    }
                }

                if (childFollowupForm.getPcr1Result() != null && childFollowupForm.getPcr1SamplingDate() == null) {
                    session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez renseigner la date de PCR 1 SVP !");
                    hasErrors = true;
                } else if (childFollowupForm.getPcr2Result() != null && childFollowupForm.getPcr2SamplingDate() == null) {
                    session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez renseigner la date de PCR 2 SVP !");
                    hasErrors = true;
                } else if (childFollowupForm.getPcr3Result() != null && childFollowupForm.getPcr3SamplingDate() == null) {
                    session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez renseigner la date de PCR 3 SVP !");
                    hasErrors = true;
                } else if (childFollowupForm.getHivSerology1Result() != null && childFollowupForm.getHivSerology1Date() == null) {
                    session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez renseigner la date de sérologie 1 SVP !");
                    hasErrors = true;
                } else if (childFollowupForm.getHivSerology2Result() != null && childFollowupForm.getHivSerology2Date() == null) {
                    session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Veuillez renseigner la date de sérologie 2 SVP !");
                    hasErrors = true;
                } else if (childFollowupForm.getHivSerology2Date() != null) {
                    if (childFollowupForm.getHivSerology2Date().after(new Date())) {
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "La date de sérologie 2 supérieure à ce jour ne peut être pris en compte !");
                        hasErrors = true;
                    } else if (childFollowupForm.getHivSerology2Date().before(child.getBirthDate())) {
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Vous avez entré une date de sérologie 2 antérieure à la date de naissance de l'enfant !");
                        hasErrors = true;
                    }
                } else if (childFollowupForm.getHivSerology1Date() != null) {
                    if (childFollowupForm.getHivSerology1Date().after(new Date())) {
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "La date de sérologie 1 supérieure à ce jour ne peut être pris en compte !");
                        hasErrors = true;
                    } else if (childFollowupForm.getHivSerology1Date().before(child.getBirthDate())) {
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Vous avez entré une date de sérologie 1 antérieure à la date de naissance de l'enfant !");
                        hasErrors = true;
                    }
                } else if (childFollowupForm.getPcr1SamplingDate() != null) {
                    if (childFollowupForm.getPcr1SamplingDate().after(new Date())) {
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "La date de PCR 1 supérieure à ce jour ne peut être pris en compte !");
                        hasErrors = true;
                    } else if (childFollowupForm.getPcr1SamplingDate().before(child.getBirthDate())) {
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Vous avez entré une date de PCR 1 antérieure à la date de naissance de l'enfant !");
                        hasErrors = true;
                    }
                } else if (childFollowupForm.getPcr2SamplingDate() != null) {
                    if (childFollowupForm.getPcr2SamplingDate().after(new Date())) {
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "La date de PCR 2 supérieure à ce jour ne peut être pris en compte !");
                        hasErrors = true;
                    } else if (childFollowupForm.getPcr2SamplingDate().before(child.getBirthDate())) {
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Vous avez entré une date de PCR 2 antérieure à la date de naissance de l'enfant !");
                        hasErrors = true;
                    }
                } else if (childFollowupForm.getPcr3SamplingDate() != null) {
                    if (childFollowupForm.getPcr3SamplingDate().after(new Date())) {
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "La date de PCR 3 supérieure à ce jour ne peut être pris en compte !");
                        hasErrors = true;
                    } else if (childFollowupForm.getPcr3SamplingDate().before(child.getBirthDate())) {
                        session.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Vous avez entré une date de PCR 3 antérieure à la date de naissance de l'enfant !");
                        hasErrors = true;
                    }
                }

                if (hasErrors){
                    return null;
                }

                ChildFollowup childFollowup;
                if(childFollowupForm.getChildFollowupId() == null) {
                    childFollowup = childFollowupForm.getChildFollowup(new ChildFollowup());
                    childFollowup.setChild(child);
                } else {
                    childFollowup = childFollowupForm.getChildFollowup(getPreventTransmissionService().
                            getChildFollowupById(childFollowupForm.getChildFollowupId()));
                }

//                getPreventTransmissionService().saveChildFollowup(childFollowup);

                if(getPreventTransmissionService().saveChildFollowup(childFollowup) != null && insertVisit ) {
                    ChildFollowupVisit childFollowupVisit;
                    if (childFollowupForm.getChildFollowupVisitId() == null) {
                        childFollowupVisit = childFollowupForm.getChildFollowupVisit(new ChildFollowupVisit());
                        childFollowupVisit.setChild(child);
                    } else {
                        childFollowupVisit = childFollowupForm.getChildFollowupVisit(getPreventTransmissionService().
                                getChildFollowupVisitById(childFollowupForm.getChildFollowupVisitId()));
                    }
                    child = getPreventTransmissionService().saveChildFollowupVisit(childFollowupVisit).getChild();
                }

                if (childFollowupForm.getFollowupResult() != null) {
                    if (!childFollowupForm.getHivCareNumber().isEmpty() && child.getPatient() == null) {

                        if (existingChildPatient == null) {

                            Patient patient = new Patient();

                            patient.addIdentifier(new PatientIdentifier(childFollowupForm.getHivCareNumber(),
                                    Context.getPatientService().getPatientIdentifierType(3), getChosenLocation(null)));

                            patient.addName(new PersonName(child.getGivenName(), null, child.getFamilyName()));
                            patient.setBirthdate(child.getBirthDate());
                            patient.setGender(child.getGender());
                            patient.setDead(false);
                            patient.setCreator(Context.getAuthenticatedUser());
                            patient.setPersonCreator(patient.getCreator());
                            patient.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
                            patient.setPersonDateCreated(patient.getDateCreated());
                            patient.setVoided(false);
                            patient.setPersonVoided(patient.getVoided());

                            child.setPatient(Context.getPatientService().savePatient(patient));

                            if (child.getMother() != null) {
                                Relationship relationship = new Relationship(child.getMother(), getPreventTransmissionService().saveChild(child).getPatient(),
                                        Context.getPersonService().getRelationshipType(3));
                                relationship.setStartDate(child.getBirthDate());
                                relationship.setCreator(Context.getAuthenticatedUser());
                                relationship.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
                                relationship.setVoided(false);
                                Context.getPersonService().saveRelationship(relationship);
                            }
                        } else {
                            child.setPatient(existingChildPatient);
                            if (child.getMother() != null) {
                                Relationship relationship = new Relationship(child.getMother(), getPreventTransmissionService().saveChild(child).getPatient(),
                                        Context.getPersonService().getRelationshipType(3));
                                if (getPreventTransmissionService().getChildRelationship(child.getMother(), child.getPatient()) != null) {
                                    relationship.setStartDate(child.getBirthDate());
                                    relationship.setCreator(Context.getAuthenticatedUser());
                                    relationship.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
                                    relationship.setVoided(false);
                                    Context.getPersonService().saveRelationship(relationship);
                                }
                            }
                        }
                    } else if(childFollowupForm.getHivCareNumber().isEmpty() && child.getPatient() != null) {

                        Context.getPatientService().voidPatient(child.getPatient(), "not to be used again");
                        Context.getPersonService().voidPerson(child.getPatient(), "not to be used again");
                        if (child.getMother() != null) {
                            Relationship relationship = getPreventTransmissionService().getChildRelationship(child.getMother(), child.getPatient());
                            Context.getPersonService().voidRelationship(relationship, "not to be used again");
                        }
                        child.setPatient(null);
                        getPreventTransmissionService().saveChild(child);
                    }
                }

//                XStream xStream = new XStream(new DomDriver());
//                xStream.registerConverter(new ChildXml());
//                xStream.alias("child", Child.class);
//
//                if (child != null) {
//                    SerializedData data = getPreventTransmissionService().getSerializedDataByObjectUuid(child.getUuid());
//                    if (data == null) {
//                        data = new SerializedData();
//                    }
//
//                    data.setObjectUuid(child.getUuid());
//                    data.setSerializedXmlData(xStream.toXML(child));
//                    data.setPackageName(Child.class.getName());
//                    getPreventTransmissionService().saveSerializedData(data);
//                }

                session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Opération effectuée avec succès");

                assert child != null;
                return "redirect:/module/ptme/childFollowup.form?childFollowupNumber="+ child.getChildFollowupNumber();
            }

        }
        return null;
    }

    @RequestMapping(value = "/module/ptme/childFollowupManage.form", method = RequestMethod.GET)
    public void childFollowupManage(ModelMap modelMap,
                                    HttpServletRequest request,
                                    @RequestParam(required = false, defaultValue = "") String status,
                                    @RequestParam(required = false, defaultValue = "") String childFollowupNumber,
                                    @RequestParam(required = false, defaultValue = "") Date followupResultStartDate,
                                    @RequestParam(required = false, defaultValue = "") Date followupResultEndDate){
        if (Context.isAuthenticated()) {
            if (status != null) {
                List<ChildFollowupTransformer> childFollowupList = getPreventTransmissionService().getChildFollowupList(status, followupResultStartDate, followupResultEndDate);
                modelMap.addAttribute("childFollowupList", childFollowupList);

            }

            ManageChildFollowupForm manageChildFollowupForm = new ManageChildFollowupForm();
            manageChildFollowupForm.setStatus(status);
            if (followupResultEndDate != null) {
                manageChildFollowupForm.setFollowupResultEndDate(followupResultEndDate);
            }
            if (followupResultStartDate != null) {
                manageChildFollowupForm.setFollowupResultStartDate(followupResultStartDate);
            }

            if (!childFollowupNumber.isEmpty()) {
                Child child = getPreventTransmissionService().getChildByFollowupNumber(childFollowupNumber);
                if (child != null) {
                    modelMap.addAttribute("currentChild", child);
                    modelMap.addAttribute("patientInfo", getPatientInfo(child.getMother()));
                    modelMap.addAttribute("childFollowupVisitCount", child.getChildFollowupVisits().size());
                    modelMap.addAttribute("childFollowup", child.getChildFollowup());
                    modelMap.addAttribute("childFollowupVisits", getPreventTransmissionService().getChildFollowupVisitByChild(child.getChildId()));
                    modelMap.addAttribute("mode", "followup");
                }
            }
            modelMap.addAttribute("manageChildFollowupForm", manageChildFollowupForm);
            modelMap.addAttribute("status", status);
        }
    }

    @RequestMapping(value = "/module/ptme/childManage.form", method = RequestMethod.GET)
    public void childManage(HttpServletRequest request,
                            @RequestParam(required = false, defaultValue = "") Integer childId,
                            @RequestParam(required = false, defaultValue = "") String add,
                            @RequestParam(required = false, defaultValue = "") Integer delId,
                            ModelMap modelMap){

        if (!Context.isAuthenticated()){
            return;
        }

        HttpSession session = request.getSession();

        String mode = "list";

        if (delId != null) {
            Child child = getPreventTransmissionService().getChildById(delId);
            if (child != null) {
                child.setVoided(true);
                child.setDateVoided(new Date());
                getPreventTransmissionService().saveChild(child);
                session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Enfant exposé supprimé avec succès !");
            }
        }

        if (!add.isEmpty()) {
            mode = "form";
        }

        ChildForm childForm = new ChildForm();

        // System.out.println(childId + "----------------childId------------------");
        if (childId != null) {
            Child child = getPreventTransmissionService().getChildById(childId);
            // System.out.println(child + "---------------child-------------------");

            if (child == null) {
                mode = "list";
                // System.out.println("------------------Mode list----------------");
            } else {
                childForm.setChild(child);
                // System.out.println("------------------Mode Form----------------");
                mode = "form";
            }
        }

        if(mode.equals("form")){
            modelMap.addAttribute("childForm", childForm);
        }

        if (mode.equals("list")) {
            FindPregnantPatientForm findPregnantPatientForm = new FindPregnantPatientForm();

            List<Child> childList = getPreventTransmissionService().getChildList();

            modelMap.addAttribute("findPregnantPatientForm", findPregnantPatientForm);
            modelMap.addAttribute("childList", childList);

        }

        modelMap.addAttribute("mode", mode);
    }

    @RequestMapping(value = "/module/ptme/childManage.form", method = RequestMethod.POST)
    public String onSubmitChildManage(HttpServletRequest request,
                                      @RequestParam(required = false, defaultValue = "") Integer childId,
                                      ChildForm childForm,
                                      BindingResult result,
                                      ModelMap modelMap){

        if (Context.isAuthenticated()) {

            HttpSession session = request.getSession();

            modelMap.addAttribute("mode", "form");

            new ChildFormValidator().validate(childForm, result);

            if (!result.hasErrors())
            {

                Child existingChild = getPreventTransmissionService().getChildByFollowupNumber(childForm.getChildFollowupNumber());

                Child child = null;
                if(childForm.getChildId() == null) {
                    child = childForm.getChild(new Child());
                } else {
                    child = childForm.getChild(getPreventTransmissionService().getChildById(childForm.getChildId()));
                }

                if(existingChild != null) {
                    if (child.getChildId() == null){
                        session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Un autre enfant porte déjà ce numéro. Veuillez le modifier SVP !");
                        return null;
                    } else {
                        Integer existingChildId = existingChild.getChildId();
                        Integer currentChildId = child.getChildId();

                        if (!existingChildId.equals(currentChildId)) {
                            session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Un autre enfant porte déjà ce numéro. Veuillez le modifier SVP !");
                            return null;
                        }
                    }
                }

                if(getPreventTransmissionService().saveChild(child) != null) {

//                    SerializedData data = getPreventTransmissionService().getSerializedDataByObjectUuid(child.getUuid());
//                    if (data == null) {
//                        data = new SerializedData();
//                    }
//
//                    XStream xStream = new XStream(new DomDriver());
//                    xStream.registerConverter(new ChildXml());
//                    xStream.alias("child", Child.class);
//                    data.setObjectUuid(child.getUuid());
//                    data.setSerializedXmlData(xStream.toXML(child));
//                    data.setPackageName(Child.class.getName());
//                    getPreventTransmissionService().saveSerializedData(data);

                    if(childForm.getChildId() != null) {
                        session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Enfant exposé mis à jour avec succès");
                    } else {
                        session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Enfant exposé enregistré avec succès");
                    }
                }

                modelMap.addAttribute("mode", "list");
                return "redirect:/module/ptme/childManage.form";
            }
            return null;
        }
        return null;
    }



}
