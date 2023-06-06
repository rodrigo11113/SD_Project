package edu.ufp.inf.sd.rmi.project.server;





import edu.ufp.inf.sd.rmi.project.client.GameClient;
import edu.ufp.inf.sd.rmi.project.client.ObserverRI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class SubjectImpl extends UnicastRemoteObject implements SubjectRI {
    private State subjectState;
    private ArrayList<ObserverRI> observers = new ArrayList<ObserverRI>();
    private int id;
    private String mapa;
    private Game game;
    private ObserverRI tokenholder;
    //private User user;
    public SubjectImpl(int id/*User user*/) throws RemoteException {
        this.id=id;
        //this.user=user;
    }
    public SubjectImpl()throws RemoteException{
        this.subjectState=new State(null,null);

    }

    @Override
    public void attach(ObserverRI observerRI) throws RemoteException {
     this.observers.add(observerRI);
     if(observers.size()==this.game.getMax_players()){
         State state=new State(this.observers.get(0).getId(),"start");
         System.out.println(this.observers.get(0).getId());
         setTokenholder(this.observers.get(0));
         setState(state);

     }

    }

    public ObserverRI getTokenholder() {
        return tokenholder;
    }

    public void setTokenholder(ObserverRI tokenholder) {
        this.tokenholder = tokenholder;
    }

    @Override
    public void detach(ObserverRI observerRI) throws RemoteException {
this.observers.remove(observerRI);
    }

    @Override
    public void notifyObserver(State state) throws RemoteException {
        for (ObserverRI obs:this.observers
        ) {
            System.out.println("     "+obs.getId());
            obs.update();
        }
    }

    @Override
    public State getState() throws RemoteException {
        return subjectState;
    }

    @Override
    public void setState(State state) throws RemoteException {
        if(state.getId().equals(tokenholder.getId())){
this.subjectState=state;
if(state.getInfo().equals("dev4")||state.getInfo().equals("end turn")){
    int currentplayer=checkpostion(tokenholder);
    currentplayer++;
    if (currentplayer>=this.game.getMax_players()) {currentplayer=0;}
    tokenholder=this.observers.get(currentplayer);
}
     notifyObserver(state);}
        System.out.println("Nao e a sua vez");
     //fazer aqui o token
    }
  private int checkpostion(ObserverRI observer) throws RemoteException {
        for (int i=0;i<observers.size();i++){
            if(observers.get(i).getId().equals(observer.getId()))
                return i;
        }
        return 0;
  }
    @Override
    public ArrayList<ObserverRI> getObservers() {
        return observers;
    }
    @Override
    public void setObservers(ArrayList<ObserverRI> observers) {
        this.observers = observers;
    }

    public void setId(int id) {
        this.id = id;
    }
     @Override
    public String getMapa() {
        return mapa;
    }
@Override
    public void setMapa(String mapa) {
        this.mapa = mapa;
    }
    /*
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public State getSubjectState() {
        return subjectState;
    }

    public void setSubjectState(State subjectState) {
        this.subjectState = subjectState;
    }
@Override
    public Game getGame() {
        return game;
    }
@Override
    public void setGame(Game game) {
        this.game = game;
    }
}
