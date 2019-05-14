package domaine;

import java.util.ArrayList;

public abstract class Case {
    private ArrayList<Pion> chevaux;

    public Case(ArrayList<Pion> chevaux) {
        this.chevaux = chevaux;
    }

    public ArrayList<Pion> getChevaux() {
        return chevaux;
    }

    public void ajouteCheval(Pion cheval) {
        chevaux.add(cheval);
    }

    public abstract boolean peutPasser(Pion cheval);

    public abstract boolean peutSArreter(Pion cheval);
}
