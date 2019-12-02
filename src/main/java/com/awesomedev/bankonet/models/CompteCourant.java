package com.awesomedev.bankonet.models;

import com.awesomedev.bankonet.exceptions.YourTooPoorException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class CompteCourant extends Compte {

    private Double montantDecouvertAutorise;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    public CompteCourant() {

    }

    public CompteCourant(String numero, String intitule, double solde) throws YourTooPoorException {
        this.setNumero(numero);
        this.setIntitule(intitule);
        this.setSolde(solde);
        this.montantDecouvertAutorise = -100.0;
    }

    public CompteCourant(com.awesomedev.bankonet.DTO.CompteCourant compteCourant) throws YourTooPoorException {
        this.setNumero(compteCourant.getNumero());
        this.setIntitule(compteCourant.getIntitule());
        this.setSolde(compteCourant.getSolde());
        this.montantDecouvertAutorise = -100.0;
    }

    @Override
    public void debiter(double montant) throws YourTooPoorException {
        if ((this.getSolde() - montant) < this.montantDecouvertAutorise) {
            throw new YourTooPoorException();
        } else {
            this.setSolde(this.getSolde() - montant);
        }
    }

    public Double getMontantDecouvertAuthorise() {
        return this.montantDecouvertAutorise;
    }


    public void setMontantDecouvertAutorise(Double montantDecouvertAutorise) {
        this.montantDecouvertAutorise = montantDecouvertAutorise;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
