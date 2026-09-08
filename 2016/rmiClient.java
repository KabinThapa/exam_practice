import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class rmiClient {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("192.168.1.4", 1099);
        rmiInterface calculator = (rmiInterface) registry.lookup("Calculator service");

        int result = calculator.add(5, 3);
        System.out.println("Result of 5+3: " + result);

        result = calculator.sub(5, 3);
        System.out.println("Result of 5-3: " + result);

        result = calculator.mul(5, 3);
        System.out.println("Result of 5*3: " + result);

        result = calculator.div(10, 2);
        System.out.println("Result of 10/2: " + result);

        result = calculator.div(10, 0);
        System.out.println("Division by zero result: " + result);
    }
}