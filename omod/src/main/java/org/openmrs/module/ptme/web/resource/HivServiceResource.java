package org.openmrs.module.ptme.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.HivService;
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
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + PreventTransmissionResourceController.PTME_REST_NAMESPACE + "/hivService",
        supportedClass = HivService.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*", "1.11.*", "1.12.*"})
public class HivServiceResource extends DelegatingCrudResource<HivService> {
    @Override
    public HivService getByUniqueId(String s) {
        return getService().getHivServiceByUuid(s);
    }

    @Override
    protected void delete(HivService hivService, String s, RequestContext requestContext) throws ResponseException {
        hivService.setVoided(true);
        hivService.setVoidReason(s);
        getService().saveHivService(hivService);
    }

    @Override
    public HivService newDelegate() {
        return new HivService();
    }

    @Override
    public HivService save(HivService hivService) {
        return getService().saveHivService(hivService);
    }

    @Override
    public void purge(HivService hivService, RequestContext requestContext) throws ResponseException {
        // not used for instance
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        DelegatingResourceDescription description = null;

        if (representation instanceof FullRepresentation) {
            description = getDelegatingResourceDescription();
            description.addProperty("location", Representation.REF);
            description.addSelfLink();
        } else if (representation instanceof DefaultRepresentation) {
            description = getDelegatingResourceDescription();
            description.addProperty("location", Representation.DEFAULT);
            description.addSelfLink();
        }
        return description;
    }

    private DelegatingResourceDescription getDelegatingResourceDescription() {
        DelegatingResourceDescription description;
        description = new DelegatingResourceDescription();
        description.addProperty("consultation");
        description.addProperty("hivStatusAtReception");
        description.addProperty("testProposal");
        description.addProperty("testResult");
        description.addProperty("resultAnnouncement");
        description.addProperty("arvDiscount");
        description.addProperty("childArvProphylaxis");
        description.addProperty("arvStatus");
        description.addProperty("arvTreatment");
        description.addProperty("uuid");
        return description;
    }

    public PreventTransmissionService getService() {
        return Context.getService(PreventTransmissionService.class);
    }
}
