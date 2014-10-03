package fr.univtln.bruno.d14.simpleihm;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ModeleBibliotheque bibliotheque = new ModeleBibliotheque();
        new VueBibliotheque(bibliotheque);

        bibliotheque.ajouterAuteur(new Auteur.AuteurBuilder().setPrenom("Jean").setNom("Martin").createAuteur());
        bibliotheque.ajouterAuteur(new Auteur.AuteurBuilder().setPrenom("Marie").setNom("Durand").createAuteur());



    }
}
