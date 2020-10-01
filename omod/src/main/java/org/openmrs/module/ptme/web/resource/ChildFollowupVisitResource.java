package org.openmrs.module.ptme.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ChildFollowupVisit;
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

@Resource(name = RestConstants.VERSION_1 + PreventTransmissionResourceController.PTME_REST_NAMESPACE + "/childFollowupVisit",
        supportedClass = ChildFollowupVisit.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*", "1.11.*", "1.12.*"})
public class ChildFollowupVisitResource extends DelegatingCrudResource<ChildFollowupVisit> {
    @Override
    public ChildFollowupVisit getByUniqueId(String s) {
        return getService().getChildFollowupVisitByUuid(s);
    }

    @Override
    protected void delete(ChildFollowupVisit childFollowupVisit, String s, RequestContext requestContext) throws ResponseException {
        childFollowupVisit.setVoided(true);
        childFollowupVisit.setVoidReason(s);
        getService().saveChildFollowupVisit(childFollowupVisit);
    }

    @Override
    public ChildFollowupVisit newDelegate() {
        return new ChildFollowupVisit();
    }

    @Override
    public ChildFollowupVisit save(ChildFollowupVisit childFollowupVisit) {
        return getService().saveChildFollowupVisit(childFollowupVisit);
    }

    @Override
    public void purge(ChildFollowupVisit childFollowupVisit, RequestContext requestContext) throws ResponseException {
        getService().deleteChildFollowupVisit(childFollowupVisit);
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        DelegatingResourceDescription description = null;

        if (representation instanceof FullRepresentation) {
            description = getDelegatingResourceDescription();
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
        DelegatingResourceDescription description;
        description = new DelegatingResourceDescription();
        description.addProperty("child");
        description.addProperty("visitDate");
        description.addProperty("arvProphylaxisGivenDate");
        description.addProperty("modernContraceptiveMethod");
        description.addProperty("ageInDay");
        description.addProperty("ageInMonth");
        description.addProperty("ageInWeek");
        description.addProperty("eatingType");
        description.addProperty("continuingCtx");
        description.addProperty("continuingInh");
        description.addProperty("uuid");
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() throws ResourceDoesNotSupportOperationException {
        DelegatingResourceDescription description = new DelegatingResourceDescription();

        description.addRequiredProperty("child");
        description.addRequiredProperty("visitDate");
        description.addRequiredProperty("ageInDay");
        description.addRequiredProperty("ageInMonth");
        description.addRequiredProperty("ageInWeek");
        description.addRequiredProperty("eatingType");
        description.addRequiredProperty("location");

        description.addProperty("arvProphylaxisGivenDate");
        description.addProperty("modernContraceptiveMethod");
        description.addProperty("continuingCtx");
        description.addProperty("continuingInh");
        description.addProperty("uuid");

        return description;
    }

    protected PreventTransmissionService getService() {
        return Context.getService(PreventTransmissionService.class);
    }
}
