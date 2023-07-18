package com.ipi.jva320.service;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.repository.SalarieAideADomicileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Ajoute des données de test si vide au démarrage
 */
@Component
public class DataInitService implements CommandLineRunner {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @Autowired
    private  SalarieAideADomicileRepository salarieAideADomicileRepository;

    @Override
    public void run(String... argv) throws Exception {
        if (this.salarieAideADomicileService.countSalaries() != 0) {
            return;
        }

        SalarieAideADomicile s1 = this.salarieAideADomicileService.creerSalarieAideADomicile(
                new SalarieAideADomicile("Jean", LocalDate.parse("2022-12-05"), LocalDate.parse("2022-12-05"),
                        20, 0,
                        80, 10, 1));

        SalarieAideADomicile s2 = this.salarieAideADomicileService.creerSalarieAideADomicile(
                new SalarieAideADomicile("Yaya", LocalDate.parse("2022-11-03"), LocalDate.parse("2022-10-04"),
                        19, 0,
                        85, 11, 2));

        salarieAideADomicileRepository.save(s1);
        salarieAideADomicileRepository.save(s2);
    }
}
