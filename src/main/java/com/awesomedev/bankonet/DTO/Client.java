package com.awesomedev.bankonet.DTO;

import java.util.ArrayList;
import java.util.Set;

public class Client {

    private Integer id;
    private String username;
    private String password;
    private ArrayList<CompteCourant> comptesCourants;

    public Client() {
    }

    public Client(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Client(com.awesomedev.bankonet.models.Client user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.id = user.getId();
        this.comptesCourants = new ArrayList<>();
        System.out.println("===========================================");
        System.out.println(user.getComptesCourants());

        for (com.awesomedev.bankonet.models.CompteCourant compte : user.getComptesCourants()){
            this.comptesCourants.add(new CompteCourant(compte));
        }
        System.out.println("GOOD");
    }

    public Client(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id ){
        this.id = id;
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

    public ArrayList<CompteCourant> getComptesCourants() {
        return comptesCourants;
    }
}


