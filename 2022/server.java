import java.io.*;
import java.net.*;

class Server {
    private Thread readThread;
    private Thread writeThread;

    public Server() {
        int port = 9999;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server instantiated");
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Client instantiated on port:" + port + " with IP address " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                readThread = new Thread(() -> {
                    String clientMessage;
                    try {
                        while ((clientMessage = in.readLine()) != null) {
                            if (clientMessage.equalsIgnoreCase("Disconnected")) {
                                System.out.println("Client: " + clientMessage);
                                break;
                            }
                            System.out.println("Client: " + clientMessage);
                        }
                    } catch (IOException e) {
                        System.out.println("Invalid IO operation");
                    }
                });

                writeThread = new Thread(() -> {
                    try {
                        String message;
                        System.out.println("Server: ");
                        // FIXED: Added != null comparison
                        while ((message = keyboard.readLine()) != null) {
                            if (message.equalsIgnoreCase("Disconnected")) {
                                break;
                            }
                            out.println(message);
                            // FIXED: Corrected spelling to println
                            System.out.println("Server: ");
                        }
                    } catch (IOException e) {
                        System.out.println("Invalid io");
                    }
                });

                readThread.start();
                writeThread.start();

                try {
                    readThread.join();
                    writeThread.join();
                } catch (InterruptedException ignored) {}

                cleanUp(clientSocket, in, out, keyboard);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cleanUp(Socket socket, Closeable... streams) {
        // FIXED: Wrapped in try-catch to handle IOException
        try {
            for (Closeable stream : streams) {
                if (stream != null) stream.close();
            }
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
