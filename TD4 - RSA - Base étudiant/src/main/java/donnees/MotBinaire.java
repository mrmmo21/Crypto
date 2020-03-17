package donnees;

import exceptions.ExceptionConversionImpossible;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * Description de la classe
 * @author Matthieu
 */
public class MotBinaire {

    private NombreBinaire listeBits;
    private int taille;
    
    //Constructeurs standard
    public MotBinaire() {
        this.listeBits = new NombreBinaire();
        this.taille = 0;
    }
    //Constructeur par paramètres (en forçant la taille)
    public MotBinaire(NombreBinaire listeBits,int taille) {
        this.listeBits = new NombreBinaire();
        this.taille = taille;
        for(int i=0;i<this.taille;i++) {
            this.listeBits.set(i,listeBits.get(i));
        } 
    }
    //Constructeur à partir d'un long
    public MotBinaire(Long valeur) {
        this.taille = 32;
        this.listeBits = new NombreBinaire();
        for(int i=0;i<32;i++) {
            this.listeBits.set(i,valeur%2==1);
            valeur /= 2;
        }
    }
    
    //Constructeur à partir d'un byte 
    public MotBinaire(byte b) {
        this.taille = 8;
        byte[] bt = new byte[1];
        bt[0] = b;
        this.listeBits = new NombreBinaire(BitSet.valueOf(bt));
    }
    
    //Constructeur à partir d'un caractère (UTF-8)
    public MotBinaire(Character c) {
        this.taille = 8;
        int codeAscii = c;
        this.listeBits = new NombreBinaire(codeAscii);
    }
    
    public NombreBinaire asNombreBinaire() {
        return this.listeBits;
    }
    
    public int getTaille() {
        return this.taille;
    }
    
    //Convertion en entier non signé 
    public int asInteger() throws ExceptionConversionImpossible{
        return this.listeBits.asInteger();
    }
    
    public String asString() {
        String res = "";
        for(int i=0;i<this.taille;i=i+8) {
            int codeAscii = 0;
            for(int j=0;j<8;j++) {
                if(this.listeBits.get(i+j)) {
                    codeAscii+=Math.pow(2, j);
                }
            }
            res += (char) codeAscii;
        }
        return res;
    }
    
    @Override
    public String toString() {
        String res = "";
        for(int i=0;i<this.taille;i++) {
            if(this.listeBits.get(i)) {
                res = "1"+res;
            }
            else {
                res = "0"+res;
            }
        }
        return res;
    }
    
    public MotBinaire xor(MotBinaire mot2) {
        MotBinaire resultat = new MotBinaire(this.listeBits,Math.min(this.taille, mot2.taille));
        for(int i=0;i<resultat.taille;i++) {
            resultat.listeBits.set(i,resultat.listeBits.get(i) ^ mot2.listeBits.get(i));
        }
        return resultat;
    }
    
    //Renvoie le résultat de l'addition de this avec mot2 modulo 2^32
     public MotBinaire additionMod2p32(MotBinaire mot2) {
        MotBinaire resultat = new MotBinaire(this.listeBits,32);
        boolean retenu = false;
        for(int i=0;i<32;i++) {
            if((retenu && (this.listeBits.get(i) || mot2.listeBits.get(i))) || (this.listeBits.get(i) && mot2.listeBits.get(i))) {
                resultat.listeBits.set(i,retenu && this.listeBits.get(i) && mot2.listeBits.get(i));
                retenu = true;
            }
            else {
                resultat.listeBits.set(i,retenu || this.listeBits.get(i) || mot2.listeBits.get(i));
                retenu = false;
            }
        }
        return resultat;
     }
         
    //Scinde le mot binaire en une liste de mot binaire de taille donnée. Etend le dernier morceau au besoin.
     public ArrayList<MotBinaire> scinder(int tailleMorceau) {
        ArrayList<MotBinaire> res = new ArrayList<>();
        for(int i=0;i<this.taille;i=i+tailleMorceau) {
            NombreBinaire bitset = new NombreBinaire();
            for(int j=0;j<tailleMorceau;j++) {
                bitset.set(j,this.listeBits.get(i+j));
            }
            res.add(new MotBinaire(bitset,tailleMorceau));
        }
        return res;
    }
     
    //Concaténation de deux mots binaires
     public MotBinaire concatenation(MotBinaire mot) {
         MotBinaire sortie = new MotBinaire(this.listeBits,this.taille+mot.taille);
         for(int i=0;i<sortie.taille;i++) {
             if(i<this.taille) sortie.listeBits.set(i,this.listeBits.get(i));
             else sortie.listeBits.set(i,mot.listeBits.get(i-this.taille));
         }
         return sortie;
     }
     
     
}
