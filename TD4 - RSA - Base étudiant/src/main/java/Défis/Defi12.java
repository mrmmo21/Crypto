/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Défis;

import coucheReseau.client.Client;
import donnees.NombreBinaire;
import exceptions.ExceptionConversionImpossible;
import java.io.IOException;

/**
 *
 * @author alexa
 */
public class Defi12 implements Defis {

    @Override
    public void lancerDefi(Client c, String str) throws IOException, ExceptionConversionImpossible {
        
        while(!str.equals("FIN")){
            String nb1 = "";
            String nb2 = "";
            String nb3 = "";
            if(str.equals("FIN")){
                c.end();
            }
            else 
            {
                nb1 = c.receiveMessage();
                if(nb1.charAt(0) == 'D')
                {
                    nb2 = c.receiveMessage();
                    nb3 = c.receiveMessage();
                    NombreBinaire bin1 = new NombreBinaire(nb1);
                    NombreBinaire bin2 = new NombreBinaire(nb2);
                    NombreBinaire bin3 = new NombreBinaire(nb3);
                    NombreBinaire b = bin1.puissanceModulo(bin2,bin3);
                    c.sendMessage(b.toString());
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
