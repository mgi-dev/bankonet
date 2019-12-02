package com.awesomedev.bankonet.controllers;

import com.awesomedev.bankonet.DTO.Client;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class UserControllerTest {
    @Autowired
    private MockMvc mvc;


    @Test
    public void testIndex() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", not("")))
                .andExpect(jsonPath("$[*].username", not("")))
                .andExpect(jsonPath("$[*].password", not("")));
    }

    @Autowired
    EntityManager entityManager;

    @Test
    public void createUser() throws Exception {
        Client testClient = new Client("jean-michel", "azerty2#");

        mvc.perform(MockMvcRequestBuilders.post("/users").content(new ObjectMapper().writeValueAsString(testClient))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", not("")))
                .andExpect(jsonPath("$.username", is("jean-michel")))
                .andExpect(jsonPath("$.password", is("azerty2#")));
    }


    @Test
    public void testUpdateClient() throws Exception {
        String clientName = "test_name";
        String clientPassword = "test_password";
        Client testClient = new Client(clientName, clientPassword);

        MvcResult tamere = mvc.perform(MockMvcRequestBuilders.post("/users").content(new ObjectMapper().writeValueAsString(testClient))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();

        // this uses a TypeReference to inform Jackson about the Lists's generic type
        Client createdClient = mapper.readValue(
                tamere.getResponse().getContentAsString(),
                new TypeReference<Client>() {
        });

        Client clientToUpdate = new Client();
        clientToUpdate.setId(createdClient.getId());
        String newUsername = "TEST2";
        clientToUpdate.setUsername(newUsername);


        MvcResult pppppppppppppppppp = mvc.perform(MockMvcRequestBuilders.patch("/users")
                .content(new ObjectMapper().writeValueAsString(clientToUpdate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        Client updatedClient = mapper.readValue(
                pppppppppppppppppp.getResponse().getContentAsString(),
                new TypeReference<Client>() {
                });
        Assert.assertEquals(updatedClient.getUsername(), newUsername);
        Assert.assertEquals(updatedClient.getUsername(), clientToUpdate.getUsername());

        Assert.assertNotEquals(updatedClient.getUsername(), createdClient.getUsername());

        Assert.assertEquals(createdClient.getPassword(), updatedClient.getPassword());
        Assert.assertEquals(updatedClient.getId(), createdClient.getId());
    }
}