package view;

import model.Commande;
import model.LigneCommande;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class Vue extends JFrame {
    Model model;
    Commande commande;
    JPanel panel1 = new JPanel(new BorderLayout());
    JPanel panel2 = new JPanel();
    JPanel commandPanel = new JPanel();
    JButton b1 = new JButton("\u2261 MENU");
    JPopupMenu menuDeroulant = new JPopupMenu();
    JMenuItem spe = new JMenuItem("~ Sp\u00E9cialit\u00E9s de la maison ~");
    JMenuItem bTomate = new JMenuItem("~ Nos pizzas base tomate ~");
    JMenuItem bCreme = new JMenuItem("~ Nos pizzas base cr\u00E8me ~");
    JPanel titlePanel;
    JPanel saPanel;
    JPanel pizzaTomatePanel;
    JPanel pizzaCremePanel;
    JPanel specialitePanel;
    JLabel title;
    Font titleFont;
    Font h2Font;
    Font textFont;
    String[] pizzaNames = {"Margherita", "4 Fromages", "Royale", "V\u00E9g\u00E9tarienne", "Chorizo"};
    String[] imagePaths = {
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/margherita.jpeg",
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/pizza4fromages.jpeg",
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/royale.jpeg",
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/vegetarienne.jpeg",
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/chorizo.jpeg"
    };
    Vector<JButton> pizzaButtons = new Vector<>();

    public Vue(Model m, Commande coco) {
        super("RaPizza");
        this.model = m;
        this.commande = coco;

        titleFont = new Font("Arial", Font.BOLD, 35);
        h2Font = new Font("Arial", Font.PLAIN, 18);
        textFont = new Font("Arial", Font.PLAIN, 18);



        // Configuration de la fenêtre
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setSize(800, 600);


///////////////////// Panel de gauche /////////////////////////
        panel1.setBackground(new Color(252, 245, 235));
        panel1.setPreferredSize(new Dimension(250, 600));
        panel1.setLayout(new GridLayout(2,1));
        panel1.add(b1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(250,250,230,200));
        buttonPanel.setLayout(new BorderLayout());

        // Configuration du bouton menu
        b1.setBackground(Color.WHITE);
        b1.setForeground(new Color(56, 118, 29));
        b1.setContentAreaFilled(false); // enleve la couleur du click
        b1.setOpaque(true); // couleur du fond
        b1.setBorderPainted(false);
        b1.setFocusPainted(false); // enleve le cadre bleu autour du texte
        b1.setHorizontalAlignment(SwingConstants.CENTER); // texte au centre
        b1.setPreferredSize(new Dimension(250, 50));
        b1.setFont(titleFont);

        //////////// menu deroulant //////////////
        menuDeroulant.add(spe);
        menuDeroulant.add(bTomate);
        menuDeroulant.add(bCreme);

        // style du menu
        menuDeroulant.setBackground(Color.WHITE);
        menuDeroulant.setBorder(BorderFactory.createEmptyBorder());

        // style des items
        for(Component c : menuDeroulant.getComponents()) {
            if(c instanceof JMenuItem) {
                JMenuItem item = (JMenuItem)c;
                item.setBackground(Color.WHITE);
                item.setBorderPainted(false);
                item.setPreferredSize(new Dimension(250, 45));
                item.setFont(h2Font);
                item.setRolloverEnabled(false);
            }
        }

        b1.addActionListener(e -> {
            menuDeroulant.show(b1, 0, b1.getHeight());
        });

        buttonPanel.add(b1, BorderLayout.NORTH);
        JLabel commandeText = new JLabel("Commande n\u00B0" + Integer.toString(commande.getId_commande())+ " :");
        commandeText.setFont(h2Font);
        buttonPanel.add(commandeText, BorderLayout.SOUTH);

        commandPanel.setBackground(new Color(250,250,230,200));
        commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));
        commandPanel.setPreferredSize(new Dimension(250, 135));
        for (LigneCommande lc : commande.getListLigneCommande()) {
            commandPanel.add(Box.createVerticalStrut(8)); // espace entre les lignes
            commandPanel.add(new JLabel(" - "
            + Integer.toString(lc.getQuantite()) + " " + lc.getPizza().getNom_pizza() + "  (Prix : "
            + Double.toString(lc.getQuantite()*lc.getPizza().getPrix_de_base()) +" \u20AC)"));
        }
        commandPanel.add(Box.createVerticalStrut(20)); // espace entre les lignes


        // prix total
        double pt = 0;
        for (LigneCommande lc : commande.getListLigneCommande()) {
            pt = pt + lc.getQuantite() * lc.getPizza().getPrix_de_base();
        }
        JLabel prixTotal = new JLabel("Total: " + Double.toString(pt) + "\u20AC");
        prixTotal.setFont(h2Font);
        commandPanel.add(prixTotal);
        panel1.add(buttonPanel);
        panel1.add(commandPanel);

//////////////////// Panel de droite ////////////////////////////
        panel2.setBackground(Color.WHITE);
        panel2.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(250, 60));
       saPanel = new JPanel();
        saPanel.setBackground(Color.LIGHT_GRAY);
        saPanel.setPreferredSize(new Dimension(550, 60));
        saPanel.setLayout(new BorderLayout());

        title = new JLabel("Pas encore modifié");
        title.setFont(titleFont);
        titlePanel.add(title);
        JButton suivant = new JButton("Suivant");
        suivant.setBackground(new Color(122, 158, 126));
        suivant.setForeground(Color.WHITE);
        suivant.setFont(h2Font);
        suivant.setPreferredSize(new Dimension(150, 50));
        saPanel.add(suivant, BorderLayout.WEST);

        JButton annuler = new JButton("Annuler");
        annuler.setBackground(new Color(212, 74, 40));
        annuler.setForeground(Color.WHITE);
        annuler.setFont(h2Font);
        annuler.setPreferredSize(new Dimension(150, 50));
        saPanel.add(annuler, BorderLayout.EAST);

        /// /////////////////////// PIZZA TOMATE Panel ///////////////////////

        //pizza base tomato
        pizzaTomatePanel = new JPanel(new GridLayout(2, 3));
        pizzaTomatePanel.setBackground(Color.WHITE);

        for (int i = 0; i < pizzaNames.length; i++) {
            // redimensionnement de l'image
            ImageIcon originalIcon = new ImageIcon(imagePaths[i]);
            Image resizedImage = originalIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

            // style des boutons
            JButton pizzaButton = new JButton(pizzaNames[i], new ImageIcon(resizedImage));
            pizzaButton.setBackground(Color.WHITE);
            pizzaButton.setBorderPainted(false);
            pizzaButton.setFocusPainted(false);
            pizzaButton.setContentAreaFilled(false);
            pizzaButton.setOpaque(true);
            pizzaButton.setRolloverEnabled(false);
            pizzaButton.setFont(h2Font);
            pizzaButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            pizzaButton.setHorizontalTextPosition(SwingConstants.CENTER);
            pizzaButton.setPreferredSize(new Dimension(170, 180));
            pizzaButtons.add(pizzaButton);

            pizzaTomatePanel.add(pizzaButton);
        }
/// //// /////////////////////// PIZZA CREME Panel ///////////////////////
        pizzaCremePanel = new JPanel(new GridLayout(2, 3));


/// //// /////////////////////// Specialité maison ///////////////////////
        specialitePanel = new JPanel(new GridLayout(10, 4));
        for(int i = 0 ; i < 40 ; i++) {
            if(i % 2 == 0) {
                specialitePanel.add(new JLabel("       C " ));
            } else {
                specialitePanel.add(new JLabel("      A " ));
            }
        }
///////////////////////////////////////////////////////////////



        // Boutons de pizzas
        updateView();


        // Ajout des panels


        this.add(panel1, BorderLayout.WEST);
        this.add(panel2, BorderLayout.CENTER);

        // Configuration finale
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    public void updateView() {
        panel2.removeAll(); // Supprime les anciens composants

        if(model.getBase() == 1) {
            title.setText("Pizzas base tomate");
            panel2.add(titlePanel, BorderLayout.NORTH);
            panel2.add(pizzaTomatePanel);
        } else if(model.getBase() == 2) {
            title.setText("Nos pizzas base cr\u00E8me");
            panel2.add(titlePanel, BorderLayout.NORTH);
            panel2.add(pizzaCremePanel);
        } else {
            title.setText("Sp\u00E9cialit\u00E9s de la maison");
            panel2.add(titlePanel, BorderLayout.NORTH);
            panel2.add(specialitePanel);
        }

        panel2.add(saPanel, BorderLayout.SOUTH);

        //this.revalidate();
        this.repaint();
    }

    public Vector<JMenuItem> getAllButtons() {
        Vector<JMenuItem> pizzaButtons = new Vector<>();
        pizzaButtons.add(spe);
        pizzaButtons.add(bTomate);
        pizzaButtons.add(bCreme);
        return pizzaButtons;
    }

    public Vector<JButton> getPizzaButtons() {
        return pizzaButtons;
    }
}