import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Client {

   public static void main(String[] args){
       try(Registry registry = LocateRegistry.getRegistry("localhost",9090)){
           Calculator calculator = (Calculator) registry.lookup("Calculator service");

           int sum =
                   calculator.add(10, 20);

           System.out.println(
                   "10 + 20 = " + sum
           );


           int diff =
                   calculator.subtract(50, 15);

           System.out.println(
                   "50 - 15 = " + diff
           );
       } catch (java.lang.Exception e) {
           throw new RuntimeException(e);
       }
   }
}