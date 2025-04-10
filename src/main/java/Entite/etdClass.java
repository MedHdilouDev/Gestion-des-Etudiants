/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

import java.io.Serializable;

/**
 *
 * @author Mohamed Hdilou
 */
public class etdClass implements Serializable {

    private int idClasse;
    private String nomClasse;
    private Integer idFiliere; // Utiliser Integer pour permettre les valeurs NULL

    // Constructeurs
    public etdClass() {
    }

    public etdClass(String nomClasse, Integer idFiliere) {
        this.nomClasse = nomClasse;
        this.idFiliere = idFiliere;
    }

    public etdClass(int idClasse, String nomClasse, Integer idFiliere) {
        this.idClasse = idClasse;
        this.nomClasse = nomClasse;
        this.idFiliere = idFiliere;
    }

    // Getters et Setters
    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public Integer getIdFiliere() {
        return idFiliere;
    }

    public void setIdFiliere(Integer idFiliere) {
        this.idFiliere = idFiliere;
    }

    @Override
    public String toString() {
        return "etdClass{" +
                "idClasse=" + idClasse +
                ", nomClasse='" + nomClasse + '\'' +
                ", idFiliere=" + idFiliere +
                '}';
    }
}