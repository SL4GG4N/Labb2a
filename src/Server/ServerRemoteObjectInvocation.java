package Server;

import Interface.Client_Agreement;
import Interface.Server_Agreement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Scalman on 20/09/16.
 */
public class ServerRemoteObjectInvocation extends UnicastRemoteObject implements Server_Agreement {

    private ArrayList<Client_Agreement> clients = new ArrayList<>();

    public ServerRemoteObjectInvocation() throws RemoteException {
        super();
        System.out.println("client handler created");
    }

    @Override
    public void invoke_addClient(Client_Agreement client) throws RemoteException {
        clients.add(client);
    }

    @Override
    public synchronized void invoke_broadcastMessage(String msg, Client_Agreement client) {

        if (msg.length() < 1){
            System.out.println("Client want to send nothing");
            return;
        }
        Iterator iterator = clients.iterator();
        try {
            while (iterator.hasNext()) {
                Client_Agreement ca = (Client_Agreement) iterator.next();
                if (!ca.equals(client))
                    ca.invoke_reciveMessage(msg);
            }
        }catch (RemoteException re){
            System.out.println("Remove client");
            iterator.remove();
        }

    }

    @Override
    public void invoke_clientCommand() throws RemoteException {

    }
}
