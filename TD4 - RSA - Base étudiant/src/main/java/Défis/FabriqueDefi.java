/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Défis;

import coucheReseau.client.Client;

/**
 *
 * @author alexa
 */
public class FabriqueDefi {
    
    
    public static Defis fabriqueDef(Client c, String str) throws Exception{
        Defis d;
        System.out.println(str);
        switch(str){
            case "–- Début du défi : Connexion au serveur –-" :
                d = new Defi1();
            break;
            case "–- Début du défi : Addition –-":
                d = new Defi2();
            break;
            case "-- Début du défi : Modulo --":
                d = new Defi10();
            break;
            case "– Début du défi : Connexion au serveur –" :
                d = new Defi1();
            break;
            
            default:
                throw new Exception("Erreur, defis non créé ou existant");
        }
        return d;
    }
    
}
