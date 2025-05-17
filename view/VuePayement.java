package view;

import control.Controller;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VuePayement extends JFrame {
    private Commande currentCommande;
    private Model model;
    private JLabel soldeLabel;
    private JLabel totalLabel;
    private Controller controller;

    public VuePayement(Model m, Controller c) {
        super("Paiement - RaPizza");
        this.model = m;
        this.currentCommande = m.getCurrentCommande();
        this.controller = c;

        // Configuration de la fenêtre
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(252, 245, 235)); // Couleur de fond cohérente
        this.setSize(800, 600);

        // Création des panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));
        mainPanel.setBackground(new Color(252, 245, 235));

        // Header avec logo/titre
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(56, 118, 29));
        JLabel headerLabel = new JLabel("Finalisation de la commande");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Panel client/solde
        JPanel clientPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clientPanel.setBackground(new Color(252, 245, 235));
        clientPanel.setBorder(BorderFactory.createTitledBorder("Votre compte"));

        soldeLabel = new JLabel(String.format("Solde actuel: %.2f€", model.getClient().getSolde()));
        soldeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton rechargeButton = new JButton("+ Réapprovisionner");
        rechargeButton.setBackground(new Color(122, 158, 126));
        rechargeButton.setForeground(Color.WHITE);

        controller.setRechargeBoutonAction(rechargeButton,this);

        clientPanel.add(soldeLabel);
        clientPanel.add(Box.createHorizontalStrut(20));
        clientPanel.add(rechargeButton);

        JPanel commandPanel = createCommandPanel();

        JPanel paymentPanel = createPaymentPanel();

        mainPanel.add(headerPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(clientPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(commandPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(paymentPanel);

        this.add(mainPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JPanel createCommandPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Détails de la commande"));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        double total = 0;
        for (LigneCommande lc : currentCommande.getListLigneCommande()) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            itemPanel.setBackground(Color.WHITE);

            JLabel itemLabel = new JLabel(String.format("%d x %s", lc.getQuantite(), lc.getPizza().getNom_pizza()));
            JLabel priceLabel = new JLabel(String.format("%.2f€", lc.getQuantite() * lc.getPizza().getPrix_de_base()));
            priceLabel.setForeground(Vue.green);

            itemPanel.add(itemLabel);
            itemPanel.add(Box.createHorizontalGlue());
            itemPanel.add(priceLabel);

            panel.add(itemPanel);
            total += lc.getQuantite() * lc.getPizza().getPrix_de_base();
        }

        // Ligne de séparation
        panel.add(new JSeparator(SwingConstants.HORIZONTAL));

        // Total
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBackground(Color.WHITE);

        totalLabel = new JLabel(String.format("Total: %.2f€", total));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(new Color(56, 118, 29));
        totalPanel.add(totalLabel);

        panel.add(totalPanel);

        return panel;
    }

    private JPanel createPaymentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(252, 245, 235));

        // Message de paiement
        JLabel paymentLabel = new JLabel("Le paiement se fera automatiquement via votre solde client");
        paymentLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        paymentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Bouton de paiement
        JButton payButton = new JButton("Payer maintenant");
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        payButton.setBackground(new Color(56, 118, 29));
        payButton.setForeground(Color.WHITE);
        payButton.setFont(new Font("Arial", Font.BOLD, 16));
        payButton.setPreferredSize(new Dimension(200, 50));
        controller.setPayButtonAction(payButton,this);

        panel.add(paymentLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(payButton);

        return panel;
    }

    public void rechargerSolde() {
        String amountStr = JOptionPane.showInputDialog(this,
                "Montant à ajouter à votre solde:",
                "Réapprovisionnement",
                JOptionPane.QUESTION_MESSAGE);

        try {
            float amount = Float.parseFloat(amountStr);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Le montant doit être positif",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Mise à jour du solde
            Client client = model.getClient();
            client.setsolde((float) (client.getSolde() + amount));
            updateSoldeDisplay();

            JOptionPane.showMessageDialog(this,
                    String.format("Votre solde a été rechargé de %.2f€", amount),
                    "Succès",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez entrer un montant valide",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void processPayment() {
        double total = currentCommande.getListLigneCommande().stream()
                .mapToDouble(lc -> lc.getQuantite() * lc.getPizza().getPrix_de_base())
                .sum();

        Client client = model.getClient();

        if (client.getSolde() < total) {
            JOptionPane.showMessageDialog(this,
                    "Solde insuffisant. Veuillez recharger votre compte.",
                    "Paiement impossible",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Débit du solde
        client.setsolde((float)(client.getSolde() - total));

        // Confirmation
        JOptionPane.showMessageDialog(this,
                String.format("Paiement de %.2f€ effectué avec succès!\nNouveau solde: %.2f€",
                        total, client.getSolde()),
                "Confirmation",
                JOptionPane.INFORMATION_MESSAGE);

        // Fermeture de la fenêtre
        this.dispose();
        model.stopPaye();
        model.addCommandes(this.currentCommande);
        model.setACommandeToLivreur();
    }

    private void updateSoldeDisplay() {
        soldeLabel.setText(String.format("Solde actuel: %.2f€", model.getClient().getSolde()));
    }
}