package edu.ufp.inf.sd.rabbitmqservices.projeto.chatgui;



import edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.engine.Game;

import java.io.IOException;
import java.rmi.RemoteException;

public class PingRunnable implements Runnable{


    private String  mapa;
    private Observer observer;
    public PingRunnable(String mapa, Observer observer) {
        this.mapa = mapa;
        this.observer=observer;
    }

    @Override
    public void run() {
        Game game= null;
            game = new Game(this.mapa,this.observer);

    }
}
