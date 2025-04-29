package control;

import view.Vue;
import javax.swing.*;
import model.Pizza;
import model.LigneCommande;
import model.Commande;

public class Controller {
    private model.Model model;
    private Vue view;
    private JSpinner choisirQuantite;

    public Controller(model.Model m, Vue v) {
        // Constructor logic if needed
        this.model = m;
        this.view = v;
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
    public void addToOrderListener(JSpinner choisirQuantite) {
        // Récupérer la pizza sélectionnée
        Pizza pizza = model.getPizzaSelectionnee();
        if (pizza != null) {
            // Créer une nouvelle ligne de commande
            LigneCommande ligneCommande = new LigneCommande((int) choisirQuantite.getValue(),
                    pizza.getPrix_de_base(), model.getCurrentCommande(), pizza);
            // Ajouter la ligne de commande à la commande
            model.getCurrentCommande().addLigneCommande(ligneCommande);
            JOptionPane.showMessageDialog(view, "Pizza ajoutée à la commande", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner une pizza", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }



}
