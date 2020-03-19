
import coucheReseau.client.Client;
import donnees.NombreBinaire;
import exceptions.ExceptionCryptographie;
import java.io.IOException;
import protocoles.Protocole;
import protocoles.*;

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
    public static void main(String[] args) throws ExceptionCryptographie, IOException {
        //Client c = new Client();
        String str="";
        
        NombreBinaire random = NombreBinaire.random(new NombreBinaire("10001"), new NombreBinaire("100"));
        System.out.println(random.toString());
        return;
        
//        while(!str.equals("FIN")){
//            
//            
//            str = c.receiveMessage();
//            //defInférieur(c,str);
//            //defAdd(c,str);
//            //defEgal(c,str);
//            //defDecal(c,str);
//            //defSous(c,str);
//            defMult(c,str);
//        }
//        
//        
//        
    }
 
    public static void defDecal(Client c, String str) throws IOException{
        String MotBinaire = "";
        String nb = "";
        if(str.equals("Fin")){
            c.end();
        }
        else
        {
            MotBinaire = c.receiveMessage();
            nb = c.receiveMessage();
            System.out.println(Integer.parseInt(nb));
            NombreBinaire bin1 = new NombreBinaire(MotBinaire);
            NombreBinaire b = bin1.decalage(Integer.parseInt(nb));
            c.sendMessage(b.toString());
        }
        
    }
    public static void defAdd(Client c, String str) throws IOException{
        String nb1 = "";
        String nb2 = "";
        if(str.equals("FIN")){
            c.end();
        }
        else 
        {
            nb1 = c.receiveMessage();
            nb2 = c.receiveMessage();
            NombreBinaire bin1 = new NombreBinaire(nb1);
            NombreBinaire b = bin1.addition(new NombreBinaire(nb2));
            c.sendMessage(b.toString());
        }
        
    }
    public static void defMult(Client c, String str) throws IOException{
        String nb1 = "";
        String nb2 = "";
        if(str.equals("FIN")){
            c.end();
        }
        else 
        {
            nb1 = c.receiveMessage();
            nb2 = c.receiveMessage();
            NombreBinaire bin1 = new NombreBinaire(nb1);
            NombreBinaire b = bin1.multiplication(new NombreBinaire(nb2));
            c.sendMessage(b.toString());
        }
        
    }
    public static void defSous(Client c, String str) throws IOException{
        String nb1 = "";
        String nb2 = "";
        if(str.equals("FIN")){
            c.end();
        }
        else 
        {
            nb1 = c.receiveMessage();
            nb2 = c.receiveMessage();
            NombreBinaire bin1 = new NombreBinaire(nb1);
            NombreBinaire b = bin1.soustraction(new NombreBinaire(nb2));
            c.sendMessage(b.toString());
        }
        
    }
    
    public static void defEgal(Client c, String str) throws IOException{
        String nb1 = "";
        String nb2 = "";
        if(str.equals("–- Début du défi : Est Inferieur --")){
            
        }
        else if(str.equals("FIN")){
            c.end();
        }
        else 
        {
            nb1 = c.receiveMessage();
            nb2 = c.receiveMessage();
            NombreBinaire bin1 = new NombreBinaire(nb1);
            boolean b = bin1.estEgal(new NombreBinaire(nb2));
            c.sendMessage(String.valueOf(b));
        }
        
    }
    
    public static void defInférieur(Client c, String str) throws IOException{
        String nb1 = "";
        String nb2 = "";
        if(str.equals("–- Début du défi : Est Inferieur --")){
            
        }
        else if(str.equals("FIN")){
            c.end();
        }
        else 
        {
            nb1 = c.receiveMessage();
            nb2 = c.receiveMessage();
            NombreBinaire bin1 = new NombreBinaire(nb1);
            boolean b = bin1.estInferieurA(new NombreBinaire(nb2));
            c.sendMessage(String.valueOf(b));
        }
        
    }
}
