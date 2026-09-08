/*
RMI remote method invocation is a service that allows user to invoke any remote method
implemented in server as if it was running in the local machine. It uses stud and skeleton
to communicate and hide all the complexity of native networking and handles communication
over networking by itself.

Architecture

Client app --> getRegistry-->registry lookup -->stub ==communication over network
Server side --> create registry --> remote object --> skeleton == communication over network
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Services extends Remote{
    public String greet(String name){
        String greet(String name) throws RemoteException;
        int Square(int n) throws RemoteException;
    }
}