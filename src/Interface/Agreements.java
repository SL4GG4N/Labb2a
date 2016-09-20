package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Scalman on 20/09/16.
 */
public interface Agreements extends Remote {

    double calcPower(double x, int n) throws RemoteException;

}