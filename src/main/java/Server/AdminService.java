/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Server;

import Entite.Etudiant;
import Entite.etdClass;
import java.awt.List;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

public interface AdminService extends Remote {

    boolean login(String email, String password) throws RemoteException;

    boolean verifyLogin(String email, String password) throws RemoteException;

    ArrayList<etdClass> chargercmbClass() throws RemoteException;

    boolean ajouterEtudiant(String nom, String prenom, String email, Date dateNaissance, int idClasse) throws RemoteException;

    ArrayList<Etudiant> afficherEtudiants() throws RemoteException; // Nouvelle méthode

    boolean modifierEtudiant(int idEtudiant, String nom, String prenom, String email, Date dateNaissance, int idClasse) throws RemoteException; // Ajout de la méthode modifierEtudiant

    boolean supprimerEtudiant(int idEtudiant) throws RemoteException; // Ajout de la méthode supprimerEtudiant
    ArrayList<Etudiant> afficherEtudiantsParClasse(int idClasse) throws RemoteException;
    ArrayList<Etudiant> searchETD(String nom) throws RemoteException;

}
