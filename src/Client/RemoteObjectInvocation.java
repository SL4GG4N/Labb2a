package Client;

import Interface.Client_Agreement;
import Interface.Server_Agreement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Scalman on 20/09/16.
 */
public class RemoteObjectInvocation extends UnicastRemoteObject implements Client_Agreement {


    public RemoteObjectInvocation(Server_Agreement agr) throws RemoteException {

        agr.invoke_addClient(this);

    }



    @Override
    public void invoke_reciveMessage(String msg) throws RemoteException {
        System.out.println(msg);
    }
}
