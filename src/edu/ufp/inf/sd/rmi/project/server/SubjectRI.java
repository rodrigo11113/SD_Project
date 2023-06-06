package edu.ufp.inf.sd.rmi.project.server;



import edu.ufp.inf.sd.rmi.project.client.GameClient;
import edu.ufp.inf.sd.rmi.project.client.ObserverRI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface SubjectRI extends Remote {
    public void attach(ObserverRI observerRI) throws RemoteException;
    public void detach(ObserverRI observerRI) throws RemoteException;
    void notifyObserver(State state) throws RemoteException;
    public State getState() throws RemoteException;
    public void setState(State state) throws RemoteException;
    public ArrayList<ObserverRI> getObservers() throws RemoteException;
    public void setObservers(ArrayList<ObserverRI> observers) throws RemoteException;
    public String getMapa() throws RemoteException;
    public void setMapa(String mapa) throws RemoteException;
    public Game getGame() throws RemoteException;
    public void setGame(Game game) throws RemoteException;
}
