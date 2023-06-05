package com.ipi.jva320.Controlers;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Detail_SalarieController {

    @Autowired
    SalarieAideADomicileService aideADomicileService;


    @GetMapping(value = "/salaries/{id}")
    public String getSalaries(final ModelMap model, @PathVariable Long id){

       SalarieAideADomicile salarie = aideADomicileService.getSalarie(id);

        model.put("salarie",salarie);

        return "detail_Salarie";
    }
}
