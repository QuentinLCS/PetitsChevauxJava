package domaine;

/**
 * Classe des instances pour les cases d'échelles.
 */
public class CaseDEchelle extends CaseColoree {

    /**
     * Constructeur CaseDEchelle()
     * @param couleur Couleur a laquelle partient la case.
     */
    public CaseDEchelle(Couleur couleur) {
        super(couleur);
    }

    /**
     * Test si le cheval peut passer sur cette case.
     * - Si un cheval est déjà une une case d'échelle, impossible de passer.
     * @param cheval Cheval qui souhaite se déplacer.
     * @return Si oui ou non il peut passer.
     */
    public boolean peutPasser(Pion cheval) {
        return this.getChevaux().isEmpty();
    }
}
