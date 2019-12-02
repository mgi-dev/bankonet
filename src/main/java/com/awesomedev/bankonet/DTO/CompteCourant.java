package com.awesomedev.bankonet.DTO;


import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;


public class CompteCourant {
    private Integer id;
    @NonNull
    private Double montantDecouvertAutorise;
    @NonNull
    @Size(min=1, max=30, message="Invalid length.")
    private String numero;
    @NonNull
    private String intitule;

    public void setMontantDecouvertAutorise(Double montantDecouvertAutorise) {
        this.montantDecouvertAutorise = montantDecouvertAutorise;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    private Double solde;

    public CompteCourant(){

    }

    public CompteCourant(com.awesomedev.bankonet.models.CompteCourant compteCourant) {
        System.out.println("====-=====-=====-======-=====-=====-======");
        this.id = compteCourant.getId();
        this.montantDecouvertAutorise = compteCourant.getMontantDecouvertAuthorise();
        this.numero = compteCourant.getNumero();
        this.intitule = compteCourant.getIntitule();
        this.solde = compteCourant.getSolde();
    }

    public Integer getId() {
        return id;
    }

    public Double getMontantDecouvertAutorise() {
        return montantDecouvertAutorise;
    }

    public String getNumero() {
        return numero;
    }

    public String getIntitule() {
        return intitule;
    }

    public Double getSolde() {
        return solde;
    }
}
