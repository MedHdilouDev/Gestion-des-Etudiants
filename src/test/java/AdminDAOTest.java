

import Entite.etdClass;
import Server.AdminDAO;
import static org.junit.jupiter.api.Assertions.*;
import Entite.Etudiant;
import Server.BD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.rmi.RemoteException;
import java.sql.Date;

import java.util.ArrayList;
import java.util.UUID;


public class AdminDAOTest {

    private AdminDAO adminDAO;
    private String uniqueEmail;

    @BeforeEach
    public void setUp() throws RemoteException {
        adminDAO = new AdminDAO();
        uniqueEmail = "etudiant_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
    }

    // Test pour ajouterEtudiant()
    @Test
    public void testAjouterEtudiant_Success() throws RemoteException {
        // Act
        boolean result = adminDAO.ajouterEtudiant(
                "Dupont",
                "Jean",
                uniqueEmail,
                Date.valueOf("2000-01-15"),
                1 // ID_Classe valide
        );

        // Assert
        assertTrue(result, "L'ajout devrait réussir avec des données valides");
    }

    // Test pour supprimerEtudiant()
    @Test
    public void testSupprimerEtudiant_NonExistant() throws RemoteException {
        // Act & Assert
        assertFalse(adminDAO.supprimerEtudiant(999),
                "Devrait échouer pour ID inexistant");
    }

    // Test pour searchETD()
    @Test
    public void testSearchETD_ByNom() throws RemoteException {
       

        
        ArrayList<Etudiant> resultats = adminDAO.searchETD("Nom29");

        
        assertFalse(resultats.isEmpty(), "Devrait trouver au moins 1 résultat");
        assertEquals("Nom29", resultats.get(0).getNom());
    }

    @Test
    public void testSearchETD_NotFound() throws RemoteException {
        
        ArrayList<Etudiant> resultats = adminDAO.searchETD("Aucun");

        
        assertTrue(resultats.isEmpty(), "Ne devrait rien trouver");
    }

    // Test pour verifyLogin()
    @Test
    public void testVerifyLogin_Success() throws RemoteException {
        boolean result = adminDAO.verifyLogin("mohamedHdilou@gmail.com", "1234");
        assertTrue(result); 
    }

    
    @Test
    public void testAfficherEtudiants() throws RemoteException {
        ArrayList<Etudiant> etudiants = adminDAO.afficherEtudiants();
        assertFalse(etudiants.isEmpty()); 
    }

   
    @Test
    public void testModifierEtudiant() throws RemoteException {
        boolean result = adminDAO.modifierEtudiant(
                19, // ID_Etudiant existant
                "Updated", "Name", "updated@example.com",
                Date.valueOf("1999-12-31"), 2 // Nouvel ID_Classe
        );
        assertTrue(result);
    }

    @Test
    public void testChargerCmbClass_Success() throws RemoteException {
        ArrayList<etdClass> classes = adminDAO.chargercmbClass();

        
        assertNotNull(classes, "La liste des classes ne devrait pas être null");
        assertFalse(classes.isEmpty(), "La liste des classes ne devrait pas être vide");

        etdClass premiereClasse = classes.get(0);
        assertTrue(premiereClasse.getIdClasse() > 0, "L'ID de classe devrait être positif");
        assertNotNull(premiereClasse.getNomClasse(), "Le nom de classe ne devrait pas être null");

        
        boolean hasNullFiliere = classes.stream()
                .anyMatch(c -> c.getIdFiliere() == null);

        if (hasNullFiliere) {
            etdClass classeWithNull = classes.stream()
                    .filter(c -> c.getIdFiliere() == null)
                    .findFirst()
                    .get();
            assertNull(classeWithNull.getIdFiliere());
        }
    }
    
    
    @Test
public void testAfficherEtudiantsParClasse() throws RemoteException {
    
    
    ArrayList<Etudiant> resultats = adminDAO.afficherEtudiantsParClasse(1);
    
    assertFalse(resultats.isEmpty(), "Devrait trouver des étudiants pour cette classe");
    
    for (Etudiant etudiant : resultats) {
        assertEquals(1, etudiant.getIdClasse(), 
                   "Tous les étudiants devraient appartenir à la classe 1");
    }
    
    Etudiant premierEtudiant = resultats.get(0);
    assertNotNull(premierEtudiant.getNom());
    assertNotNull(premierEtudiant.getPrenom());
    assertNotNull(premierEtudiant.getEmail());
    assertNotNull(premierEtudiant.getDateNaissance());
    assertNotNull(premierEtudiant.getNomClasse());
    assertNotNull(premierEtudiant.getNomFiliere());
}

@Test
public void testAfficherEtudiantsParClasse_Inexistante() throws RemoteException {
    ArrayList<Etudiant> resultats = adminDAO.afficherEtudiantsParClasse(999);
    
    assertTrue(resultats.isEmpty(), 
              "Devrait retourner une liste vide pour une classe inexistante");
}
    
    

}
