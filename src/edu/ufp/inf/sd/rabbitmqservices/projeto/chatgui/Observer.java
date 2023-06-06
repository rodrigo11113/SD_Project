package edu.ufp.inf.sd.rabbitmqservices.projeto.chatgui;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.*;
import edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.engine.Game;
import edu.ufp.inf.sd.rabbitmqservices.util.RabbitUtils;
import edu.ufp.inf.sd.rmi.project.client.jogo.menus.MenuHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create a chat room (like zoom chat private msg), supporting:
 * - open *general* messages to all users
 * - private messages to a specific *user*
 *
 * <p>
 * Each _05_observer will receive messages from its queue with 2 Binding keys:
 * - room1.general (public msg for general/all users) and room1.pedro (private msg for given user)
 *
 * <p>
 * Send message with specific Routing keys:
 * - routingKey="room1.general" (public to general/all users)
 * - routingKey="room1.pedro"   (private to specific user)
 *
 * <p>
 * Run _05_observer with 3 parameters <room> <user> <general>:
 * $ runobserver room1 pedro general
 *
 *
 * @author rui
 */
public class Observer {
    //Reference for edu.ufp.inf.sd.rabbitmqservices.project.game.gui
    private final ObserverGuiClient gui;

    //Preferences for exchange...
    private final Channel channelToRabbitMq;
    private final String exchangeName;
    private final BuiltinExchangeType exchangeType;
    private final String[] exchangeBindingKeys;
    private final String messageFormat;
    private final String room;
    private final String user;
    public ArrayList<Integer> observers = new ArrayList<>();
    public int tokenholder=1;

    //Store received message to be get by edu.ufp.inf.sd.rabbitmqservices.project.game.gui
    private String receivedMessage;

    /**
     * @param gui
     */
    public Observer(ObserverGuiClient gui, String host, int port, String brokerUser, String brokerPass, String room, String user, String exchangeName, BuiltinExchangeType exchangeType, String messageFormat) throws IOException, TimeoutException {
        this.gui=gui;
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, " going to attach project to host: " + host + "...");

        Connection connection=RabbitUtils.newConnection2Server(host, port, brokerUser, brokerPass);
        this.channelToRabbitMq=RabbitUtils.createChannel2Server(connection);
        this.exchangeName=exchangeName;
        this.exchangeType=exchangeType;

        this.room=room;
        this.user=user;

        // TODO: Set 2 binding keys (to receive msg from public room.general and private room.user
        String bingingKeys[]={room};

        this.exchangeBindingKeys=bingingKeys;
        this.messageFormat=messageFormat;
        bindExchangeToChannelRabbitMQ();
        attachConsumerToChannelExchangeWithKey();
    }

    /**
     * Declare exchange of specified type.
     */
    private void bindExchangeToChannelRabbitMQ() throws IOException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Declaring Exchange '" + this.exchangeName + "' with policy = " + this.exchangeType);

        // TODO: Declare exchange type
        this.channelToRabbitMq.exchangeDeclare(this.exchangeName, this.exchangeType);
    }

    /**
     * Creates a Consumer associated with an unnamed queue.
     */
    private void attachConsumerToChannelExchangeWithKey() {
        try {
            // TODO: Create a non-durable, exclusive, autodelete queue with a generated name.
            String queuName = this.channelToRabbitMq.queueDeclare().getQueue();

            // TODO: Bind to each routing key (received from args[3] upward)
            this.channelToRabbitMq.queueBind(queuName, this.exchangeName, this.exchangeBindingKeys[0]);

            /* Use a DeliverCallback lambda function instead of DefaultConsumer to receive messages from queue;
               DeliverCallback is an interface which provides a single method:
                void handle(String tag, Delivery delivery) throws IOException; */
            DeliverCallback deliverCallback=(consumerTag, delivery) -> {
                String message=new String(delivery.getBody(), "UTF-8");
                setReceivedMessage(message);
                System.out.println(" [x] Received '" + message + "'");
                //gui.updateTextArea();//update game
            };
            CancelCallback cancelCalback=consumerTag -> {
                System.out.println(" [x] CancelCallback invoked");
            };

            // TODO: Consume with deliver and cancel callbacks
            this.channelToRabbitMq.basicConsume(queuName, true, deliverCallback, cancelCalback);

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.toString());
        }
    }

    /**
     * Publish messages to existing exchange instead of the nameless one.
     * - Messages will be lost if no queue is bound to the exchange yet.
     * - User may be some 'username' or 'general' (for all)
     */
    public void sendMessage(String msgToSend) throws IOException {
        BasicProperties prop=MessageProperties.PERSISTENT_TEXT_PLAIN;
        //User maybe some <username> (private msg) or 'general' (public msg for all)

        // TODO: Publish message with routing key
        this.channelToRabbitMq.basicPublish(exchangeName, this.room, prop, msgToSend.getBytes("UTF-8"));
    }

    public ObserverGuiClient getGui() {
        return gui;
    }

    /**
     * @return the receivedMessage
     */
    public String getReceivedMessage() {
        return receivedMessage;
    }

    /**
     * @param receivedMessage the receivedMessage to set
     */
    public void setReceivedMessage(String receivedMessage) throws IOException {
        //String[] string = receivedMessage.split(":");
        if (receivedMessage.equals("ola")) {
            this.observers.add(1);
            this.gui.check();
        }
        if(receivedMessage.equals("Start")) {
            //this.tokenid=this.observers.size()-1;
            this.gui.startGame(this);}
        if(receivedMessage.equals("up play")){
            edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.players.Base ply = Game.player.get(Game.btl.currentplayer);
            ply.selecty--;if (ply.selecty<0) {ply.selecty++;
        }
    }
        if(receivedMessage.equals("down play")){
            edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.players.Base ply = Game.player.get(Game.btl.currentplayer);
            ply.selecty++;if (ply.selecty>=Game.map.height) {ply.selecty--;}
        }
        if(receivedMessage.equals("left play")){
            edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.players.Base ply = Game.player.get(Game.btl.currentplayer);
            ply.selectx--;if (ply.selectx<0) {ply.selectx++;}
        }
        if(receivedMessage.equals("right play")){
            edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.players.Base ply = Game.player.get(Game.btl.currentplayer);
            ply.selectx++;if (ply.selectx>=Game.map.width) {ply.selectx--;}
        }
        if(receivedMessage.equals("select play")){
            Game.btl.Action();
        }
        if(receivedMessage.equals("cancel play")){
            Game.player.get(Game.btl.currentplayer).Cancle();
        }
        if(receivedMessage.equals("start play")){
            new edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.menus.Pause();
        }
        if(receivedMessage.equals("up edit")){
            Game.edit.selecty--;if (Game.edit.selecty<0) {Game.edit.selecty++;} Game.edit.moved = true;
        }
        if(receivedMessage.equals("down edit")){
            Game.edit.selecty++;if (Game.edit.selecty>=Game.map.height) {Game.edit.selecty--;} Game.edit.moved = true;
        }
        if(receivedMessage.equals("left edit")){
            Game.edit.selectx--;if (Game.edit.selectx<0) {Game.edit.selectx++;} Game.edit.moved = true;
        }
        if(receivedMessage.equals("right edit")){
            Game.edit.selectx++;if (Game.edit.selectx>=Game.map.width) {Game.edit.selectx--;} Game.edit.moved = true;
        }
        if(receivedMessage.equals("select edit")){
            Game.edit.holding = true;
        }
        if(receivedMessage.equals("cancel edit")){
            Game.edit.ButtButton();
        }
        if(receivedMessage.equals("start edit")){
            new edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.menus.EditorMenu();
        }
        if(receivedMessage.equals("dev1")){
            Game.gui.LoginScreen();
        }
        if(receivedMessage.equals("dev2")){
            Game.load.LoadTexturePack("Test");
        }
        if(receivedMessage.equals("dev3 1")){
            Game.pathing.ShowCost=false;
        }
        if(receivedMessage.equals("dev3 2")){
            Game.pathing.ShowHits=true;
        }
        if(receivedMessage.equals("dev3 3")){
            Game.pathing.ShowHits=false;Game.pathing.ShowCost=true;
        }
        if(receivedMessage.equals("dev4")){
            Game.btl.EndTurn();
           tokenholder= gui.changetoken(tokenholder);
        }
        if(receivedMessage.equals("dev5")){
            Game.player.get(Game.btl.currentplayer).npc = !Game.player.get(Game.btl.currentplayer).npc; Game.btl.EndTurn();
        }
        if(receivedMessage.equals("dev6")){
            new edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.menus.StartMenu();
        }
        if(receivedMessage.equals("key release")){
            Game.edit.holding = false;
        }
        if(receivedMessage.contains("buy")){
            String[] arrOfStr = receivedMessage.split(",");
            edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.engine.Game.btl.Buyunit(Integer.parseInt(arrOfStr[1]), Integer.parseInt(arrOfStr[2]),Integer.parseInt(arrOfStr[3]));
            //MenuHandler.CloseMenu();
        }
        if(receivedMessage.equals("end turn")){
            //MenuHandler.CloseMenu();
            Game.btl.EndTurn();
            tokenholder=gui.changetoken(tokenholder);
        }
        this.receivedMessage = receivedMessage;}
}
