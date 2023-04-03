package ma.fstt.model;

public class Produit {
    private Long id_produit ;

    private String prix ;

    private String description ;

    public Produit() {
    }

    public Produit(Long id_produit, String prix, String description) {
        this.id_produit = id_produit;
        this.prix = prix;
        this.description = description;
    }

    public Long getId_produit() {return id_produit;}

    public void setId_produit(Long id_livreur) {
        this.id_produit = id_livreur;
    }

    public String getPrix() {
        return prix;
    }

    public void setprix(String nom) {
        this.prix = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setdescription(String telephone) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "produit{" +
                "id_produit=" + id_produit +
                ", prix='" + prix + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


