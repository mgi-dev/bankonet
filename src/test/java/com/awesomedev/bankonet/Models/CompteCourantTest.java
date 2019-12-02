package com.awesomedev.bankonet.Models;

import com.awesomedev.bankonet.exceptions.YourTooPoorException;
import com.awesomedev.bankonet.models.CompteCourant;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CompteCourantTest {

    private String numero = "01343183";
    private String intitule = "test_intitulé";


    @Test
    public void constructorShouldWork() throws YourTooPoorException {
        double solde = 120.0;
        CompteCourant compteCourant = new CompteCourant(numero, intitule, solde);
        Assert.assertEquals(compteCourant.getIntitule(), intitule);
        Assert.assertEquals(compteCourant.getNumero(), numero);
        Assert.assertEquals(compteCourant.getSolde(), solde, 0.1);
        System.out.println("==========================================================");
        System.out.println("==========================================================");
        System.out.println("==========================================================");
        System.out.println("==========================================================");

        Assert.assertNotNull(compteCourant.getMontantDecouvertAuthorise());
        Assert.assertTrue(compteCourant.getMontantDecouvertAuthorise() <= 0);
    }

    @Test
    public void debiterShouldWork() throws YourTooPoorException {
        double solde = 100.0;
        CompteCourant compteCourant = new CompteCourant(numero, intitule, solde);
        compteCourant.debiter(30.00);
        Assert.assertEquals(solde - 30, compteCourant.getSolde(), 0.1);
    }

    @Test(expected = YourTooPoorException.class)
    public void debiterShouldFailBelowZero() throws YourTooPoorException {
        double solde = 120.0;
        CompteCourant compteCourant = new CompteCourant(numero, intitule, solde);
        compteCourant.debiter(1000.0);
    }

    @Test
    public void debiterShouldWorkWithDecouvertAuthorise() throws YourTooPoorException {
        double solde = 120.0;
        CompteCourant compteCourant = new CompteCourant(numero, intitule, solde);
        compteCourant.debiter(140.0);
        Assert.assertEquals(solde - 140.0, compteCourant.getSolde(), 0.1);
    }
}
