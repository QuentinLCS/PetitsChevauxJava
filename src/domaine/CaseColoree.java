package domaine;

public abstract class CaseColoree extends Case {
    private Couleur couleur;

    public CaseColoree(Couleur couleur) {
        super();
        this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

}
