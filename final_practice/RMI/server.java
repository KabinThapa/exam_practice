import java.rmi.Registry;
import java.rmi.LocateRegistry;

public class server{
    public static void main(String[] args){
        Services s1 = new Services();

        try{
            Registry registry = LocateRegistry.createRegistry(9090);
            registry.rebind("Services service",service);
        }catch (Exception e){
            System.out.println("Unable to create registry");
        }

    }
}