import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class PowerServiceImpl extends UnicastRemoteObject implements PowerService {

    protected PowerServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public long powerOfTwo(int n) throws RemoteException {
        return (long) Math.pow(2, n);
    }
}

