import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {

    /*
     * Every remote method must declare
     * RemoteException because network
     * failures can occur at any time.
     */

    int add(int a, int b)
            throws RemoteException;

    int subtract(int a, int b)
            throws RemoteException;

    int multiply(int a, int b)
            throws RemoteException;

    double divide(int a, int b)
            throws RemoteException;
}