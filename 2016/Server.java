import java.io.*;
import java.net.*;

public class Server {
    public Server() {
        int port = 8989;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server socket created on port " + port + ". Waiting for a client...");

            // Wait for a client to connect
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            // Initialize network streams (We do NOT use try-with-resources here so the threads can keep them open)
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            // THREAD 1: Listen for incoming messages from the client
            Thread readThread = new Thread(() -> {
                try {
                    String clientMessage;
                    while ((clientMessage = in.readLine()) != null) {
                        if (clientMessage.equalsIgnoreCase("exit")) {
                            System.out.println("\nClient typed exit. Disconnected.");
                            break;
                        }
                        // Clear the line visually and print the message
                        System.out.println("\nClient: " + clientMessage);
                        System.out.print("Server: ");
                    }
                } catch (IOException e) {
                    System.out.println("\nConnection lost while reading.");
                } finally {
                    cleanUp(clientSocket, in, out, keyboard);
                }
            });

            // THREAD 2: Listen for terminal keyboard input from the server operator
            Thread writeThread = new Thread(() -> {
                try {
                    String serverMessage;
                    System.out.print("Server: ");
                    while ((serverMessage = keyboard.readLine()) != null) {
                        out.println(serverMessage);
                        if (serverMessage.equalsIgnoreCase("exit")) {
                            System.out.println("Server closing chat.");
                            break;
                        }
                        System.out.print("Server: ");
                    }
                } catch (IOException e) {
                    System.out.println("Error reading from server keyboard.");
                } finally {
                    cleanUp(clientSocket, in, out, keyboard);
                }
            });

            // Fire up both engines simultaneously!
            readThread.start();
            writeThread.start();

            // Keep the main thread alive until the chat threads finish
            readThread.join();
            writeThread.join();

        } catch (Exception e) {
            System.out.println("Server system error: " + e.getMessage());
        }
    }

    private void cleanUp(Socket socket, Closeable... streams) {
        try {
            for (Closeable stream : streams) if (stream != null) stream.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException ignored) {}
    }

    public static void main(String[] args) {
        new Server();
    }
}
