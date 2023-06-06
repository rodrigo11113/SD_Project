/**
 * <p>
 * Title: Projecto SD</p>
 * <p>
 * Description: Projecto apoio aulas SD</p>
 * <p>
 * Copyright: Copyright (c) 2011</p>
 * <p>
 * Company: UFP </p>
 *
 * @author Rui Moreira
 * @version 2.0
 */
package edu.ufp.inf.sd.rabbitmqservices.projeto.chatgui;

import com.rabbitmq.client.BuiltinExchangeType;

import edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.engine.Game;
import edu.ufp.inf.sd.rabbitmqservices.util.RabbitUtils;


import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rjm
 */
public class ObserverGuiClient extends JFrame {

    private Observer observer;
    public String path;
    private int maxplayers;

    /**
     * Creates new form ChatClientFrame
     *
     * @param args
     */
    public ObserverGuiClient(String args[]) {
        try {
            //1. Create edu.ufp.inf.sd.rabbitmqservices.project.game.gui components
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, " After initComponents()...");

            //initObserver(args);
            RabbitUtils.printArgs(args);

            //Read args passed via shell command
            String host=args[0];
            int port=Integer.parseInt(args[1]);
            String exchangeName=args[2];
            String room=args[3];
            String user=args[4];
            String map=args[5];

            if(map.equals("map1")) {
                this.maxplayers = 2;
                this.path = "C:\\Users\\ACER-PC\\IdeaProjects\\SD\\maps\\SmallVs.txt";
            } else if(map.equals("map2")) {
                this.maxplayers = 4;
                this.path = "C:\\Users\\ACER-PC\\IdeaProjects\\SD\\maps\\FourCorners.txt";
            }

            //2. Create the _05_observer object that manages send/receive of messages to/from rabbitmq
            this.observer=new Observer(this, host, port, "guest", "guest", room, user, exchangeName, BuiltinExchangeType.TOPIC, "UTF-8");
            this.observer.sendMessage("ola");


        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, " After initObserver()...");
    }

    public void startGame(Observer observer) {
        //Game game = new Game(this.path,observer);
        PingRunnable p=new PingRunnable(this.path,observer);
        new Thread(p).start();
    }
   public void check() throws IOException {
       //System.out.println(this.observer.oserveres);
if(this.observer.observers.size()==this.maxplayers){
            this.observer.sendMessage("Start");}

   }
   public boolean verificatoken(int tokenholder){
        if(tokenholder==this.observer.observers.size()){
            return true;
        }
        return false;
   }
   public int changetoken(int tokenholder){
        if(tokenholder==this.maxplayers){
            return 1;
        }
        else return tokenholder+1;
   }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new ObserverGuiClient(args);
    }}
