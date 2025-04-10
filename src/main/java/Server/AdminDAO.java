package Server;

import Entite.Admin;
import Entite.Etudiant;
import Entite.etdClass;
import java.sql.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdminDAO extends UnicastRemoteObject implements AdminService {

    public AdminDAO() throws RemoteException {
        super();
    }

    @Override
    public boolean login(String email, String password) throws RemoteException {
        return verifyLogin(email, password);
    }

    public boolean verifyLogin(String email, String password) throws RemoteException {
        Connection con = BD.getInstance();
        try {
            String sql = "SELECT MotDePasse FROM administrateur WHERE Email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("MotDePasse");  // Mot de passe stocké en base

                
                if (password.equals(storedPassword)) {
                    return true;  
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;  
    }

    @Override
    public ArrayList<etdClass> chargercmbClass() throws RemoteException {
    ArrayList<etdClass> classes = new ArrayList<>();
    Connection con = BD.getInstance();

    String sql = "SELECT ID_Classe, Nom_Classe, ID_Filiere FROM Classe";

    try (
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()
    ) {
        while (rs.next()) {
            etdClass etdClass = new etdClass();
            etdClass.setIdClasse(rs.getInt("ID_Classe"));
            etdClass.setNomClasse(rs.getString("Nom_Classe"));

            int idFiliere = rs.getInt("ID_Filiere");
            etdClass.setIdFiliere(rs.wasNull() ? null : idFiliere);

            classes.add(etdClass);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RemoteException("Erreur lors de la récupération des classes : " + e.getMessage());
    }

    return classes;
}


    @Override
    public boolean ajouterEtudiant(String nom, String prenom, String email, Date dateNaissance, int idClasse) throws RemoteException {
        Connection con = BD.getInstance();
        String sql = "INSERT INTO Etudiant (Nom, Prenom, Email, Date_Naissance, ID_Classe) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, email);
            ps.setDate(4, dateNaissance);
            ps.setInt(5, idClasse); 

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Erreur lors de l'ajout de l'étudiant : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Etudiant> afficherEtudiants() throws RemoteException {
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        Connection con = BD.getInstance();
        String sql = "SELECT e.ID_Etudiant, e.Nom, e.Prenom, e.Email, e.Date_Naissance, c.Nom_Classe, f.Nom_Filiere, e.ID_Classe "
                + "FROM Etudiant e "
                + "JOIN Classe c ON e.ID_Classe = c.ID_Classe "
                + "JOIN Filiere f ON c.ID_Filiere = f.ID_Filiere";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(rs.getInt("ID_Etudiant"));
                etudiant.setNom(rs.getString("Nom"));
                etudiant.setPrenom(rs.getString("Prenom"));
                etudiant.setEmail(rs.getString("Email"));
                etudiant.setDateNaissance(rs.getDate("Date_Naissance"));
                etudiant.setNomClasse(rs.getString("Nom_Classe")); 
                etudiant.setNomFiliere(rs.getString("Nom_Filiere"));
                etudiant.setIdClasse(rs.getInt("ID_Classe"));
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Erreur lors de la récupération des étudiants : " + e.getMessage());
        }
        return etudiants;
    }

    @Override
    public boolean modifierEtudiant(int idEtudiant, String nom, String prenom, String email, Date dateNaissance, int idClasse) throws RemoteException {
 Connection con = BD.getInstance();
        String sql = "UPDATE Etudiant SET Nom = ?, Prenom = ?, Email = ?, Date_Naissance = ?, ID_Classe = ? WHERE ID_Etudiant = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, email);
            ps.setDate(4, dateNaissance);
            ps.setInt(5, idClasse);
            ps.setInt(6, idEtudiant);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Erreur lors de la modification de l'étudiant : " + e.getMessage());
        }
    }

    @Override
    public boolean supprimerEtudiant(int idEtudiant) throws RemoteException {
   Connection con = BD.getInstance();
        String sql = "DELETE FROM Etudiant WHERE ID_Etudiant = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idEtudiant);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Erreur lors de la suppression de l'étudiant : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Etudiant> afficherEtudiantsParClasse(int idClasse) throws RemoteException {
  Connection con = BD.getInstance();
    ArrayList<Etudiant> etudiants = new ArrayList<>();
    String sql = "SELECT e.ID_Etudiant, e.Nom, e.Prenom, e.Email, e.Date_Naissance, c.Nom_Classe, f.Nom_Filiere, e.ID_Classe "
            + "FROM Etudiant e "
            + "JOIN Classe c ON e.ID_Classe = c.ID_Classe "
            + "JOIN Filiere f ON c.ID_Filiere = f.ID_Filiere "
            + "WHERE e.ID_Classe = ?"; 

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idClasse);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getInt("ID_Etudiant"));
            etudiant.setNom(rs.getString("Nom"));
            etudiant.setPrenom(rs.getString("Prenom"));
            etudiant.setEmail(rs.getString("Email"));
            etudiant.setDateNaissance(rs.getDate("Date_Naissance"));
            etudiant.setNomClasse(rs.getString("Nom_Classe"));
            etudiant.setNomFiliere(rs.getString("Nom_Filiere"));
            etudiant.setIdClasse(rs.getInt("ID_Classe"));
            etudiants.add(etudiant);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RemoteException("Erreur lors de la récupération des étudiants par classe : " + e.getMessage());
    }

    return etudiants;
    }

    @Override
    public ArrayList<Etudiant> searchETD(String nom) throws RemoteException {
 Connection con = BD.getInstance();
    ArrayList<Etudiant> etudiants = new ArrayList<>();
    
    // Requête avec LIKE pour une recherche partielle (insensible à la casse)
    String sql = "SELECT e.ID_Etudiant, e.Nom, e.Prenom, e.Email, e.Date_Naissance, "
               + "c.Nom_Classe, f.Nom_Filiere, e.ID_Classe "
               + "FROM Etudiant e "
               + "JOIN Classe c ON e.ID_Classe = c.ID_Classe "
               + "JOIN Filiere f ON c.ID_Filiere = f.ID_Filiere "
               + "WHERE LOWER(e.Nom) LIKE LOWER(?) OR LOWER(e.Prenom) LIKE LOWER(?)";
    
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + nom + "%"); 
        ps.setString(2, "%" + nom + "%"); 
        
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getInt("ID_Etudiant"));
            etudiant.setNom(rs.getString("Nom"));
            etudiant.setPrenom(rs.getString("Prenom"));
            etudiant.setEmail(rs.getString("Email"));
            etudiant.setDateNaissance(rs.getDate("Date_Naissance"));
            etudiant.setNomClasse(rs.getString("Nom_Classe"));
            etudiant.setNomFiliere(rs.getString("Nom_Filiere"));
            etudiant.setIdClasse(rs.getInt("ID_Classe"));
            etudiants.add(etudiant);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RemoteException("Erreur lors de la recherche des étudiants : " + e.getMessage());
    }
    return etudiants;
}    }

  


