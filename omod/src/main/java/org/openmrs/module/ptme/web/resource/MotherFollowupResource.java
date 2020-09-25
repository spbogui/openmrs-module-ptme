package org.openmrs.module.ptme.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.MotherFollowup;
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
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + PreventTransmissionResourceController.PTME_REST_NAMESPACE + "/motherFollowup",
        supportedClass = MotherFollowup.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*", "1.11.*", "1.12.*"})
public class MotherFollowupResource extends DelegatingCrudResource<MotherFollowup> {
    @Override
    public MotherFollowup getByUniqueId(String s) {
        return getService().getMotherFollowupByUuid(s);
    }

    @Override
    protected void delete(MotherFollowup motherFollowup, String s, RequestContext requestContext) throws ResponseException {
        motherFollowup.setVoided(true);
        motherFollowup.setVoidReason(s);
        getService().saveMotherFollowup(motherFollowup);
    }

    @Override
    public MotherFollowup newDelegate() {
        return new MotherFollowup();
    }

    @Override
    public MotherFollowup save(MotherFollowup motherFollowup) {
        return getService().saveMotherFollowup(motherFollowup);
    }

    @Override
    public void purge(MotherFollowup motherFollowup, RequestContext requestContext) throws ResponseException {
        getService().removeMotherFollowup(motherFollowup);
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        DelegatingResourceDescription description = null;

        if (representation instanceof FullRepresentation) {
            description = getDelegatingResourceDescription();
            description.addProperty("pregnantPatient", Representation.FULL);
            description.addProperty("location", Representation.FULL);
            description.addSelfLink();
        }else if (representation instanceof DefaultRepresentation) {
            description = getDelegatingResourceDescription();
            description.addProperty("pregnantPatient", Representation.DEFAULT);
            description.addProperty("location", Representation.DEFAULT);
            description.addSelfLink();
        }
        return description;
    }

    private DelegatingResourceDescription getDelegatingResourceDescription() {
        DelegatingResourceDescription description;
        description = new DelegatingResourceDescription();
        description.addProperty("startDate");
        description.addProperty("endDate");
        description.addProperty("arvStatusAtRegistering");
        description.addProperty("estimatedDeliveryDate");
        description.addProperty("spousalScreeningResult");
        description.addProperty("spousalScreeningDate");
        description.addProperty("pregnancyOutcome");
        description.addProperty("deliveryType");
        description.addProperty("motherFollowupVisits");
        description.addProperty("uuid");
        return description;
    }

    protected PreventTransmissionService getService() {
        return Context.getService(PreventTransmissionService.class);
    }
}
