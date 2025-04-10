package client;

import Entite.Etudiant;
import Entite.etdClass;
import Server.AdminService;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

public class Client {

    private AdminService adminService;

    public Client() {
        try {
            // Connexion au serveur RMI
            adminService = (AdminService) Naming.lookup("rmi://localhost/AdminService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean login(String email, String password) throws RemoteException {
        return adminService.login(email, password);
    }

    public ArrayList<etdClass> chargercmbClass() throws RemoteException {
        return adminService.chargercmbClass();
    }

    public boolean ajouterEtudiant(String nom, String prenom, String email, Date dateNaissance, int idClasse) throws RemoteException {
        return adminService.ajouterEtudiant(nom, prenom, email, dateNaissance, idClasse);
    }

    public ArrayList<Etudiant> afficherEtudiants() throws RemoteException {
        return adminService.afficherEtudiants();
    }

    public boolean modifierEtudiant(int idEtudiant, String nom, String prenom, String email, Date dateNaissance, int idClasse) throws RemoteException {
        return adminService.modifierEtudiant(idEtudiant, nom, prenom, email, dateNaissance, idClasse);
    }

    public boolean supprimerEtudiant(int idEtudiant) throws RemoteException {
        return adminService.supprimerEtudiant(idEtudiant);
    }

    public ArrayList<Etudiant> afficherEtudiantsParClasse(int idClasse) throws RemoteException {
        return adminService.afficherEtudiantsParClasse(idClasse);
    }
    
    public ArrayList<Etudiant> searchETD(String nom) throws RemoteException {
        return adminService.searchETD(nom);
    }

 }
