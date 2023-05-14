package edu.ufp.inf.sd.rmi.project.client;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ObserverRI extends Remote {
    public void update() throws RemoteException;
    public void passTokenToNextPlayer() throws RemoteException;
    public void playerActionCompleted(GameClient player) throws RemoteException;
}
