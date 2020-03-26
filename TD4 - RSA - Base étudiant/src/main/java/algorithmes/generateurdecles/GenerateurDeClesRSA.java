package algorithmes.generateurdecles;

import algorithmes.chiffrement.RSA.ParametresRSA;
import algorithmes.chiffrement.RSA.RabinMiller;
import donnees.MotBinaire;
import donnees.NombreBinaire;
import donnees.cles.CleBinaire;
import donnees.cles.Cles;
import java.util.logging.Logger;

/**
 * Description de la classe
 * @author Matthieu
 */
public class GenerateurDeClesRSA implements GenerateurDeCles{

    
    private NombreBinaire P;
    private NombreBinaire Q;
    private NombreBinaire N;;
    private NombreBinaire phi;
        private NombreBinaire e;

    public NombreBinaire getP() {
        return P;
    }

    public NombreBinaire getQ() {
        return Q;
    }

    public NombreBinaire getN() {
        return N;
    }

    public NombreBinaire getPhi() {
        return phi;
    }

    public NombreBinaire getE() {
        return e;
    }

    @Override
    public Cles genererClePublique() {
       NombreBinaire t = new NombreBinaire(1);
       this.N = this.P.multiplication(this.Q);
       this.phi = (this.P.soustraction(t)).multiplication(this.Q.soustraction(t));
       
       return null;
    }

    @Override
    public Cles genererClePrivee() {
       //TODO
       return null;
    }

    
    
}
