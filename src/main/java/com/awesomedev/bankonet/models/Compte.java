package com.awesomedev.bankonet.models;

import com.awesomedev.bankonet.exceptions.YourTooPoorException;
import com.awesomedev.bankonet.utils.ObjectTools;

import javax.persistence.*;

@MappedSuperclass
public abstract class Compte {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Integer id;
    protected String numero;
    protected String intitule;
    protected double solde;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Integer getId(){
        return this.id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) throws YourTooPoorException {
        if (this.solde < 0){
            throw new YourTooPoorException();
        } else {
            this.solde = solde;
        }

    }

    public void debiter(double montant) throws YourTooPoorException {
        if (this.solde - montant < 0){
            throw new YourTooPoorException();
        } else {
            this.solde -= montant;
        }
        //todo : todo
        // todo : check value before operate, throws banking exceptions, catch them in appropriate controller
    }

    public void crediter(double montant){
        this.solde += montant;
    }

    public String toString(){
        return ObjectTools.stringifyObject(this, true);
    }
}
