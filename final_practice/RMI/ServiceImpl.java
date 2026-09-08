import java.rmi.RemoteException;
import java.rmi.UnicastRemoteObject;

class ServiceImpl extends UnicastRemoteObject implements Services{
    public ServiceImpl() throws RemoteException{
        super();
    }
    @Override
    public String greet(String name) throws RemoteException{
        String greeting = "Hello" +name;
        return (greeting);
    }
    @Override
    public int square(int n)throws RemoteException{
        return (n*n);
    }
}