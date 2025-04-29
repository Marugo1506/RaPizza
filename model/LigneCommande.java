package model;
// import java.io.*;
// import java.util.*;

public class LigneCommande {

    private int quantite;
    private double taille; // naine, humaine ou ogresse
    Commande commande;
	private Pizza pizza;

// constructeur de model.LigneCommande
    public LigneCommande(int q, double t, Commande c, Pizza p) {
        this.quantite = q;
        this.taille = t;
        this.commande = c;
        this.pizza = p;
    }
// methodes

    public void setCommande(Commande c){
        this.commande = c;
    }
    public void setPizza(Pizza p){
        this.pizza = p;
    }
    public void setQuantite(int q){
        this.quantite = q;
    }
    public void setTaille(float t){
        this.taille = t;
    }
    public int getQuantite() { 
        return quantite;
    }
    public double getTaille() { 
        return taille;
    }
    public Pizza getPizza() {
        return pizza;
    }
    public Commande getCommande() {
        return commande;
    }
}