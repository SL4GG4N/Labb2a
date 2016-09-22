package Client;

import Interface.Client_Agreement;
import Interface.Server_Agreement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Scalman on 20/09/16.
 */
public class ClientRemoteObjectInvocation extends UnicastRemoteObject implements Client_Agreement {




    public ClientRemoteObjectInvocation(Server_Agreement agr,String user_name) throws RemoteException {
        agr.invoke_addClient(this,user_name);
    }

    @Override
    public void invoke_reciveMessage(String msg) throws RemoteException {

        System.out.println(msg);
    }


}
