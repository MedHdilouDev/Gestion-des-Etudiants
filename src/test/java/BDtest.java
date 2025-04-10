
import Server.BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BDtest {
    
    
    


    @Test
    public void testGetInstance_ShouldReturnValidConnection() {
        
        Connection connection = BD.getInstance();
        
        assertNotNull(connection, "La connexion ne devrait pas être null");
        
        try {
            assertFalse(connection.isClosed(), "La connexion devrait être ouverte");
            assertTrue(connection.isValid(2), "La connexion devrait être valide");
        } catch (SQLException e) {
            fail("Exception inattendue: " + e.getMessage());
        }
    }

    @Test
    public void testGetInstance_ShouldReturnSameInstance() {
        Connection connection1 = BD.getInstance();
        Connection connection2 = BD.getInstance();
        
        assertSame(connection1, connection2, "Devrait retourner la même instance de connexion");
    }
}