package control;

import view.Vue;
import javax.swing.*;
import model.Pizza;
import model.LigneCommande;
import model.Commande;
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
        // reglage des actions listener des boutons du menu
        for(JMenuItem button : view.getAllButtons()) {
            button.addActionListener(e -> {
                // Handle button click event
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


    public void setAddToOrderListener() {
        JButton addPizza = view.getAddButton();
        addPizza.addActionListener(e -> addToOrder());
    }

    public void setAddToOrderListener(JButton addButton) {
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

            // Crée une seule ligne de commande avec la quantité spécifiée
            LigneCommande ligneCommande = new LigneCommande(q,
                    pizza.getPrix_de_base(), currentCommande, pizza);

            // Ajoute la ligne à la commande
            currentCommande.addLigneCommande(ligneCommande);

            // Met à jour l'affichage
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

//    public void retourMenuListener() {
//
//        backButton.addActionListener(e -> {
//            model.setBase(0);
//            view.updateView();
//        });
//    }

}
