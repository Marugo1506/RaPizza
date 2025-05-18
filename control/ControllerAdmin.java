package control;

import model.Commande;
import model.Livreur;
import model.Model;
import view.VueAdmin;

import javax.swing.*;

public class ControllerAdmin {
    private Model model;
    private VueAdmin vueAdmin;

    public ControllerAdmin(Model m, VueAdmin vueAdmin){
        this.vueAdmin = vueAdmin;
        this.model = m;
    }

    public void setAction(JButton bouton){
        switch (bouton.getText()){
            case "Clients" -> bouton.addActionListener(e->{
                vueAdmin.setBase(0);vueAdmin.updateView();
            });
            case "Commandes" -> bouton.addActionListener(e->{
                vueAdmin.setBase(1);vueAdmin.updateView();
            });
            case "Retour" -> bouton.addActionListener(e -> {
                model.disconnect();
                vueAdmin.dispose();
            });
            case "Livreurs" -> bouton.addActionListener(e -> {
                vueAdmin.setBase(2);vueAdmin.updateView();
            });
        }

    }

    public void setActionCommande(JButton b, Commande c){
        b.addActionListener(e -> vueAdmin.showCommande(c));

    }

    public void setActionAddLivreur(JButton addLivreur) {
        addLivreur.addActionListener(e -> {
            String nom = JOptionPane.showInputDialog("Nom du livreur : ");
            String vehicule = JOptionPane.showInputDialog("Type de véhicule : ");
            if (nom != null && vehicule != null) {
                model.addLivreur(nom, vehicule);
                vueAdmin.updateView();
            }
        });
    }

    public void setActionRemoveLivreur(JButton supprLivreur, Livreur l) {
        supprLivreur.addActionListener(e -> {
            int confirmation = JOptionPane.showConfirmDialog(vueAdmin, "Êtes-vous sûr de vouloir supprimer ce livreur ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                model.removeLivreur(l);
                vueAdmin.updateView();
            }
        });
    }
}
