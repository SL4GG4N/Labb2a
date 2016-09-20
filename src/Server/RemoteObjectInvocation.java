package Server;

import Interface.Agreements;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by Scalman on 20/09/16.
 */
public class RemoteObjectInvocation extends UnicastRemoteObject implements Agreements{

    private AbstractList<Agreements> clients = new ArrayList<>();

    public RemoteObjectInvocation()throws RemoteException{
        super();
    }


    @Override
    public double calcPower(double x, int n) throws RemoteException {
        int absn = Math.abs(n);
        double res = 1;
        for(int i = 0; i < absn; i++) {
            res = res*x;
        }
        return (n >= 0 ? res : 1/res);
    }
}
