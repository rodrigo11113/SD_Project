package edu.ufp.inf.sd.rmi.project.server;



import edu.ufp.inf.sd.rmi.project.client.ObserverRI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface GameSessionRI extends Remote {
    public Game criar_jogo(String nivel, int max_players, String token, ObserverRI observerRI) throws RemoteException;
    Game escolher_jogo(int idG, String token,ObserverRI observerRI) throws RemoteException;
    public ArrayList<Game> listAllGames() throws RemoteException;
    public ArrayList<Game> listAvbGames() throws RemoteException;
    String getToken() throws RemoteException;
    void setToken(String token) throws RemoteException;
    String getUsername() throws RemoteException;
    void setUsername(String username) throws RemoteException;
    void logout()throws RemoteException;
}
