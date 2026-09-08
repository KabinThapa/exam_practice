import java.net.*;
import java.io.*;

public class chatServer {
    private int port = 8787;

    public chatServer(){
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server socket created. Listening in port: "+port);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected on port: "+port+" with IP address+ "+clientSocket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);

            Thread readThread = new Thread(() ->{
                try{
                    String clientMessage;
                    while((clientMessage = in.readLine())!=null){
                        if (clientMessage.equalsIgnoreCase("exit")){
                            break;
                        }
                        System.out.println(clientMessage);
                    }
                }catch (Exception e){
                    System.out.println("Unable to resolve client input:");
                    e.printStackTrace();
                }finally{
                    cleanUp(clientSocket,in,out,keyboard);
                }
            });

            Thread writeThread = new Thread(() -> {
               String sendMessage;
               try{
                   while((sendMessage = keyboard.readLine()) != null ){
                       if (sendMessage.equalsIgnoreCase("Exit")){
                           break;
                       }
                       out.println("Server: "+sendMessage);
                   }
               }catch (Exception e){
                   System.out.println("Cannot resolve sending message");
                   e.printStackTrace();
               }finally {
                   cleanUp(clientSocket,in,out,keyboard);
               }
            });

            readThread.start();
            writeThread.start();
            try{
                readThread.join();
                writeThread.join();
            }catch(InterruptedException ignored){}
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void cleanUp(Socket socket, Closeable... streams){
        try{
            for (Closeable stream : streams){
                if(stream != null) stream.close();
            }
            if(socket != null && !socket.isClosed()) socket.close();
        }catch (IOException ignored){}
    }

    public static void main(String[] args) { chatServer server = new chatServer();}
}