import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FactorialService extends Remote {
    long calculateFactorial(int n) throws RemoteException;
}

