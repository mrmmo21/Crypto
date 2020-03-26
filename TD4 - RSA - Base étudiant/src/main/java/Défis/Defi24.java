/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Défis;

import algorithmes.generateurdecles.GenerateurDeClesRSA;
import coucheReseau.client.Client;
import donnees.NombreBinaire;
import donnees.cles.Cle;
import donnees.cles.Cles;
import exceptions.ExceptionConversionImpossible;
import java.io.IOException;

/**
 *
 * @author alexa
 */
public class Defi24 implements Defis {

    @Override
    public void lancerDefi(Client c, String str) throws IOException, ExceptionConversionImpossible {
        while(!str.equals("FIN")){
            if(str.equals("FIN")){
                c.end();
            }
            else 
            {
                GenerateurDeClesRSA gene = new GenerateurDeClesRSA();
                Cles cles= gene.genererClePublique();
                c.sendMessage(gene.getP().toString());
                c.sendMessage(gene.getQ().toString());
                c.sendMessage(gene.getN().toString());
                c.sendMessage(gene.getPhi().toString());       
                c.sendMessage(gene.getE().toString());
            
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
