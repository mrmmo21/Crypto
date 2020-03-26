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
            String nb1 = "";
            String nb2 = "";
            String nb3 = "";
            if(str.equals("FIN")){
                c.end();
            }
            else 
            {
                
            
        
                nb1 = c.receiveMessage();
                if(nb1.charAt(0) != 'D')
                {
                    nb2 = c.receiveMessage();
                    nb3 = c.receiveMessage();
                    NombreBinaire bin1 = new NombreBinaire(nb1);
                    NombreBinaire bin2 = new NombreBinaire(nb2);
                    NombreBinaire bin3 = new NombreBinaire(nb3);
                    GenerateurDeClesRSA gene = new GenerateurDeClesRSA();
                    gene.setP(bin1);
                    gene.setQ(bin2);
                    gene.setE(bin3);
                    Cles cles= gene.genererClePublique();
                    c.sendMessage(gene.getP().toString());
                    c.sendMessage(gene.getQ().toString());
                    c.sendMessage(gene.getN().toString());
                    c.sendMessage(gene.getPhi().toString());       
                    c.sendMessage(gene.getE().toString());}
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
