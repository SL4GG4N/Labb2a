package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Scalman on 20/09/16.
 */
public interface Agreements extends Remote {

    void connect_client(Agreements client)throws RemoteException;
}
