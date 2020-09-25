package org.openmrs.module.ptme.web.resource;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.PregnantPatient;
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

@Resource(name = RestConstants.VERSION_1 + PreventTransmissionResourceController.PTME_REST_NAMESPACE + "/pregnantPatient",
        supportedClass = PregnantPatient.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*", "1.11.*", "1.12.*"})
public class PregnantPatientResource extends DelegatingCrudResource<PregnantPatient> {
    @Override
    public PregnantPatient getByUniqueId(String s) {
        return getService().getPregnantPatientByUuid(s);
    }

    @Override
    protected void delete(PregnantPatient pregnantPatient, String s, RequestContext requestContext) throws ResponseException {
        pregnantPatient.setVoidReason(s);
        pregnantPatient.setVoided(true);
        getService().savePregnantPatient(pregnantPatient);
    }

    @Override
    public PregnantPatient newDelegate() {
        return new PregnantPatient();
    }

    @Override
    public PregnantPatient save(PregnantPatient pregnantPatient) {
        return getService().savePregnantPatient(pregnantPatient);
    }

    @Override
    public void purge(PregnantPatient pregnantPatient, RequestContext requestContext) throws ResponseException {
        if (pregnantPatient != null) {
            getService().removePregnantPatient(pregnantPatient);
        }
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        DelegatingResourceDescription description = null;

        if (representation instanceof FullRepresentation) {
            description = new DelegatingResourceDescription();
            description.addProperty("familyName");
            description.addProperty("givenName");
            description.addProperty("pregnantNumber");
            description.addProperty("age");
            description.addProperty("uuid");
            description.addProperty("screeningNumber");
            description.addProperty("hivCareNumber");
            description.addProperty("maritalStatus");
            description.addProperty("patient", Representation.FULL);
            description.addProperty("location", Representation.REF);
            description.addSelfLink();
        }else if (representation instanceof DefaultRepresentation) {
            description = new DelegatingResourceDescription();
            description.addProperty("familyName");
            description.addProperty("givenName");
            description.addProperty("pregnantNumber");
            description.addProperty("hivCareNumber");
            description.addProperty("age");
            description.addProperty("uuid");
            description.addProperty("patient", Representation.DEFAULT);
            description.addProperty("location", Representation.DEFAULT);
            description.addSelfLink();
        }
        return description;
    }

    protected PreventTransmissionService getService() {
        return Context.getService(PreventTransmissionService.class);
    }
}
