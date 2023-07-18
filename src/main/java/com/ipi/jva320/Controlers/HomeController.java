package com.ipi.jva320.Controlers;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;

@Controller
public class HomeController {

    @Autowired
    SalarieAideADomicileService aideADomicileService;


    @GetMapping(value = "/")
    public String home(final ModelMap model){

        model.put("countSalarie",aideADomicileService.countSalaries());
        return "home";
    }
}
