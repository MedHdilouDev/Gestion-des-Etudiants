package Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {

    protected Server() throws Exception {
        super();
    }

    public static void main(String[] args) {
        try {
            
            LocateRegistry.createRegistry(1099);
            
            
            AdminService adminService = new AdminDAO();
            
            
            Naming.rebind("rmi://localhost:1099/AdminService", adminService);
            
            
            System.out.println("🚀 Serveur RMI démarré sur le port 1099...");
        } catch (Exception ex) {
            System.out.println("Erreur: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
