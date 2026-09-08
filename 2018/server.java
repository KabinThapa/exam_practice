import java.net.*;
import java.io.*;

public class server {
    private int port = 9090;

    public server() {
        System.out.println("Server started. Waiting for a client...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            // Keep the server socket listening
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Client connected from: " + clientSocket.getRemoteSocketAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                Thread writeThread = new Thread(() -> {
                    String serverMessage;
                    try {
                        // Print prompt initially
                        System.out.print("Server: ");
                        while ((serverMessage = keyboard.readLine()) != null) {
                            if (serverMessage.equalsIgnoreCase("Exit")) {
                                out.println("Server has exited the chat.");
                                System.out.println("Server exited the chat.");
                                break;
                            }
                            out.println(serverMessage);
                            System.out.print("Server: "); // Reprint prompt for the next line
                        }
                    } catch (IOException e) {
                        System.out.println("\nError while reading system input.");
                    }
                });

                Thread readThread = new Thread(() -> {
                    String clientMessage;
                    try {
                        while ((clientMessage = in.readLine()) != null) {
                            // Clear current line prompt slightly for formatting
                            System.out.print("\rClient: " + clientMessage + "\nServer: ");

                            if (clientMessage.equalsIgnoreCase("Exit") ||
                                    clientMessage.equalsIgnoreCase("Client has exited the chat.")) {
                                System.out.println("\nClient disconnected.");
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("\nConnection lost with the client.");
                    }
                });

                writeThread.start();
                readThread.start();

                // Wait safely for both processing loops to terminate
                try {
                    writeThread.join();
                    readThread.join();
                } catch (InterruptedException ignored) {}

                // Clean up strictly AFTER threads have fully finished executing
                cleanUp(clientSocket, in, out, keyboard);

            } catch (Exception e) {
                System.out.println("Client session error.");
            }
        } catch (Exception e) {
            System.out.println("Could not bind to port " + port);
        }
    }

    public void cleanUp(Socket socket, Closeable... streams) {
        try {
            for (Closeable stream : streams) {
                if (stream != null) stream.close();
            }
            if (socket != null && !socket.isClosed()) socket.close();
            System.out.println("Resources successfully freed.");
        } catch (IOException ignored) {}
    }

    public static void main(String[] args) {
        new server();
    }
}
