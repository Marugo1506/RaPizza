package model;
// import java.io.*;
import java.util.*;

public class Pizza {

    private int id_pizza;
    // public String categorie;
    private String nom_pizza;
    private double prix_de_base;
	public Vector<Ingredient> listIngredient = new Vector<Ingredient> ();
	Vector<PointRaPizza> listPointRaPizza = new Vector<PointRaPizza> ();
	Vector<LigneCommande> listLigneCommande = new Vector<LigneCommande> ();

// constructeur de model.Pizza
    public Pizza(int id, String n, double p) {
        this.id_pizza = id;
        // this.categorie = cat;
        this.nom_pizza = n;
        this.prix_de_base = p;
    }

    // methodes
    public int getId_pizza() {
        return id_pizza;
    }
    public String getNom_pizza() {
        return nom_pizza;
    }
    public double getPrix_de_base() {
        return prix_de_base;
    }
    public void setid_pizza(int id_pizza) {
        this.id_pizza = id_pizza;
    }

    // public void setcategorie(String categorie) {
    //     this.categorie = categorie;
    // }

    public void setnom_pizza(String nom_pizza) {
        this.nom_pizza = nom_pizza;
    }

    public void setprix_de_base(double p) {
        this.prix_de_base = p;
    }

    public void addIngredient(Ingredient listIngredient){
        this.listIngredient.add(listIngredient);
    }

    public void addPointRaPizza(PointRaPizza listPointRaPizza){
        this.listPointRaPizza.add(listPointRaPizza);
    }

    public void addLigneCommande(LigneCommande listLigneCommande){
        this.listLigneCommande.add(listLigneCommande);
    }
}