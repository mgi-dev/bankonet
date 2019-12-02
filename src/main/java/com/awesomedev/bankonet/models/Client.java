package com.awesomedev.bankonet.models;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;


@Entity
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<CompteCourant> comptesCourants;

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<CompteCourant> getComptesCourants() {
        return (this.comptesCourants == null) ? Collections.emptySet(): this.comptesCourants;
    }

    public void setComptesCourants(Set<CompteCourant> comptesCourants) {
        this.comptesCourants = comptesCourants;
    }

    public Client() {
    }

    public Client(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Client(com.awesomedev.bankonet.DTO.Client client) {
        this.username = client.getUsername();
        this.password = client.getPassword();
    }

    public Client(String username) {
        this.username = username;

    }

    public Integer getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
