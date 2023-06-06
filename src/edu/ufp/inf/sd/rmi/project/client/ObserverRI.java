package edu.ufp.inf.sd.rmi.project.client;

import edu.ufp.inf.sd.rmi.project.server.State;
import edu.ufp.inf.sd.rmi.project.server.SubjectRI;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ObserverRI extends Remote {

    public void update() throws RemoteException;
    public String getId() throws RemoteException;
    public void setId(String id) throws RemoteException;
    public void setLastObserverState(State lastObserverState) throws RemoteException;
    public SubjectRI getSubjectRI() throws RemoteException;
    public void setSubjectRI(SubjectRI subjectRI) throws RemoteException;
    public State getLastObserverState() throws RemoteException;
}
