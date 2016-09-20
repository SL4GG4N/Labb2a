package Server;

import java.rmi.Naming;

/**
 * Wheres the Server should be implemented.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Server Started");

        try {
            // Create the distributed object
            RemoteObjectInvocation rm = new RemoteObjectInvocation();
            // Register the object in the RMI registry
            Naming.bind("power", rm);
            System.out.println("Power initialized");


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
