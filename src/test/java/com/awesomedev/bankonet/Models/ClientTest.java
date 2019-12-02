package com.awesomedev.bankonet.Models;

import com.awesomedev.bankonet.models.Client;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ClientTest {
    private String username = "Mussolini";
    private String password = "petitOursBrun";

    @Test
    public void fullConstructorShouldWork(){
        Client client = new Client(username, password);
        Assert.assertEquals(client.getUsername(), username);
        Assert.assertEquals(client.getPassword(), password);
        Assert.assertNull(client.getId());
    }

    @Test
    public void partialConstructorShouldWork(){
        Client client = new Client(username);
        Assert.assertEquals(client.getUsername(), username);
        Assert.assertNull(client.getPassword());
        Assert.assertNull(client.getId());
    }

    @Test
    public void ConstructorFromDtoShouldWork(){
        com.awesomedev.bankonet.DTO.Client clientDto = new com.awesomedev.bankonet.DTO.Client(username, password);
        Client client = new Client(clientDto);
        Assert.assertEquals(client.getUsername(), username);
        Assert.assertEquals(client.getUsername(), clientDto.getUsername());
        Assert.assertEquals(client.getPassword(), clientDto.getPassword());
        Assert.assertEquals(client.getPassword(), password);
        Assert.assertEquals(client.getId(), clientDto.getId());
        Assert.assertNull(client.getId());
    }
}
