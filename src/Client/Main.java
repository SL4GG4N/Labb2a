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


    private static Server_Agreement agr;
    public static void main(String[] args) {

        System.out.printf("Client Started");
        // Get a remote reference to the distributed object from the rmi registry

        try {
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",5001);
            agr = (Server_Agreement)reg.lookup("server_chat");

            RemoteObjectInvocation ROI = new RemoteObjectInvocation(agr);

            start();

            /*agr.invoke_addClient(this);

            double res, x = 3;
            int n = 2;
            res = agr.calcPower(x, n); //<--- The RMI !
            System.out.println("" + x + "^" + n + " = " + res);*/

        }catch (NotBoundException e) {
            e.printStackTrace();
        }catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static void start(){

        Scanner input = new Scanner(System.in);

        while (true){

            try {
                String data = input.nextLine();
                agr.invoke_broadcastMessage(data);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }
}
