/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Défis;

import algorithmes.chiffrement.AlgorithmeRSA;
import algorithmes.chiffrement.RSA.ParametresRSA;
import coucheReseau.client.Client;
import donnees.MotBinaire;
import donnees.NombreBinaire;
import donnees.cles.CleBinaire;
import donnees.cles.Cles;
import donnees.messages.Message;
import donnees.messages.MessageBinaire;
import exceptions.ExceptionConversionImpossible;
import exceptions.ExceptionCryptographie;
import java.io.IOException;

/**
 *
 * @author alexa
 */
public class Defi21 implements Defis {

    @Override
    public void lancerDefi(Client c, String str) throws ExceptionConversionImpossible, IOException, ExceptionCryptographie {
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
                    NombreBinaire M = new NombreBinaire(nb1);
                    NombreBinaire N = new NombreBinaire(nb2);
                    NombreBinaire e = new NombreBinaire(nb3);
                    AlgorithmeRSA algorithmeRSA = new AlgorithmeRSA();
                    Cles clesPublique = new Cles();
                    clesPublique.addCle("cleRSA_N", new CleBinaire(new MotBinaire(N,ParametresRSA.getTailleCle())));
                    clesPublique.addCle("cleRSA_e", new CleBinaire(new MotBinaire(e,ParametresRSA.getTailleCle())));
                    Message s = algorithmeRSA.chiffrer(new MessageBinaire(new MotBinaire(M,ParametresRSA.getTailleMorceau()*2)),clesPublique,null);
                    c.sendMessage(s.asMotBinaire().toString());
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
