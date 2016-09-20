package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Scalman on 20/09/16.
 */
public interface Server_Agreement extends Remote {

    void invoke_addClient(Client_Agreement client) throws RemoteException;
    void invoke_broadcastMessage(String msg) throws RemoteException;
    void invoke_clientCommand()throws RemoteException;

}