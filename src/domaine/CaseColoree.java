package domaine;

/**
 * Classe colorée, enfant de Case. Parent des écuries et échelles.
 */
public abstract class CaseColoree extends Case {
    private Couleur couleur;

    /**
     * Constructeur CaseColoree()
     * @param couleur Couleur de l'équipe à laquelle appartient la case.
     */
    public CaseColoree(Couleur couleur) {
        super();
        this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

}
