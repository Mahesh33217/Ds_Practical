import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VowelCountService extends Remote {
    int countVowels(String word) throws RemoteException;
}

