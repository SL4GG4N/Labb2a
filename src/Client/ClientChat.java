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

    public void start(Server_Agreement server_chat){

        Scanner input = new Scanner(System.in);
        HeartBeat heartBeat = new HeartBeat(server_chat,this);

        while (running){
            try {

                String data = input.nextLine();
                if (data.equals("/quit"))
                    running = false;

                server_chat.invoke_broadcastMessage(data,this);

            } catch (RemoteException e) {
                System.out.println("client could not invoke server");
                e.printStackTrace();
            }
        }


    }

    private class HeartBeat implements Runnable{

        private Server_Agreement server_chat;
        private ClientChat clientChat;
        public HeartBeat(Server_Agreement server_chat,ClientChat clientChat) {
            this.clientChat = clientChat;
            this.server_chat = server_chat;
            new Thread(this).start();
        }

        @Override
        public void run() {
            boolean running = true;
            while (running) {
                try {
                    Thread.sleep(1000);
                    server_chat.invoke_broadcastMessage("", clientChat);
                } catch (RemoteException e) {
                    //e.printStackTrace();
                    System.out.println("Server is dead plz try another time");
                    running = false;
                    System.exit(1);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.out.println("Cant Sleep, Too much coffee");
                }
            }
        }
    }


}
