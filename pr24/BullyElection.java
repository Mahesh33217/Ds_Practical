import java.io.*;
import java.net.*;
import java.util.*;

public class BullyElection {
    
    // Process details
    private static final Map<Integer, Integer> processPorts = Map.of(1, 5001, 2, 5002, 3, 5003);
    private static Integer leader = null;
    private static boolean electionInProgress = false;
    private static int processId;
    
    // Constructor
    public BullyElection(int processId) {
        this.processId = processId;
    }
    
    // Send message to a target process
    private void sendMessage(int targetId, String message) {
        try (Socket socket = new Socket("localhost", processPorts.get(targetId));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println(message);
            System.out.println("Process " + processId + " sent message to Process " + targetId + ": " + message);
        } catch (IOException e) {
            System.out.println("Error sending message to Process " + targetId + ": " + e.getMessage());
        }
    }
    
    // Listen for incoming connections from other processes
    private void handleIncomingConnections() {
        try (ServerSocket serverSocket = new ServerSocket(processPorts.get(processId))) {
            System.out.println("Process " + processId + " is listening on port " + processPorts.get(processId) + "...");
            
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String message = in.readLine();
                    if (message.equals("election")) {
                        System.out.println("Process " + processId + " received 'election' message");
                        handleElection();
                    } else if (message.equals("OK")) {
                        System.out.println("Process " + processId + " received 'OK' from higher ID process");
                    } else if (message.equals("leader")) {
                        System.out.println("Process " + processId + " received 'leader' message");
                        handleLeaderMessage();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Start the election process
    private void startElection() {
        if (electionInProgress) return;
        electionInProgress = true;
        System.out.println("Process " + processId + " is starting the election process.");
        
        // Send election messages to higher ID processes
        for (int i = processId + 1; i <= 3; i++) {
            sendMessage(i, "election");
        }
        
        // Wait for responses
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // If no leader was chosen, this process becomes the leader
        if (leader == null) {
            System.out.println("Process " + processId + " becomes the leader!");
            leader = processId;
            for (int i = 1; i <= 3; i++) {
                sendMessage(i, "leader");
            }
        }
        
        electionInProgress = false;
    }

    // Handle election message
    private void handleElection() {
        if (processId > 1) {
            sendMessage(1, "OK"); // Respond to higher ID process with OK
        }
    }

    // Handle leader message
    private void handleLeaderMessage() {
        leader = processId; // Update leader info when a leader message is received
    }

    // Main function to run the process
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide the process ID (1, 2, or 3) as a command-line argument.");
            return;
        }
        
        processId = Integer.parseInt(args[0]);
        if (processId < 1 || processId > 3) {
            System.out.println("Invalid process ID. Please use 1, 2, or 3.");
            return;
        }
        
        BullyElection election = new BullyElection(processId);
        
        // Start listening in a separate thread for incoming connections
        new Thread(election::handleIncomingConnections).start();
        
        // Start election process
        try {
            Thread.sleep(1000); // Wait for the process to be ready to listen
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        election.startElection();
    }
}

