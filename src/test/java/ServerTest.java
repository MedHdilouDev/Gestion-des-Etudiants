import Server.AdminService;
import Server.Server;
import org.junit.jupiter.api.*;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServerTest {

    private static Process serverProcess;
    private static final int PORT = 1099;
    private static AdminService adminService;

    @BeforeAll
    static void startServer() {
        try {
            
            try {
                Registry registry = LocateRegistry.getRegistry(PORT);
                registry.unbind("AdminService");
            } catch (Exception ignored) {
                
            }

            
            serverProcess = new ProcessBuilder("java", "-cp",
                    System.getProperty("java.class.path"), "Server.Server")
                    .inheritIO()
                    .start();

            
            Thread.sleep(4000);

            
            adminService = (AdminService) Naming.lookup("rmi://localhost:" + PORT + "/AdminService");
            assertNotNull(adminService, "❌ Le service AdminService n'a pas été trouvé.");
            System.out.println("✅ Serveur RMI accessible et service chargé.");

        } catch (Exception e) {
            fail("Erreur lors du démarrage du serveur : " + e.getMessage());
        }
    }

    @Test
    @Order(1)
    void testLoginSuccess() throws RemoteException {
        boolean result = adminService.login("mohamedHdilou@gmail.com", "1234");
        assertTrue(result, "L'authentification devrait réussir pour l'admin.");
    }

    @Test
    @Order(2)
    void testLoginWrongPassword() throws RemoteException {
        boolean result = adminService.login("mohamedHdilou@gmail.com", "wrongPassword");
        assertFalse(result, "L'authentification ne devrait pas réussir avec un mauvais mot de passe.");
    }

    @Test
    @Order(3)
    void testLoginWrongEmail() throws RemoteException {
        boolean result = adminService.login("wrong@mail.com", "1234");
        assertFalse(result, "L'authentification ne devrait pas réussir avec un mauvais email.");
    }

    @Test
    @Order(4)
    void testServiceNotNull() {
        assertNotNull(adminService, "Le service AdminService est null.");
    }

    

    @AfterAll
    static void stopServer() {
        try {
            Registry registry = LocateRegistry.getRegistry(PORT);
            registry.unbind("AdminService");
        } catch (Exception e) {
            System.err.println(" Erreur lors du nettoyage : " + e.getMessage());
        }

        if (serverProcess != null) {
            serverProcess.destroyForcibly();
            System.out.println("Serveur arrêté.");
        }
    }
}
