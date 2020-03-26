package algorithmes.chiffrement;

import algorithmes.chiffrement.RSA.ParametresRSA;
import donnees.MotBinaire;
import donnees.NombreBinaire;
import donnees.cles.Cle;
import donnees.cles.Cles;
import donnees.messages.Message;
import donnees.messages.MessageBinaire;
import exceptions.ExceptionConversionImpossible;
import exceptions.ExceptionCryptographie;
import java.util.ArrayList;

public class AlgorithmeRSA implements Algorithme{

    
    @Override
    public String getNom() {
        return "RSA";
    }
    
    //Chiffre un morceau (entrée : tailleMorceau, sortie : tailleCle)
    public MotBinaire chiffrerMorceau(MotBinaire morceau, Cles clesPublique) throws ExceptionConversionImpossible {
        NombreBinaire cleRSA_N = clesPublique.getCle("cleRSA_N").asMotBinaire().asNombreBinaire();
        NombreBinaire cleRSA_e = clesPublique.getCle("cleRSA_e").asMotBinaire().asNombreBinaire();
        NombreBinaire morceauNombreBinaire = morceau.asNombreBinaire();
        morceauNombreBinaire = morceauNombreBinaire.puissanceModulo(cleRSA_e,cleRSA_N);
        MotBinaire res = new MotBinaire(morceauNombreBinaire,ParametresRSA.getTailleMorceau()+2);
        return res;
    }
    
    //Déchiffre un morceau (entrée : tailleCle, sortie : tailleMorceau)
    public MotBinaire dechiffrerMorceau(MotBinaire morceau, Cles clesPublique, Cles clesPrivee) throws ExceptionConversionImpossible {
       //TODO
       return null;
    }

    @Override
    public Message chiffrer(Message message, Cles clesPubliques, Cles clesPrivees) throws ExceptionCryptographie {
       MotBinaire motBin = message.asMotBinaire();
       ArrayList<MotBinaire> motBins = motBin.scinder(ParametresRSA.getTailleMorceau());
       MotBinaire msg = new MotBinaire();
       
        for (int i = 0; i < motBins.size(); i++) {
            msg = msg.concatenation(this.chiffrerMorceau(motBins.get(i),clesPubliques));
        }
       return new MessageBinaire(msg);
    }

    @Override
    public Message dechiffrer(Message message, Cles clesPubliques, Cles clesPrivees) throws ExceptionCryptographie {
       //TODO
       return null;
    }

}
