import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {

        try {

            // Create the remote object
            Calculator service =
                    new CalculatorImpl();

            /*
             * Create RMI Registry on port 1099.
             * 1099 is the standard RMI port.
             */
            Registry registry =
                    LocateRegistry.createRegistry(1099);

            /*
             * Bind the object with a name.
             * Client will use this name to look it up.
             */
            registry.rebind(
                    "CalculatorService",
                    service
            );

            System.out.println(
                    "RMI Server started."
            );

            System.out.println(
                    "Calculator service registered."
            );

        } catch (Exception e) {
            System.out.println(
                    "Server error: " + e.getMessage()
            );
        }
    }
}