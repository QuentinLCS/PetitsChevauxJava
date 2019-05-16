package domaine;

public class CaseDEchelle extends CaseColoree {
    public CaseDEchelle(Couleur couleur) {
        super(couleur);
    }

    public boolean peutPasser(Pion cheval) {
        return this.getChevaux().isEmpty();
    }
}
