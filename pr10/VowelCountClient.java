import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class VowelCountClient {

    public static void main(String[] args) {
        try {
            // Get the RMI registry and lookup the remote object
            Registry registry = LocateRegistry.getRegistry("localhost", 2000); // Use the server IP address if different
            
            // Lookup the remote service
            VowelCountService stub = (VowelCountService) registry.lookup("VowelCountService");
            
            // Get input from user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a word: ");
            String word = scanner.nextLine();
            
            // Call the remote method to count vowels
            int vowelCount = stub.countVowels(word);
            
            // Print the result
            System.out.println("Number of vowels in \"" + word + "\": " + vowelCount);
        } catch (Exception e) {
            System.out.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

