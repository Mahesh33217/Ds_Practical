import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer extends UnicastRemoteObject implements EchoService {

    private static final int PORT = 2000; // Port for RMI
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(10); // Multi-threaded handling of clients

    public EchoServer() throws RemoteException {
        super();
    }

    // Implement the remote method to say hello with the name
    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello " + name; // Append "Hello" with the client's name
    }

    public static void main(String[] args) {
        try {
            // Create the RMI registry on port 2000
            Registry registry = LocateRegistry.createRegistry(PORT);
            
            // Create an instance of the server
            EchoServer server = new EchoServer();

            // Bind the server to the RMI registry with the name "EchoService"
            registry.rebind("EchoService", server);
            System.out.println("Server is ready...");
            
            // Server is multi-threaded, can handle multiple clients concurrently
            while (true) {
                threadPool.submit(new Runnable() {
                    @Override
                    public void run() {
                        // Handle each client request (In this case, no actual interaction here, it's for handling more clients)
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

