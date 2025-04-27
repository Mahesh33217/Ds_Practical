import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class PowerServer implements PowerService {
    public static void main(String[] args) {
        try {
            // Create an instance of the server
            PowerServer obj = new PowerServer();
            
            // Export the object to the registry
            PowerService stub = (PowerService) UnicastRemoteObject.exportObject(obj, 2000);

            // Create and bind the registry
            Registry registry = LocateRegistry.createRegistry(2000);
            registry.rebind("PowerService", stub);

            System.out.println("Server is ready. Listening on port 2000...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Implement the method to calculate 2^n
    @Override
    public int calculatePowerOfTwo(int n) throws RemoteException {
        return (int) Math.pow(2, n);  // Calculate 2 raised to the power of n
    }
}

