import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class rmiServerRegistery {
    public static void main(String[] args) throws Exception {
        rmiInterface service = new rmiServer();

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("Calculator service", service);
        System.out.println("RMI server is running...");
    }
}