import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PowerService extends Remote {
    // Method to calculate 2^n
    int calculatePowerOfTwo(int n) throws RemoteException;
}

