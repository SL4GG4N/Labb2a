package Server;

import Interface.Client_Agreement;
import Interface.Server_Agreement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by Scalman on 20/09/16.
 */
public class ServerRemoteObjectInvocation extends UnicastRemoteObject implements Server_Agreement {

    private AbstractList<Client_Agreement> clients = new ArrayList<>();

    public ServerRemoteObjectInvocation()throws RemoteException{
        super();
    }

    @Override
    public void invoke_addClient(Client_Agreement client) throws RemoteException {
        clients.add(client);
    }

    @Override
    public void invoke_broadcastMessage(String msg) throws RemoteException {

        for (Client_Agreement sa: clients){
            sa.invoke_reciveMessage(msg);
        }
    }

    @Override
    public void invoke_clientCommand() throws RemoteException {

    }
}
