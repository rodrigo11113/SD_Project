package edu.ufp.inf.sd.rmi.project.client;






import edu.ufp.inf.sd.rmi.project.client.jogo.engine.Game;
import edu.ufp.inf.sd.rmi.project.server.State;
import edu.ufp.inf.sd.rmi.project.server.SubjectRI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ObserverImpl extends UnicastRemoteObject implements ObserverRI {
private String id;
private State LastObserverState;
protected SubjectRI subjectRI;
//private Game advanceWarsGame;


    public ObserverImpl() throws RemoteException {
        super();
    }

    public ObserverImpl(String username) throws RemoteException {
        super();
        this.id=username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastObserverState(State lastObserverState) {
        LastObserverState = lastObserverState;
    }

    public SubjectRI getSubjectRI() {
        return subjectRI;
    }

    public void setSubjectRI(SubjectRI subjectRI) {
        this.subjectRI = subjectRI;
    }

    public State getLastObserverState() {
        return LastObserverState;
    }

    @Override
    public void update() throws RemoteException {
        this.LastObserverState= subjectRI.getState();
    }
    @Override
    public void passTokenToNextPlayer() throws RemoteException {
      /*  int currentPlayerIndex = edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.buildings.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.players.indexOf(currentTokenHolder);
        int nextPlayerIndex = (currentPlayerIndex + 1) % edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.buildings.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.players.size();
        currentTokenHolder = edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.buildings.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.players.get(nextPlayerIndex);*/
    }
    @Override
    public void playerActionCompleted(GameClient player) throws RemoteException {
       /* if (player.equals(currentTokenHolder)) {
            // Atualize o estado do jogo com base na ação

            passTokenToNextPlayer();
            notifyObservers(gameState);
        }*/
    }

}
