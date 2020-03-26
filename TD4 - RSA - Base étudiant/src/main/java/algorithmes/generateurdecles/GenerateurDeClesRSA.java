package algorithmes.generateurdecles;

import algorithmes.chiffrement.RSA.ParametresRSA;
import algorithmes.chiffrement.RSA.RabinMiller;
import donnees.MotBinaire;
import donnees.NombreBinaire;
import donnees.cles.CleBinaire;
import donnees.cles.Cles;
import exceptions.ExceptionConversionImpossible;
import java.util.logging.Level;
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
       NombreBinaire p = NombreBinaire.randomAvecTailleMax(ParametresRSA.getTailleCle());
       NombreBinaire q = NombreBinaire.randomAvecTailleMax(ParametresRSA.getTailleCle());
        try {
            while (p.estEgal(q) || !RabinMiller.testRabinMiller(p) || !RabinMiller.testRabinMiller(q))
            {
                if (p.estEgal(q))
                {
                    if (!RabinMiller.testRabinMiller(p) && RabinMiller.testRabinMiller(q))
                        p = NombreBinaire.randomAvecTailleMax(ParametresRSA.getTailleCle());
                    else if (RabinMiller.testRabinMiller(p) && !RabinMiller.testRabinMiller(q))
                        q = NombreBinaire.randomAvecTailleMax(ParametresRSA.getTailleCle());
                    else
                    {
                        p = NombreBinaire.randomAvecTailleMax(ParametresRSA.getTailleCle());
                        q = NombreBinaire.randomAvecTailleMax(ParametresRSA.getTailleCle());
                    }
                }
                if (!RabinMiller.testRabinMiller(p))
                    p = NombreBinaire.randomAvecTailleMax(ParametresRSA.getTailleCle());
                if (!RabinMiller.testRabinMiller(q))
                    q = NombreBinaire.randomAvecTailleMax(ParametresRSA.getTailleCle());
            }
        } catch (ExceptionConversionImpossible ex) {
            Logger.getLogger(GenerateurDeClesRSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        NombreBinaire one = new NombreBinaire(1);
        P = p;
        Q = q;
        N = this.P.multiplication(this.Q);
        phi = (P.soustraction(one)).multiplication(Q.soustraction(one));
        NombreBinaire eP = NombreBinaire.randomAvecTailleMax(ParametresRSA.getTailleCle());
        try {
            while (RabinMiller.temoin(e, phi))
                eP = NombreBinaire.randomAvecTailleMax(ParametresRSA.getTailleCle());
        } catch (ExceptionConversionImpossible ex) {
            Logger.getLogger(GenerateurDeClesRSA.class.getName()).log(Level.SEVERE, "RabinMiller.temoin -> crash", ex);
        }
        return null;
    }

    @Override
    public Cles genererClePrivee() {
        Cles cles = new Cles();
        try {
            NombreBinaire temp1 = this.P.soustraction(new NombreBinaire(1));
            NombreBinaire temp2 = this.Q.soustraction(new NombreBinaire(1));
            this.phi = temp1.multiplication(temp2);
            NombreBinaire valeurD = this.e.inverseModulaire(phi);
            MotBinaire motD = new MotBinaire(valeurD,valeurD.getTaille());
            CleBinaire d = new CleBinaire(motD);
            cles.addCle("cleRSA", d);
        } catch (ExceptionConversionImpossible ex) {
            Logger.getLogger(GenerateurDeClesRSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cles;
    }

    public void setP(NombreBinaire P) {
        this.P = P;
    }

    public void setQ(NombreBinaire Q) {
        this.Q = Q;
    }

    public void setPhi(NombreBinaire phi) {
        this.phi = phi;
    }

    public void setE(NombreBinaire e) {
        this.e = e;
    }
    

    
    
}
