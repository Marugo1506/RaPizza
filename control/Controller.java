package control;

import view.Vue;
import javax.swing.*;
import model.Pizza;
import model.LigneCommande;
import	java.util.Scanner;

public class Controller {
    private model.Model model;
    private Vue view;
    public JTextField choisirQuantite;

    public Controller(model.Model m, Vue v) {
        // Constructor logic if needed
        this.model = m;
        this.view = v;
        this.choisirQuantite = v.getChoisirQuantite();
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
    public void setChoisirQuantite(JTextField choisirQ) {
        this.choisirQuantite = choisirQ;
    }

    public void setAddToOrderListener() {
        JButton addPizza = view.getAddButton();
        addPizza.addActionListener(e -> addToOrder());
    }

    public void addToOrder() {
            try {
                // Récupérer la pizza sélectionnée et la quantité choisie
                Pizza pizza = model.getPizzaSelectionnee();
                Scanner scanner = new Scanner(choisirQuantite.getText());
                if (scanner.hasNextInt()) {
                    int q = scanner.nextInt();
                    // Créer une nouvelle ligne de commande
                    LigneCommande ligneCommande = new LigneCommande(q,
                            pizza.getPrix_de_base(), model.getCurrentCommande(), pizza);
                    // Ajouter la ligne de commande à la commande
                    model.getCurrentCommande().addLigneCommande(ligneCommande);
                    JOptionPane.showMessageDialog(view, "Pizza ajoutée à la commande", "Succès", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(view, "Nombre invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erreur lors de l'ajout à la commande", "Erreur", JOptionPane.ERROR_MESSAGE);
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
