package de.brainactivit.empathica.kunde.api;

import de.brainactivit.empathica.kunde.model.Kunde;
import de.brainactivit.empathica.kunde.service.KundeService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/kunde-api/v1")
public class KundeResource {

    @Autowired
    private KundeService kundeService;


    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_kunde_read')")
    public ResponseEntity<Kunde> getKunde(@PathVariable(name = "id") long kundeId) {

        Optional<Kunde> kundeFound = kundeService.getKundeById(kundeId);

        if(kundeFound.isPresent())
            return new ResponseEntity<>(kundeFound.get(), HttpStatus.OK);

        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_kunde_write')")
    public ResponseEntity<Kunde> saveKunde(@RequestBody Kunde kundeToSave) {

        Kunde found = kundeService.saveKunde(kundeToSave);
        return new ResponseEntity<Kunde>(found, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_kunde_read')")
    public ResponseEntity<Iterable<Kunde>> getAllKunde() {

        return new ResponseEntity<>(kundeService.getAllKunde(), HttpStatus.OK);
    }
}
