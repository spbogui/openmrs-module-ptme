package org.openmrs.module.ptme.web.controller;

import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("rest/" + RestConstants.VERSION_1 + PreventTransmissionResourceController.PTME_REST_NAMESPACE)
public class PreventTransmissionResourceController
        extends MainResourceController {

    public static final String PTME_REST_NAMESPACE = "/ptme";

    @Override
    public String getNamespace() {
        return RestConstants.VERSION_1 + PTME_REST_NAMESPACE;
    }
}
