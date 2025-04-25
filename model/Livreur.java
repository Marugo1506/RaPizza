package model;
// import java.io.*;
import java.util.*;

public class Livreur {

    public int id_livreur;
    public String nom;
    public String vehicule;
	Vector<PointRaPizza> listPointRaPizza = new Vector<PointRaPizza> ();
	Vector<Commande> listCommande = new Vector<Commande>();
    public PointRaPizza pointRaPizza;

// constructeur de model.Livreur
    public Livreur(int id, String n, String v) {
        this.id_livreur = id;
        this.nom = n;
        this.vehicule = v;
    }

// methodes

    public void setid_livreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public void setvehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    public void addPointRaPizza(PointRaPizza listPointRaPizza){
        this.listPointRaPizza.add(listPointRaPizza);
    }

    public void addCommande(Commande listCommande){
        this.listCommande.add(listCommande);
    }
}