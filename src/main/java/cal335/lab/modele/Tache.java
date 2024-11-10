package cal335.lab.modele;

public class Tache {

    private String nom;
    private String description;
    private Boolean aFaire;

    public Tache() {

    }

    public Tache(String nom, String description, Boolean aFaire) {
        this.nom = nom;
        this.description = description;
        this.aFaire = aFaire;
    }

    // Getters et Setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isaFaire() {
        return aFaire;
    }

    public void setaFaire(Boolean aFaire) {
        this.aFaire = aFaire;
    }
}
