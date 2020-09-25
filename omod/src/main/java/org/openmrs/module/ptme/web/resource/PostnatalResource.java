package org.openmrs.module.ptme.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.Postnatal;
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

@Resource(name = RestConstants.VERSION_1 + PreventTransmissionResourceController.PTME_REST_NAMESPACE + "/postnatal",
        supportedClass = Postnatal.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*", "1.11.*", "1.12.*"})
public class PostnatalResource extends DelegatingCrudResource<Postnatal> {
    @Override
    public Postnatal getByUniqueId(String s) {
        return getService().getPostnatalConsultationByUuid(s);
    }

    @Override
    protected void delete(Postnatal postnatal, String s, RequestContext requestContext) throws ResponseException {
        postnatal.setVoided(true);
        postnatal.setVoidReason(s);
        getService().savePostnatalConsultation(postnatal);
    }

    @Override
    public Postnatal newDelegate() {
        return new Postnatal();
    }

    @Override
    public Postnatal save(Postnatal postnatal) {
        return getService().savePostnatalConsultation(postnatal);
    }

    @Override
    public void purge(Postnatal postnatal, RequestContext requestContext) throws ResponseException {
        // not used for instance
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        DelegatingResourceDescription description = null;

        if (representation instanceof FullRepresentation) {
            description = new DelegatingResourceDescription();
            description.addProperty("consultationDate");
            description.addProperty("pregnantPatient", Representation.FULL);
            description.addProperty("location", Representation.REF);
            description.addProperty("uuid");
            description.addSelfLink();
        } else if (representation instanceof DefaultRepresentation) {
            description = new DelegatingResourceDescription();
            description.addProperty("consultationDate");
            description.addProperty("pregnantPatient", Representation.DEFAULT);
            description.addProperty("location", Representation.DEFAULT);
            description.addProperty("uuid");
            description.addSelfLink();
        }
        return description;
    }

    public PreventTransmissionService getService() {
        return Context.getService(PreventTransmissionService.class);
    }
}
