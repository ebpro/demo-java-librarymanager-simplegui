package fr.univtln.bruno.coursjava.librarymanager.simplegui;


import fr.univtln.bruno.coursjava.librarymanager.IBibliotheque;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by bruno on 03/10/14.
 */
public class AdherentListModel extends DefaultComboBoxModel<IBibliotheque.Adhérent> implements Observer {
    private final List<IBibliotheque.Adhérent> adhérents;

    public AdherentListModel(List<IBibliotheque.Adhérent> adhérents) {
        this.adhérents = adhérents;
    }

    @Override
    public void update(Observable o, Object arg) {
        //si l'adhérent selectionné n'est plus dans la liste on déselectionne.
        if (!adhérents.contains(getSelectedItem()))
            setSelectedItem(null);
        else System.out.println(getSelectedItem() + " in " + adhérents);
        fireContentsChanged(this, 0, adhérents.size() - 1);
    }

    @Override
    public int getSize() {
        return adhérents.size();
    }

    @Override
    public IBibliotheque.Adhérent getElementAt(int index) {
        return adhérents.get(index);
    }
}
