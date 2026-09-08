import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class rmiServer extends UnicastRemoteObject implements rmiInterface {
    public rmiServer() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public int sub(int a, int b) throws RemoteException {
        return a - b;
    }

    @Override
    public int mul(int a, int b) throws RemoteException {
        return a * b;
    }

    @Override
    public int div(int a, int b) throws RemoteException {
        if (b == 0) {
            System.out.println("Division by zero is not allowed.");
            return 0;
        }
        return a / b;
    }
}