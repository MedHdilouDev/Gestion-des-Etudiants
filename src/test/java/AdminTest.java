import Entite.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new Admin(); 
    }

    @Test
    public void testSetAndGetId() {
        admin.setId(1);
        assertEquals(1, admin.getId(), "L'id devrait être 1");
    }

    @Test
    public void testSetAndGetNom() {
        admin.setNom("Ahmed");
        assertEquals("Ahmed", admin.getNom(), "Le nom devrait être Ahmed");
    }

    @Test
    public void testSetAndGetEmail() {
        admin.setEmail("ahmed@example.com");
        assertEquals("ahmed@example.com", admin.getEmail(), "L'email devrait être ahmed@example.com");
    }

    @Test
    public void testSetAndGetMotDePasse() {
        admin.setMotDePasse("1234");
        assertEquals("1234", admin.getMotDePasse(), "Le mot de passe devrait être 1234");
    }

    @Test
    public void testConstructeurAvecParametres() {
        Admin a = new Admin(2, "Sara", "sara@example.com", "pass123");
        assertEquals(2, a.getId());
        assertEquals("Sara", a.getNom());
        assertEquals("sara@example.com", a.getEmail());
        assertEquals("pass123", a.getMotDePasse());
    }

    @Test
    public void testToString() {
        admin.setId(10);
        admin.setNom("Ali");
        admin.setEmail("ali@example.com");
        admin.setMotDePasse("admin123");

        String expected = "Admin{id=10, nom='Ali', email='ali@example.com', motDePasse='admin123'}";
        assertEquals(expected, admin.toString(), "toString() ne retourne pas la bonne chaîne");
    }
}
