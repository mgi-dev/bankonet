package com.awesomedev.bankonet.repositories;

import com.awesomedev.bankonet.models.Client;
import com.awesomedev.bankonet.models.CompteCourant;
import org.springframework.data.repository.CrudRepository;

public interface CompteCourantRepository extends CrudRepository<CompteCourant, Integer> {
}
