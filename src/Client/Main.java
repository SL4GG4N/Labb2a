package Client;

import Interface.Server_Agreement;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Wheres the Client should be implemented.
 */
public class Main {


    private static Server_Agreement server_chat;
    private static ClientChat clientChat;

    public static void main(String[] args) {

        System.out.println("Client Started");
        // Get a remote reference to the distributed object from the rmi registry
        String ip_address = "127.0.0.1";
        Registry reg;
        try {
            reg = LocateRegistry.getRegistry(ip_address,5001);
            server_chat = (Server_Agreement)reg.lookup("server_chat");


            clientChat = new ClientChat(server_chat,ip_address);

            clientChat.start(server_chat,clientChat);

           // System.out.println("hello");
            //reg.unbind("server_chat");
            System.exit(1);
        }catch (NotBoundException e) {
          //  System.out.println("ollon");
            e.printStackTrace();
        }catch (RemoteException e) {
          //  System.out.println("bög");
            e.printStackTrace();
        }finally {
           // System.out.println("kuk");
        }

    }


}
