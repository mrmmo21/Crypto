/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Défis;

import algorithmes.chiffrement.RSA.RabinMiller;
import coucheReseau.client.Client;
import donnees.NombreBinaire;
import exceptions.ExceptionConversionImpossible;
import java.io.IOException;

/**
 *
 * @author alexa
 */
public class Defi19 implements Defis {

    @Override
    public void lancerDefi(Client c, String str) throws IOException, ExceptionConversionImpossible {
        while(!str.equals("FIN")){
            String nb1 = "";
            if(str.equals("FIN")){
                c.end();
            }
            else 
            {
                nb1 = c.receiveMessage();
                if(nb1.charAt(0) != 'D')
                {   
                    NombreBinaire bin1 = new NombreBinaire(nb1);
                    boolean b = RabinMiller.testRabinMiller(bin1);
                    c.sendMessage(String.valueOf(b));
                }
                str = c.receiveMessage();
                if(str.equals("NOK"))
                {
                    str = "FIN";
                }
            }
            
        }
        c.end();
    }
    
}
