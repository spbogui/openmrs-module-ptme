package org.openmrs.module.ptme.web.resource;

import org.openmrs.api.context.Context;
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
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + PreventTransmissionResourceController.PTME_REST_NAMESPACE + "/childFollowup",
        supportedClass = ChildFollowup.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*", "1.11.*", "1.12.*"})
public class ChildFollowupResource extends DelegatingCrudResource<ChildFollowup> {
    @Override
    public ChildFollowup getByUniqueId(String s) {
        return getService().getChildFollowupByUuid(s);
    }

    @Override
    protected void delete(ChildFollowup childFollowup, String s, RequestContext requestContext) throws ResponseException {
        childFollowup.setVoided(true);
        childFollowup.setVoidReason(s);
        getService().saveChildFollowup(childFollowup);
    }

    @Override
    public ChildFollowup newDelegate() {
        return new ChildFollowup();
    }

    @Override
    public ChildFollowup save(ChildFollowup childFollowup) {
        return getService().saveChildFollowup(childFollowup);
    }

    @Override
    public void purge(ChildFollowup childFollowup, RequestContext requestContext) throws ResponseException {
        getService().deleteChildFollowup(childFollowup);
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        DelegatingResourceDescription description = null;

        if (representation instanceof FullRepresentation) {
            description = new DelegatingResourceDescription();
            description.addProperty("child");
            description.addProperty("arvProphylaxisGiven");
            description.addProperty("arvProphylaxisGivenDate");
            description.addProperty("pcr1SamplingDate");
            description.addProperty("ageInMonthOnPcr1Sampling");
            description.addProperty("ageInWeekOnPcr1Sampling");
            description.addProperty("pcr1Result");
            description.addProperty("pcr2SamplingDate");
            description.addProperty("ageInMonthOnPcr2Sampling");
            description.addProperty("ageInWeekOnPcr2Sampling");
            description.addProperty("pcr2Result");
            description.addProperty("pcr3SamplingDate");
            description.addProperty("ageInMonthOnPcr3Sampling");
            description.addProperty("ageInWeekOnPcr3Sampling");
            description.addProperty("pcr3Result");
            description.addProperty("ctxInitiationDate");
            description.addProperty("ageInMonthOnCtxInitiation");
            description.addProperty("ageInWeekOnCtxInitiation");
            description.addProperty("inhInitiationDate");
            description.addProperty("ageInMonthOnInhInitiation");
            description.addProperty("ageInWeekOnInhInitiation");
            description.addProperty("hivSerology1Date");
            description.addProperty("ageInMonthOnHivSerology1");
            description.addProperty("ageInWeekOnHivSerology1");
            description.addProperty("hivSerology1Result");
            description.addProperty("hivSerology2Date");
            description.addProperty("ageInMonthOnHivSerology2");
            description.addProperty("ageInWeekOnHivSerology2");
            description.addProperty("hivSerology2Result");
            description.addProperty("followupResult");
            description.addProperty("followupResultDate");
            description.addProperty("referenceLocation");
            description.addProperty("location", Representation.REF);
            description.addProperty("uuid");
            description.addSelfLink();
        }else if (representation instanceof DefaultRepresentation) {
            description = new DelegatingResourceDescription();

            description.addProperty("child");
            description.addProperty("arvProphylaxisGiven");
            description.addProperty("arvProphylaxisGivenDate");
            description.addProperty("pcr1SamplingDate");
            description.addProperty("pcr1Result");
            description.addProperty("pcr2SamplingDate");
            description.addProperty("pcr2Result");
            description.addProperty("pcr3SamplingDate");
            description.addProperty("pcr3Result");
            description.addProperty("ctxInitiationDate");
            description.addProperty("inhInitiationDate");
            description.addProperty("hivSerology1Date");
            description.addProperty("hivSerology1Result");
            description.addProperty("hivSerology2Date");
            description.addProperty("hivSerology2Result");
            description.addProperty("followupResult");
            description.addProperty("followupResultDate");
            description.addProperty("referenceLocation");
            description.addProperty("location", Representation.REF);
            description.addProperty("uuid");
            description.addSelfLink();
        }
        return description;
    }

    protected PreventTransmissionService getService() {
        return Context.getService(PreventTransmissionService.class);
    }
}
