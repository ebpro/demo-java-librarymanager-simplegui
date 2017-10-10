package fr.univtln.bruno.coursjava.librarymanager.simplegui;

import fr.univtln.bruno.coursjava.librarymanager.BibliothequeModele;
import fr.univtln.bruno.coursjava.librarymanager.IBibliotheque;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Created by bruno on 03/10/14.
 */
public class ControleurBibliotheque {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private VueBibliotheque vueBibliotheque;
    private BibliothequeModele modeleBibliotheque;
    private Document emailNouvelAdherentModel = new PlainDocument();
    private Document nomNouvelAdherentModel = new PlainDocument();
    private Document prenomNouvelAdherentModel = new PlainDocument();


    public ControleurBibliotheque(final VueBibliotheque vueBibliotheque, BibliothequeModele modeleBibliotheque) {
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
                try {
                    if ((emailNouvelAdherentModel.getLength() == 0
                            || !(VALID_EMAIL_ADDRESS_REGEX.matcher(emailNouvelAdherentModel.getText(0, emailNouvelAdherentModel.getLength())).find())
                            || nomNouvelAdherentModel.getLength() == 0 || prenomNouvelAdherentModel.getLength() == 0))
                        vueBibliotheque.setCreationAdherentOk(false);
                    else
                        vueBibliotheque.setCreationAdherentOk(true);
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
        };
        emailNouvelAdherentModel.addDocumentListener(ecouteurChangementTexte);
        nomNouvelAdherentModel.addDocumentListener(ecouteurChangementTexte);
        prenomNouvelAdherentModel.addDocumentListener(ecouteurChangementTexte);

    }

    public Document getEmailNouvelAdherentModel() {
        return emailNouvelAdherentModel;
    }

    public Document getNomNouvelAdherentModel() {
        return nomNouvelAdherentModel;
    }

    public Document getPrenomNouvelAdherentModel() {
        return prenomNouvelAdherentModel;
    }

    public void ajouterAdherent() {
        try {
            modeleBibliotheque.addAdhérent(
                    emailNouvelAdherentModel.getText(0, emailNouvelAdherentModel.getLength()), prenomNouvelAdherentModel.getText(0, prenomNouvelAdherentModel.getLength()),
                    nomNouvelAdherentModel.getText(0, nomNouvelAdherentModel.getLength()), IBibliotheque.Adhérent.Statut.ENSEIGNANT);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        clearAdherent();
    }

    public void clearAdherent() {
        try {
            emailNouvelAdherentModel.remove(0, emailNouvelAdherentModel.getLength());
            nomNouvelAdherentModel.remove(0, nomNouvelAdherentModel.getLength());
            prenomNouvelAdherentModel.remove(0, prenomNouvelAdherentModel.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void removeAdherent(IBibliotheque.Adhérent adhérent) {
        modeleBibliotheque.remove(adhérent);
    }

    public Collection<IBibliotheque.Adhérent> getAdherents() {
        return modeleBibliotheque.getAdhérents();
    }
}
