import java.io.*;
import java.net.*;

public class chatClient {
    private int port = 8787;
    private String ip = "";

    public chatClient(){
        try(Socket clientSocket = new Socket(ip,port)){
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);

            Thread readMessage = new Thread(()->{
                try{
                    String serverMessage ;
                    while((serverMessage = in.readLine())!=null){
                        if(serverMessage.equalsIgnoreCase("exit")) break;
                        System.out.println(serverMessage);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    cleanUp(clientSocket,in,out,keyboard);
                }
            });

            Thread sendMessage = new Thread(() -> {
                try{
                    String clientMessage;
                    while((clientMessage = keyboard.readLine()) != null){
                        if(clientMessage.equalsIgnoreCase("Exit")) break;
                        out.println("Client: "+clientMessage);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    cleanUp(clientSocket,in,out,keyboard);
                }
            });

            readMessage.start();
            sendMessage.start();
            try{
                readMessage.join();
                sendMessage.join();
            }catch (InterruptedException ignored){};
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void cleanUp(Socket socket, Closeable... streams){
        try{
            for(Closeable stream:streams){
                if(stream != null) stream.close();
            }
            if(socket != null && !socket.isClosed()) socket.close();
        }catch(IOException ignored){};
    }

    public static void main(String[] args){
        new chatClient();
    }
}