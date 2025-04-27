	import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConversionService extends Remote {
    // Remote method to convert Miles to Kilometers
    double convertMilesToKilometers(double miles) throws RemoteException;
}

