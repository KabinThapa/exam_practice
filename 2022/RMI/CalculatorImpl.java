import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class CalculatorImpl
        extends UnicastRemoteObject
        implements Calculator {

    /*
     * Constructor must declare RemoteException
     * because UnicastRemoteObject constructor
     * throws it.
     */
    public CalculatorImpl()
            throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b)
            throws RemoteException {
        System.out.println(
                "Server: add(" + a + ", " + b + ")"
        );
        return a + b;
    }

    @Override
    public int subtract(int a, int b)
            throws RemoteException {
        System.out.println(
                "Server: subtract(" + a + ", " + b + ")"
        );
        return a - b;
    }

    @Override
    public int multiply(int a, int b)
            throws RemoteException {
        System.out.println(
                "Server: multiply(" + a + ", " + b + ")"
        );
        return a * b;
    }

    @Override
    public double divide(int a, int b)
            throws RemoteException {

        if (b == 0) {
            throw new RemoteException(
                    "Cannot divide by zero"
            );
        }

        System.out.println(
                "Server: divide(" + a + ", " + b + ")"
        );

        return (double) a / b;
    }
}