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
       NombreBinaire one = new NombreBinaire(1);
       String nb = n.soustraction(one).toString();
       while (nb.charAt(nb.length()-s-1) == '0')
        {
                s+=1;
        }
        NombreBinaire d = new NombreBinaire(nb.substring(0, nb.length()-s));
        NombreBinaire x = new NombreBinaire(a.puissanceModulo(d, n));
        if (x.estEgal(one) || x.estEgal(n.soustraction(one)))
        {
            return false;
        }
        else
        {
            for (int i = 0; i < s-1; i++)
            {
                x = x.puissanceModulo(one.addition(one), n);
                if(x.estEgal( n.soustraction(one)))
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
       NombreBinaire one = new NombreBinaire(2);
       NombreBinaire nm1 = n.soustraction(one);
       for(int i = 1; i<=25 && res; i++)
       {
           NombreBinaire a = random(one,nm1);
           if(temoin(n,a))
           {
               res = false;
           }
       }
       return res;
    }
    
    //Renvoie le premier nombre premier supérieur à min
    public static NombreBinaire nombrePremier(NombreBinaire min) throws ExceptionConversionImpossible {
        NombreBinaire one = new NombreBinaire(1);
        NombreBinaire two = new NombreBinaire(2);
        NombreBinaire res = new NombreBinaire(min);
        if (res.estPair())
            res = res.addition(one);
        while (!testRabinMiller(res))
        {
            System.out.println(res.toString());
            res = res.addition(two);
        }
        return res;
    }
}
