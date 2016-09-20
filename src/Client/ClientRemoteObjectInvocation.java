package Client;

import Interface.Client_Agreement;
import Interface.Server_Agreement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Scalman on 20/09/16.
 */
public class ClientRemoteObjectInvocation extends UnicastRemoteObject implements Client_Agreement {

    private String user_name;

    public ClientRemoteObjectInvocation(Server_Agreement agr,String user_name) throws RemoteException {

        this.user_name = user_name;
        agr.invoke_addClient(this);
    }

    @Override
    public void invoke_reciveMessage(String msg) throws RemoteException {
        System.out.println(msg);
    }

    @Override
    public void invoke_setUserName(String usr_name) throws RemoteException {
        this.user_name = usr_name;
    }

    @Override
    public void invoke_showCommands() throws RemoteException {
        System.out.println("/who");
        System.out.println("/quit");
        System.out.println("/nick <new nick name>");
        System.out.println("/help");
    }
}
