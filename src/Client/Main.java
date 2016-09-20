package Client;

import Interface.Agreements;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Wheres the Client should be implemented.
 */
public class Main {

    public static void main(String[] args) {

        System.out.printf("Client Started");

        // Get a remote reference to the distributed object from the rmi registry
        Agreements pow = null;
        try {
            pow = (Agreements) Naming.lookup("rmi://5003/power");
            double res, x = 12;
            int n = 3;
            res = pow.calcPower(x, n); //<--- The RMI !
            System.out.println("" + x + "^" + n + " = " + res);

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
}
