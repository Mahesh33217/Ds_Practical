import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConversionService extends Remote {
    // Remote method to convert Celsius to Fahrenheit
    double convertToFahrenheit(double celsius) throws RemoteException;
}

