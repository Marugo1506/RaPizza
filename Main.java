import control.ConnexionController;
import control.Controller;
import control.ControllerAdmin;
import model.*;
import view.ConnexionVue;
import view.Vue;
import view.VueAdmin;
import view.VuePayement;

import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        PointRaPizza pointRaPizza1 = new PointRaPizza(1, "123 Rue de la Paix");

        PointRaPizza pointRaPizza2 = new model.PointRaPizza(2, "456 Avenue des Champs-Élysées");

        Livreur livreur1 = new Livreur(1, "Jean Dupont", "Scooter");
        pointRaPizza1.addLivreur(livreur1);
        livreur1.addPointRaPizza(pointRaPizza1);
/**
// Clients enregistrés
        Client client1 = new Client(123456789, "Alice", 50.0f, "789 Boulevard Saint-Germain");
        pointRaPizza1.addClient(client1); // faire les liens entre les objets 
        client1.addPointRaPizza(pointRaPizza1);
        //model.Client client2 = new model.Client(987654321, "Bob", 30.0f, "321 Rue de Rivoli");


// Ingrédients disponibles
        Ingredient ingredient1 = new Ingredient("Sauce tomate");
        Ingredient ingredient2 = new Ingredient("Mozzarella");
        Ingredient ingredient3 = new Ingredient("Basilic");
        
        pointRaPizza1.addIngredient(ingredient1); // lien
        pointRaPizza1.addIngredient(ingredient2);
        pointRaPizza1.addIngredient(ingredient3);

// Pizzas disponibles
        Pizza pizza1 = new Pizza(1, "Margherita", 8.5);


// Ajout des ingrédients aux pizzas
        pizza1.addIngredient(ingredient1);
        pizza1.addIngredient(ingredient2);
        pizza1.addIngredient(ingredient3);

// Date
        java.util.Date date1 = new java.util.Date();

// Livreurs enregistrés

        // Commandes
        Commande commande1 = new Commande(1, date1, client1, pointRaPizza1, livreur1);

// Ligne de commande
        LigneCommande ligneCommande1 = new LigneCommande(1, 0.5, commande1, pizza1);
        LigneCommande ligneCommande2 = new LigneCommande(2, 1, commande1, pizza1);

        commande1.addLigneCommande(ligneCommande1);
        commande1.addLigneCommande(ligneCommande2);
        pizza1.addLigneCommande(ligneCommande1); 
        pizza1.addLigneCommande(ligneCommande2); 
        client1.addCommande(commande1); 
        livreur1.addCommande(commande1); 
        pointRaPizza1.addCommande(commande1); 
    

// Mise à jour de la commande avec le livreur
        commande1.setLivreur(livreur1);
*/
// Affichage pour vérifier
        /**System.out.println("model.Commande ID: " + commande1.getId_commande());
        System.out.println("model.Commande passee le: " + commande1.getDate());
        System.out.println("model.Client: " + commande1.getClient().getNom());
        System.out.println("model.Livreur en charge de la commande: " + livreur1.nom);
        System.out.println("Adresse du point de Ra-model.Pizza: " + commande1.getPointRaPizza().adresse);
        System.out.println("Adresse du client: " + client1.getAdresse());
        System.out.println("Pizzas dans la commande:");
        for (LigneCommande lc : commande1.getListLigneCommande()) {
            System.out.println("- " + lc.getQuantite()+ " " + lc.getPizza().getNom_pizza() + " (prix: " + lc.getPizza().getPrix_de_base() + ")");
        }
        System.out.println("Ingredients de la pizza:");
        for (Ingredient ing : pizza1.listIngredient) {
            System.out.println("- " + ing.nom_ingredient);
        }
        double prix_total = 0;
        for (LigneCommande lc : commande1.getListLigneCommande()) {
            prix_total += (lc.getPizza().getPrix_de_base() * lc.getTaille()) * lc.getQuantite();
        }
        System.out.println("Prix total de la commande: " + prix_total + " euros");
        **/

        /** Etapes du main :
         * 1 - Creer un modele (qui contient toutes les infos de la pizzeria
         * 2 - Créer une vue qui permet a un client de se connecter
         * 3 - Lorsque le client est connecté, passée a la vue du menu
         * 4 - Le client fais ce qu'il veux puis doit payer
         * 5 - possiblement afficher les infos de la commande / livraison / du client */

        Vector<PointRaPizza> points = new Vector<>(Arrays.asList(
                pointRaPizza1,pointRaPizza2
        ));
        Vector<Livreur> livreurs = new Vector<>();
        Model m1 = new Model("RaPizza", points ,livreurs);
        while(true) {
            // Afficher la vue de connexion
            ConnexionVue connexionVue = new ConnexionVue();
            connexionVue.setPreferredSize(new Dimension(800, 600));
            connexionVue.setVisible(true);
            connexionVue.pack();
            ConnexionController connexionController = new ConnexionController(m1, connexionVue);

            // Attendre la connexion (passe la boucle quand le client est connecté)
            while(!m1.isConnected()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Si la personne qui s'est connectée est un admin ou pas
            if(m1.getClient() instanceof Admin){

                connexionVue.setVisible(false);

                VueAdmin vueAdmin = new VueAdmin(m1);
                ControllerAdmin controllerAdmin = new ControllerAdmin(m1,vueAdmin);
                vueAdmin.setController(controllerAdmin);
                vueAdmin.setPreferredSize(new Dimension(1200, 900 ));
                vueAdmin.setVisible(true);
                vueAdmin.pack();

                // Tant que l'admin est connecté on fait rien dans le main
                while(m1.isConnected()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else { //coté client
                connexionVue.setVisible(false);
                m1.initCurrentCommande(pointRaPizza1);
                Vue v1 = new Vue(m1, m1.getCurrentCommande());
                Controller controller = new Controller(m1, v1);
                v1.setController(controller);
                v1.setPreferredSize(new Dimension(1200, 900 ));
                v1.setVisible(true);
                v1.pack();

                // Attendre que l'utilisateur se déconnecte (via le bouton Annuler)
                while(m1.isConnected()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Lorsque déconnecté, fermer la vue principale et recommencer le cycle
                v1.dispose();
            }
        }


    }
}