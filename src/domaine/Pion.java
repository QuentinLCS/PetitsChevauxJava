package domaine;

public class Pion {
    private String id;
    private Couleur couleur;
    private Case position;

    public Pion(String id, Couleur couleur) {
        this.id = id;
        this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public Case getPosition() {
        return position;
    }

    public void setPosition(Case position) {
        this.position = position;
    }
}
