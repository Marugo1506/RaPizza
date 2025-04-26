package model;

import java.util.Arrays;
import java.util.Vector;

public class Model {
    
    public String oui;
    int base;// 0 -> spécialités, 1 -> base tomate, 2 -> base Creme
    Client client;
    Vector<Client> listeUtilisateurs = new Vector<Client>(Arrays.asList(
            new Client(39, "Alice", 50.0f, "789 Boulevard Saint-Germain"),
            new Client(123456789, "Bob", 30.0f, "321 Rue de Rivoli"),
            new Client(987654321, "Charlie", 20.0f, "456 Avenue des Champs-Élysées")));

    // Constructeur
    public Model(String o) {
        this.oui = o;
        base = 1;
    }
    //Méthodes
    public void setBase(int base) {
        if (base < 0 || base > 2) {
            throw new IllegalArgumentException("Base must be between 0 and 2");
        }
        this.base = base;
    }
    public void setBase(String base) {
       switch (base){
            case "~ Sp\u00E9cialit\u00E9s de la maison ~":
                this.base = 0;
                break;
            case "~ Nos pizzas base tomate ~":
                this.base = 1;
                break;
            case "~ Nos pizzas base cr\u00E8me ~":
                this.base = 2;
                break;
            default:
                throw new IllegalArgumentException("Invalid base type");
        }
    }

    public void addUser(Client user) {
        this.listeUtilisateurs.add(user);
    }

    public boolean isUser(Client user) {
        for (Client u : listeUtilisateurs) {
            if (u.getNom().equals(user.getNom())) {
                // u a toutes les informations de l'utilisateur car il est dans la liste des users
                setConnectedUser(u);
                return true;
            }
        }
        return false;
    }

    public void setConnectedUser(Client c){
        this.client = c;
    }

    public Client getClient(){
        return client;
    }
    public boolean isConnected(){
        return  (client != null);
    }

    public int getBase() {
        return base;
    }
}

