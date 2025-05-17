package model;

import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

public class Model {
    
    public String oui;
    private boolean paying = false;
    int base;// 0 -> spécialités, 1 -> base tomate, 2 -> base Creme, 3 -> infos pizza
    Client client;
    private static int commandeID = 0;
    Vector<Client> listeUtilisateurs = new Vector<Client>(Arrays.asList(
            new Client("6541", "Alice", 50.0f, "789 Boulevard Saint-Germain"),
            new Client("123", "Bob", 30.0f, "321 Rue de Rivoli"),
            new Admin("000", "a", 20.0f, "456 Avenue des Champs-Élysées")));

    Vector <Ingredient> listeIngredients;
    private Vector<Pizza> allPizzas;
    private Pizza pizzaSelectionnee;
    private Commande currentCommande;
    private Vector<Commande> listeCommandes;
    private Vector<PointRaPizza> pointsLivraison;
    private Vector<Livreur> livreurs;
    private int compteurLivreur = 0;
    // Constructeur
    public Model(String o,Vector<PointRaPizza> livraisons , Vector<Livreur> l) {
        this.oui = o;
        base = 0;
        this.listeIngredients = new Vector<Ingredient>();
        this.pointsLivraison = livraisons;
        this.listeCommandes = new Vector<>();
        this.livreurs = l;
        this.paying = false;
        this.allPizzas = new Vector<Pizza>();
        initialiserIngredients();
        initialiserPizzas();
    }
    //Méthodes

    public void initialiserIngredients() {
        // Initialisation des ingrédients
        listeIngredients.add(new Ingredient("Sauce tomate")); // 0
        listeIngredients.add(new Ingredient("Crème fraîche")); // 1
        listeIngredients.add(new Ingredient("Mozzarella")); // 2
        listeIngredients.add(new Ingredient("Parmesan")); // 3
        listeIngredients.add(new Ingredient("Emmental")); // 4
        listeIngredients.add(new Ingredient("Chèvre")); // 5
        listeIngredients.add(new Ingredient("Basilic")); // 6
        listeIngredients.add(new Ingredient("Jambon")); // 7
        listeIngredients.add(new Ingredient("Chorizo")); // 8
        listeIngredients.add(new Ingredient("Poivrons")); // 9
        listeIngredients.add(new Ingredient("Oignons")); // 10
        listeIngredients.add(new Ingredient("Olives")); // 11
        listeIngredients.add(new Ingredient("Lardons")); // 12
        listeIngredients.add(new Ingredient("Saumon")); // 13
        listeIngredients.add(new Ingredient("Champignons")); // 14
        listeIngredients.add(new Ingredient("Ail")); // 15
        listeIngredients.add(new Ingredient("Huile d'olive")); // 16
        listeIngredients.add(new Ingredient("Poulet")); // 17
        listeIngredients.add(new Ingredient("Viande hachée")); // 18
        listeIngredients.add(new Ingredient("Carottes")); // 19
        listeIngredients.add(new Ingredient("Oeuf")); // 20
        listeIngredients.add(new Ingredient("Roquette")); // 21
        listeIngredients.add(new Ingredient("Herbes aromatiques")); // 22
        listeIngredients.add(new Ingredient("Curry")); // 23
    }

    public void initialiserPizzas() {
        // Initialisation des pizzas
        Vector<Ingredient> margheritaIngredients = new Vector<Ingredient>();
        Pizza margherita = new Pizza(1, "Margherita", 8.50, margheritaIngredients);
        margherita.addIngredient(listeIngredients.get(0));
        margherita.addIngredient(listeIngredients.get(2));
        margherita.addIngredient(listeIngredients.get(6));
        allPizzas.add(margherita);

        Vector<Ingredient> chorizoIngredients = new Vector<Ingredient>();
        Pizza chorizo = new Pizza(2, "Chorizo", 9.00, chorizoIngredients);
        chorizo.addIngredient(listeIngredients.get(0));
        chorizo.addIngredient(listeIngredients.get(2));
        chorizo.addIngredient(listeIngredients.get(8));
        allPizzas.add(chorizo);

        Vector<Ingredient> quatreFromagesIngredients = new Vector<Ingredient>();
        Pizza quatreFromages = new Pizza(3, "4 Fromages", 10.00, quatreFromagesIngredients);
        quatreFromages.addIngredient(listeIngredients.get(0));
        quatreFromages.addIngredient(listeIngredients.get(2));
        quatreFromages.addIngredient(listeIngredients.get(3));
        quatreFromages.addIngredient(listeIngredients.get(4));
        quatreFromages.addIngredient(listeIngredients.get(5));
        allPizzas.add(quatreFromages);

        Vector<Ingredient> royaleIngredients = new Vector<Ingredient>();
        Pizza royale = new Pizza(4, "Royale", 11.00, royaleIngredients);
        royale.addIngredient(listeIngredients.get(0));
        royale.addIngredient(listeIngredients.get(2));
        royale.addIngredient(listeIngredients.get(7));
        royale.addIngredient(listeIngredients.get(11));
        royale.addIngredient(listeIngredients.get(14));
        allPizzas.add(royale);

        Vector<Ingredient> vegetarienneIngredients = new Vector<Ingredient>();
        Pizza vegetarienne = new Pizza(5, "Végétarienne", 10.50, vegetarienneIngredients);
        vegetarienne.addIngredient(listeIngredients.get(0));
        vegetarienne.addIngredient(listeIngredients.get(2));
        vegetarienne.addIngredient(listeIngredients.get(9));
        vegetarienne.addIngredient(listeIngredients.get(10));
        vegetarienne.addIngredient(listeIngredients.get(11));
        vegetarienne.addIngredient(listeIngredients.get(21));
        allPizzas.add(vegetarienne);

        Vector<Ingredient> flammekuecheIngredients = new Vector<Ingredient>();
        Pizza flammekueche = new Pizza(6, "Flammekueche", 9.50, flammekuecheIngredients);
        flammekueche.addIngredient(listeIngredients.get(1));
        flammekueche.addIngredient(listeIngredients.get(2));
        flammekueche.addIngredient(listeIngredients.get(12));
        flammekueche.addIngredient(listeIngredients.get(10));
        flammekueche.addIngredient(listeIngredients.get(22));
        allPizzas.add(flammekueche);

        Vector<Ingredient> saumonIngredients = new Vector<Ingredient>();
        Pizza saumon = new Pizza(7, "Saumon", 12.00, saumonIngredients);
        saumon.addIngredient(listeIngredients.get(1));
        saumon.addIngredient(listeIngredients.get(2));
        saumon.addIngredient(listeIngredients.get(13));
        saumon.addIngredient(listeIngredients.get(10));
        saumon.addIngredient(listeIngredients.get(21));
        allPizzas.add(saumon);

        Vector<Ingredient> ailIngredients = new Vector<Ingredient>();
        Pizza ail = new Pizza(8, "Ail", 9.50, ailIngredients);
        ail.addIngredient(listeIngredients.get(16));
        ail.addIngredient(listeIngredients.get(22));
        ail.addIngredient(listeIngredients.get(15));
        allPizzas.add(ail);

        Vector<Ingredient> curryIngredients = new Vector<Ingredient>();
        Pizza curry = new Pizza(9, "Curry", 10.50, curryIngredients);
        curry.addIngredient(listeIngredients.get(23));
        curry.addIngredient(listeIngredients.get(2));
        curry.addIngredient(listeIngredients.get(17));
        curry.addIngredient(listeIngredients.get(19));
        allPizzas.add(curry);

        Vector<Ingredient> campioneIngredients = new Vector<Ingredient>();
        Pizza campione = new Pizza(10, "Campione", 11.50, campioneIngredients);
        campione.addIngredient(listeIngredients.get(0));
        campione.addIngredient(listeIngredients.get(2));
        campione.addIngredient(listeIngredients.get(18));
        campione.addIngredient(listeIngredients.get(20));
        campione.addIngredient(listeIngredients.get(10));
        allPizzas.add(campione);

        Vector<Ingredient> marinaraIngredients = new Vector<Ingredient>();
        Pizza marinara = new Pizza(11, "Marinara", 8.50, marinaraIngredients);
        marinara.addIngredient(listeIngredients.get(0));
        marinara.addIngredient(listeIngredients.get(22));
        allPizzas.add(marinara);
    }
    public Commande getCurrentCommande() {
        return this.currentCommande;
    }

    public void setBase(int base) {
        if (base < 0 || base > 3) {
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
            case "~ Infos pizza ~":
                this.base = 3;
                break;
            default:
                throw new IllegalArgumentException("Invalid base type");
        }
    }

    public void initCurrentCommande(PointRaPizza pointLivraison) {
        this.currentCommande = new Commande(generateNewId(), new java.util.Date(), this.client, pointLivraison);
        this.currentCommande.setLivreur(nextLivreurs());
    }

    // Méthode pour générer un nouvel ID (à adapter selon votre logique)
    private int generateNewId() {
        commandeID++;
        return commandeID;
    }

    private Livreur nextLivreurs(){
        compteurLivreur++;
        if(compteurLivreur >= this.livreurs.size()){
            compteurLivreur = 0;
        }
        return this.livreurs.get(compteurLivreur);
    }

    public void addUser(Client user) {
        this.listeUtilisateurs.add(user);
    }

    public boolean isUser(Client user, String tel) {
        for (Client u : listeUtilisateurs) {
            if (u.getNom().equals(user.getNom()) && u.getNum_tel().equals(tel)) {
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
    public void disconnect(){this.client = null;}

    public Client getClient(){
        return client;
    }
    public boolean isConnected(){
        return  (client != null);
    }
    public boolean isPaying(){return paying;}
    public int getBase() {
        return base;
    }


    public Pizza getPizzaParNom(String nom) {
        for (Pizza pizza : allPizzas) {
            if (pizza.getNom_pizza().equals(nom)) {
                return pizza;
            }
        }
        return null;
    }

    public int getBaseFromPizza() {
        if (pizzaSelectionnee.getNom_pizza().equals("Margherita") || pizzaSelectionnee.getNom_pizza().equals("Chorizo") || pizzaSelectionnee.getNom_pizza().equals("4 Fromages") || pizzaSelectionnee.getNom_pizza().equals("Royale") || pizzaSelectionnee.getNom_pizza().equals("Végétarienne")) {
            return 1;
        } else if (pizzaSelectionnee.getNom_pizza().equals("Flammekueche") || pizzaSelectionnee.getNom_pizza().equals("Saumon")) {
            return 2;
        } else if (pizzaSelectionnee.getNom_pizza().equals("Ail") || pizzaSelectionnee.getNom_pizza().equals("Curry") || pizzaSelectionnee.getNom_pizza().equals("Campione") || pizzaSelectionnee.getNom_pizza().equals("Marinara")) {
            return 0;
        }
        throw new IllegalArgumentException("Invalid pizza");
    }

    public void setPizzaSelectionnee(Pizza pizza) {
        this.pizzaSelectionnee = pizza;
    }

    public Pizza getPizzaSelectionnee() {
        return pizzaSelectionnee;
    }

    public void paye(){this.paying = true;}
    public void stopPaye(){this.paying = false;}

    public void reinitialiserCommande(){
        this.base = 0;
        this.currentCommande = new Commande(generateNewId(), new java.util.Date(), this.client, this.currentCommande.getPointRaPizza());
    }

    public Vector<Client> getUsers(){return this.listeUtilisateurs;}
    public Vector<Commande> getCommandes(){return this.listeCommandes;}
    public void addCommandes(Commande c){ this.listeCommandes.add(c);}
    public Vector<Livreur> getLivreurs(){return this.livreurs;}
    public void setACommandeToLivreur(){
        this.livreurs.get(compteurLivreur).addCommande(this.currentCommande);

    }

}

