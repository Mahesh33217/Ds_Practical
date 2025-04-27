import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class FactorialClient {

    public static void main(String[] args) {
        try {
            // Get the RMI registry and lookup the remote object
            Registry registry = LocateRegistry.getRegistry("localhost", 2000); // Replace with server IP if needed
            
            // Lookup the remote service
            FactorialService stub = (FactorialService) registry.lookup("FactorialService");
            
            // Get input from user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a number: ");
            int number = scanner.nextInt();
            
            // Call the remote method to calculate factorial
            long result = stub.calculateFactorial(number);
            
            // Print the result
            System.out.println("Factorial of " + number + " is: " + result);
        } catch (Exception e) {
            System.out.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

