package Server;

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
            Registry connect = LocateRegistry.createRegistry(5001);
            ServerChat rm = new ServerChat();
            // Register the object in the RMI registry
            connect.rebind("server_chat", rm);

        }catch (Exception e){
            System.out.println("CRASH tack vare dig");
            e.printStackTrace();
        }
    }
}
