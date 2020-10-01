package org.openmrs.module.ptme.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.MotherFollowupVisit;
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

@Resource(name = RestConstants.VERSION_1 + PreventTransmissionResourceController.PTME_REST_NAMESPACE + "/motherFollowupVisit",
        supportedClass = MotherFollowupVisit.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*", "1.11.*", "1.12.*"})
public class MotherFollowupVisitResource extends DelegatingCrudResource<MotherFollowupVisit> {
    @Override
    public MotherFollowupVisit getByUniqueId(String s) {
        return getService().getMotherFollowupVisitByUuid(s);
    }

    @Override
    protected void delete(MotherFollowupVisit motherFollowupVisit, String s, RequestContext requestContext) throws ResponseException {
        motherFollowupVisit.setVoided(true);
        motherFollowupVisit.setVoidReason(s);
        getService().saveMotherFollowupVisit(motherFollowupVisit);
    }

    @Override
    public MotherFollowupVisit newDelegate() {
        return new MotherFollowupVisit();
    }

    @Override
    public MotherFollowupVisit save(MotherFollowupVisit motherFollowupVisit) {
        return getService().saveMotherFollowupVisit(motherFollowupVisit);
    }

    @Override
    public void purge(MotherFollowupVisit motherFollowupVisit, RequestContext requestContext) throws ResponseException {
        getService().removeMotherFollowupVisit(motherFollowupVisit);
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        DelegatingResourceDescription description = null;

        if (representation instanceof FullRepresentation) {
            description = getDelegatingResourceDescription();
            description.addProperty("motherFollowup", Representation.FULL);
            description.addProperty("location", Representation.REF);
            description.addSelfLink();
        }else if (representation instanceof DefaultRepresentation) {
            description = getDelegatingResourceDescription();
            description.addProperty("location", Representation.DEFAULT);
            description.addSelfLink();
        }
        return description;
    }

    private DelegatingResourceDescription getDelegatingResourceDescription() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addProperty("visitDate");
        description.addProperty("gestationalAge");
        description.addProperty("continuingArv");
        description.addProperty("continuingCtx");
        description.addProperty("uuid");
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() throws ResourceDoesNotSupportOperationException {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addRequiredProperty("motherFollowup");
        description.addRequiredProperty("location");
        description.addRequiredProperty("visitDate");

        description.addProperty("visitDate");
        description.addProperty("gestationalAge");
        description.addProperty("continuingArv");
        description.addProperty("continuingCtx");
        description.addProperty("uuid");

        return description;
    }

    protected PreventTransmissionService getService() {
        return Context.getService(PreventTransmissionService.class);
    }
}
