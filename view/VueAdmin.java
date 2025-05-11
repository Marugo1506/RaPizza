package view;

import control.ControllerAdmin;
import model.Client;
import model.Model;

import javax.swing.*;
import java.awt.*;

public class VueAdmin extends JFrame {
    private Model m;
    private JPanel mainPanel;
    private int base; // Les différents onglets de la vue
    private JPanel topPanel;
    private ControllerAdmin controllerAdmin;
    private JButton clientsButton;
    private JButton commandButton;


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
                mainPanel.add(new JLabel("Commandes"));
            }
            default -> {
                mainPanel.add(new JLabel("Default"));
            }
        }
    }
    public void setController(ControllerAdmin ca){
        this.controllerAdmin = ca;
        controllerAdmin.setAction(clientsButton);
        controllerAdmin.setAction(commandButton);
    }

    private void loadTopPanel(){
        topPanel = new JPanel();
        clientsButton = new JButton("Clients");
        clientsButton.setBackground(Color.lightGray);
        clientsButton.setForeground(Color.black);
        clientsButton.setContentAreaFilled(false); // enleve la couleur du click
        clientsButton.setOpaque(true); // couleur du fond
        clientsButton.setBorderPainted(false);
        clientsButton.setFocusPainted(false); // enleve le cadre bleu autour du texte
        clientsButton.setHorizontalAlignment(SwingConstants.CENTER); // texte au centre
        clientsButton.setPreferredSize(new Dimension(250, 50));

        commandButton = new JButton("Commandes");
        commandButton.setBackground(Color.lightGray);
        commandButton.setForeground(Color.black);
        commandButton.setContentAreaFilled(false); // enleve la couleur du click
        commandButton.setOpaque(true); // couleur du fond
        commandButton.setBorderPainted(false);
        commandButton.setFocusPainted(false); // enleve le cadre bleu autour du texte
        commandButton.setHorizontalAlignment(SwingConstants.CENTER); // texte au centre
        commandButton.setPreferredSize(new Dimension(250, 50));

        topPanel.add(clientsButton);
        topPanel.add(commandButton);

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


}
