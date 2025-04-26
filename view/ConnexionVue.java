package view;

import javax.swing.*;

public class ConnexionVue extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    public ConnexionVue() {
        setTitle("Connexion");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
        usernameLabel.setBounds(10, 10, 150, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(150, 10, 120, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setBounds(10, 40, 150, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 40, 120, 25);
        panel.add(passwordField);

        loginButton = new JButton("Connexion");
        loginButton.setBounds(50, 80, 100, 25);
        panel.add(loginButton);

        cancelButton = new JButton("Annuler");
        cancelButton.setBounds(160, 80, 100, 25);
        panel.add(cancelButton);

        add(panel);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

}
