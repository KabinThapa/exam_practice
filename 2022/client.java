import java.io.*;
import java.net.*;

class client {
    // FIXED: Added missing parentheses () for the constructor
    public client() {
        int port = 9999;
        String ip = "192.168.1.4";

        try (Socket clientSocket = new Socket(ip, port)) {
            // FIXED: Changed 'in.getInputStream' to 'clientSocket.getInputStream()'
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            Thread readThread = new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = in.readLine()) != null) {
                        if (serverMessage.equalsIgnoreCase("Disconnect")) {
                            System.out.println("Server: " + serverMessage);
                            break;
                        }
                        System.out.println("Server: " + serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Error while resolving IO operation");
                }
            });

            Thread writeThread = new Thread(() -> {
                String message;
                try {
                    System.out.println("Client: ");
                    while ((message = keyboard.readLine()) != null) {
                        if (message.equalsIgnoreCase("Disconnect")) {
                            out.println(message);
                            break;
                        }
                        out.println(message);
                        System.out.println("Client:");
                    }
                } catch (IOException e) {
                    System.out.println("Error while resolving IO operation");
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
    } // FIXED: Removed the stray extra closing brace that was here

    public void cleanUp(Socket socket, Closeable... streams) {
        // FIXED: Wrapped in try-catch and changed socket.close() to stream.close()
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
        new client();
    }
}
