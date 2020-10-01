package org.openmrs.module.ptme.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.Birth;
import org.openmrs.module.ptme.Prenatal;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.web.controller.PreventTransmissionResourceController;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + PreventTransmissionResourceController.PTME_REST_NAMESPACE + "/prenatal",
        supportedClass = Prenatal.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*", "1.11.*", "1.12.*"})
public class PrenatalResource extends DelegatingCrudResource<Prenatal> {
    @Override
    public Prenatal getByUniqueId(String s) {
        return getService().getPrenatalConsultationByUuid(s);
    }

    @Override
    protected void delete(Prenatal prenatal, String s, RequestContext requestContext) throws ResponseException {
        prenatal.setVoided(true);
        prenatal.setVoidReason(s);
        getService().savePrenatalConsultation(prenatal);
    }

    @Override
    public Prenatal newDelegate() {
        return new Prenatal();
    }

    @Override
    public Prenatal save(Prenatal prenatal) {
        return getService().savePrenatalConsultation(prenatal);
    }

    @Override
    public void purge(Prenatal prenatal, RequestContext requestContext) throws ResponseException {
        // not used for instance
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        DelegatingResourceDescription description = null;

        if (representation instanceof FullRepresentation) {
            description = new DelegatingResourceDescription();
            description.addProperty("pregnantPatient", Representation.FULL);
            description.addProperty("consultationDate");
            description.addProperty("rank");
            description.addProperty("weekOfAmenorrhea");
            description.addProperty("spousalScreening");
            description.addProperty("spousalScreeningResult");
            description.addProperty("appointmentDate");
            description.addProperty("location", Representation.REF);
            description.addProperty("uuid");
            description.addSelfLink();
        } else if (representation instanceof DefaultRepresentation) {
            description = new DelegatingResourceDescription();
            description.addProperty("pregnantPatient", Representation.DEFAULT);
            description.addProperty("consultationDate");
            description.addProperty("rank");
            description.addProperty("weekOfAmenorrhea");
            description.addProperty("spousalScreening");
            description.addProperty("spousalScreeningResult");
            description.addProperty("appointmentDate");
            description.addProperty("location", Representation.DEFAULT);
            description.addProperty("uuid");
            description.addSelfLink();
        }
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() throws ResourceDoesNotSupportOperationException {
        DelegatingResourceDescription description = new DelegatingResourceDescription();

        description.addRequiredProperty("pregnantPatient");
        description.addRequiredProperty("consultationDate");
        description.addRequiredProperty("rank");
        description.addRequiredProperty("location");
        description.addRequiredProperty("weekOfAmenorrhea");
        description.addRequiredProperty("appointmentDate");

        description.addProperty("spousalScreening");
        description.addProperty("spousalScreeningResult");
        description.addProperty("uuid");

        return description;
    }

    public PreventTransmissionService getService() {
        return Context.getService(PreventTransmissionService.class);
    }
}
