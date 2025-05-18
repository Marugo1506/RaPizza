package model;
// import java.io.*;
import java.util.*;

public class Client {

    private String num_tel;
    private String nom;
    private float solde;
    private String adresse;
    Vector<PointRaPizza> listPointRaPizza = new Vector<PointRaPizza>();
    Vector<Commande> listCommande = new Vector<Commande>();

    // constructeur de model.Client
    public Client(String num, String n, float s, String ad) {
        this.num_tel = num;
        this.nom = n;
        this.solde = s;
        this.adresse = ad;
    }

    public Client(String nom) {
        this.nom = nom;
    }

    // methodes
    public void addPointRaPizza(PointRaPizza listPointRaPizza){
        this.listPointRaPizza.add(listPointRaPizza);
    }
    public void addCommande(Commande listCommande){
        this.listCommande.add(listCommande);
    }
    public void setnum_tel(String num_tel) {
        this.num_tel = num_tel;
    }
    public void setnom(String nom) {
        this.nom = nom;
    }
    public void setsolde(float solde) {
        this.solde = solde;
    }
    public void setadresse(String adresse) {
        this.adresse = adresse;
    }
    public String getNum_tel() {
        return num_tel;
    }
    public String getNom() {
        return nom;
    }
    public double getSolde() {
        return solde;
    }
    public String getAdresse() {
        return adresse;
    }
}