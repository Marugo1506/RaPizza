package view;

import model.Commande;
import model.LigneCommande;
import model.Pizza;
import model.Ingredient;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Vue extends JFrame {
    Model model;
    Commande currentCommande;
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
    JPanel ajouterPizzaPanel;
    JLabel title;
    Font titleFont;
    Font h2Font;
    Font textFont;
    String[] pizzaBTNames = {"Margherita", "4 Fromages", "Royale", "V\u00E9g\u00E9tarienne", "Chorizo"};
    String[] pizzaBCNames = {"Saumon", "Flammekueche"};
    String[] pizzaSNames = {"Ail", "Marinara", "Curry", "Campione"};
    String[] imagePBTPaths = {
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/margherita.jpeg",
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/pizza4fromages.jpeg",
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/royale.jpeg",
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/vegetarienne.jpeg",
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/chorizo.jpeg"
    };
    String[] imagePBCPaths = {
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/saumon.jpg",
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/flammekueche.jpg",
    };
    String[] imagePSPaths = {
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/ail.jpg",
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/marinara.jpg",
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/curry.jpg",
            "C:/Users/Margo/Desktop/licence info/SEMESTRE 4/POO-Java/projet Rapizza/images/campione.jpg"
    };
    Vector<JButton> pizzaButtons = new Vector<>();
    private JTextField choisirQ;
    private JButton addButton;

    public Vue(Model m, Commande com) {
        super("RaPizza");
        this.model = m;
        this.currentCommande = com;

        titleFont = new Font("Arial", Font.BOLD, 35);
        h2Font = new Font("Arial", Font.BOLD, 22);
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
                item.setFont(textFont);
                item.setRolloverEnabled(false);
            }
        }

        b1.addActionListener(e -> {
            menuDeroulant.show(b1, 0, b1.getHeight());
        });

        buttonPanel.add(b1, BorderLayout.NORTH);
        JLabel commandeText = new JLabel(" Commande n\u00B0" + currentCommande.getId_commande()+ " :");
        commandeText.setFont(h2Font);
        buttonPanel.add(commandeText, BorderLayout.SOUTH);

        commandPanel.setBackground(new Color(250,250,230,200));
        commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));
        commandPanel.setPreferredSize(new Dimension(250, 135));
        for (LigneCommande lc : currentCommande.getListLigneCommande()) {
            commandPanel.add(Box.createVerticalStrut(8)); // espace entre les lignes
            commandPanel.add(new JLabel(" - "
                    + (lc.getQuantite()) + " " + lc.getPizza().getNom_pizza() + "  (Prix : "
                    + (lc.getQuantite()*lc.getPizza().getPrix_de_base()) +" \u20AC)"));
        }
        commandPanel.add(Box.createVerticalStrut(20)); // espace entre les lignes


        // prix total
        double pt = 0;
        for (LigneCommande lc : currentCommande.getListLigneCommande()) {
            pt = pt + lc.getQuantite() * lc.getPizza().getPrix_de_base();
        }
        JLabel prixTotal = new JLabel(" Total: " + pt + "\u20AC");
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
        saPanel.setBackground(Color.WHITE);
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

        ////////////////////////// PIZZA TOMATE Panel ///////////////////////

        //pizza base tomato
        pizzaTomatePanel = new JPanel(new GridLayout(2, 3));
        pizzaTomatePanel.setBackground(Color.WHITE);

        for (int i = 0; i < pizzaBTNames.length; i++) {
            // redimensionnement de l'image
            ImageIcon originalIcon = new ImageIcon(imagePBTPaths[i]);
            Image resizedImage = originalIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

            // style des boutons
            JButton pizzaButton = new JButton(pizzaBTNames[i], new ImageIcon(resizedImage));
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
        ///////////////////////// PIZZA CREME Panel ///////////////////////
        pizzaCremePanel = new JPanel(new GridLayout(2, 3));
        pizzaCremePanel.setBackground(Color.WHITE);
        for (int i = 0; i < pizzaBCNames.length; i++) {
            // redimensionnement de l'image
            ImageIcon originalIcon = new ImageIcon(imagePBCPaths[i]);
            Image resizedImage = originalIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

            // style des boutons
            JButton pizzaButton = new JButton(pizzaBCNames[i], new ImageIcon(resizedImage));
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

            pizzaCremePanel.add(pizzaButton);
        }


/// //// /////////////////////// Specialité maison ///////////////////////
        specialitePanel = new JPanel(new GridLayout(2, 3));
        specialitePanel.setBackground(Color.WHITE);
        for (int i = 0; i < pizzaSNames.length; i++) {
            // redimensionnement de l'image
            ImageIcon originalIcon = new ImageIcon(imagePSPaths[i]);
            Image resizedImage = originalIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

            // style des boutons
            JButton pizzaButton = new JButton(pizzaSNames[i], new ImageIcon(resizedImage));
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

            specialitePanel.add(pizzaButton);
        }
////////////////////////// Panel pour ajouter une pizza a la commande /////////////////////////

        ajouterPizzaPanel = new JPanel();



        // raffraichi la vue
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
        }
        else if(model.getBase() == 2) {
            title.setText("Nos pizzas base crème");
            panel2.add(titlePanel, BorderLayout.NORTH);
            panel2.add(pizzaCremePanel);
        }
        else if(model.getBase() == 0) {
            title.setText("Spécialités de la maison");
            panel2.add(titlePanel, BorderLayout.NORTH);
            panel2.add(specialitePanel);
        }
        else if(model.getBase() == 3) {
            title.setText("Info sur la pizza");
            panel2.add(titlePanel, BorderLayout.NORTH);
            panel2.add(ajouterPizzaPanel); // Utilisation de ajouterPizzaPanel
        }

        panel2.add(saPanel, BorderLayout.SOUTH);

        this.revalidate();
        this.repaint();
    }

    public void showInfoPizza(Pizza pizza) {
        // Réinitialisation et configuration directe de ajouterPizzaPanel
        ajouterPizzaPanel.removeAll();
        ajouterPizzaPanel.setLayout(new BoxLayout(ajouterPizzaPanel, BoxLayout.Y_AXIS));
        ajouterPizzaPanel.setBackground(Color.WHITE);
        ajouterPizzaPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Nom de la pizza
        JLabel nameLabel = new JLabel(pizza.getNom_pizza());
        nameLabel.setFont(titleFont);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Prix
        JLabel priceLabel = new JLabel("Prix: " + pizza.getPrix_de_base() + " €");
        priceLabel.setFont(h2Font);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Titre "Ingrédients"
        JLabel ingredientsTitle = new JLabel("Ingrédients:");
        ingredientsTitle.setFont(h2Font);
        ingredientsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel pour les ingrédients
        JPanel ingredientsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        ingredientsPanel.setBackground(Color.WHITE);

        Vector<Ingredient> ingredients = pizza.getIngredients();
        for (int i = 0; i < ingredients.size(); i++) {
            JLabel ingredientLabel = new JLabel(ingredients.get(i).getNom_ingredient());
            ingredientLabel.setFont(textFont);
            ingredientsPanel.add(ingredientLabel);

            if (i < ingredients.size() - 1) {
                JLabel commaLabel = new JLabel(", ");
                commaLabel.setFont(textFont);
                ingredientsPanel.add(commaLabel);
            }
        }

        JPanel quantitePanel = new JPanel();
        quantitePanel.setBackground(Color.WHITE);
        quantitePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel quantite = new JLabel("Choisissez la quantité souhaitée : ");
        quantite.setFont(h2Font);
        quantite.setAlignmentX(Component.CENTER_ALIGNMENT);

        choisirQ = new JTextField("1", 1);

        quantitePanel.add(quantite);
        quantitePanel.add(choisirQ);

        // Boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton("Retour");
        backButton.setFont(h2Font);
        backButton.setBackground(new Color(212, 74, 40));
        backButton.setForeground(Color.WHITE);

        addButton = new JButton("Ajouter à la commande");
        addButton.setFont(h2Font);
        addButton.setBackground(new Color(122, 158, 126));
        addButton.setForeground(Color.WHITE);


        buttonPanel.add(backButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(addButton);

        // Ajout direct des composants à ajouterPizzaPanel
        ajouterPizzaPanel.add(nameLabel);
        ajouterPizzaPanel.add(Box.createVerticalStrut(15));
        ajouterPizzaPanel.add(priceLabel);
        ajouterPizzaPanel.add(Box.createVerticalStrut(15));
        ajouterPizzaPanel.add(ingredientsTitle);
        ajouterPizzaPanel.add(Box.createVerticalStrut(5));
        ajouterPizzaPanel.add(ingredientsPanel);
        ajouterPizzaPanel.add(Box.createVerticalStrut(20));
        ajouterPizzaPanel.add(quantitePanel);
        ajouterPizzaPanel.add(buttonPanel);

        // Mise à jour de la vue
        model.setBase(3);
        updateView();
    }

    public void updateCommandPanel() {
        commandPanel.removeAll();

        // Détails
        for (LigneCommande lc : model.getCurrentCommande().getListLigneCommande()) {
            String line = lc.getQuantite() + " x " + lc.getPizza().getNom_pizza()
                    + " (" + (lc.getQuantite() * lc.getPizza().getPrix_de_base()) + " €)";
            commandPanel.add(new JLabel(line));
        }

        // Calcul du total
        double total = model.getCurrentCommande().getListLigneCommande().stream()
                .mapToDouble(lc -> lc.getQuantite() * lc.getPizza().getPrix_de_base())
                .sum();

        commandPanel.add(new JLabel("Total: " + total + " €"));

        commandPanel.revalidate();
        commandPanel.repaint();
    }

    /// /////// getters et setters /////////////
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

    public JTextField getChoisirQuantite() {
        return this.choisirQ;
    }
    public JButton getAddButton() {
        return this.addButton;
    }

}