package domaine;

public class CaseDeChemin extends Case {
    public CaseDeChemin() {
        super();
    }

    public boolean peutPasser(Pion cheval) {
        return this.getChevaux().isEmpty() || cheval.getCouleur().equals(this.getChevaux().get(0).getCouleur());
    }

}
