package edu.ufp.inf.sd.rmi.project.server;

//import edu.ufp.inf.sd.rmi._04_diglib.server.DigLibSessionRI;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface GameFactoryRI extends Remote {
    boolean register(String username,String pwd) throws RemoteException;
    GameSessionRI login(String username, String pwd)throws RemoteException;
    void removeSession (String u) throws RemoteException;
}
