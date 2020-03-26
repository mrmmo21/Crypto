/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Défis;

import coucheReseau.client.Client;
import donnees.NombreBinaire;
import java.io.IOException;

/**
 *
 * @author alexa
 */
public class Defi11 implements Defis {

    @Override
    public void lancerDefi(Client c, String str) throws IOException {
        while(!str.equals("FIN")){
            String nb1 = "";
            String nb2 = "";
            if(str.equals("FIN")){
                c.end();
            }
            else 
            {
                nb1 = c.receiveMessage();
                if(nb1.charAt(0) != 'D')
                {   
                    NombreBinaire b = NombreBinaire.randomAvecTailleMax(Integer.parseInt(nb1));
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
