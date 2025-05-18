package control;

import view.Vue;
import javax.swing.*;
import javax.swing.plaf.ViewportUI;

import model.Pizza;
import model.LigneCommande;
import model.Commande;
import view.VuePayement;

import java.awt.*;
import java.awt.event.ActionListener;
import	java.util.Scanner;

public class Controller {
    private model.Model model;
    private Vue view;


    public Controller(model.Model m, Vue v) {
        // Constructor logic if needed
        this.model = m;
        this.view = v;
        setMenuButtonActionListener();
        setPizzaButtonActionListener();
    }

    public void setMenuButtonActionListener( ) {
        // Reglage des actions listener des boutons du menu
        for(JMenuItem button : view.getAllButtons()) {
            button.addActionListener(e -> {
                // Click event
                System.out.println("Button clicked!" + button.getText());
                model.setBase(button.getText());
                view.updateView();
            });
        }
        }


    public void setPizzaButtonActionListener() {
        for (JButton button : view.getPizzaButtons()) {
            System.out.println("debug" + button.getText());
            button.addActionListener(e -> {
                Pizza pizza = model.getPizzaParNom(button.getText());
                if (pizza != null) {
                    model.setPizzaSelectionnee(pizza);
                    model.setBase(3); // Mode détail

                    view.showInfoPizza(pizza);
                } else {
                    JOptionPane.showMessageDialog(view, "Pizza non trouvée", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    public void setAddToOrderListener(JButton addButton) {
        // Supprime tous les listeners existants
        for (ActionListener al : addButton.getActionListeners()) {
            addButton.removeActionListener(al);
        }
        addButton.addActionListener(e -> addToOrder());
    }

    public void addToOrder() {
        try {
            Pizza pizza = model.getPizzaSelectionnee();
            if (pizza == null) {
                throw new IllegalStateException("Aucune pizza sélectionnée");
            }

            JTextField quantiteField = view.getChoisirQuantite();
            if (quantiteField == null) {
                throw new IllegalStateException("Le champ de quantité n'est pas initialisé");
            }

            String quantiteText = quantiteField.getText().trim();
            if (quantiteText.isEmpty()) {
                throw new NumberFormatException("Veuillez entrer une quantité");
            }

            int q = Integer.parseInt(quantiteText);
            if (q <= 0) {
                throw new NumberFormatException("La quantité doit être positive");
            }

            Commande currentCommande = model.getCurrentCommande();
            if (currentCommande == null) {
                throw new IllegalStateException("Aucune commande en cours");
            }

            // crée une seule ligne de commande avec la quantité spécifiée
            LigneCommande ligneCommande = new LigneCommande(q,
                    pizza.getPrix_de_base(), currentCommande, pizza);

            // ajoute la ligne à la commande
            currentCommande.addLigneCommande(ligneCommande);

            // met à jour l'affichage de la commande
            view.updateCommandPanel();

            JOptionPane.showMessageDialog(view, q + " " + pizza.getNom_pizza() + "(s) ajoutée(s) à la commande",
                    "Succès", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Quantité invalide: " + ex.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }

//    public void menuListener(JButton b) {
//        b.addActionListener(e -> {
//            view.showMenuDeroulant();
//        });
//    }
public void setSuivantBoutonActionListener(JButton bouton){
    bouton.addActionListener(e -> {
        // Vérifie si la commande n'est pas vide
        if (model.getCurrentCommande().getListLigneCommande().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Votre commande est vide", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Passe en mode paiement
        model.paye();
        view.setVisible(false); // Cache la vue actuelle

        // Crée et affiche la vue de paiement
        VuePayement vp = new VuePayement(model,this);
        vp.setPreferredSize(new Dimension(800, 600));
        vp.pack();
        vp.setVisible(true);
    });
}
    public void setAnnulerBoutonActionListener(JButton bouton){
        bouton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(view,
                    "Voulez-vous vraiment annuler et vous déconnecter ?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                SwingUtilities.invokeLater(() -> {
                    view.dispose(); // Ferme la fenêtre de commande
                    model.reinitialiserCommande();
                    model.disconnect(); // Déconnecte l'utilisateur
                }); // Ferme la fenêtre principale
                //System.exit(0);
            }
        });
    }

    public void setDeleteButtonActionListener(JButton deleteButton, LigneCommande lc){
        deleteButton.addActionListener(e -> {
            System.out.println("Supprimer " + lc.getPizza().getNom_pizza());
            model.getCurrentCommande().removeLigneCommande(lc);
            view.updateCommandPanel(); // Rafraîchir l'affichage
        });
    }

    public void retourListener(JButton button) {

        button.addActionListener(e -> {
            System.out.println("Retour au menu principal" + model.getBase());
            model.setBase(model.getBaseFromPizza()); // Retour au menu principal
            view.updateView();
        });
    }

    public void setRechargeBoutonAction(JButton rechargeButton, VuePayement vp) {
        rechargeButton.addActionListener(e-> vp.rechargerSolde());
    }
    public void setPayButtonAction(JButton payButton, VuePayement vp) {
        payButton.addActionListener(e -> {
            vp.processPayment();
            model.reinitialiserCommande();
            view.setVisible(true);
            view.updateCommandPanel();
            view.updateView();
        });
    }
}
