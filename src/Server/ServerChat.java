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
public class ServerChat extends UnicastRemoteObject implements Server_Agreement {

    private ArrayList<ClientInstance> clients = new ArrayList<>();

    public ServerChat() throws RemoteException {
        super();
        System.out.println("client handler created");
    }

    @Override
    public void invoke_addClient(Client_Agreement client, String user_name) throws RemoteException {
        clients.add(new ClientInstance(client, user_name));
        client.invoke_reciveMessage("Welcome");
    }

    @Override
    public synchronized void invoke_broadcastMessage(String msg, Client_Agreement client) {

        StringBuilder sb = new StringBuilder();

        if (msg.length() < 1) {
            //System.out.println("Client: Send nothing");
            return;
        } else if (msg.contains("/nick")) {
            for (int i = 6; i < msg.length(); i++)
                sb.append(msg.charAt(i));
            msg = "/nick";
            System.out.println(sb.toString());
        }
        checkConnections();

        switch (msg) {
            case "/help":
                System.out.println("Client inside /help");
                sb.append("/who\n/nick <new nick name>\n/quit\n/help");
                echoMessage(client, sb);
                break;
            case "/quit":
                sendToAll("Im leaving", client);

                break;
            case "/nick":
                System.out.println("Client inside /nick");
                for (ClientInstance c : clients)
                    if (c.client.equals(client)) {
                        c.user_name = sb.toString();
                        System.out.println("c: " + c.user_name);
                    }
                break;
            case "/who":
                echoConnections(client, sb);
               // closeClient(client);
                break;
            default:
                sendToAll(msg, client);
        }

    }

    private void checkConnections() {

        Iterator iterator = clients.iterator();

        while (iterator.hasNext()) {
            ClientInstance ca = (ClientInstance) iterator.next();
            try {
                ca.client.invoke_checkIfUserIsAlive();
            } catch (RemoteException e) {
                iterator.remove();
                System.out.println("Dead Client");
                //e.printStackTrace();
            }
        }
    }

    private void echoConnections(Client_Agreement client, StringBuilder sb) {

        Iterator iterator = clients.iterator();

        while (iterator.hasNext()) {
            ClientInstance ca = (ClientInstance) iterator.next();
            if (!ca.client.equals(client))
                sb.append(ca.user_name + "\n");
        }

        echoMessage(client, sb);
    }

    private void echoMessage(Client_Agreement client, StringBuilder sb) {

        try {
            client.invoke_reciveMessage(sb.toString());
        } catch (RemoteException re) {
            System.out.println("Client has disconnected");
        }
    }

    private void sendToAll(String msg, Client_Agreement client) {

        Iterator iterator = clients.iterator();
        String name = "";
        try {
            for (ClientInstance c : clients)
                if (c.client.equals(client))
                    name = c.user_name;


            while (iterator.hasNext()) {
                ClientInstance ca = (ClientInstance) iterator.next();
                if (!ca.client.equals(client))
                    ca.client.invoke_reciveMessage(name + ": " + msg);
            }
        } catch (RemoteException re) {
            System.out.println("Remove client");
            iterator.remove();
        }

    }

}
