package org.openmrs.module.ptme.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.Birth;
import org.openmrs.module.ptme.ChildFollowup;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.web.controller.PreventTransmissionResourceController;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + PreventTransmissionResourceController.PTME_REST_NAMESPACE + "/birth",
        supportedClass = Birth.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*", "1.11.*", "1.12.*"})
public class BirthResource extends DelegatingCrudResource<Birth> {
    @Override
    public Birth getByUniqueId(String s) {
        return getService().getBirthConsultationByUuid(s);
    }

    @Override
    protected void delete(Birth birth, String s, RequestContext requestContext) throws ResponseException {
        birth.setVoided(true);
        birth.setVoidReason(s);
        getService().saveBirthConsultation(birth);
    }

    @Override
    public Birth newDelegate() {
        return new Birth();
    }

    @Override
    public Birth save(Birth birth) {
        return getService().saveBirthConsultation(birth);
    }

    @Override
    public void purge(Birth birth, RequestContext requestContext) throws ResponseException {
        // not used for instance
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        DelegatingResourceDescription description = null;

        if (representation instanceof FullRepresentation) {
            description = new DelegatingResourceDescription();
            description.addProperty("consultationDate");
            description.addProperty("pregnantPatient", Representation.FULL);
            description.addProperty("deliveryDate");
            description.addProperty("homeBirth");
            description.addProperty("pregnancyIssue");
            description.addProperty("childState");
            description.addProperty("location", Representation.REF);
            description.addProperty("uuid");
            description.addSelfLink();
        }else if (representation instanceof DefaultRepresentation) {
            description = new DelegatingResourceDescription();

            description.addProperty("consultationDate");
            description.addProperty("pregnantPatient", Representation.DEFAULT);
            description.addProperty("deliveryDate");
            description.addProperty("homeBirth");
            description.addProperty("pregnancyIssue");
            description.addProperty("childState");
            description.addProperty("location", Representation.REF);
            description.addProperty("uuid");
            description.addSelfLink();
        }
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() throws ResourceDoesNotSupportOperationException {
        DelegatingResourceDescription description = new DelegatingResourceDescription();

        description.addRequiredProperty("consultationDate");
        description.addRequiredProperty("pregnantPatient");
        description.addRequiredProperty("deliveryDate");
        description.addRequiredProperty("homeBirth");
        description.addRequiredProperty("pregnancyIssue");
        description.addRequiredProperty("childState");
        description.addRequiredProperty("location");
        description.addProperty("uuid");
        return super.getCreatableProperties();
    }

    public PreventTransmissionService getService() {
        return Context.getService(PreventTransmissionService.class);
    }
}
