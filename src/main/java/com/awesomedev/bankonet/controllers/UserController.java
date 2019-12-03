package com.awesomedev.bankonet.controllers;

import com.awesomedev.bankonet.DTO.Client;
import com.awesomedev.bankonet.exceptions.ClientNotFoundException;
import com.awesomedev.bankonet.repositories.UserRepository;
import com.awesomedev.bankonet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends BaseController{

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<Client> index() {
        System.out.println("couu");
        return userService.findAll();
    }


    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Client getUser(@PathVariable(value="id") Integer id) throws ClientNotFoundException {
        return userService.find(id);
    }


    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Iterable<com.awesomedev.bankonet.models.Client> test() {
        return userRepository.findAll();
    }



    @PostMapping(path = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Client addUser(@RequestBody Client user) {
        return userService.save(user);
    }

    @PatchMapping(path = "/users")
    public @ResponseBody
    Client updateClient(@RequestBody Client user) throws Exception {
        return userService.update(user);
    }
}
