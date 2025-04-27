import java.net.*;
import java.util.*;
import java.io.*;

public class TimeServer {
    private static final int PORT = 12345;
    private static final int BUFFER_SIZE = 1024;
    private static List<ClientData> clients = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(PORT);
        System.out.println("Time server started on port " + PORT);

        // Start the thread to broadcast time adjustments
        new Thread(() -> broadcastTimeAdjustment(socket)).start();

        while (true) {
            // Receive data from clients
            byte[] receiveData = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            long clientTime = Long.parseLong(clientMessage.trim());
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            
            System.out.println("Received time from client: " + clientTime);
            
            // Add the client data to the list
            clients.add(new ClientData(clientTime, clientAddress, clientPort));
        }
    }

    private static void broadcastTimeAdjustment(DatagramSocket socket) {
        while (true) {
            if (!clients.isEmpty()) {
                // Calculate the average time from all clients
                long sum = 0;
                for (ClientData client : clients) {
                    sum += client.getTime();
                }
                long avgTime = sum / clients.size();
                System.out.println("Average time calculated: " + avgTime);

                // Send time adjustments to each client
                for (ClientData client : clients) {
                    // Calculate time difference between server time and client's time
                    long timeDifference = avgTime - client.getTime();
                    String message = String.valueOf(timeDifference);
                    byte[] sendData = message.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, client.getAddress(), client.getPort());
                    try {
                        socket.send(sendPacket);
                        System.out.println("Sent time adjustment to client: " + timeDifference);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(10000);  // Periodically adjust every 10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ClientData {
    private long time;
    private InetAddress address;
    private int port;

    public ClientData(long time, InetAddress address, int port) {
        this.time = time;
        this.address = address;
        this.port = port;
    }

    public long getTime() {
        return time;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}

