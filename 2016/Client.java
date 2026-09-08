import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String host = "localhost"; // Change this to your Server's IP address for multi-laptop testing
        int port = 8989;

        try {
            Socket clientSocket = new Socket(host, port);
            System.out.println("Connection to socket is successful! Start typing below.");

            // Initialize streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            // THREAD 1: Listen for incoming messages from the Server
            Thread readThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        if (serverMessage.equalsIgnoreCase("exit")) {
                            System.out.println("\nServer typed exit. Disconnected.");
                            break;
                        }
                        System.out.println("\nServer: " + serverMessage);
                        System.out.print("Client: ");
                    }
                } catch (IOException e) {
                    System.out.println("\nConnection lost while reading.");
                } finally {
                    cleanUp(clientSocket, in, out, keyboard);
                }
            });

            // THREAD 2: Listen for terminal keyboard input from the client operator
            Thread writeThread = new Thread(() -> {
                try {
                    String clientMessage;
                    System.out.print("Client: ");
                    while ((clientMessage = keyboard.readLine()) != null) {
                        out.println(clientMessage);
                        if (clientMessage.equalsIgnoreCase("exit")) {
                            System.out.println("Client closing chat.");
                            break;
                        }
                        System.out.print("Client: ");
                    }
                } catch (IOException e) {
                    System.out.println("Error reading from client keyboard.");
                } finally {
                    cleanUp(clientSocket, in, out, keyboard);
                }
            });

            // Fire up both engines simultaneously!
            readThread.start();
            writeThread.start();

            // Keep the execution frame alive until threads exit
            readThread.join();
            writeThread.join();

        } catch (Exception e) {
            System.out.println("Client network error: " + e.getMessage());
        }
    }

    private static void cleanUp(Socket socket, Closeable... streams) {
        try {
            for (Closeable stream : streams) if (stream != null) stream.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException ignored) {}
    }
}
