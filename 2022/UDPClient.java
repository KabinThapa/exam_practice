import java.net.*;
import java.io.*;

public class UDPClient {
    private DatagramSocket socket;
    private Thread readThread;
    private Thread writeThread;
    private InetAddress serverAddress;
    private int serverPort = 9999;

    public UDPClient() {
        try {
            // Client socket binds to any available ephemeral port
            socket = new DatagramSocket();
            serverAddress = InetAddress.getByName("localhost");
            System.out.println("Client instantiated. Target server: localhost:" + serverPort);

            // Thread for reading incoming messages from the Server
            readThread = new Thread(() -> {
                byte[] buffer = new byte[1024];
                try {
                    while (!socket.isClosed()) {
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        socket.receive(packet);

                        String serverMessage = new String(packet.getData(), 0, packet.getLength()).trim();
                        System.out.println("\nServer: " + serverMessage);

                        if (serverMessage.equalsIgnoreCase("Disconnect")) {
                            System.out.println("Server shut down the chat.");
                            break;
                        }
                    }
                } catch (IOException e) {
                    if (!socket.isClosed()) {
                        System.out.println("Read error: " + e.getMessage());
                    }
                } finally {
                    cleanUp();
                }
            });

            // Thread for reading from keyboard and sending to the Server
            writeThread = new Thread(() -> {
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                try {
                    while (!socket.isClosed()) {
                        System.out.print("Client: ");
                        String message = keyboard.readLine();
                        if (message == null) break;

                        byte[] buffer = message.getBytes();
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
                        socket.send(packet);

                        if (message.equalsIgnoreCase("Disconnect")) {
                            System.out.println("Disconnecting from server...");
                            break;
                        }
                    }
                } catch (IOException e) {
                    if (!socket.isClosed()) {
                        System.out.println("Write error: " + e.getMessage());
                    }
                } finally {
                    cleanUp();
                }
            });

            // Start processing elements concurrently
            readThread.start();
            writeThread.start();

            // Wait for both execution flows to cleanly stop before continuing
            try {
                readThread.join();
                writeThread.join();
            } catch (InterruptedException ignored) {}

        } catch (SocketException | UnknownHostException e) {
            System.out.println("Initialization error: " + e.getMessage());
            cleanUp();
        }
    }

    private void cleanUp() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
            System.out.println("Client shut down cleanly.");
        }
    }

    public static void main(String[] args) {
        new UDPClient();
    }
}
