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
            case "-- Début du défi : Connexion au serveur --" :
                d = new Defi1();
            break;
            case "-- Début du défi : Addition --":
                d = new Defi2();
            break;
            case "-- Début du défi : Décalage --":
                d = new Defi3();
            break;
            case "-- Début du défi : Soustraction --":
                d = new Defi4();
            break;
            case "-- Début du défi : Adiion --":
                d = new Defi5();
            break;
            case "-- Début du défi : Adition --":
                d = new Defi6();
            break;
            case "-- Début du éfi : Addition --":
                d = new Defi7();
            break;
            case "-- Début du déf : Addition --":
                d = new Defi8();
            break;
            case "-- Débt du défi : Addition --":
                d = new Defi9();
            break;
            case "-- Début du défi : Modulo --":
                d = new Defi10();
            break;
            case "-- Début du défi : Random avec taille --" :
                d = new Defi11();
            break;
            case "-- Début du défi : Puissance Modulo --" :
                d = new Defi12();
            break;
            case "– Début du défi : Connon au serveur –" :
                d = new Defi13();
            break;
            case "-- Début du défi : Random avec bornes --" :
                d = new Defi14();
            break;
            case "-- Début du défi : Inverse Modulaire --" :
                d = new Defi15();
            break;
            case "-- Début du défi : Temoin de Rabin-Miller --" :
                d = new Defi16();
            break;
            case "– Début du défi : Connion au serveur –" :
                d = new Defi17();
            break;
            case "– Début du défi : Conexion au serveur –" :
                d = new Defi18();
            break;
            case "– Début du défi : Connexin au serveur –" :
                d = new Defi19();
            break;
            case "-- Début du défi : Generer cle privee --" :
                d = new Defi20();
            break;
            case "– Début du défi : Connxion au serveur –" :
                d = new Defi21();
            break;
            case "– Début du défi : Connexion au erveur –" :
                d = new Defi22();
            break;
            case "– Début du défi : Conneion au serveur –" :
                d = new Defi23();
            break;
            case "– Début du défi : Conexion au sereur –" :
                d = new Defi24();
            break;
            
            
            
            default:
                throw new Exception("Erreur, defis non créé ou existant");
        }
        return d;
    }
    
}
