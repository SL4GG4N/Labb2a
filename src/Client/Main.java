package Client;

import Interface.Agreements;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Wheres the Client should be implemented.
 */
public class Main {

    public static void main(String[] args) {

        System.out.printf("Client Started");

        // Get a remote reference to the distributed object from the rmi registry

        try {
            Registry pow = LocateRegistry.getRegistry("127.0.0.1",5001);
            Agreements agr = (Agreements)pow.lookup("power");

            double res, x = 2;
            int n = 2;
            res = agr.calcPower(x, n); //<--- The RMI !
            System.out.println("" + x + "^" + n + " = " + res);

        }catch (NotBoundException e) {
            e.printStackTrace();
        }catch (RemoteException e) {
            e.printStackTrace();
        }


    }
}
