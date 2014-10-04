package fr.univtln.bruno.d14.simpleihm;

import java.util.*;

/**
 * Created by bruno on 03/10/14.
 */
public class ModeleBibliotheque extends Observable {
    final List<Auteur> auteurs = new ArrayList();

    public enum ModeleBibliothequeEvent {AUTEUR, DOCUMENT, MATERIEL}

    public Auteur getAuteur(final int ID) {
        for (Auteur auteur:auteurs)
            if (auteur.ID == ID) return auteur;
        return null;
    }

    public void ajouterAuteur(Auteur auteur) {
        auteurs.add(auteur);
        //On previent les observateurs du changement
        setChanged();
        notifyObservers(ModeleBibliothequeEvent.AUTEUR);
    }

    public void supprimerAuteur(Auteur auteur) {
        auteurs.remove(auteur);
        //On previent les observateurs du changement
        setChanged();
        notifyObservers(ModeleBibliothequeEvent.AUTEUR);
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }
}
