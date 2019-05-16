package domaine;

import java.util.ArrayList;

public abstract class CaseColoree extends Case {
    private Couleur couleur;

    public CaseColoree(Couleur couleur) {
        super();
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public abstract boolean peutPasser(Pion cheval);

    public abstract boolean peutSArreter(Pion cheval);
}
