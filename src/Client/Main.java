package Client;

import Interface.Server_Agreement;
import com.sun.corba.se.spi.activation.Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Wheres the Client should be implemented.
 */
public class Main {


    private static Server_Agreement agr;
    private static ClientRemoteObjectInvocation ROI;

    public static void main(String[] args) {

        System.out.println("Client Started");
        // Get a remote reference to the distributed object from the rmi registry
        String ip_address = "127.0.0.1";

        try {
            Registry reg = LocateRegistry.getRegistry(ip_address,5001);
            agr = (Server_Agreement)reg.lookup("server_chat");


            ROI = new ClientRemoteObjectInvocation(agr,ip_address);

            start();

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

    private static void start(){

        Scanner input = new Scanner(System.in);

        while (true){

            try {
                String data = input.nextLine();
                agr.invoke_broadcastMessage(data,ROI);
            } catch (RemoteException e) {
                System.out.println("client could not invoke server");
                e.printStackTrace();
            }
        }

    }
}
