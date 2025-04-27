import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class EchoClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Prompt the user for the server's IP address
            System.out.print("Enter server IP address: ");
            String serverIp = scanner.nextLine();

            // Connect to the RMI registry on the server
            Registry registry = LocateRegistry.getRegistry(serverIp, 2000);
            
            // Lookup the remote object (EchoService) in the RMI registry
            EchoService stub = (EchoService) registry.lookup("EchoService");

            // Ask the user for their name
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            
            // Call the remote method to say hello with the name
            String greeting = stub.sayHello(name);
            
            // Print the result
            System.out.println(greeting);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

