package algorithmes.generateurdecles;

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
        
    @Override
    public Cles genererClePublique() {
       //TODO
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
            return cles;
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
