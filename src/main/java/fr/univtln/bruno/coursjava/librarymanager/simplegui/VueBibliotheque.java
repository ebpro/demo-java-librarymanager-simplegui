package fr.univtln.bruno.coursjava.librarymanager.simplegui;

import fr.univtln.bruno.coursjava.librarymanager.Bibliotheque;
import fr.univtln.bruno.coursjava.librarymanager.BibliothequeModele;
import fr.univtln.bruno.coursjava.librarymanager.IBibliotheque;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by bruno on 03/10/14.
 */
public class VueBibliotheque extends JFrame {
    private final BibliothequeModele<Bibliotheque> modeleBibliotheque;
    private final ControleurBibliotheque controleurBibliotheque;

    private final AdherentListModel adherentListModel;

    private final JPanel adherentsSuppressionPanel = new JPanel(new GridBagLayout());
    private final JPanel adherentDetailPanel = new JPanel(new GridBagLayout());
    private final JPanel adherentAjoutPanel = new JPanel(new GridBagLayout());

    private final JList<IBibliotheque.Adhérent> adhérentJList;
    private final JButton supprimerAdherentJButton = new JButton("Supprimer Adhérent");

    private final JComboBox<IBibliotheque.Adhérent> adhérentJComboBox;
    private final JTextField emailAdherentAjoutjTextField;
    private final JTextField nomAdherentAjoutjTextField;
    private final JTextField prenomAdherentAjoutjTextField;
    private final JLabel emailAdherentJLabel = new JLabel("Email");
    private final JLabel nomAdherentJLabel = new JLabel("Nom");
    private final JLabel prenomAdherentJLabel = new JLabel("Prénom");
    private final JButton annulerAdherentJButton = new JButton("Annuler");
    private final JButton ajouterAdherentJButton = new JButton("Ajouter Adhérent");

    private final JTextField idAdherentDetailjTextField = new JTextField();
    private final JTextField prenomAherentDetailjTextField = new JTextField();
    private final JTextField nomAherentDetailjTextField = new JTextField();

    public VueBibliotheque(BibliothequeModele modeleBibliotheque) {

        super("Bibliotheque");
        setSize(800, 600);

        this.modeleBibliotheque = modeleBibliotheque;
        this.controleurBibliotheque = new ControleurBibliotheque(this, modeleBibliotheque);
        this.adherentListModel = new AdherentListModel(new ArrayList(this.modeleBibliotheque.getAdhérents()));

        modeleBibliotheque.addObserver(adherentListModel);

        adhérentJList = new JList<>(adherentListModel);
        adhérentJList.setCellRenderer(new AdherentRenderer());
        adhérentJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setSuppressionAdherentOk(adhérentJList.getSelectedValue() != null);
            }
        });

        adhérentJComboBox = new JComboBox<IBibliotheque.Adhérent>(adherentListModel);
        adhérentJComboBox.setRenderer(new AdherentRenderer());
        adhérentJComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        montrerDetail(null);
                        break;
                    case ItemEvent.SELECTED:
                        montrerDetail(adhérentJComboBox.getItemAt(adhérentJComboBox.getSelectedIndex()));
                        break;
                }

            }
        });

        supprimerAdherentJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (IBibliotheque.Adhérent adhérent : adhérentJList.getSelectedValuesList())
                    controleurBibliotheque.removeAdherent(adhérent);
            }
        });

        ajouterAdherentJButton.setEnabled(false);
        ajouterAdherentJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleurBibliotheque.ajouterAdherent();
            }
        });

        supprimerAdherentJButton.setEnabled(false);

        annulerAdherentJButton.setEnabled(false);
        annulerAdherentJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleurBibliotheque.clearAdherent();
            }
        });

        emailAdherentAjoutjTextField = new JTextField(controleurBibliotheque.getEmailNouvelAdherentModel(), "", 10);
        nomAdherentAjoutjTextField = new JTextField(controleurBibliotheque.getNomNouvelAdherentModel(), "", 10);
        prenomAdherentAjoutjTextField = new JTextField(controleurBibliotheque.getPrenomNouvelAdherentModel(), "", 10);

        idAdherentDetailjTextField.setEditable(false);
        prenomAherentDetailjTextField.setEditable(false);
        nomAherentDetailjTextField.setEditable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints c = new GridBagConstraints();

        // Suppression d'un adherent
        adherentsSuppressionPanel.setBorder(BorderFactory.createTitledBorder("Suppression"));
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTHEAST;
        c.insets = new Insets(2, 2, 2, 2);
        c.weighty = 0.8;
        c.gridx = 0;
        c.gridy = 0;
        adherentsSuppressionPanel.add(adhérentJList, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridy = 1;
        adherentsSuppressionPanel.add(supprimerAdherentJButton, c);

        //Le détail d'un adherent
        adherentDetailPanel.setBorder(BorderFactory.createTitledBorder("Détails"));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        adherentDetailPanel.add(adhérentJComboBox, c);
        c.gridx = 0;
        c.gridy = 1;
        adherentDetailPanel.add(idAdherentDetailjTextField, c);
        c.gridx = 0;
        c.gridy = 2;
        adherentDetailPanel.add(nomAherentDetailjTextField, c);
        c.gridx = 0;
        c.gridy = 3;
        adherentDetailPanel.add(prenomAherentDetailjTextField, c);


        //L'ajout d'un adherent
        adherentAjoutPanel.setBorder(BorderFactory.createTitledBorder("Ajout"));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        adherentAjoutPanel.add(emailAdherentJLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        adherentAjoutPanel.add(emailAdherentAjoutjTextField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        adherentAjoutPanel.add(nomAdherentJLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        adherentAjoutPanel.add(nomAdherentAjoutjTextField, c);

        c.gridx = 0;
        c.gridy = 2;
        adherentAjoutPanel.add(prenomAdherentJLabel, c);
        c.gridx = 1;
        c.gridy = 2;
        adherentAjoutPanel.add(prenomAdherentAjoutjTextField, c);

        c.gridx = 0;
        c.gridy = 3;
        adherentAjoutPanel.add(annulerAdherentJButton, c);

        c.gridx = 1;
        c.gridy = 3;
        adherentAjoutPanel.add(ajouterAdherentJButton, c);

        //Ajout des panel de suppression, d'ajout et de detail
        getContentPane().setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        getContentPane().add(adherentDetailPanel, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        getContentPane().add(adherentsSuppressionPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        getContentPane().add(adherentAjoutPanel, c);
        //pack();

        setVisible(true);
    }

    private void setSuppressionAdherentOk(boolean b) {
        supprimerAdherentJButton.setEnabled(b);
    }

    public void setCreationAdherentOk(boolean creationAdherentOk) {
        ajouterAdherentJButton.setEnabled(creationAdherentOk);
        annulerAdherentJButton.setEnabled(creationAdherentOk);
    }

    public void montrerDetail(IBibliotheque.Adhérent adhérent) {
        if (adhérent == null) {
            idAdherentDetailjTextField.setText("");
            prenomAherentDetailjTextField.setText("");
            nomAherentDetailjTextField.setText("");
        } else {
            idAdherentDetailjTextField.setText(String.valueOf(adhérent.getId()));
            prenomAherentDetailjTextField.setText(adhérent.getPrenom());
            nomAherentDetailjTextField.setText(adhérent.getNom());
        }
    }

    private class AdherentListModel extends DefaultComboBoxModel<IBibliotheque.Adhérent> implements Observer {
        private final java.util.List<IBibliotheque.Adhérent> adhérents;

        public AdherentListModel(List<IBibliotheque.Adhérent> adhérents) {
            this.adhérents = adhérents;
        }

        @Override
        public void update(Observable o, Object arg) {
            //On raffraichit la liste
            this.adhérents.clear();
            this.adhérents.addAll(controleurBibliotheque.getAdherents());

            //si l'adhérent selectionné n'est plus dans la liste on déselectionne.
            if (!adhérents.contains(getSelectedItem()))
                setSelectedItem(null);
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

    class AdherentRenderer implements ListCellRenderer {
        protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

        public Component getListCellRendererComponent(JList list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
                    isSelected, cellHasFocus);
            if (value instanceof IBibliotheque.Adhérent) {
                IBibliotheque.Adhérent adherent = (IBibliotheque.Adhérent) value;
                renderer.setText(adherent.getNom() + ", " + adherent.getPrenom());
            }
            return renderer;
        }
    }
}
