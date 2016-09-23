package Client;

import Interface.Client_Agreement;
import Interface.Server_Agreement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * Created by Scalman on 20/09/16.
 */
public class ClientChat extends UnicastRemoteObject implements Client_Agreement {

    private boolean running = true;


    public ClientChat(Server_Agreement agr, String user_name) throws RemoteException {
        agr.invoke_addClient(this,user_name);
    }

    @Override
    public void invoke_reciveMessage(String msg) throws RemoteException {

        System.out.println(msg);
    }

    @Override
    public void invoke_checkIfUserIsAlive() throws RemoteException {
        //System.out.println("Dummy Method");
    }

    @Override
    public void invoke_closeClient(){
        //System.exit(1);

    }

    public void start(Server_Agreement server_chat,ClientChat clientChat){

        Scanner input = new Scanner(System.in);

        while (running){
            try {

                String data = input.nextLine();
                if (data.equals("/quit"))
                    running = false;

                server_chat.invoke_broadcastMessage(data,clientChat);

            } catch (RemoteException e) {
                System.out.println("client could not invoke server");
                e.printStackTrace();
            }
        }

    }


}
