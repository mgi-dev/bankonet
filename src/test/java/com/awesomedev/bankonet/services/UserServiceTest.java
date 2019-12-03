package com.awesomedev.bankonet.services;

import com.awesomedev.bankonet.DTO.Client;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @Transactional
    public void testIndex() {
        Iterable<Client> clientsList = userService.findAll();
        Assert.assertNotNull(clientsList);
        for (Client client : clientsList) {
            Assert.assertNotNull(client.getUsername());
            Assert.assertNotNull(client.getPassword());
            Assert.assertNotNull(client.getId());
        }
    }


    @Test
    public void createClient() {
        String clientName = "test_name";
        String clientPassword = "test_password";

        Client client = new Client(clientName, clientPassword);
        Client savedClient = userService.save(client);
        Assert.assertEquals(savedClient.getUsername(), clientName);
        Assert.assertEquals(savedClient.getPassword(), clientPassword);
        Assert.assertNotNull(savedClient.getId());
    }

    @Test
    public void testUpdateClient() throws Exception {
        String clientName = "test_name";
        String clientPassword = "test_password";
        Client client = new Client(clientName, clientPassword);

        Client createdClient = userService.save(client);
        Client clientToUpdate = new Client();
        clientToUpdate.setId(createdClient.getId());
        String newUsername = "TEST2";
        clientToUpdate.setUsername(newUsername);

        Client updatedClient = userService.update(clientToUpdate);
        Assert.assertEquals(updatedClient.getUsername(), newUsername);
        Assert.assertNotEquals(updatedClient.getUsername(), createdClient.getUsername());
        Assert.assertEquals(updatedClient.getPassword(), createdClient.getPassword());
        Assert.assertEquals(updatedClient.getPassword(), createdClient.getPassword());
        Assert.assertEquals(updatedClient.getId(), createdClient.getId());
    }
}
