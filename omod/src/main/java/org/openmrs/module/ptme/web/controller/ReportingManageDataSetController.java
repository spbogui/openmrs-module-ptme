package org.openmrs.module.ptme.web.controller;

import org.openmrs.module.ptme.forms.GetDataSetFromFrom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportingManageDataSetController {

    @RequestMapping(value = "/module/ptme/reportDataSet.form")
    public void manage(ModelMap modelMap) {
        GetDataSetFromFrom getDataSetFromFrom = new GetDataSetFromFrom();

        modelMap.addAttribute("getDataSetFromFrom", getDataSetFromFrom);
        modelMap.addAttribute("pageName", "DataSet.jsp");
    }

}
