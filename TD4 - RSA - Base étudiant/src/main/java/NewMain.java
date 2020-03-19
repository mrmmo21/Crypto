
import coucheReseau.client.Client;
import donnees.NombreBinaire;
import exceptions.ExceptionConversionImpossible;
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
        Client c = new Client();
        String str="";
        
        while(!str.equals("FIN")){
            
            str = c.receiveMessage();
                       
            //defInférieur(c,str);
            //defAdd(c,str);
            //defEgal(c,str);
            //defDecal(c,str);
            //defSous(c,str);
            //defMult(c,str);
            //defrandomFixe(c,str);
            
            
            //defrandomBorne(c,str);
            //defModulo(c,str);
            //defQuotient(c,str);
            defPGCD(c,str);
        }
    }
    
    
    public static void defPGCD(Client c, String str) throws IOException, ExceptionConversionImpossible{
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
            NombreBinaire b = bin1.PGCD(new NombreBinaire(nb2));
            c.sendMessage(b.toString());
        }
        
    }
    
    public static void defModulo(Client c, String str) throws IOException, ExceptionConversionImpossible{
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
            NombreBinaire b = bin1.modulo(new NombreBinaire(nb2));
            c.sendMessage(b.toString());
        }
        
    }
    
    public static void defQuotient(Client c, String str) throws IOException, ExceptionConversionImpossible{
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
            NombreBinaire b = bin1.quotient(new NombreBinaire(nb2));
            c.sendMessage(b.toString());
        }
        
    }
    
    
 
    public static void defrandomBorne(Client c, String str) throws IOException, ExceptionConversionImpossible{
        String nb2 = "";
        String nb1 = "";
        if(str.equals("Fin")){
            c.end();
        }
        else
        {
            nb1 = c.receiveMessage();
            nb2 = c.receiveMessage();
            NombreBinaire n1 = new NombreBinaire(nb1);
            NombreBinaire n2 = new NombreBinaire(nb2);
            NombreBinaire b = NombreBinaire.random(n1, n2);
            c.sendMessage(b.toString());
        }
        
    }
    
    public static void defrandomFixe(Client c, String str) throws IOException{
        String nb2 = "";
        String nb1 = "";
        if(str.equals("Fin")){
            c.end();
        }
        else
        {
            nb1 = c.receiveMessage();
            nb2 = c.receiveMessage();
            int nb = Integer.parseInt(nb2);
            NombreBinaire bin = NombreBinaire.randomAvecTailleMax(nb);
            c.sendMessage(bin.toString());
        }
        
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
