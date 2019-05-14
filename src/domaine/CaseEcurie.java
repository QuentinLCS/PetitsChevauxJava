package domaine;

import java.util.ArrayList;

public class CaseEcurie extends CaseColoree {
    public CaseEcurie(ArrayList<Pion> chevaux) {
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
