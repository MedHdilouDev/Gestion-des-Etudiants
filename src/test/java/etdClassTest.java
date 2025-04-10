/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import Entite.etdClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mohamed Hdilou
 */




class etdClassTest {

    private etdClass classe;

    @BeforeEach
    void setUp() {
        classe = new etdClass("2ème Année BTS", 3);
    }

    @Test
    void testConstructeurSansId() {
        assertEquals("2ème Année BTS", classe.getNomClasse());
        assertEquals(3, classe.getIdFiliere());
    }

    @Test
    void testConstructeurAvecId() {
        etdClass c = new etdClass(10, "1ère Année BTS", 2);
        assertEquals(10, c.getIdClasse());
        assertEquals("1ère Année BTS", c.getNomClasse());
        assertEquals(2, c.getIdFiliere());
    }

    @Test
    void testConstructeurVide() {
        etdClass c = new etdClass();
        assertNotNull(c);
    }

    @Test
    void testSettersEtGetters() {
        classe.setIdClasse(5);
        classe.setNomClasse("3ème Année BTS");
        classe.setIdFiliere(null); // Tester valeur null

        assertEquals(5, classe.getIdClasse());
        assertEquals("3ème Année BTS", classe.getNomClasse());
        assertNull(classe.getIdFiliere());
    }

    @Test
    void testToString() {
        etdClass c = new etdClass(7, "Licence 1", 4);
        String result = c.toString();
        assertTrue(result.contains("idClasse=7"));
        assertTrue(result.contains("nomClasse='Licence 1'"));
        assertTrue(result.contains("idFiliere=4"));
    }
}