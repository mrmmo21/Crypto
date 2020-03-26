
import Défis.Defi1;
import Défis.Defis;
import Défis.FabriqueDefi;
import algorithmes.generateurdecles.GenerateurDeClesRSA;
import coucheReseau.client.Client;
import donnees.NombreBinaire;
import exceptions.ExceptionConversionImpossible;
import exceptions.ExceptionCryptographie;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//test

/**
 *
 * @author Matthieu
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        GenerateurDeClesRSA generator = new GenerateurDeClesRSA();
        generator.genererClePublique();
        System.out.println(generator.getE() + " " + generator.getPhi() + " " + generator.getN() + " " + generator.getQ() + " " + generator.getP());
        Client c = new Client();
        String str="";
        str = c.receiveMessage(); 
        Defis d = FabriqueDefi.fabriqueDef(c, str);
        d.lancerDefi(c, str);
    }
    
}
