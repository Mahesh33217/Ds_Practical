import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class FactorialServiceImpl extends UnicastRemoteObject implements FactorialService {

    // Constructor
    public FactorialServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public long calculateFactorial(int n) throws RemoteException {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

