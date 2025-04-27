import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class VowelCountServer {

    public static void main(String[] args) {
        try {
            // Create and export the remote object
            VowelCountServiceImpl service = new VowelCountServiceImpl();

            // Create and start the RMI registry on port 2000
            Registry registry = LocateRegistry.createRegistry(2000);
            
            // Bind the service to the RMI registry
            registry.rebind("VowelCountService", service);
            System.out.println("VowelCountService is ready.");
        } catch (Exception e) {
            System.out.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

