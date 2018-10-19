package org.openmrs.module.ptme.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportingManageController {

    @RequestMapping(value = "/module/ptme/manageReport.form")
    public void manage(ModelMap modelMap) {
        modelMap.addAttribute("pageName", "ReportManage.jsp");
    }

}
