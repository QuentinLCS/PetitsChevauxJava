package domaine;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe case, parent de toutes les autres instances de type case du plateau.
 */
public abstract class Case implements Serializable{
    private ArrayList<Pion> chevaux;

    /**
     * Constructeur Case() initialise la liste de chevaux pour chaque case.
     */
    public Case() {
        this.chevaux = new ArrayList<>();
    }

    public ArrayList<Pion> getChevaux() {
        return chevaux;
    }

    /**
     * Ajouter un Pion dans la case.
     * @param cheval Cheval de type Pion à ajouter dans le case.
     */
    public void ajouteCheval(Pion cheval) {
        chevaux.add(cheval);
    }


}
