package Entite;

import java.io.Serializable;
import java.sql.Date;

public class Etudiant implements Serializable {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private Date dateNaissance;
    private int idClasse;
    private String nomClasse; // Ajout de l'attribut nomClasse
    private String nomFiliere; // Ajout de l'attribut nomFiliere

    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, String email, Date dateNaissance, int idClasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.idClasse = idClasse;
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public Date getDateNaissance() { return dateNaissance; }
    public int getIdClasse() { return idClasse; }
    public String getNomClasse() { return nomClasse; } // Getter pour nomClasse
    public String getNomFiliere() { return nomFiliere; } // Getter pour nomFiliere

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }
    public void setIdClasse(int idClasse) { this.idClasse = idClasse; }
    public void setNomClasse(String nomClasse) { this.nomClasse = nomClasse; } // Setter pour nomClasse
    public void setNomFiliere(String nomFiliere) { this.nomFiliere = nomFiliere; } // Setter pour nomFiliere
}