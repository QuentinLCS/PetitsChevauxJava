package domaine;

import exceptions.ConflitDeCouleurException;

/**
 * Classe pour les instances de cases de chemin.
 */
public class CaseDeChemin extends Case {

    /**
     * Constructeur des cases de chemain.
     */
    public CaseDeChemin() {
        super();
    }

    /**
     * Test si le cheval peut passer sur cette case.
     * - Si un cheval de couleur adverse est déjà sur la case, impossible de passer.
     * @param cheval Cheval souhaitant se déplacer.
     * @return Si oui ou non il peut se déplacer.
     * @throws ConflitDeCouleurException Exception si une couleur adverse est présente.
     */
    public boolean peutPasser(Pion cheval) throws ConflitDeCouleurException {
        if (!(this.getChevaux().isEmpty())) if (!(cheval.getCouleur().equals(this.getChevaux().get(0).getCouleur()))) throw new ConflitDeCouleurException();
        return this.getChevaux().isEmpty() || cheval.getCouleur().equals(this.getChevaux().get(0).getCouleur());
    }

}
