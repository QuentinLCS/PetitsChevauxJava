package domaine;

public class CaseEcurie extends CaseColoree {
    public CaseEcurie(Couleur couleur) {
        super(couleur);
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
