package model;
// import java.io.*;
import java.util.*;

public class Commande {

    private int id_commande;
    private Date date;
	private Client client;
	private PointRaPizza pointRaPizza;
	private Livreur livreur;
	private Vector<LigneCommande> listLigneCommande = new Vector<LigneCommande> ();

// constructeur de model.Commande
    public Commande(int id, Date date, Client c, PointRaPizza p) {
        this.id_commande = id;
        this.date = date;
        this.client = c;
        this.pointRaPizza = p;

    }

// methodes
    public void addLigneCommande(LigneCommande listLigneCommande){
        this.listLigneCommande.add(listLigneCommande);
    }

    public int getId_commande(){ 
        return id_commande;
    }
    public Date getDate(){
        return date;
    }
    public Client getClient(){ 
        return client;
    }
    public PointRaPizza getPointRaPizza(){ 
        return pointRaPizza;
    }
    public Livreur getLivreur(){ 
        return livreur;
    }
    public Vector<LigneCommande> getListLigneCommande(){
         return listLigneCommande;
    }
    public void setClient(Client c){
        this.client = c;
    }
    public void setPointRaPizza(PointRaPizza p){
        this.pointRaPizza = p;
    }
    public void setLivreur(Livreur l){
        this.livreur = l;
    }

}