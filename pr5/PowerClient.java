import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class PowerClient {
    public static void main(String[] args) {
        try {
            // Get the server's registry
            Registry registry = LocateRegistry.getRegistry("192.168.20.82", 2000);  // Use server's IP address

            // Look up the remote object (PowerService)
            PowerService stub = (PowerService) registry.lookup("PowerService");

            // Taking input from the user for n
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the exponent for 2^n: ");
            int n = scanner.nextInt();

            // Call remote method to calculate 2^n
            int result = stub.calculatePowerOfTwo(n);

            // Output the result
            System.out.println("2^" + n + " = " + result);

            // Close the scanner
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

