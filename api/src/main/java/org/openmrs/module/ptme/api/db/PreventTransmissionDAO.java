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
package org.openmrs.module.ptme.api.db;

import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Relationship;
import org.openmrs.module.ptme.*;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.utils.*;

import java.util.Date;
import java.util.List;

/**
 *  Database methods for {@link PreventTransmissionService}.
 */
public interface PreventTransmissionDAO {
	
	/**
	 * Pregnant Patient
	 */

    List<PregnantPatient> getAllPregnantPatient();
    PregnantPatient getPregnantPatientById(Integer id);
    PregnantPatient savePregnantPatient(PregnantPatient pregnantPatient);
    void deletePregnantPatient(PregnantPatient pregnantPatient);
    PregnantPatient voidPregnantPatient(Integer id);
    List<PregnantPatient> getAllPregnantPatientByVoided(Boolean voidedIncluded);
    PregnantPatient getPregnantPatientByPregnantNumber(String pregnantNumber);
    PregnantPatient getPregnantPatientByHivCareNumber(String hivCareNumber);


    /**
     * Hiv Service
     */
    List<HivService> getAllHivService();
    HivService getHivServiceById(Integer id);
    HivService saveHivService(HivService hivService);
    void deleteHivService(HivService hivService);
//    HivService voidHivService(Integer id);
    List<HivService> getAllHivServiceByVoided(Boolean voidedIncluded);

    /***
     * Consultation
     */
    Birth saveBirthConsultation(Birth birth);
    Prenatal savePrenatalConsultation(Prenatal prenatal);
    Postnatal savePostnatalConsultation(Postnatal postnatal);
//    Birth updateBirthConsultation(Integer id);
//    Prenatal updatePrenatalConsultation(Integer id);
//    Postnatal updatePostnatalConsultation(Integer id);
    Consultation getConsultation(Integer id);
    List<Consultation> getAllConsultations();
    List<Consultation> getAllConsultationsByDate(Date currentDate, Boolean voided);
    List<ConsultationWithType> getConsultationsByDate(Date currentDate, Boolean voided);
    List<Consultation> getConsultationsByDate(Date startDate, Date endDate);
    List<Birth> getAllBirthConsultation();
    List<Prenatal> getAllPrenatalConsultation();
    List<Postnatal> getAllPostnatalConsultation();
    List<Birth> getBirthConsultationsByDate(Date sDate, Date eDate);
    List<Prenatal> getPrenatalConsultationsByDate(Date startDate, Date endDate);
    List<Postnatal> getPostnatalConsultationsByDate(Date sDate, Date eDate);
    Birth getBirthConsultation(Integer id);
    Prenatal getPrenatalConsultation (Integer id);
    Postnatal getPostnatalConsultation (Integer id);

    List<Prenatal> getPrenatalConsultationsByPregnantPatientNumber(String pregnantNumber);
    List<Postnatal> getPostnatalConsultationsByPregnantPatientNumber(String pregnantNumber);

    // Mother followup
    MotherFollowup getCurrentMotherFollowupByPregnantPatient(PregnantPatient pregnantPatient);
    MotherFollowup getMotherFollowupById(Integer motherFollowupId);
    MotherFollowupVisit getMotherFollowUpVisitById(Integer id);
    MotherFollowup saveMotherFollowup(MotherFollowup motherFollowup);
    void removeMotherFollowupVisit(MotherFollowupVisit motherFollowupVisit);
    void removeMotherFollowup(MotherFollowup motherFollowup);
    MotherFollowupVisit saveMotherFollowupVisit(MotherFollowupVisit motherFollowupVisit);
    MotherFollowupVisit getPregnantPatientFollowupByDate(Integer pregnantPatientId, Date visitDate);
    MotherFollowupVisit getEarlierPregnantPatientFollowupVisitForFollowup(Integer motherFollowupId);

    Child getChildByFollowupNumber(String childFollowupNumber);
    List<PregnantPatientToFollow> getPregnantPatientFollowupList();

    Patient getPatientByIdentifier(String identifier);

    List<MotherFollowupCurrentlyOn> getMotherFollowupCurrentlyOnList(Date startDate, String status, Integer pregnancyOutcome, Date endDate);
    List<MotherFollowupCurrentlyOn> getMotherFollowupList(Date startDate, Date endDate, String status, Integer pregnancyOutcome, String startOrEnd);

        // Child Followup
    List<Child> getChildList();
    Child getChildById(Integer childId);

    Child saveChild(Child child);

    ChildFollowup getChildFollowupById(Integer childFollowupId);

    ChildFollowupVisit getChildFollowupVisitById(Integer childFollowupVisitId);

    ChildFollowup saveChildFollowup(ChildFollowup childFollowup);

    ChildFollowupVisit saveChildFollowupVisit(ChildFollowupVisit childFollowupVisit);

    ChildFollowupVisit getChildFollowupVisitByChildAndDate(Integer childId, Date visitDate);

    List<ChildFollowupVisit> getChildFollowupVisitByChild(Integer childId);

    void deleteChildFollowupVisit(ChildFollowupVisit childFollowupVisit);

    List<ChildFollowupTransformer> getChildFollowupList(String status, Date startDate, Date endDate);

    void deleteChildFollowup(ChildFollowup childFollowup);

    List<MotherFollowupVisit> getMotherFollowupVisitByPatientAndFollowup(MotherFollowup motherFollowup);

    Relationship getChildRelationship(Patient mother, Patient patient);

    Consultation getPatientConsultationByDate(Integer pregnantPatientId, Date consultationDate);

    /****
     * Home Services
     */
    Integer getNumberOfPrenatalConsultation(String prenatalConsultationRank, Date startDate, Date endDate);
    Integer getNumberOfPrenatalConsultationARV(String prenatalConsultationRank, Date startDate, Date endDate);
    Integer getNumberOfBirthConsultationARV(Date startDate, Date endDate);
    Integer getNumberOfBirthConsultationLocation(Date startDate, Date endDate, String location);
    Integer getNumberOfPostnatalConsultation(Date startDate, Date endDate);

    List<MotherFollowupAppointment> getPregnantPatientsAppointment();
    List<MotherFollowupAppointment> getPregnantPatientsAppointmentMissed();
    List<MotherFollowupAppointment> getPregnantPatientsAppointmentForCV();
    List<ChildFollowupAppointment> getChildByAppointment();
    List<ChildFollowupAppointment> getChildByAppointmentMissed();
    List<ChildFollowupAppointment> getChildByAppointmentPcr(String days, Integer pcrType);
    List<ChildPcrResultWaitingTransformer> getChildPcrResultWaiting();

    Boolean isDead(Patient patient);
    Boolean isTransferred(Patient patient);
    Boolean isDeclaredNegative(Patient patient);


    /****
     * Reporting bloc
     */

    // Indicators
    List<ReportingIndicator> getAllIndicators();
    List<ReportingIndicator> getAllIndicators(Boolean includeVoided);
    ReportingIndicator getIndicatorById(Integer indicatorId);
    ReportingIndicator saveReportingIndicator(ReportingIndicator indicator);
    Boolean removeIndicator(Integer indicatorId);
    ReportingIndicator voidIndicator(Integer indicatorId);
    ReportingIndicator getIndicatorByUuid(String uuid);

    // DataSet Service
    List<ReportingDataset> getAllDatasets();
    List<ReportingDataset> getAllDatasets(Boolean includeVoided);
    ReportingDataset getDatasetById(Integer indicatorId);
    ReportingDataset saveReportingDataset(ReportingDataset dataset);
    Boolean removeDataset(Integer datasetId);
    ReportingDataset voidDataset(Integer datasetId);

    // Report Service
    List<ReportingReport> getAllReports();
    List<ReportingReport> getAllReports(Boolean includeVoided);
    ReportingReport getReportById(Integer reportId);
    ReportingReport saveReportingReport(ReportingReport report);
    Boolean removeReport(Integer reportId);
    ReportingReport voidReport(Integer reportId);

    // Template Service
    List<ReportingTemplate> getAllTemplates();
    List<ReportingTemplate> getAllTemplates(Boolean includeVoided);
    ReportingTemplate getTemplateById(Integer templateId);
    ReportingTemplate getTemplateByName(String name);
    ReportingTemplate saveReportingTemplate(ReportingTemplate template);
    Boolean removeTemplate(Integer templateId);
    ReportingTemplate voidTemplate(Integer templateId);

    /**
     * End Reporting bloc
     */

    /**
     * Serialized Data
     */
    SerializedData getSerializedDataById(Integer id);
    SerializedData getSerializedDataByObjectUuid(String objectUuid);
    List<SerializedData> getAllSerializedData();
    SerializedData saveSerializedData(SerializedData serializedData);
    Boolean removeSerializedDataById(Integer id);

    ReportingReportGeneration getGeneratedReport(Integer generatedReportId);
    String getGeneratedReportXmlString(Date startDate, Date endDate, Integer reportId, String location);
    ReportingReportGeneration saveGenerationReport(ReportingReportGeneration reportingReportGeneration);
    List<ReportingReportGeneration> getAllGeneratedReport(Boolean includeVoided);
    ReportingDataset getDatasetByUuid(String uuid);
    Boolean removeGeneratedReport(Integer delId);
    Location getLocationByName(String name);
    ReportingIndicator getIndicatorByName(String name);
    ReportingIndicator getIndicatorByCode(String code);
    ReportingReportGeneration getGeneratedReportByName(String name);
    ReportingReportGeneration getGeneratedReportByNameAndReportId(String name, Integer reportId);
    Child getChildByUuid(String uuid);
    void removeChild(Child child);

    PregnantPatient getPregnantPatientByUuid(String s);
    void removePregnantPatient(PregnantPatient pregnantPatient);

    ChildFollowup getChildFollowupByUuid(String s);

    Birth getBirthConsultationByUuid(String uuid);

    Prenatal getPrenatalConsultationByUuid(String uuid);

    Postnatal getPostnatalConsultationByUuid(String uuid);

    HivService getHivServiceByUuid(String uuid);

    ChildFollowupVisit getChildFollowupVisitByUuid(String uuid);
    MotherFollowup getMotherFollowupByUuid(String uuid);
    MotherFollowupVisit getMotherFollowupVisitByUuid(String uuid);
}
