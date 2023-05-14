package edu.ufp.inf.sd.rmi.project.server;





import edu.ufp.inf.sd.rmi.project.client.ObserverRI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class SubjectImpl extends UnicastRemoteObject implements SubjectRI {
    private State subjectState;
    private ArrayList<ObserverRI> observers = new ArrayList<ObserverRI>();
    private int id;
    private User user;
    public SubjectImpl(int id,User user) throws RemoteException {
        this.id=id;
        this.user=user;
    }

    @Override
    public void attach(ObserverRI observerRI) throws RemoteException {
     this.observers.add(observerRI);
    }

    @Override
    public void detach(ObserverRI observerRI) throws RemoteException {
this.observers.remove(observerRI);
    }

    @Override
    public void notifyObserver(State state) throws RemoteException {
        for (ObserverRI obs:observers
        ) {
            obs.update();
        }
    }

    @Override
    public State getState() throws RemoteException {
        return subjectState;
    }

    @Override
    public void setState(State state) throws RemoteException {
this.subjectState=state;
     notifyObserver(state);
    }

    public ArrayList<ObserverRI> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<ObserverRI> observers) {
        this.observers = observers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
