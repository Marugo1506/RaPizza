package model;
// import java.io.*;
import java.util.*;

public class Ingredient {

    public String nom_ingredient;
	Vector<PointRaPizza> listPointRaPizza = new Vector<PointRaPizza>();
	Vector<Pizza> listPizza = new Vector<Pizza> ();

// constructeur d'ingredient
    public Ingredient(String n) {
        this.nom_ingredient = n;
    }
// methodes
    public void setnom_ingredient(String nom_ingredient) {
        this.nom_ingredient = nom_ingredient;
    }

    public void addPointRaPizza(PointRaPizza listPointRaPizza){
        this.listPointRaPizza.add(listPointRaPizza);
    }

    public void addPizza(Pizza listPizza){
        this.listPizza.add(listPizza);
    }
}