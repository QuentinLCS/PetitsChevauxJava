package domaine;

import exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe instaciable de la Partie.
 */
public class Partie implements Serializable {

    private Random de = new Random() ;
    private Joueur joueurCourant;
    private ArrayList<Joueur> joueurs;
    private Plateau plateau;

    /**
     * Constructeur Partie()
     *
     * Ce dernier initialise la partie :
     * - Création du plateau
     * - Nombre de Joueurs
     * - Initialisation des joueurs.
     */
    public Partie() {
        this.initialiserPlateau();
        Scanner sc = new Scanner(System.in);
        System.out.print("Veuillez entrer le nombre de joueurs de la partie (entre 1 et 4) : ");
        byte nbJoueur = PetitsChevaux.choixMenu((byte)1, (byte)4);
        initialiserJoueurs(nbJoueur);
    }

    /**
     * Permet d'initialiser les instances de joueurs humains, leur assigne une couleur et choisir qui commence
     * @param nbJoueur Nombre de joueurs renseignés par le joueur dans le constructeur.
     */
    public void initialiserJoueurs(int nbJoueur) {
        joueurs = new ArrayList<>(nbJoueur);
        ArrayList<Joueur> joueurs = new ArrayList<>(nbJoueur);
        if (nbJoueur <= 4) {
            Scanner sc = new Scanner(System.in);
            String nom;
            for (int i = 0; i < nbJoueur; i++) {
                System.out.print("Entrer le nom du joueur " + (i + 1) + " : ");
                nom = sc.nextLine();
                joueurs.add(new JoueurHumain(nom, Couleur.values()[i]));
                System.out.println("Très bien " + joueurs.get(i).getNom() + ", vous serez de couleur " + joueurs.get(i).getCouleur().getCodeCouleurFond() + joueurs.get(i).getCouleur() + "\033[0m !");
            }
            // initialisation des bots
            byte difficulte;
            if (nbJoueur < 4) {
                System.out.println("Choisissez le niveau de difficulté de l'I.A :\n[1] Facile\n[2] Moyen\n[3] Difficile");
                difficulte = PetitsChevaux.choixMenu((byte)1, (byte)3);
                for (int j = nbJoueur; j < 4; j++)
                    joueurs.add(new JoueurIA(("Bot" + j), Couleur.values()[j], difficulte));
            }


            int res = -1;
            int de;
            System.out.println("Déterminons maintenant l'ordre de jeu !");
            for (Joueur player : joueurs) {
                for (Pion cheval : player.getChevaux()){
                    cheval.setPosition(plateau.getEcuries().get(player.getCouleur().getId()));
                    plateau.getEcuries().get(player.getCouleur().getId()).ajouteCheval(cheval);
                }
                switch (player.getCouleur().getId()) {
                    case 0:
                        player.setCaseDeDepart(plateau.getChemin().get(44));
                        break;
                    case 1:
                        player.setCaseDeDepart(plateau.getChemin().get(2));
                        break;
                    case 2:
                        player.setCaseDeDepart(plateau.getChemin().get(30));
                        break;
                    case 3:
                        player.setCaseDeDepart(plateau.getChemin().get(16));
                        break;
                }
                if (player instanceof JoueurHumain) {
                    System.out.print("\nJoueur " + player.getNom() + " (" + player.getCouleur().getCodeCouleurFond() + "     \033[0m), c'est à vous de lancer le dé ! [Appuyez sur entrée]");
                    sc.nextLine();
                    de = lancerDe();
                    System.out.println("Résultat : " + de);
                    if (de > res) {
                        joueurCourant = player;
                        res = de;
                    }
                } else {
                    de = lancerDe();
                    System.out.print("\nJoueur " + player.getNom() + " (" + player.getCouleur().getCodeCouleurFond() + "     \033[0m)");
                    System.out.println("Résultat : " + de);
                    if (de > res) {
                        joueurCourant = player;
                        res = de;
                    }
                }
            }
            for (int i=0; i<4; i++){
                this.joueurs.add(joueurs.get((joueurs.indexOf(joueurCourant)+i)%4));
            }
        }
    }

    /**
     * Appel le constructeur du plateau pour cette partie.
     */
    public void initialiserPlateau(){
        plateau = new Plateau();
    }

    /**
     * Permet de lancer un dé de 6.
     * @return Retourne la valeur du dé.
     */
    private int lancerDe(){
        return de.nextInt(6)+1;
    }

    public void jouerUnTour() throws ConflitDeCouleurException{
        Case arrivee;
        int de;

        for (Pion pion:joueurCourant.getChevaux()) System.out.println(pion.getPosition());

        if (joueurCourant instanceof JoueurHumain){
            Scanner sc = new Scanner(System.in);
            System.out.print("\nJoueur " + joueurCourant.getNom() + " (" + joueurCourant.getCouleur().getCodeCouleurFond() + "     \033[0m), c'est à vous de lancer le dé ! [Appuyez sur entrée]");
            sc.nextLine();
            de = lancerDe();
            System.out.println("Résultat : " + de);
        }
        else{
            System.out.print("\nC'est au tour du bot " + joueurCourant.getNom() + " (" + joueurCourant.getCouleur().getCodeCouleurFond() + "     \033[0m)");
            de=lancerDe();
            System.out.println("Résultat : " + de);
        }

        Pion choix=joueurCourant.choisirPion(de, plateau);

        if (choix != null){
            int idCouleurJoueur = joueurCourant.getCouleur().getId();
            CaseEcurie ecurieDuJoueur = plateau.getEcuries().get(idCouleurJoueur);
            ArrayList<CaseDEchelle> echelleDuJoueur = plateau.getEchelles().get(idCouleurJoueur);

            if (ecurieDuJoueur.equals(choix.getPosition())){
                arrivee = joueurCourant.getCaseDeDepart();
                if(!(arrivee.getChevaux().isEmpty()) && arrivee.getChevaux().get(0).getCouleur()!=joueurCourant.getCouleur() ) mangerLesPions(arrivee);
            }
            else if (echelleDuJoueur.contains(choix.getPosition())) {
                arrivee = echelleDuJoueur.get(echelleDuJoueur.indexOf(choix.getPosition()) + 1);
            }
            else{
                arrivee = plateau.getChemin().get(plateau.getChemin().indexOf(choix.getPosition())+de);
                if(!(arrivee.getChevaux().isEmpty()) && arrivee.getChevaux().get(0).getCouleur()!=joueurCourant.getCouleur()) mangerLesPions(arrivee);
            }
            plateau.deplacerPionA(choix, arrivee);
        }

        if (de == 6){
            jouerUnTour();
        }
    }

    /**
     * Test les conditions de fin de partie. C'est à dire que les 4 pions d'une couleur sont sur les case d'échelle les plus hautes.
     * @return Retourne si la condition est validée ou non.
     */
    public boolean estPartieTermine(){
        ArrayList<ArrayList<CaseDEchelle>> listeEchelles = getPlateau().getEchelles();
        boolean victoire = false;
        int i;
        for (ArrayList<CaseDEchelle> echelle : listeEchelles ){
            i=5;
            while (i>1 && echelle.get(i).getChevaux()!=null){
                i--;
            }
            if (i==1){
                victoire = true;
            }
        }
        return victoire;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * Méthode permettant de manger les chevaux, de les renvoyer dans leurs écuries et de se mettre à leur position.
     * @param cs Case sur laquelles les pions à manger se trouvent.
     */
    private void mangerLesPions(Case cs){
        ArrayList<Pion> chevaux = cs.getChevaux();
        ArrayList<CaseEcurie> ecuries = getPlateau().getEcuries();
        CaseEcurie ecu = ecuries.get(ecuries.indexOf(new CaseEcurie(chevaux.get(0).getCouleur())));
        for (Pion pion:chevaux) getPlateau().deplacerPionA(pion, ecu);
    }

    /**
     * Sauvegarder dans un fichier donné en paramètre.
     * @param fichier Nom du fichier en format string.
     */
    public void sauvegarderPartie(String fichier) {
        ObjectOutputStream oos;

        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    new File(fichier))));

            oos.writeObject(this);

            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charger un fichier donné en paramètre.
     * @param fichier Nom du fichier en format string.
     */
    public void chargerPartie(String fichier) {
        ObjectInputStream ois;

        try {
            ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    new File(fichier))));

            try {
                System.out.println("Affichage des jeux :");
                System.out.println("*************************\n");
                System.out.println(((Partie)ois.readObject()).toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            ois.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

