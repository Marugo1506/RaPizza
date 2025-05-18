package view;

import control.Controller;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class VuePayement extends JFrame {
    private Model model;
    private Controller controller;
    private Commande currentCommande;
    private JLabel soldeLabel;

    // Couleurs
    public static Color red = new Color(212, 74, 40);
    public static Color green = new Color(122, 168, 126);
    public static Color beige = new Color(250, 250, 230, 255);
    public static Color white = Color.WHITE;

    // Polices
    private Font titleFont = new Font("Arial", Font.BOLD, 35);
    private Font h2Font = new Font("Arial", Font.BOLD, 22);
    private Font textFont = new Font("Arial", Font.PLAIN, 18);

    public VuePayement(Model m, Controller c) {
        super("Paiement - RaPizza");
        this.model = m;
        this.currentCommande = m.getCurrentCommande();
        this.controller = c;

        // Configuration de la fenêtre
        this.setLayout(new BorderLayout());
        this.setBackground(white);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(beige);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Titre
        JLabel title = new JLabel("Finalisation de la commande");
        title.setFont(titleFont);
        title.setForeground(green);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Info client
        JPanel clientPanel = new JPanel();
        clientPanel.setBackground(beige);
        clientPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        soldeLabel = new JLabel(String.format("Solde: %.2f€", model.getClient().getSolde()));
        soldeLabel.setFont(h2Font);

        JButton rechargeButton = new JButton("+ Réapprovisionner");
        rechargeButton.setBackground(green);
        rechargeButton.setForeground(white);
        rechargeButton.setFont(textFont);
        controller.setRechargeBoutonAction(rechargeButton, this);

        clientPanel.add(soldeLabel);
        clientPanel.add(Box.createHorizontalStrut(20));
        clientPanel.add(rechargeButton);

        // Détails commande
        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));
        commandPanel.setBackground(white);
        commandPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        commandPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (LigneCommande lc : currentCommande.getListLigneCommande()) {
            JLabel item = new JLabel(String.format("%d x %s - %.2f€",
                    lc.getQuantite(),
                    lc.getPizza().getNom_pizza(),
                    lc.getQuantite() * lc.getPizza().getPrix_de_base()));
            item.setFont(textFont);
            commandPanel.add(item);
            commandPanel.add(Box.createVerticalStrut(5));
        }

        // Total
        double total = currentCommande.getListLigneCommande().stream()
                .mapToDouble(lc -> lc.getQuantite() * lc.getPizza().getPrix_de_base())
                .sum();

        JLabel totalLabel = new JLabel(String.format("Total: %.2f€", total));
        totalLabel.setFont(h2Font);
        totalLabel.setForeground(green);
        commandPanel.add(Box.createVerticalStrut(10));
        commandPanel.add(totalLabel);

        // Bouton paiement
        JButton payButton = new JButton("Payer maintenant");
        payButton.setBackground(green);
        payButton.setForeground(white);
        payButton.setFont(h2Font);
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        payButton.setPreferredSize(new Dimension(200, 50));
        controller.setPayButtonAction(payButton, this);

        // Message info
        JLabel infoLabel = new JLabel("Le paiement se fera automatiquement via votre solde client");
        infoLabel.setFont(textFont);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ajout des composants
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(clientPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(commandPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(infoLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(payButton);

        this.add(mainPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

            // Mise à jour de l'affichage
            soldeLabel.setText(String.format("Solde: %.2f€", client.getSolde()));

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

        this.dispose();
        model.stopPaye();
        model.addCommandes(this.currentCommande);
        model.setACommandeToLivreur();
    }
}