package control;

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
        }

    }
}
