package fr.univtln.bruno.coursjava.librarymanager.simplegui;

import fr.univtln.bruno.coursjava.librarymanager.Bibliotheque;
import fr.univtln.bruno.coursjava.librarymanager.BibliothequeModele;
import fr.univtln.bruno.coursjava.librarymanager.IBibliotheque;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        Logger logger = Logger.getAnonymousLogger();
        logger.setLevel(Level.FINEST);

        BibliothequeModele bibliotheque = null;
        bibliotheque = new BibliothequeModele<Bibliotheque>("Ma Bibliothèque");
        new VueBibliotheque(bibliotheque);
        bibliotheque.addAdhérent("jean.martin@test.fr", "Jean", "Martin", IBibliotheque.Adhérent.Statut.ENSEIGNANT);
        bibliotheque.addAdhérent("mdurand@labas.com", "Marie", "Durand", IBibliotheque.Adhérent.Statut.ETUDIANT);
    }
}
