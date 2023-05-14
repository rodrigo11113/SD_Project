package edu.ufp.inf.sd.rmi.project.server;

import edu.ufp.inf.sd.rmi._01_helloworld.server.HelloWorldRI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GameImpl extends UnicastRemoteObject implements HelloWorldRI {


    public GameImpl() throws RemoteException {
        super();
    }

    @Override
    public void print(String msg) throws RemoteException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "someone called me with msg = {0}", new Object[]{msg});
    }
}
