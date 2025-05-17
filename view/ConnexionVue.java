package view;

import javax.swing.*;

public class ConnexionVue extends JFrame {
    private JTextField nameField;
    private JTextField telField;

    private JTextField nameCreateField;
    private JTextField telCreateField;
    private JTextField adresseCreateField;
    private JButton loginButton;
    private JButton cancelButton;
    private JButton registerButton;

    private JButton createButton;
    private JButton cancelCreateButton;

    private JPanel panel;
    public JPanel createPanel;

    private boolean etat = false; // 0 = connexion, 1 = creation

    public ConnexionVue() {
        setTitle("Connexion");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initCreatePanel();
        initPanel1();
        add(panel);

    }

    private void initPanel1() {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
        usernameLabel.setBounds(10, 10, 150, 25);
        panel.add(usernameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(150, 10, 120, 25);
        panel.add(nameField);

        JLabel passwordLabel = new JLabel("Téléphone:");
        passwordLabel.setBounds(10, 40, 150, 25);
        panel.add(passwordLabel);

        telField = new JTextField(20);
        telField.setBounds(150, 40, 120, 25);
        panel.add(telField);

        loginButton = new JButton("Connexion");
        loginButton.setBounds(50, 80, 100, 25);
        panel.add(loginButton);

        cancelButton = new JButton("Annuler");
        cancelButton.setBounds(160, 80, 100, 25);
        panel.add(cancelButton);

        createButton = new JButton("CrÃ©er un compte");
        createButton.setBounds(160, 200, 200, 25);
        panel.add(createButton);
    }

    private void initCreatePanel() {
        createPanel = new JPanel();
        createPanel.setLayout(null);

        JLabel usernameLabel = new JLabel("Nom :");
        usernameLabel.setBounds(10, 10, 150, 25);
        createPanel.add(usernameLabel);

        nameCreateField = new JTextField(20);
        nameCreateField.setBounds(150, 10, 120, 25);
        createPanel.add(nameCreateField);

        JLabel passwordLabel = new JLabel("telephone:");
        passwordLabel.setBounds(10, 40, 150, 25);
        createPanel.add(passwordLabel);

        telCreateField = new JTextField(20);
        telCreateField.setBounds(150, 40, 120, 25);
        createPanel.add(telCreateField);

        JLabel adlabel = new JLabel("adresse:");
        adlabel.setBounds(10, 70, 150, 25);
        createPanel.add(adlabel);

        adresseCreateField = new JTextField(20);
        adresseCreateField.setBounds(150, 70, 120, 25);
        createPanel.add(adresseCreateField);

        registerButton = new JButton("S'inscrire");
        registerButton.setBounds(50, 180, 100, 25);
        createPanel.add(registerButton);

        cancelCreateButton = new JButton("Annuler");
        cancelCreateButton.setBounds(160, 180, 100, 25);
        createPanel.add(cancelCreateButton);


        createPanel.add(registerButton);
        createPanel.setVisible(true);

    }
    public void switchVisibility() {
        this.getContentPane().removeAll();
        if(etat == false) {
            this.add(createPanel);
            etat = true;
        } else {
            this.add(panel);
            etat = false;
        }
        this.revalidate();
        this.repaint();
        this.pack();
    }

    public JTextField getNameCreateField() {
        return nameCreateField;
    }

    public JTextField getTelCreateField() {
        return telCreateField;
    }

    public JTextField getAdresseCreateField() {
        return adresseCreateField;
    }

    public String getUsername() {
        return nameField.getText();
    }

    public String getPassword() {
        return new String(telField.getText());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getCreateButton() {
        return createButton;
    }
    public JButton getCancelCreateButton() {
        return cancelCreateButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

}