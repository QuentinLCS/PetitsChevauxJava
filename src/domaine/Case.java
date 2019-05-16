package domaine;

import java.util.ArrayList;

public abstract class Case {
    private ArrayList<Pion> chevaux;

    public Case() {
        this.chevaux = new ArrayList<Pion>();
    }

    public ArrayList<Pion> getChevaux() {
        return chevaux;
    }

    public void ajouteCheval(Pion cheval) {
        chevaux.add(cheval);
    }

}
