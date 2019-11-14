package de.brainactivit.empathica.kunde.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.brainactivit.empathica.kunde.model.Kunde;
import de.brainactivit.empathica.kunde.repository.KundeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KundeService {

    private final KundeRepository kundeRepository;
    private final Logger logger = LoggerFactory.getLogger(KundeService.class);

    public Kunde saveKunde(Kunde toStore) {

        logger.info("");
        return kundeRepository.save(toStore);
    }

    public Optional<Kunde> getKundeById(long kundeId) {

        logger.info("");
        return kundeRepository.findById(kundeId);
    }

    public Iterable<Kunde> getAllKunde() {

        logger.info("");
        return kundeRepository.findAll();
    }

}
