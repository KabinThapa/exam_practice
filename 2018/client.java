import java.io.*;
import java.net.*;

public class client {
    private int port = 9090;
    private String host = "localhost"; // FIXED: Set to localhost so it can find the server

    public client() {
        // Try-with-resources automatically closes the main socket when execution finishes
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to the server!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Thread readThread = new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = in.readLine()) != null) {
                        // Print the server's message cleanly
                        System.out.print("\rServer: " + serverMessage + "\nClient: ");

                        if (serverMessage.equalsIgnoreCase("Exit") ||
                                serverMessage.equalsIgnoreCase("Server has exited the chat.")) {
                            System.out.println("\nServer disconnected.");
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("\nLost connection to the server.");
                }
            });

            Thread writeThread = new Thread(() -> {
                String clientMessage; // FIXED: Used properly below
                try {
                    System.out.print("Client: ");
                    while ((clientMessage = keyboard.readLine()) != null) { // FIXED: Variable name updated
                        if (clientMessage.equalsIgnoreCase("Exit")) {
                            out.println("Client has exited the chat.");
                            System.out.println("Client exited the chat.");
                            break;
                        }
                        out.println(clientMessage);
                        System.out.print("Client: ");
                    }
                } catch (IOException e) {
                    System.out.println("\nError while reading system input.");
                } // FIXED: Added missing closing brace for try-catch
            }); // FIXED: Cleaned up thread syntax closure

            // Start both operations concurrently
            readThread.start();
            writeThread.start();

            // Block the main thread until background operations finish
            try {
                readThread.join();
                writeThread.join();
            } catch (InterruptedException ignored) {}

            // Clean up resources strictly after threads finish executing
            cleanUp(socket, in, out, keyboard); // FIXED: Changed clientSocket to socket

        } catch (UnknownHostException e) {
            System.out.println("Server host could not be resolved.");
        } catch (IOException e) {
            System.out.println("Could not establish a connection to the server. Is it running?");
        }
    }

    // FIXED: Added missing cleanUp method structure
    public void cleanUp(Socket socket, Closeable... streams) {
        try {
            for (Closeable stream : streams) {
                if (stream != null) stream.close();
            }
            if (socket != null && !socket.isClosed()) socket.close();
            System.out.println("Client resources successfully cleaned up.");
        } catch (IOException ignored) {}
    }

    public static void main(String[] args) {
        new client();
    }
}
