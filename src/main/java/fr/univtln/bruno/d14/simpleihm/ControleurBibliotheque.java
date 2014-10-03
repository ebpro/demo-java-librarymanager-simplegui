package fr.univtln.bruno.d14.simpleihm;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * Created by bruno on 03/10/14.
 */
public class ControleurBibliotheque {
    private VueBibliotheque vueBibliotheque;
    private ModeleBibliotheque modeleBibliotheque;

    private Document nomNouvelAuteurModel = new PlainDocument();
    private Document prenomNouvelAuteurModel = new PlainDocument();

    public Document getNomNouvelAuteurModel() {
        return nomNouvelAuteurModel;
    }

    public Document getPrenomNouvelAuteurModel() {
        return prenomNouvelAuteurModel;
    }

    public ControleurBibliotheque(final VueBibliotheque vueBibliotheque, ModeleBibliotheque modeleBibliotheque) {
        this.vueBibliotheque = vueBibliotheque;
        this.modeleBibliotheque = modeleBibliotheque;

        DocumentListener ecouteurChangementTexte = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if ((nomNouvelAuteurModel.getLength() == 0 || prenomNouvelAuteurModel.getLength() == 0))
                    vueBibliotheque.setCreationAuteurOk(false);
                else
                    vueBibliotheque.setCreationAuteurOk(true);
            }
        };
        nomNouvelAuteurModel.addDocumentListener(ecouteurChangementTexte);
        prenomNouvelAuteurModel.addDocumentListener(ecouteurChangementTexte);

    }

    public void ajouterAuteur() {
        try {
            modeleBibliotheque.ajouterAuteur(new Auteur.AuteurBuilder()
                    .setPrenom(prenomNouvelAuteurModel.getText(0, prenomNouvelAuteurModel.getLength()))
                    .setNom(nomNouvelAuteurModel.getText(0, nomNouvelAuteurModel.getLength()))
                    .createAuteur());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        clearAuteur();
    }

    public void ajouterAuteur(Auteur auteur) {
        modeleBibliotheque.ajouterAuteur(auteur);
    }

    public void supprimerAuteur(Auteur auteur) {
        modeleBibliotheque.supprimerAuteur(auteur);
    }

    public void clearAuteur() {
        try {
            nomNouvelAuteurModel.remove(0,nomNouvelAuteurModel.getLength());
            prenomNouvelAuteurModel.remove(0,prenomNouvelAuteurModel.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
