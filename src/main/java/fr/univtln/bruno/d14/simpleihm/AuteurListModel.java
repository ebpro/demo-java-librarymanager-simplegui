package fr.univtln.bruno.d14.simpleihm;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by bruno on 03/10/14.
 */
public class AuteurListModel extends DefaultComboBoxModel<Auteur> implements Observer {
    private final List<Auteur> auteurs;

    public AuteurListModel(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    @Override
    public void update(Observable o, Object arg) {
        //si l'auteur selectionné n'est plus dans la liste on déselectionne.
        if (!auteurs.contains(getSelectedItem()))
            setSelectedItem(null);
        else System.out.println(getSelectedItem()+" in "+auteurs);
        fireContentsChanged(this, 0, auteurs.size() - 1);
    }

    @Override
    public int getSize() {
        return auteurs.size();
    }

    @Override
    public Auteur getElementAt(int index) {
        return auteurs.get(index);
    }
}
