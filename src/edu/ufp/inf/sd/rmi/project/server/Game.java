package edu.ufp.inf.sd.rmi.project.server;

import java.io.Serializable;

/**
 *
 * @author rmoreira
 */
public class Game implements Serializable {
    private int id;
    private int num_players;
    private int max_players;
    private String nivel="";

    public Game( int max_players, String nivel) {
        this.max_players = max_players;
        this.nivel = nivel;
        this.num_players=1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_players() {
        return num_players;
    }

    public void setNum_players(int num_players) {
        this.num_players = num_players;
    }

    public int getMax_players() {
        return max_players;
    }

    public void setMax_players(int max_players) {
        this.max_players = max_players;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}