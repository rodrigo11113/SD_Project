package edu.ufp.inf.sd.rmi.project.server;

import edu.ufp.inf.sd.rmi._04_diglib.server.Book;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class simulates a DBMockup for managing users and games.
 *
 * @author rmoreira
 *
 */
public class DBMockup {

    private final ArrayList<User> users;// = new ArrayList();
    private final ArrayList<Game> games;

    /**
     * This constructor creates and inits the database with some books and users.
     */
    public DBMockup() {
        users = new ArrayList<>();
        games = new ArrayList<>();
    }

    /**
     * Registers a new user.
     * 
     * @param u username
     * @param p passwd
     */
    public void register(String u, String p) {
        if (!exists(u, p)) {
            users.add(new User(u, p));
            System.out.println(u+" registado com sucesso!");

        }
    }

    /**
     * Checks the credentials of an user.
     * 
     * @param u username
     * @param p passwd
     * @return
     */
    public boolean exists(String u, String p) {
        for (User usr : this.users) {
            if (usr.getUname().compareTo(u) == 0 && usr.getPword().compareTo(p) == 0) {
                return true;
            }
        }
        return false;
        //return ((u.equalsIgnoreCase("guest") && p.equalsIgnoreCase("ufp")) ? true : false);
    }

    /**
     * Inserts a new game into the DigLib.
     */
    public Game insert(int  max_players, String nivel) throws RemoteException {

       Game game= new Game(max_players, nivel);
        if(games.size()<1){
            game.setId(1);
        }else{
            game.setId(games.size()+1);
        }
        games.add(game);
        return game;
    }
    /**
     * Imprime os jogos existentes
     */
    public ArrayList<Game> printGames() throws RemoteException{
        for (Game game : games)
            System.out.println(game.getId()+game.getNivel());
        return games;
    }
    /**
     * Imprime os jogos disponiveis
     */
    public ArrayList<Game> printAvbGames() throws RemoteException{
        ArrayList<Game> AvbGames=new ArrayList<>();
        for (Game game : games){
            if(game.getMax_players()!=game.getNum_players()){
                AvbGames.add(game);
            }}
        return AvbGames;
    }

    /**
     * seleciona o jogo.
     */
    public Game select(int id) throws RemoteException {
        for (Game game :games){
            if(game.getId()==id){
                if(game.getMax_players()== game.getNum_players()){
                    System.out.println("o jogo ja esta cheio e a decorrer");
                }else

                return game;}
            else{
                System.out.println("Nao existe jogo com o id"+id);
            }
        }
        return null;
    }

}
