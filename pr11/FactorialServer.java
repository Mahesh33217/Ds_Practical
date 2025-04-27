import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FactorialServer {

    public static void main(String[] args) {
        try {
            // Create and export the remote object
            FactorialServiceImpl service = new FactorialServiceImpl();

            // Create and start the RMI registry on port 2000
            Registry registry = LocateRegistry.createRegistry(2000);
            
            // Bind the service to the RMI registry
            registry.rebind("FactorialService", service);
            System.out.println("FactorialService is ready.");
        } catch (Exception e) {
            System.out.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

