import java.net.*;
import java.util.*;
import java.io.*;

public class TimeClient {
    private static final String SERVER_ADDRESS = "192.168.1.100";  // Set your server's IP address here
    private static final int SERVER_PORT = 12345;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);

        while (true) {
            long currentTime = System.currentTimeMillis();
            String message = String.valueOf(currentTime);

            // Send the current time to the server
            DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, SERVER_PORT);
            socket.send(sendPacket);
            System.out.println("Sent time to server: " + currentTime);

            // Receive the time adjustment from the server
            byte[] receiveData = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String adjustmentMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            long timeAdjustment = Long.parseLong(adjustmentMessage.trim());
            System.out.println("Received time adjustment: " + timeAdjustment);

            // Adjust the system time
            long adjustedTime = currentTime + timeAdjustment;
            System.out.println("Adjusting system time to: " + adjustedTime);

            // Simulate system time adjustment (here we are just printing it)
            // In a real-world scenario, you'd adjust the system time here
            // For example, on Linux: `sudo date -s @<adjustedTime>`
            // In this case, we just print the adjusted time.

            Thread.sleep(10000);  // Wait for the next adjustment
        }
    }
}

