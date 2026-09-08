import java.rmi.RemoteException;
import java.rmi.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator{
    public CalculatorImpl() throws RemoteException{
        super();
    }
    @Override
    public int add(int a, int b) throws RemoteException{
        return (a+b);
    }
    @Override
    public int sub (int a, int b) throws RemoteException{
        return (a-b);
    }
}