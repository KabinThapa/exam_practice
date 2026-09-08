import java.rmi.Registry;
import java.rmi.LocateRegistry;

public class Client{
    public static void main(String[] args){
        try{
            Registry registry = LocateRegistry.getRegistry("localhost",9090);
            Services service = (Services) registry.lookup("Services service");

            System.out.println("Server says:"+service.greet("Kabin"));
            System.out.println("Server says square of number is:"+service.square(5));
        }catch(Exception e){
            System.out.println("Unable to locate the remote server ");
        }
    }
}