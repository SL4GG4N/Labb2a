package Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Wheres the Server should be implemented.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Server Started");

        try {
            // Create the distributed object
            Registry bogjavel = LocateRegistry.createRegistry(5001);
            RemoteObjectInvocation rm = new RemoteObjectInvocation();
            // Register the object in the RMI registry
            bogjavel.rebind("power", rm);
            System.out.println("Power initialized");


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
