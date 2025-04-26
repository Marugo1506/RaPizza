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
    }

    private void handleLogin() {
        String username = view.getUsername();
        String password = view.getPassword();

        if (model.isUser(new Client(username))) {
            // Handle successful login
            System.out.println("Login successful for user: " + username);

        } else {
            JOptionPane.showMessageDialog(view, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleCancel() {
        System.exit(0);
    }
}
