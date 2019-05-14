package domaine;

import java.util.ArrayList;

public class CaseDeChemin extends Case {
    public CaseDeChemin(ArrayList<Pion> chevaux) {
        super(chevaux);
    }

    @Override
    public boolean peutPasser(Pion cheval) {
        return false;
    }

    @Override
    public boolean peutSArreter(Pion cheval) {
        return false;
    }
}
