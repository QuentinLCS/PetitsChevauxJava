package domaine;

import java.util.ArrayList;

public class CaseDEchelle extends CaseColoree {
    public CaseDEchelle(ArrayList<Pion> chevaux) {
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
