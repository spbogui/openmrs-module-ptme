package org.openmrs.module.ptme.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportingManageTemplateController {

    @RequestMapping(value = "/module/ptme/reportTemplate.form")
    public void manage(ModelMap modelMap) {
        modelMap.addAttribute("pageName", "Template.jsp");
    }

}
