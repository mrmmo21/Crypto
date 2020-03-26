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
        System.out.println("Generating randoms");
        NombreBinaire min = new NombreBinaire(1).decalage(ParametresRSA.getTailleDemiCle()-1);
        NombreBinaire max = new NombreBinaire(1).decalage(ParametresRSA.getTailleDemiCle());
        NombreBinaire random1 = NombreBinaire.random(min, max);
        NombreBinaire random2 = NombreBinaire.random(min, max);
        System.out.println("Finished generating randoms");
        NombreBinaire p = null;
        try {
            System.out.println("Generating p with " + random1.toString());
            p = RabinMiller.nombrePremier(random1);
            System.out.println("Finished generating p");
        } catch (ExceptionConversionImpossible ex) {
            Logger.getLogger(GenerateurDeClesRSA.class.getName()).log(Level.SEVERE, "could not find 'p'", ex);
        }
        NombreBinaire q = null;
        try {
            System.out.println("Generating q with " + random2.toString());
            q = RabinMiller.nombrePremier(random2);
            System.out.println("Finished generating q");
        } catch (ExceptionConversionImpossible ex) {
            Logger.getLogger(GenerateurDeClesRSA.class.getName()).log(Level.SEVERE, "could not find 'q'", ex);
        }
        if (q == null || p == null) throw new NullPointerException("p or q is null");
        try {
            while (p.estEgal(q))
            {
                System.out.println(p.estEgal(q));
                System.out.println("Rabin Miller p : " + RabinMiller.testRabinMiller(p));
                System.out.println("Rabin Miller q : " + RabinMiller.testRabinMiller(q));
                if (p.estEgal(q))
                {
                    random2 = NombreBinaire.randomAvecTailleMax(ParametresRSA.getTailleCle());
                    try {
                        p = RabinMiller.nombrePremier(random2);
                    } catch (ExceptionConversionImpossible ex) {
                        Logger.getLogger(GenerateurDeClesRSA.class.getName()).log(Level.SEVERE, "could not generate 'p'", ex);
                    }
                }
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
            while (RabinMiller.temoin(eP, phi))
                eP = NombreBinaire.randomAvecTailleMax(ParametresRSA.getTailleCle());
            e = eP;
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
            MotBinaire motD = new MotBinaire(valeurD,ParametresRSA.getTailleCle());
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
