import java.net.*;
import java.io.*;

public class UDPServer {
    private DatagramSocket socket;
    private Thread readThread;
    private Thread writeThread;
    private InetAddress add = -1;
    private int port;
    public UDPServer(){
            try(socket = new DatagramSocket(9999)){
                System.out.println("Port instantiated");

                 readThread = new Thread(() -> {
                    byte[] buffer = new byte[1024];
                    try{
                        while(!socket.isClosed()){
                            DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
                            socket.receive(packet);

                            add = packet.getAddress();
                            port = packet.getPort();

                            String clientMessage = new String (packet.getData(),0,packet.getLength().trim());
                            System.out.println("\nClient: "+clientMessage);
                            if (clientMessage.equalsIgnoreCase("Disconnect")) {
                                System.out.println("Client requested disconnect.");
                                break;
                            }
                        }
                    }catch (IOException e){
                        if(!socket.isClosed()){
                            e.printStackTrace();
                        }
                    }
                });

                 writeThread = new Thread(() -> {
                     BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                     try{
                         while(!socket.isClosed()){
                             String message = keyboard.readLine();
                             if(message == null) break;

                             if(add == null || add == -1){
                                 System.out.println("Client not connected yet");
                             }

                             byte[] buffer = new byte[1024];
                             DatagramPacket packet = new DatagramPacket(buffer,buffer.length,add,port);
                             socket.send(packet);
                             if (message.equalsIgnoreCase("Disconnect")) {
                                 break;
                             }
                         }
                     }
                     catch (IOException e) {
                         System.out.println("Write error: " + e.getMessage());
                     }
                 });
            readThread.start();
            writeThread.start();

            try {
                readThread.join();
                writeThread.join();
            } catch (InterruptedException ignored) {}
        }
    }catch (SocketException e) {
        System.out.println("Socket error: " + e.getMessage());
    } finally {
        cleanUp();
    }

    private void cleanUp() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        System.out.println("Server shut down cleanly.");
    }

    public static void main(String[] args) {
        new UDPServer();
    }
}
}