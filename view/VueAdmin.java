package view;

import control.ControllerAdmin;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class VueAdmin extends JFrame {
    private Model m;
    private JPanel mainPanel;
    private int base; // Les différents onglets de la vue
    private JPanel topPanel;
    private ControllerAdmin controllerAdmin;
    private JButton clientsButton;
    private JButton commandButton;
    private JButton retourButton;
    private JButton livreurButton;


    public VueAdmin(Model m2){
        this.m = m2;
        this.base = 0;
        mainPanel = new JPanel();

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(252, 245, 235)); // Couleur de fond cohérente
        this.setSize(800, 600);

        loadTopPanel();
        this.add(topPanel,BorderLayout.NORTH);
        loadInfosIntoPanel();
        this.add(mainPanel);


        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);


    }

    private void loadInfosIntoPanel(){
        mainPanel.removeAll();
        switch (base){
            case 0 -> {
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                for(Client c : m.getUsers()){
                    mainPanel.add(new JLabel(c.getNom() + " - " + c.getAdresse()
                    + " - " + c.getNum_tel() + " - " + c.getSolde()+"€"));
                }
            }
            case 1 ->{
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                mainPanel.add(new JLabel("Commandes : "));
                mainPanel.add(new JLabel(""));
                for(Commande commande : m.getCommandes()){
                    JButton boutonsDetails = new JButton("Détails");
                    controllerAdmin.setActionCommande(boutonsDetails,commande);
                    boutonsDetails.setBackground(Color.lightGray);
                   boutonsDetails.setPreferredSize(new Dimension(140, 30));
                    mainPanel.add(
                            new JLabel("ID : " +commande.getId_commande()
                            + " | Nom du Client : " + commande.getClient().getNom()
                            + " | Adresse de livraison : " + commande.getClient().getAdresse()
                            + " | Nom du Livreur : " +
                            ((commande.getLivreur()!=null)?commande.getLivreur().nom:"Aucun livreur lié à la commande" )
                            + " | "
                            )
                    );
                    mainPanel.add(boutonsDetails);
                }

            }
            case 2 -> {
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                mainPanel.add(new JLabel("Livreurs : "));
                mainPanel.add(new JLabel(""));
                for(Livreur l : m.getLivreurs()){
                    mainPanel.add(
                            new JLabel("ID : " + l.id_livreur
                                    + " | Nom du Livreur : " + l.nom
                                    + " | Véhicule utilisée : " + l.vehicule
                                    + " | Nombre de commandes liées au Livreur : " +
                                    l.listCommande.size()
                                    + " | "
                            )
                    );
                }

            }
            default -> {
                mainPanel.add(new JLabel("Default"));
            }
        }
    }
    public void setController(ControllerAdmin ca){
        this.controllerAdmin = ca;
        controllerAdmin.setAction(retourButton);
        controllerAdmin.setAction(clientsButton);
        controllerAdmin.setAction(commandButton);
        controllerAdmin.setAction(livreurButton);
    }

    private void loadTopPanel(){
        topPanel = new JPanel();

        retourButton =  new JButton("Retour");
        retourButton.setBackground(Color.red);
        retourButton.setForeground(Color.black);
        retourButton.setContentAreaFilled(false); // enleve la couleur du click
        retourButton.setOpaque(true); // couleur du fond
        retourButton.setBorderPainted(false);
        retourButton.setFocusPainted(false); // enleve le cadre bleu autour du texte
        retourButton.setHorizontalAlignment(SwingConstants.CENTER); // texte au centre
        retourButton.setPreferredSize(new Dimension(170, 30));


        clientsButton = new JButton("Clients");
        clientsButton.setBackground(Color.lightGray);
        clientsButton.setForeground(Color.black);
        clientsButton.setContentAreaFilled(false); // enleve la couleur du click
        clientsButton.setOpaque(true); // couleur du fond
        clientsButton.setBorderPainted(false);
        clientsButton.setFocusPainted(false); // enleve le cadre bleu autour du texte
        clientsButton.setHorizontalAlignment(SwingConstants.CENTER); // texte au centre
        clientsButton.setPreferredSize(new Dimension(170, 30));

        commandButton = new JButton("Commandes");
        commandButton.setBackground(Color.lightGray);
        commandButton.setForeground(Color.black);
        commandButton.setContentAreaFilled(false); // enleve la couleur du click
        commandButton.setOpaque(true); // couleur du fond
        commandButton.setBorderPainted(false);
        commandButton.setFocusPainted(false); // enleve le cadre bleu autour du texte
        commandButton.setHorizontalAlignment(SwingConstants.CENTER); // texte au centre
        commandButton.setPreferredSize(new Dimension(170, 30));

        livreurButton = new JButton("Livreurs");
        livreurButton.setBackground(Color.lightGray);
        livreurButton.setForeground(Color.black);
        livreurButton.setContentAreaFilled(false); // enleve la couleur du click
        livreurButton.setOpaque(true); // couleur du fond
        livreurButton.setBorderPainted(false);
        livreurButton.setFocusPainted(false); // enleve le cadre bleu autour du texte
        livreurButton.setHorizontalAlignment(SwingConstants.CENTER); // texte au centre
        livreurButton.setPreferredSize(new Dimension(170, 30));


        topPanel.add(retourButton);
        topPanel.add(clientsButton);
        topPanel.add(commandButton);
        topPanel.add(livreurButton);

    }

    public void setBase(int k ){
        this.base = k;
        System.out.println("Changement de base : " + k);
    }

    public void updateView(){
        System.out.println("Changement...");
        loadInfosIntoPanel();
        this.revalidate();
        this.repaint();
    }

    public void showCommande(Commande c){
        JFrame commandePanel = new JFrame();
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
        pane.add(new JLabel("Contenu de la Commande : "));
        if(c.getListLigneCommande().isEmpty()){
            pane.add(new JLabel("La commande est vide"));
        }
        else {
            for(LigneCommande lc : c.getListLigneCommande()){
                pane.add(new JLabel(
                        lc.getPizza().getNom_pizza() + " - " + lc.getQuantite() + "x"
                ));
            }
        }

        commandePanel.add(pane);
        commandePanel.setPreferredSize(new Dimension(400, 300));
        commandePanel.setVisible(true);
        commandePanel.pack();
    }


}
