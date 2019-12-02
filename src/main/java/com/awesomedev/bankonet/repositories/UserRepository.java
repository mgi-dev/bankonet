package com.awesomedev.bankonet.repositories;

import com.awesomedev.bankonet.models.Client;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<Client, Integer> {

}