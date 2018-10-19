package org.openmrs.module.ptme.extension.html;

import org.openmrs.api.context.Context;
import org.openmrs.module.web.extension.LinkExt;

public class PreventTransmissionGutterLink extends LinkExt {
    @Override
    public String getLabel() {
        return Context.getMessageSourceService().getMessage("ptme.gutterTitle");
    }

    @Override
    public String getUrl() {
        return "module/ptme/manage.form";
    }

    @Override
    public String getRequiredPrivilege() {
        return "Manage PTME";
    }
}
