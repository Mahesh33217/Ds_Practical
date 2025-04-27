import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ConversionClient {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Prompt user for server IP address
            System.out.print("Enter server IP address: ");
            String serverIp = scanner.nextLine();
            
            // Connect to the RMI registry on the server
            Registry registry = LocateRegistry.getRegistry(serverIp, 2000);
            
            // Lookup the remote object (server)
            ConversionService stub = (ConversionService) registry.lookup("ConversionService");

            // Ask user for temperature in Celsius
            System.out.print("Enter temperature in Celsius: ");
            double celsius = scanner.nextDouble();
            
            // Call the remote method to convert Celsius to Fahrenheit
            double fahrenheit = stub.convertToFahrenheit(celsius);
            
            // Print the result
            System.out.println("Temperature in Fahrenheit: " + fahrenheit);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

