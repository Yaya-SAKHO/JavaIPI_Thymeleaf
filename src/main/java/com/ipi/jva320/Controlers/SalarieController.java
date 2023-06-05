package com.ipi.jva320.Controlers;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreerSalarieController {

    @Autowired
    SalarieAideADomicileService aideADomicileService;

    @GetMapping(value = "/salaries/aide/new")
    public String ajouterSalarie(ModelMap model) {
        return "detail_Salarie";
    }

    @PostMapping(value = "/salaries/save")
    public String creerSalarie(SalarieAideADomicile salarie) throws SalarieException {
        aideADomicileService.creerSalarieAideADomicile(salarie);
        return "redirect:/salaries";
    }


}
