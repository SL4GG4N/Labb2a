package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Scalman on 20/09/16.
 */
public interface Client_Agreement extends Remote {


    void invoke_reciveMessage(String msg)throws RemoteException;
}
