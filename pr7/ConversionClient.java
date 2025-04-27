import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ConversionClient {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Prompt the user for the server's IP address
            System.out.print("Enter server IP address: ");
            String serverIp = scanner.nextLine();

            // Connect to the RMI registry on the server
            Registry registry = LocateRegistry.getRegistry(serverIp, 2000);
            
            // Lookup the remote object (ConversionService) in the RMI registry
            ConversionService stub = (ConversionService) registry.lookup("ConversionService");

            // Ask the user for the miles value
            System.out.print("Enter distance in miles: ");
            double miles = scanner.nextDouble();
            
            // Call the remote method to convert miles to kilometers
            double kilometers = stub.convertMilesToKilometers(miles);
            
            // Print the result
            System.out.println("Distance in kilometers: " + kilometers);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

