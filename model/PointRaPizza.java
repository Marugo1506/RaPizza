package model;
// import java.io.*;
import java.util.*;

public class PointRaPizza {

    public int id_rapizza;
    public String adresse;
	Vector<Ingredient> listIngredient = new Vector<Ingredient> ();
	Vector<Client> listClient = new Vector<Client> ();
	Vector<Commande> listCommande = new Vector<Commande> ();
	Vector<Livreur> listLivreur = new Vector<Livreur> ();
	Vector<Pizza> listPizza = new Vector<Pizza> ();

// constructeur de model.PointRaPizza
    public PointRaPizza(int id, String ad) {
        this.id_rapizza = id;
        this.adresse = ad;
    }

// methodes

    public void setid_rapizza(int id_rapizza) {
        this.id_rapizza = id_rapizza;
    }

    public void setadresse(String adresse) {
        this.adresse = adresse;
    }

    public void addIngredient(Ingredient listIngredient){
        this.listIngredient.add(listIngredient);
    }

    public void addClient(Client listClient){
        this.listClient.add(listClient);
    }

    public void addLivreur(Livreur listLivreur){
        this.listLivreur.add(listLivreur);
    }

    public void addCommande(Commande listCommande){
        this.listCommande.add(listCommande);
    }

    public void addPizza(Pizza listPizza){
        this.listPizza.add(listPizza);
    }

}