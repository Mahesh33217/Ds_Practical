import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class VowelCountServiceImpl extends UnicastRemoteObject implements VowelCountService {
    
    // Constructor
    public VowelCountServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public int countVowels(String word) throws RemoteException {
        int count = 0;
        for (char c : word.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}

