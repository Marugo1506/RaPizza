package control;

import view.Vue;

import javax.swing.*;

public class Controller {
    private model.Model model;
    private Vue view;

    public Controller(model.Model model, Vue view) {
        // Constructor logic if needed
        this.model = model;
        this.view = view;
    }

    public void setMenuButtonActionListener( ) {
        // reglage des actions listener des boutons du menu
        for(JMenuItem button : view.getAllButtons()) {
            button.addActionListener(e -> {
                // Handle button click event
                System.out.println("Button clicked!" + button.getText());
                model.setBase(button.getText());
                view.updateView();
            });
        }
        }


    public void setPizzaButtonActionListener() {
        // Set the action listener for the pizza button
        for (JButton button : view.getPizzaButtons()) {
            button.addActionListener(e -> {
                // Handle pizza button click event
                System.out.println("Pizza button clicked!" + button.getText());
                //model.setBase(button.getText());
                view.showInfoPizza(button.getText());
            });
        }
    }
}
