package edu.ufp.inf.sd.rmi.project.client;

import edu.ufp.inf.sd.rmi._03_pingpong.client.PongRi;
import edu.ufp.inf.sd.rmi._03_pingpong.server.Ball;
import edu.ufp.inf.sd.rmi.project.client.jogo.engine.Game;
import edu.ufp.inf.sd.rmi.project.server.SubjectRI;

import java.rmi.RemoteException;

public class PingRunnable implements Runnable{


    private SubjectRI subjectRI;
    private ObserverRI observerRI;
    public PingRunnable(SubjectRI subjectRI,ObserverRI observerRI) {
        this.subjectRI = subjectRI;
        this.observerRI=observerRI;
    }

    @Override
    public void run() {
        Game game= null;
        try {
            game = new Game(subjectRI.getMapa(),subjectRI,observerRI);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            game.begin(subjectRI.getMapa());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
