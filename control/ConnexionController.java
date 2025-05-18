package control;

import model.Client;

import javax.swing.*;

public class ConnexionController {
    private model.Model model;
    private view.ConnexionVue view;

    public ConnexionController(model.Model model, view.ConnexionVue view) {
        this.model = model;
        this.view = view;

        // Set action listeners for buttons
        view.getLoginButton().addActionListener(e -> handleLogin());
        view.getCancelButton().addActionListener(e -> handleCancel());
        view.getCancelCreateButton().addActionListener(e -> view.switchVisibility());
        view.getCreateButton().addActionListener(e -> view.switchVisibility());
        view.getRegisterButton().addActionListener(e -> handleRegister());
    }

    private void handleLogin() {
        String username = view.getUsername();
        String tel = view.getPassword();

        if (model.isUser(new Client(username),tel)) {
            // Handle successful login
            System.out.println("Login successful for user: " + username);

        } else {
            JOptionPane.showMessageDialog(view, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRegister() {
        // gérer l'inscription d'un client
        String adresse = view.getAdresseCreateField().getText();
        String tel = view.getTelCreateField().getText();
        String nom = view.getNameCreateField().getText();

        if (adresse.isEmpty() || tel.isEmpty() || nom.isEmpty()
        || !model.numTelValide(tel)) {
            JOptionPane.showMessageDialog(view, "Saississez des Informations correctes", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            Client newClient = new Client(tel,nom,0 , adresse);
            model.addUser(newClient);
            JOptionPane.showMessageDialog(view, "Inscription réussie", "Succès", JOptionPane.INFORMATION_MESSAGE);
            model.setConnectedUser(newClient);

        }
    }

    private void handleCancel() {
        System.exit(0);
    }
}