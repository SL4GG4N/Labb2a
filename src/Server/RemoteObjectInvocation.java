package Server;

import Interface.Agreements;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by Scalman on 20/09/16.
 */
public class RemoteObjectInvocation extends UnicastRemoteObject implements Agreements{

    private AbstractList<Agreements> clients = new ArrayList<>();

    public RemoteObjectInvocation()throws RemoteException{
        super();
    }

    @Override
    public void connect_client(Agreements client) throws RemoteException {
        clients.add(client);
    }


}
