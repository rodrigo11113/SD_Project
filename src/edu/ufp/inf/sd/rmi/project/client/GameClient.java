package edu.ufp.inf.sd.rmi.project.client;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import edu.ufp.inf.sd.rmi.project.server.*;
import edu.ufp.inf.sd.rmi.util.rmisetup.SetupContextRMI;

import java.io.UnsupportedEncodingException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.exit;

/**
 * <p>
 * Title: Projecto SD</p>
 * <p>
 * Description: Projecto apoio aulas SD</p>
 * <p>
 * Copyright: Copyright (c) 2017</p>
 * <p>
 * Company: UFP </p>
 *
 * @author Rui S. Moreira
 * @version 3.0
 */
public class GameClient {

    /**
     * Context for connecting a RMI client MAIL_TO_ADDR a RMI Servant
     */
    private SetupContextRMI contextRMI;
    /**
     * Remote interface that will hold the Servant proxy
     */
    private GameFactoryRI gameFactoryRI;

    private GameFactoryImpl gameFactory;

    //private ObserverRI observerRI;
    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Escolha uma opção: ");
    }
    private static String[] options = {"1- Criar um jogo.",
            "2- Juntar-me a um jogo já criado.",
            "3- Sair",
    };
    private static String[] options_login = {"1-registar.",
            "2-login.",
            "3- Sair",
    };
    private static String[] options_game={"Esperando que os outros jogadores se conectem ",
              "1-Sair do jogo"};

    public static void main(String[] args) {
        if (args != null && args.length < 2) {
            System.err.println("usage: java [options] edu.ufp.sd.inf.rmi._01_helloworld.server.HelloWorldClient <rmi_registry_ip> <rmi_registry_port> <service_name>");
            System.exit(-1);
        } else {
            //1. ============ Setup client RMI context ============
            GameClient hwc=new GameClient(args);
            //2. ============ Lookup service ============
            hwc.lookupService();
            //3. ============ Play with service ============
            hwc.playService();
        }
    }

    public GameClient(String args[]) {
        try {
            //List ans set args
            SetupContextRMI.printArgs(this.getClass().getName(), args);
            String registryIP = args[0];
            String registryPort = args[1];
            String serviceName = args[2];
            //Create a context for RMI setup
            contextRMI = new SetupContextRMI(this.getClass(), registryIP, registryPort, new String[]{serviceName});
        } catch (RemoteException e) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private Remote lookupService() {
        try {
            //Get proxy MAIL_TO_ADDR rmiregistry
            Registry registry = contextRMI.getRegistry();
            //Lookup service on rmiregistry and wait for calls
            if (registry != null) {
                //Get service url (including servicename)
                String serviceUrl = contextRMI.getServicesUrl(0);
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "going MAIL_TO_ADDR lookup service @ {0}", serviceUrl);
                
                //============ Get proxy MAIL_TO_ADDR HelloWorld service ============
                gameFactoryRI = (GameFactoryRI) registry.lookup(serviceUrl);
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "registry not bound (check IPs). :(");
                //registry = LocateRegistry.createRegistry(1099);
            }
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return gameFactoryRI;
    }
    
    private void playService() {
        try {
            //============ Call HelloWorld remote service ============
            Scanner username = new Scanner(System.in);  // Create a Scanner object
            Scanner password = new Scanner(System.in);  // Create a Scanner object
            Scanner username2 = new Scanner(System.in);  // Create a Scanner object
            Scanner password2 = new Scanner(System.in);  // Create a Scanner object
            Scanner optioN = new Scanner(System.in); //create a Scanner object
            Scanner optioN2 = new Scanner(System.in); //create a Scanner object
            Scanner optioN3 = new Scanner(System.in); //create a Scanner object
            String userName;
            String passWord;
            GameSessionRI gameSessionRI;
            int option = 1;
            while (option!=4){
                printMenu(options_login);
                option = optioN.nextInt();
                switch (option) {
                    case 1 : {
                        System.out.println("Introduza o nome:");
                        userName = username.nextLine();  // Read user input
                        System.out.println("Introduza a password:");
                        passWord = password.nextLine();  // Read user input
                        if( this.gameFactoryRI.register(userName,passWord)) {
                            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "User registado com sucesso!");
                        } else {
                            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "User nao registado! Ja existe um User com esse nome");
                        }
                        break;

                    }
                    case 2 : {
                        do{
                        System.out.println("Introduza o nome:");
                        userName = username2.nextLine();  // Read user input
                        System.out.println("Introduza a password:");
                        passWord = password2.nextLine();  // Read user input
                         gameSessionRI=this.gameFactoryRI.login(userName,passWord);}
                        while(gameSessionRI==null);
                        String token=GameServer.giveToken(userName);
                        gameSessionRI.setToken(token);

                        option = 1;
                        while (option!=4){
                            printMenu(options);
                            option = optioN2.nextInt();
                            switch (option) {
                                case 1 : {

                                    criarjogo(gameSessionRI);
                                     option =2;
                                     while(option!=4){
                                         printMenu(options_game);
                                         option=optioN3.nextInt();
                                         switch (option){
                                             case 1:exit(0);
                                         }
                                          break;
                                     }
                                }
                                case 2 : {
                                    escolherjogo(gameSessionRI);
                                    option =2;
                                    while(option!=4){
                                        printMenu(options_game);
                                        option=optioN3.nextInt();
                                        switch (option){
                                            case 1:exit(0);
                                        }
                                        break;
                                    }

                                }
                                case 3 : exit(0);
                            }
                        }
                    }
                    case 3 : exit(0);
                }

            }


                   } catch (RemoteException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Metodo para criar um jogo
     * @param gameSessionRI

     * @return
     * @throws RemoteException
     */
    private static Game criarjogo(GameSessionRI gameSessionRI) throws RemoteException {
        Game game;
        Scanner mapa = new Scanner(System.in);
        System.out.println("Introduza o mapa :1-FourCorners 2-SmallVs");
        int mapas = mapa.nextInt();
        //cria observer para o jogador
        ObserverRI observerRI = new ObserverImpl(gameSessionRI.getUsername());
        if(mapas==1){
             game= gameSessionRI.criar_jogo("C:\\Users\\ACER-PC\\IdeaProjects\\SD\\maps\\FourCorners.txt",4, gameSessionRI.getToken(), observerRI);
        }
        else{
             game= gameSessionRI.criar_jogo("C:\\Users\\ACER-PC\\IdeaProjects\\SD\\maps\\SmallVs.txt",2, gameSessionRI.getToken(), observerRI);
        }


        observerRI.setSubjectRI(game.getSubjectRI());

        System.out.println("Jogo criado com sucesso!");

        return game;
    }
    private static void escolherjogo(GameSessionRI gameSessionRI) throws RemoteException{
        System.out.println("Lista dos jogos disponiveis:");

        assert gameSessionRI != null;
        ArrayList<Game> games =  gameSessionRI.listAvbGames();
         for (Game game:games){
             System.out.println( game.getId()+" "+game.getNivel()+" faltam "+(game.getMax_players()-game.getNum_players())+"jogadores");
         }
        Scanner escolherJogo = new Scanner(System.in);
        System.out.println("Escolha um jogo para se juntar:");
        int jogo = escolherJogo.nextInt();

        ObserverRI observerRI = new ObserverImpl(gameSessionRI.getUsername());

        //chama o escolher jogo
        Game game = gameSessionRI.escolher_jogo(jogo, gameSessionRI.getToken(), observerRI);
        observerRI.setSubjectRI(game.getSubjectRI());
        game.getSubjectRI().attach(observerRI);
       game.getSubjectRI().setMapa(game.getNivel());

    }

    private static void comecar_jogo() throws RemoteException{}
}
