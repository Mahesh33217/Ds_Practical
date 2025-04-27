import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EchoService extends Remote {
    // Remote method to append the name to "Hello" and return the result
    String sayHello(String name) throws RemoteException;
}

