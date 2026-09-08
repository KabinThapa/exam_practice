import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args){
        try(Registry registry = LocateRegistry.createRegistry(9090)){
            Calculator calc = new CalculatorImpl();

            registry.rebind("Calculator service",calc);
        }catch(Exception e){
            System.out.println("Error during server handling");
        }
    }
}