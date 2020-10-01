package org.openmrs.module.ptme.web.resource;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.Child;
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
import org.openmrs.module.webservices.rest.web.response.ConversionException;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + PreventTransmissionResourceController.PTME_REST_NAMESPACE + "/child",
        supportedClass = Child.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*", "1.11.*", "1.12.*"})
public class ChildResource extends DelegatingCrudResource<Child> {
    @Override
    public Child getByUniqueId(String s) {
        return getService().getChildByUuid(s);
    }

    @Override
    protected void delete(Child child, String s, RequestContext requestContext) throws ResponseException {
        purge(child, requestContext);
    }

    @Override
    public Child newDelegate() {
        return new Child();
    }

    @Override
    public Child save(Child child) {
        return getService().saveChild(child);
    }

    @Override
    public void purge(Child child, RequestContext requestContext) throws ResponseException {
        if (child != null) {
            getService().removeChild(child);
        }
    }

    @Override
    public void setProperty(Object instance, String propertyName, Object value) throws ConversionException {
        {
            Class<?> definitionClassType = null;
            try {
                if (propertyName.equals("mother") || propertyName.equals("patient")) {
                    definitionClassType = Patient.class;
                }
            } catch (Exception ex) {
                throw new ConversionException(propertyName, ex);
            }

            super.setProperty(instance, propertyName, value);
        }
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        DelegatingResourceDescription description = null;

        if (representation instanceof FullRepresentation) {
            description = new DelegatingResourceDescription();
            description.addProperty("mother", Representation.REF);
            description.addProperty("childFollowupNumber");
            description.addProperty("birthDate");
            description.addProperty("gender");
            description.addProperty("familyName");
            description.addProperty("givenName");
            description.addProperty("patient", Representation.REF);
            description.addProperty("location", Representation.REF);
            description.addProperty("uuid");
            description.addProperty("creator", Representation.REF);
            description.addProperty("dateCreated");
            description.addProperty("changedBy", Representation.REF);
            description.addProperty("dateChanged");
            description.addProperty("voided");
            description.addProperty("voidedBy", Representation.REF);
            description.addProperty("dateVoided");
            description.addProperty("voidReason");
            description.addSelfLink();
            description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
        }else if (representation instanceof DefaultRepresentation) {
            description = new DelegatingResourceDescription();
            description.addProperty("mother", Representation.DEFAULT);
            description.addProperty("childFollowupNumber");
            description.addProperty("birthDate");
            description.addProperty("gender");
            description.addProperty("familyName");
            description.addProperty("givenName");
            description.addProperty("patient", Representation.DEFAULT);
            description.addProperty("location", Representation.DEFAULT);
            description.addProperty("uuid");
            description.addProperty("creator", Representation.DEFAULT);
            description.addProperty("dateCreated");
            description.addProperty("changedBy", Representation.DEFAULT);
            description.addProperty("dateChanged");
            description.addProperty("voided");
            description.addProperty("voidedBy", Representation.DEFAULT);
            description.addProperty("dateVoided");
            description.addProperty("voidReason");
            description.addSelfLink();
        }
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() throws ResourceDoesNotSupportOperationException {
        DelegatingResourceDescription description = new DelegatingResourceDescription();

        description.addRequiredProperty("childFollowupNumber");
        description.addRequiredProperty("birthDate");
        description.addRequiredProperty("gender");
        description.addRequiredProperty("familyName");
        description.addRequiredProperty("givenName");
        description.addRequiredProperty("location");

        description.addProperty("mother");
        description.addProperty("patient");
        description.addProperty("uuid");

        return description;
    }

    protected PreventTransmissionService getService() {
        return Context.getService(PreventTransmissionService.class);
    }
}
