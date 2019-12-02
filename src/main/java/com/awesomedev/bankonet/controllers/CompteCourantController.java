package com.awesomedev.bankonet.controllers;

import com.awesomedev.bankonet.DTO.CompteCourant;
import com.awesomedev.bankonet.exceptions.YourTooPoorException;
import com.awesomedev.bankonet.services.CompteCourantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping(path="/comptesCourants")
public class CompteCourantController {

    @Autowired
    CompteCourantService compteCourantService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Iterable<CompteCourant> index() {
        return compteCourantService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    void add(@RequestBody CompteCourant compteCourant) throws YourTooPoorException {
        System.out.println("''''''''''''''''(((((((((((('''''''''''");
        System.out.println(compteCourant.getIntitule());
        System.out.println(compteCourant.getNumero());
        compteCourantService.add(compteCourant);
    }

}
