package donnees;

import exceptions.ExceptionConversionImpossible;
import java.security.SecureRandom;
import java.util.BitSet;

/**
 * Description de la classe
 * @author Matthieu
 */
public class NombreBinaire {
        
    private BitSet listeBits;
    
    //Génère un nombre binaire aléatoire de "taille" bits au maximum.
    public static NombreBinaire randomAvecTailleMax(int taille) {
        String key = "";
        SecureRandom generator = new SecureRandom();
        for (int i = 0; i < taille; i++)
            key += (generator.nextBoolean() ? '1' : '0');
        return new NombreBinaire(key);
    }
    
    
    //renvoie un nombre aléatoire entre min (inclu) et max (non inclu)
    public static NombreBinaire random(NombreBinaire min,NombreBinaire max)
    {
        NombreBinaire n = randomAvecTailleMax(max.getTaille());
        if (min.estEgal(max)) return new NombreBinaire(max);
        while (n.estInferieurA(min) || (!n.estEgal(max) && !n.estInferieurA(max)) || n.estEgal(max))
            n = randomAvecTailleMax(max.getTaille());
        return n;
    }
   
    
    //Set un bit
    public void set(int i, boolean valeur) {
        this.listeBits.set(i,valeur);
    }
    
    //Get un bit
    public boolean get(int i) {
        return this.listeBits.get(i);
    }
    
    
    //Constructeurs standard
    public NombreBinaire() {
        this.listeBits = new BitSet();
    }
    
    //Constructeur clone
    public NombreBinaire(NombreBinaire nombre) {
        this.listeBits = new BitSet();
        for(int i=0;i<nombre.listeBits.length();i++) {
            this.listeBits.set(i,nombre.listeBits.get(i));
        } 
    }
    
    //Constructeur clone
    public NombreBinaire(BitSet bitset) {
        this.listeBits = new BitSet();
        for(int i=0;i<bitset.length();i++) {
            this.listeBits.set(i,bitset.get(i));
        }
    }
    
    //Constructeur à partir d'un long
    public NombreBinaire(Long valeur) {
        this.listeBits = new BitSet();
        int i = 0;
        while(valeur != 0) {
            this.listeBits.set(i,valeur%2==1);
            valeur /= 2;
            i++;
        }
    }
    
    //Constructeur à partir d'un int
    public NombreBinaire(int valeur) {
        this.listeBits = new BitSet();
        int i = 0;
        while(valeur != 0) {
            this.listeBits.set(i,valeur%2==1);
            valeur /= 2;
            i++;
        }
    }
    
    //Constructeur à partir d'un byte 
    public NombreBinaire(byte b) {
        byte[] bt = new byte[1];
        bt[0] = b;
        this.listeBits = BitSet.valueOf(bt);
    }
    
    //Constructeur à partir d'une chaine de caractère binaire
    public NombreBinaire(String s) {
        this();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(s.length()-i-1) == '1') {
                this.listeBits.set(i,true);
            }
        }
    }
    
    public BitSet asBitSet() {
        return this.listeBits;
    }
    
    public int getTaille() {
        return this.listeBits.length();
    }
    
    //Convertion en entier non signé 
    public int asInteger() throws ExceptionConversionImpossible{
        if(this.listeBits.length() > 31) throw new ExceptionConversionImpossible("Nombre binaire en entier (trop grand)");
        int res = 0;
        for(int i=0;i<this.listeBits.length();i++) {
            if(this.listeBits.get(i)) {
                res += Math.pow(2, i);
            }
        }
        return res;
    }
    
    //Affichage (dans le bon sens cette foi)
    @Override
    public String toString() {
        String res = "";
        for(int i=0;i<this.getTaille();i++) {
            if(this.listeBits.get(i)) {
                res = "1"+res;
            }
            else {
                res = "0"+res;
            }
        }
        if(res == "") {
            res = "0";
        }
        return res;
    }
     
     //Renvoie le résultat de l'addition de this avec mot2
     public NombreBinaire addition(NombreBinaire mot2) {
       int ret = 0;
       BitSet b1;
       BitSet b2;
       BitSet res = new BitSet();
       if(this.listeBits.length() > mot2.listeBits.length()){
           b1 = this.listeBits;
           b2 = mot2.listeBits;
       }
       else{
           b2 = this.listeBits;
           b1 = mot2.listeBits; 
       }
       
         for (int i = 0; i < b1.length(); i++) {
             int nb;
             if(i<b2.length())
                nb = (b1.get(i)?1:0)+(b2.get(i)?1:0) + ret;
             else
                nb = (b1.get(i)?1:0)+ ret;
             
             res.set(i, nb%2==1);
             ret = (int)Math.floor(nb/2);
         }
       if(ret != 0)
           res.set(b1.length(),true);
       return new NombreBinaire(res);
     }
     
     //renvoie le resultat de l'addition de this avec mot3
    public NombreBinaire soustraction(NombreBinaire mot2) {
        int ret = 0;
        BitSet b1 = this.listeBits;
        BitSet b2 = mot2.listeBits;
        BitSet res = new BitSet();
        for (int i = 0; i<b1.length(); i++) {
            int nb;
            
            if(i<b2.length()){
                nb = (b1.get(i)?1:0)-(b2.get(i)?1:0) - ret;
                if(nb<0){
                    if(nb == -1)
                        nb = 1;
                    else 
                        nb =0;
                    ret = 1;
                }
                else
                    ret = 0;
            }
            else{
                nb = (b1.get(i)?1:0) - ret;
                if(nb<0){
                    if(nb == -1)
                        nb = 1;
                    else 
                        nb =0;
                    ret = 1;
                }
                else
                    ret = 0;
            }
            res.set(i,nb==1);
        }
       return new NombreBinaire(res);
    }
     
     //Caclule le décalage de n bits (multiplie par 2^n)
    public NombreBinaire decalage(int n) {
       String str = "" + this.toString();
       for (int i = 0; i < n; i++) {
           str+= "0";
       }
       NombreBinaire b = new NombreBinaire(str);
    return b;
    }
     
     //Calcul la multiplication de this avec mot2
     public NombreBinaire multiplication(NombreBinaire mot2) {
       NombreBinaire resultat = new NombreBinaire(0);
       NombreBinaire n1; 
       NombreBinaire n2;
       
       if(this.getTaille()<mot2.getTaille()){
           n1 = this;
           n2 = mot2;
       }
       else
       {
           n2 = this;
           n1 = mot2;
       }
       
         for (int i = 0; i < n2.getTaille(); i++) {
             if(n2.listeBits.get(i)){
                resultat = resultat.addition(n1.decalage(i));
             }
                 
         }
       
       
 
       return resultat;
     }
     
     //Renvoie si this est plus petit ou égal à mot2
     public boolean estInferieurA(NombreBinaire mot2) {
        BitSet b1 = this.listeBits;
        BitSet b2 = mot2.listeBits;
        if(b2.length()>b1.length())
            return true;
        else if(b2.length()<b1.length())
            return false;
        
        int N = b1.length();

        for (int i = N-1; i >= 0; i--) {
           if (b1.get(i) ^ b2.get(i)) return b2.get(i);
        }

        return false;
     }
     
     //Calcul this modulo mot2 via une division euclidienne
     public NombreBinaire modulo(NombreBinaire mot2) throws ExceptionConversionImpossible {
         if (mot2.estEgal(new NombreBinaire(0))) throw new ExceptionConversionImpossible("mot2 = 0");
         NombreBinaire a = this;
         NombreBinaire b = mot2;
         NombreBinaire r = a;
         NombreBinaire bPrime;
         NombreBinaire q = new NombreBinaire();
         while (!r.estInferieurA(b)){
            int n  = r.getTaille()-b.getTaille();
            bPrime = new NombreBinaire(b.decalage(n));
            if (r.estInferieurA(bPrime) && !r.estEgal(bPrime)){
                bPrime = b.decalage(n-1);
                n -= 1;
            }
            r = r.soustraction(bPrime);
            q = q.addition(new NombreBinaire(1).decalage(n));
         }
         return new NombreBinaire(r);
     }  

     //Calcul le quotient dans la division euclidienne de this par mot2
     public NombreBinaire quotient(NombreBinaire mot2) {
         NombreBinaire a = this;
         NombreBinaire b = mot2;
         NombreBinaire r = a;
         NombreBinaire bPrime;
         int q = 0;
         while (!r.estInferieurA(b)){
            int n  = r.toString().length()-b.toString().length();
            bPrime = new NombreBinaire(b.decalage(n));
            if (r.estInferieurA(bPrime)){
                bPrime = b.decalage(n-1);
                n -= 1;
            }
            r = r.soustraction(bPrime);
            q+=Math.pow(2, n);
         }
         return new NombreBinaire(q);
     }
     
     //Calcul de this^exposant modulo m par exponentiation modulaire rapide
     public NombreBinaire puissanceModulo(NombreBinaire exposant, NombreBinaire m) throws ExceptionConversionImpossible {
         
         NombreBinaire a = new NombreBinaire(this);
         NombreBinaire e = new NombreBinaire(exposant);
         NombreBinaire n = new NombreBinaire(m);
         NombreBinaire p = new NombreBinaire(1);
         
         for (int i=0; i<e.getTaille();i++){
             if (e.get(i))
             {
                 p = (p.multiplication(a)).modulo(n);
             }
             a = (a.multiplication(a)).modulo(n);
         }
       return p ;
     }
     
    public boolean estEgal(NombreBinaire mot2) {
        boolean retour = true;
        if (this.listeBits.length() == mot2.asBitSet().length())
        {
            for (int i =0; i<mot2.asBitSet().length();i++)
            {
               if(!mot2.asBitSet().get(i)== this.listeBits.get(i))
                   retour = false;
            }
        }       
        else
            retour = false;
        return retour;
     }
     
     //Renvoie si un nombre est pair
     public boolean estPair() {
       return !this.listeBits.get(0);
     }
     
     
     public NombreBinaire PGCD(NombreBinaire mot2) throws ExceptionConversionImpossible {
         NombreBinaire zero = new NombreBinaire(0);
         NombreBinaire a;
         NombreBinaire b;
         if (this.estInferieurA(mot2))
         {
             a = new NombreBinaire(mot2);
             b = new NombreBinaire(this);
         }
         else
         {
             a = new NombreBinaire(this);
             b = new NombreBinaire(mot2);
         }
         while (!b.estEgal(zero))
         {
             NombreBinaire btemp = new NombreBinaire(b);
             b = a.modulo(b);
             a = btemp;
         }
         return a;
     }
     
     //Calcul de l'inverse modulo nombre
     //Basé sur l'algo d'euclide étendu (adapté).
     public NombreBinaire inverseModulaire(NombreBinaire nombre) throws ExceptionConversionImpossible {
         NombreBinaire ZERO = new NombreBinaire(0);
            
         NombreBinaire n0 = new NombreBinaire(nombre);
         NombreBinaire b0 = new NombreBinaire(this);
         NombreBinaire t0 = new NombreBinaire(0);
         NombreBinaire t = new NombreBinaire(1);
         
         NombreBinaire q = n0.quotient(b0);
         NombreBinaire r = n0.modulo(b0);
         while(!r.estEgal(ZERO)) {
             NombreBinaire produit = q.multiplication(t);
             NombreBinaire memoire;
             //Gére le fait qu'un nombreBinaire ne peut pas être négatif......
             if(t0.estInferieurA(produit)) {
                memoire = nombre.soustraction(produit.soustraction(t0).modulo(nombre));
             }
             else {
                memoire = t0.soustraction(produit).modulo(nombre);  
             }
             
             t0 = t;
             t = memoire;
             n0 = b0;
             b0 = r;
             q = n0.quotient(b0);
             r = n0.modulo(b0);
         }
         return t;
     }
}
