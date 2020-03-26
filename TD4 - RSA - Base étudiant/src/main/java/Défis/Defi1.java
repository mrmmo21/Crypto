/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Défis;

import coucheReseau.client.Client;
import donnees.NombreBinaire;

/**
 *
 * @author alexa
 */
public class Defi1 implements Defis {

    @Override
    public void lancerDefi(Client c, String str) throws Exception {
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
                    int n = Integer.parseInt(nb1);
                    n++;
                    c.sendMessage(String.valueOf(n));
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
