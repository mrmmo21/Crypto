package algorithmes.chiffrement.RSA;

import donnees.NombreBinaire;

/**
 * Description de la classe
 * @author Matthieu
 */
public class RabinMiller {

    
    //Méthode renvoyant si a est un témoin de Miller de n (preuve que n est composé)
    public static boolean temoin(NombreBinaire n, NombreBinaire a) {
       int s = 0;
       boolean res = false;
       NombreBinaire sous = new NombreBinaire(1);
       String nb = n.soustraction(sous).toString();
       while (nb.charAt(nb.length()-s) == 0)
        {
                s+=1;
        }
        NombreBinaire d = new NombreBinaire(nb.substring(0, nb.length()-s));
        NombreBinaire x = new NombreBinaire(a.puissanceModulo(d, n));
        if (x.estEgal(sous) || x == n.soustraction(sous) )
        {
            res = false;
            for (int i = 0; i < s-1; i++)
            {
                x = x.puissanceModulo(sous.addition(sous), n);
                if(x == n.soustraction(sous))
                {
                    res = false;
                }
            }
        }
        else
        {
            res = true;
        }
        return res;
    }
    
    //Test de RabinMiller, test probabilistiquement que n est premier (proba erreur = 1/4^k)
    public static boolean testRabinMiller(NombreBinaire n) {
       //TODO
       return false;
    }
    
    //Renvoie le premier nombre premier supérieur à min
    public static NombreBinaire nombrePremier(NombreBinaire min) {
       //TODO
       return null;
    }
}
