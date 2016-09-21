package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Scalman on 20/09/16.
 */
public interface Client_Agreement extends Remote {


    void invoke_reciveMessage(String msg)throws RemoteException;
    void invoke_setUserName(String usr_name)throws RemoteException;
    String invoke_getUserName()throws RemoteException;

}
