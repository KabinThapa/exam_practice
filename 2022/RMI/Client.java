import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) {

        try {

            /*
             * Connect to the RMI Registry
             * running on localhost port 1099.
             */
            Registry registry =
                    LocateRegistry.getRegistry(
                            "192.168.1.4",
                            1099
                    );

            /*
             * Look up the remote object by name.
             * What we get back is a stub.
             */
            Calculator calculator =
                    (Calculator) registry.lookup(
                            "CalculatorService"
                    );

            System.out.println(
                    "Connected to Calculator service."
            );


            // These look like local method calls
            // but execute on the server

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


            int product =
                    calculator.multiply(6, 7);

            System.out.println(
                    "6 * 7 = " + product
            );


            double quotient =
                    calculator.divide(22, 7);

            System.out.printf(
                    "22 / 7 = %.4f%n",
                    quotient
            );

        } catch (Exception e) {
            System.out.println(
                    "Client error: " + e.getMessage()
            );
        }
    }
}