/*
i believe we've just done the theory part!!
this code allows continuous read and write so if this is correct, sending hello is not hard
 */


import java.io.*;
import java.net.*;

class server{
    private int port = 9090;
    public Server(){

        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server instantiated at port :"+port);
            try(Socket clientSocekt = serverSocket.accept()){
                System.out.println("Client conected at port:"+port+" with IP address:"+clientSocekt.getInetAddress());
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocekt.getInputStream()));
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter out = new PrintWriter(clientSocekt.getOutputStream(),true));


                Thread readThread = new Thread(()->{
                    String clientMessage;
                    try{
                        while((clientMessage = in.readLine())!=null){
                            if(clientSocekt.equalsIgnoreCase("Disconnect")){
                                System.out.println("Client:"+clientMessage);
                                break;
                            }
                            System.out.println("Client: "+clientMessage);
                        }
                    }catch(IOException e){
                        System.out.println("Error during system IO");
                    }
                });

                Thread writeThread = new Thread(()->{
                    String message;
                    try{
                        System.out.print("Server: ");
                        while((message = keyboard.readLine())!=null){
                            if(message.equalsIgnoreCase("Disconnect")){
                                break;
                            }
                            out.println("Server: "+message);
                            System.out.print("Server: ");
                        }
                    }catch (IOException e){
                        System.out.println("Error during system IO");
                    }
                });

                readThread.start();
                writeThread.start();

                try{
                    readThread.join();
                    writeThread.join();
                }catch(InterruptedException ignored){}

                cleanUp(clientSocekt,in,out,keyboard);
            } catch (java.lang.Exception e) {
                throw new RuntimeException(e);
            }
        } catch (java.lang.Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void cleanUp(Socket socket , Closeable... streams){
        try{
            for (Closeable stream:streams){
                if(stream!=null) stream.close();
            }
            if(socket!=null || !socket.isClosed()) socket.close();
        } catch (java.lang.Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        new server();
    }
}