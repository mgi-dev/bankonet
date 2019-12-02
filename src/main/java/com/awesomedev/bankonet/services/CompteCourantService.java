package com.awesomedev.bankonet.services;


import com.awesomedev.bankonet.DTO.CompteCourant;
import com.awesomedev.bankonet.exceptions.YourTooPoorException;
import com.awesomedev.bankonet.repositories.CompteCourantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompteCourantService {

    @Autowired
    CompteCourantRepository compteCourantRepository;

    public Iterable<CompteCourant> findAll(){
        ArrayList<CompteCourant> comptesList = new ArrayList<>();

        for (com.awesomedev.bankonet.models.CompteCourant compte: compteCourantRepository.findAll()){
            comptesList.add(new CompteCourant(compte));
        }
        return comptesList;
    }

    public CompteCourant add(CompteCourant compteCourant) throws YourTooPoorException {
        return new CompteCourant(compteCourantRepository.save(new com.awesomedev.bankonet.models.CompteCourant(compteCourant)));
    }
}
