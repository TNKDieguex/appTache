package cal335.lab.dto;

public class TacheDTO {

    private String nom;
    private String description;
    private boolean aFaire;

    public TacheDTO() {
    }

//    public TacheDTO(String nom, String description) {
//        this.nom = nom;
//        this.description = description;
//        this.aFaire = false;
//    }

    public TacheDTO(String nom, String description, boolean aFaire) {
        this.nom = nom;
        this.description = description;
        this.aFaire = aFaire;
    }

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

    public boolean isaFaire() {
        return aFaire;
    }

    public void setaFaire(boolean aFaire) {
        this.aFaire = aFaire;
    }
}
