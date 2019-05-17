package domaine;

/**
 * Enumeration de couleurs, cette dernière est utilisée pour l'affichage des couleurs surtout.
 * @author Quentin LECHASLES
 */
public enum Couleur {
    JAUNE("\033[93;107m","\033[1;97;43m", 0),
    BLEU("\033[94;107m","\033[1;97;104m",1),
    VERT("\033[92;107m","\033[1;97;42m",2),
    ROUGE("\033[91;107m","\033[1;97;101m",3);

    private String codeCouleur, codeCouleurFond;
    private int indexCaseDepart;

    /**
     * Le constructeur d'un objet Couleur. Les codes couleurs sont à disposer avant le message devant recevoir la coloration. Fermer la coloration avec "\033[0m".
     * @param codeCouleur Code couleur sur fond blanc de la couleur.
     * @param codeCouleurFond Code couleur blanc sur fond de la couleur.
     * @param indexCaseDepart Valeur de l'index dans la liste chemin de la case de départ pour une couleur donnée.
     */
    Couleur(String codeCouleur, String codeCouleurFond, int indexCaseDepart) {
        this.codeCouleur = codeCouleur;
        this.codeCouleurFond = codeCouleurFond;
        this.indexCaseDepart = indexCaseDepart;
    }

    public String getCodeCouleur() {
        return this.codeCouleur;
    }

    public String getCodeCouleurFond() {
        return this.codeCouleurFond;
    }

    public int getId() {
        return this.indexCaseDepart;
    }}
