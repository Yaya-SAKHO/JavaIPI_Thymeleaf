package com.ipi.jva320.Controlers;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SalarieController {

    @Autowired
    SalarieAideADomicileService aideADomicileService;

    /***********************************Créer_un_Salarie*******************************************/
    @GetMapping(value = "/salaries/aide/new")
    public String ajouterSalarie(ModelMap model) {
        return "detail_Salarie";
    }

    @PostMapping(value = "/salaries/save")
    public String creerSalarie(@Valid @ModelAttribute("salarie") SalarieAideADomicile salarie,
                               BindingResult bindingResult,
                               ModelMap model) throws SalarieException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "erreur";
        }

        aideADomicileService.creerSalarieAideADomicile(salarie);
        return "redirect:/salaries";
    }



    /***********************************Détail_du_Salarie*******************************************/
    @GetMapping(value = "/salaries/{id}")
    public String getSalaries(final ModelMap model, @PathVariable Long id){

        SalarieAideADomicile salarie = aideADomicileService.getSalarie(id);

        model.put("salarie",salarie);

        return "detail_Salarie";
    }

    /***********************************Supprimer_Un_Salarié*******************************************/

    @GetMapping("/salaries/{id}/delete")
    public String deleteSalarie(SalarieAideADomicile salarie) throws SalarieException {

        aideADomicileService.deleteSalarieAideADomicile(salarie.getId());

        return "redirect:/salaries";
    }

    /***********************************Modifier_Un_Salarié*******************************************/

    @PostMapping(value = "/salaries/{id}")
    public String modifierSalarie(SalarieAideADomicile salarie) throws SalarieException {
        aideADomicileService.updateSalarieAideADomicile(salarie);
        return "redirect:/salaries" ;
    }

    /***********************************Rechercher_Un_Salarié*******************************************/

    @GetMapping("/salarie/recherche")
    public String rechercheSalarie(@RequestParam("nom") String nom, ModelMap model) {
        List<SalarieAideADomicile> salaries = aideADomicileService.getSalaries(nom);
        if (salaries != null && !salaries.isEmpty()) {
            Page<SalarieAideADomicile> salariesPage = new PageImpl<>(salaries);
            model.put("salaries", salariesPage);
            model.put("number", salariesPage.getNumber());
            model.put("totalElements", salariesPage.getTotalElements());
            model.put("totalPages", salariesPage.getTotalPages());
            return "list";
        } else {
            throw new SalarieNotFoundException("Aucun salarié trouvé pour le nom " + nom);
        }
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class SalarieNotFoundException extends RuntimeException {
        public SalarieNotFoundException(String message) {
            super(message);
        }
    }



}


