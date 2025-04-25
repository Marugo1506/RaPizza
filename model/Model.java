package model;

public class Model {
    
    public String oui;
    int base; // 0 -> spécialités, 1 -> base tomate, 2 -> base Creme
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

    public int getBase() {
        return base;
    }
}

