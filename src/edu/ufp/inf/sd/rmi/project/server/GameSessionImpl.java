package edu.ufp.inf.sd.rmi.project.server;


import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class GameSessionImpl extends UnicastRemoteObject implements GameSessionRI {
    GameFactoryImpl gameFactoryRI;
     String username;
     ArrayList<Game> gamesArray;
     String token;
    public GameSessionImpl(GameFactoryImpl gameFactoryimpl, String username) throws RemoteException {
        super();
        this.gameFactoryRI=gameFactoryimpl;
        this.username=username;
    }


    @Override
    public Game criar_jogo( String nivel, int max_players, String token) throws RemoteException {

            //SubjectRI subjectRI = new SubjectImpl();
            Game game = gameFactoryRI.getDb().insert(max_players, nivel);

            //game.getFroggerRI().attach(observerRI);

            System.out.println("Jogo criado com sucesso!");
            return game;

    }

    @Override
    public Game escolher_jogo(int idG, String token) throws RemoteException {
        Game game = gameFactoryRI.getDb().select(idG);
        game.setNum_players(game.getNum_players()+1);
        //game.getFroggerRI().attach(observerRI);
        return game;
    }

    @Override
    public ArrayList<Game> listAllGames() throws RemoteException {
        return this.gameFactoryRI.getDb().printGames();
    }
    @Override
    public ArrayList<Game> listAvbGames() throws RemoteException {
        return this.gameFactoryRI.getDb().printAvbGames();
    }

    @Override
    public String getToken() throws RemoteException {
        return token;
    }

    @Override
    public void setToken(String token) throws RemoteException {
       this.token=token;
    }

    @Override
    public void logout() throws RemoteException {
        this.gameFactoryRI.removeSession(this.username);
    }
}
