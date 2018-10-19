package org.openmrs.module.ptme.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportingController {
    public static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(ReportingController.class);

    @RequestMapping(value = "/module/ptme/report.form", method = RequestMethod.GET)
    public void manage(ModelMap modelMap) {

    }

    @RequestMapping(value = "/module/ptme/reportGenerate.form", method = RequestMethod.GET)
    public void reportGeneration(ModelMap modelMap) {

    }
}
