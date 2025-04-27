import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConversionServer extends UnicastRemoteObject implements ConversionService {
    
    private static final int PORT = 2000;
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(10); // Multi-threading
    
    public ConversionServer() throws RemoteException {
        super();
    }

    // Implementation of the remote method to convert Celsius to Fahrenheit
    @Override
    public double convertToFahrenheit(double celsius) throws RemoteException {
        return (celsius * 9 / 5) + 32;
    }
    
    public static void main(String[] args) {
        try {
            // Create RMI registry on port 2000
            Registry registry = LocateRegistry.createRegistry(PORT);
            ConversionServer server = new ConversionServer();

            // Bind the service to the registry with the name "ConversionService"
            registry.rebind("ConversionService", server);
            System.out.println("Server is ready...");

            // Multi-threading: handle multiple clients simultaneously
            while (true) {
                threadPool.submit(new Runnable() {
                    @Override
                    public void run() {
                        // Handle each client request
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

