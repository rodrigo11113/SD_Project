package edu.ufp.inf.sd.rmi.project.server;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import edu.ufp.inf.sd.rmi.project.client.ObserverRI;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class GameSessionImpl extends UnicastRemoteObject implements GameSessionRI {
    GameFactoryImpl gameFactoryRI;
     String username;
     String token;
    public GameSessionImpl(GameFactoryImpl gameFactoryimpl, String username) throws RemoteException {
        super();
        this.gameFactoryRI=gameFactoryimpl;
        this.username=username;
    }



    @Override
    public Game criar_jogo(String nivel, int max_players, String token, ObserverRI observerRI) throws RemoteException {

          if(GameServer.verificaToken(token,this.username)){
            SubjectRI subjectRI = new SubjectImpl();
            Game game = gameFactoryRI.getDb().insert(max_players, nivel,subjectRI);

            subjectRI.setGame(game);
            game.getSubjectRI().attach(observerRI);

            System.out.println("Jogo criado com sucesso!");
            return game;
        }
        else{
            System.out.println("Token errado!");
            return null;
        }


    }

    @Override
    public Game escolher_jogo(int idG, String token,ObserverRI observerRI) throws RemoteException {
          if(GameServer.verificaToken(token,this.username)){
            Game game = gameFactoryRI.getDb().select(idG);
        System.out.println("jogo-"+game.getId());
            game.setNum_players(game.getNum_players()+1);

            //game.getSubjectRI().attach(observerRI);
            return game;        }
        else{
            System.out.println("Token errado!");
            return null;
        }

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
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
}
