

import Entite.Etudiant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class EtudiantTest {

    private Etudiant etudiant;
    private Date dateNaissance;

    @BeforeEach
    void setUp() {
        dateNaissance = Date.valueOf("2000-05-15");

        etudiant = new Etudiant("Ali", "Ahmed", "ali.ahmed@example.com", dateNaissance, 2);
    }

    @Test
    void testConstructeurAvecParametres() {
        assertEquals("Ali", etudiant.getNom());
        assertEquals("Ahmed", etudiant.getPrenom());
        assertEquals("ali.ahmed@example.com", etudiant.getEmail());
        assertEquals(dateNaissance, etudiant.getDateNaissance());
        assertEquals(2, etudiant.getIdClasse());
    }

    @Test
    void testConstructeurSansParametres() {
        Etudiant e = new Etudiant();
        assertNotNull(e);
    }

    @Test
    void testSettersEtGetters() {
        etudiant.setId(10);
        etudiant.setNom("Khalid");
        etudiant.setPrenom("Youssef");
        etudiant.setEmail("khalid.youssef@example.com");
        etudiant.setDateNaissance(Date.valueOf("1999-10-01"));
        etudiant.setIdClasse(5);
        etudiant.setNomClasse("2ème Année BTS");
        etudiant.setNomFiliere("Informatique");

        assertEquals(10, etudiant.getId());
        assertEquals("Khalid", etudiant.getNom());
        assertEquals("Youssef", etudiant.getPrenom());
        assertEquals("khalid.youssef@example.com", etudiant.getEmail());
        assertEquals(Date.valueOf("1999-10-01"), etudiant.getDateNaissance());
        assertEquals(5, etudiant.getIdClasse());
        assertEquals("2ème Année BTS", etudiant.getNomClasse());
        assertEquals("Informatique", etudiant.getNomFiliere());
    }
}
