package com.ipi.jva320.Controlers;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ListController {

    @Autowired
    SalarieAideADomicileService aideADomicileService;


 /*  @GetMapping(value = "/salaries")

    public String getSalaries(final ModelMap model){

        model.put("salaries", aideADomicileService.getSalaries());

        return "list";
    }*/

    /************************************Pagination**************************************************/
    @GetMapping(value = "/salaries")
    public String getAllSalaries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "nom") String sortProperty,
            ModelMap model
    ) {
        if (page < 0) {
            page = 0;
        }

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        Page<SalarieAideADomicile> salaries = aideADomicileService.getSalaries(pageable);


        model.addAttribute("salaries", salaries);

        model.put("salaries", salaries);
        model.put("number", salaries.getNumber());
        model.put("totalElements", salaries.getTotalElements());
        model.put("totalPages", salaries.getTotalPages());


        return "list";
    }
}
