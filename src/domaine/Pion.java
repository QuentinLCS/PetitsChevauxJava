package domaine;

import java.io.Serializable;

/**
 * Classe de l'instance pion représentant les chevaux.
 */
public class Pion implements Serializable {
    private String id;
    private Couleur couleur;
    private Case position;

    /**
     * Constructeur Pion()
     * @param id Il s'agit de l'id du pion, utile à leur différenciation dans une ArrayList.
     * @param couleur Ce paramètre permet de définir leur couleur d'appartenance.
     */
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

    @Override
    public String toString() {
        return "Pion{" +
                "id='" + id + '\'' +
                ", couleur=" + couleur +
                ", position=" + position +
                '}';
    }
}
