package de.brainactivit.empathica.kunde.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Kunde {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String vorname;

    @NotNull
    private String nachname;

    @NotNull
    private String geschlecht;

    private String strasse;
    private String plz;
    private String ort;
    private String telefon;
    private LocalDateTime firstvisit;
    private LocalDateTime lastvisit;
    private String cntvisit;
    private String anrede;
    private String email;
    private LocalDate geburtstag;
    private String customerCreditable;
    private Integer customerCreditLimit;
    private Integer referrerId;
}
