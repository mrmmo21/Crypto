package algorithmes.chiffrement.RSA;

import donnees.NombreBinaire;
import static donnees.NombreBinaire.*;
import exceptions.ExceptionConversionImpossible;

/**
 * Description de la classe
 * @author Matthieu
 */
public class RabinMiller {

    
    //Méthode renvoyant si a est un témoin de Miller de n (preuve que n est composé)
    public static boolean temoin(NombreBinaire n, NombreBinaire a) throws ExceptionConversionImpossible {
       int s = 0;
       NombreBinaire sous = new NombreBinaire(1);
       String nb = n.soustraction(sous).toString();
       while (nb.charAt(nb.length()-s-1) == '0')
        {
                s+=1;
        }
        NombreBinaire d = new NombreBinaire(nb.substring(0, nb.length()-s));
        NombreBinaire x = new NombreBinaire(a.puissanceModulo(d, n));
        if (x.estEgal(sous) || x.estEgal(n.soustraction(sous)))
        {
            return false;
        }
        else
        {
            for (int i = 0; i < s-1; i++)
            {
                x = x.puissanceModulo(sous.addition(sous), n);
                if(x.estEgal( n.soustraction(sous)))
                {
                    return false;
                }

            } 
        }
        return true;
    }
    
    //Test de RabinMiller, test probabilistiquement que n est premier (proba erreur = 1/4^k)
    public static boolean testRabinMiller(NombreBinaire n) throws ExceptionConversionImpossible {
       boolean res = true;
       NombreBinaire s = new NombreBinaire(2);
       for(int i = 1; i<=25; i++)
       {
           NombreBinaire a = random(s,n.soustraction(s));
           if(temoin(n,a))
           {
               res = false;
           }
       }
       return res;
    }
    
    //Renvoie le premier nombre premier supérieur à min
    public static NombreBinaire nombrePremier(NombreBinaire min) throws ExceptionConversionImpossible {
       NombreBinaire t = new NombreBinaire(1);
       NombreBinaire res;
       if (!testRabinMiller(min))
       {
           res = min.addition(t);
           while(!testRabinMiller(res))
           {
               if(res.toString().charAt(res.toString().length())=='0')
               {
                   res=res.addition(t);
               }
               else
               {
                   res=res.addition(t).addition(t);
               }
           }
       }
       else
       {
           res = min;
       }
       return res;
    }
}
